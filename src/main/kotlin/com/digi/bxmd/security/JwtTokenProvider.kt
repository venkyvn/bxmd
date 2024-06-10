package com.digi.bxmd.security

import com.digi.bxmd.component.JwtConfig
import com.digi.bxmd.constant.MessageKey
import com.digi.bxmd.repository.AccessTokenRepository
import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import java.security.Key
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*
import javax.annotation.PostConstruct
import javax.servlet.http.HttpServletRequest

@Component
@Slf4j
class JwtTokenProvider(
    private val jwtConfig: JwtConfig,
    @Autowired private val accessTokenRepository: AccessTokenRepository,
) {
    private val log = LoggerFactory.getLogger(JwtTokenProvider::class.java)
    private lateinit var secretKey: String


    private val AUTHORITIES_KEY = "auth"

    private val SCOPES = "scopes"
    private val REFRESH_TOKEN = "REFRESH_TOKEN"
    private lateinit var key: Key

    private lateinit var accessTokenExpireTime: Instant
    private lateinit var refreshTokenValidityInMilliseconds: Instant

    private val tokenCache = mutableMapOf<Long, String>()

    @PostConstruct
    private fun init() {
        secretKey = Base64.getEncoder().encodeToString(jwtConfig.secret.toByteArray())
        val keyBytes: ByteArray = Decoders.BASE64.decode(secretKey)

        key = Keys.hmacShaKeyFor(keyBytes)
        val now = Instant.now()
        accessTokenExpireTime = now.plus(jwtConfig.accessTokenExpirationInMilliseconds ?: 0, ChronoUnit.MILLIS)
        refreshTokenValidityInMilliseconds =
            now.plus(jwtConfig.refreshTokenExpirationInMilliseconds ?: 0, ChronoUnit.MILLIS)
    }

    /**
     * Create JWT token based on the given authenticated information.
     */
    fun createToken(authentication: Authentication): String {
        val authorities = authentication.authorities.joinToString(",") { it.authority }

        return Jwts.builder()
            .setSubject(authentication.name)
            .claim(AUTHORITIES_KEY, authorities)
            .signWith(key, SignatureAlgorithm.HS512)
            .setExpiration(Date.from(accessTokenExpireTime))
            .compact()
    }

    /**
     * Create refresh token based on authenticated information.
     */
    fun createRefreshToken(authentication: Authentication): String {
        if (authentication.name.isEmpty()) {
            throw IllegalArgumentException(MessageKey.BAD_CREDENTIAL)
        }

        val authorities = authentication.authorities.joinToString(",") { it.authority }

        val claims = Jwts.claims().setSubject(authentication.name)
        claims.put(SCOPES, listOf(REFRESH_TOKEN))
        claims.put(AUTHORITIES_KEY, authorities)

        return Jwts.builder()
            .setClaims(claims)
            .setId(UUID.randomUUID().toString())
            .setExpiration(Date.from(refreshTokenValidityInMilliseconds))
            .signWith(key, SignatureAlgorithm.HS512)
            .compact()
    }


    fun resolveToken(request: HttpServletRequest): String? {
        val header = request.getHeader(org.springframework.http.HttpHeaders.AUTHORIZATION)

        return if (StringUtils.hasText(header) && header.contains(jwtConfig.authPrefix)) {
            header.substring(7)
        } else {
            null
        }
    }

    fun validateToken(token: String): Boolean {
        return try {
            if (!token.isNullOrEmpty()) {
                val claims: Jws<Claims> = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                val dateExpired: Date? = claims.body.expiration
                if (Objects.nonNull(dateExpired) && dateExpired!!.before(Date())) {
                    log.debug("validateToken >> validateToken: token is expired.")
                    return false
                }
                true
            } else {
                false
            }
        } catch (e: JwtException) {
            log.debug("validateToken >> Authorization Token is Expired or Invalid JWT")
            false
        } catch (e: IllegalArgumentException) {
            log.debug("validateToken >> Authorization Token is Expired or Invalid JWT")
            false
        }
    }

    fun validateTokenDB(token: String?): Boolean {
        return try {
            if (token.isNullOrEmpty()) false
            else this.validateToken(token) && accessTokenRepository.existsByToken(token)
        } catch (e: JwtException) {
            log.debug("Authorization Token is Expired or Invalid JWT")
            false
        } catch (e: IllegalArgumentException) {
            log.debug("Authorization Token is Expired or Invalid JWT")
            false
        }
    }

    fun getAuthentication(token: String): Authentication {
        val claims = Jwts.parser().setSigningKey(key)
            .parseClaimsJws(token)
            .body
        val authorities = claims[AUTHORITIES_KEY].toString().split(",").map { SimpleGrantedAuthority(it) }.toList()

        val user = User(claims.subject,"", authorities)
        return UsernamePasswordAuthenticationToken(user, token, authorities)
    }

    fun addToken(userId: Long, accessToken: String) {
        tokenCache[userId] = accessToken
    }


    fun getConfigExpired(): Date {
        return Date(Date().time + jwtConfig.accessTokenExpirationInMilliseconds!!)
    }

    fun getRefreshTokenExpired(): Instant {
        return this.refreshTokenValidityInMilliseconds
    }

    fun revokeToken(userId: Long) = tokenCache.remove(userId)

}