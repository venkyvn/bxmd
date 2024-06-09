package com.digi.bxmd.service.impl

import com.digi.bxmd.constant.MessageKey
import com.digi.bxmd.dto.JwtToken
import com.digi.bxmd.dto.SignInDto
import com.digi.bxmd.entity.UserToken
import com.digi.bxmd.entity.enumerate.UserStatusEnum
import com.digi.bxmd.exception.BusinessException
import com.digi.bxmd.repository.UserRepo
import com.digi.bxmd.repository.UserTokenRepo
import com.digi.bxmd.security.JwtTokenProvider
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
@Slf4j
class AuthServiceImpl @Autowired constructor(
    private val tokenProvider: JwtTokenProvider,
    private val authenticationManager: AuthenticationManager,
    private val userTokenRepo: UserTokenRepo,
    private val userRepo: UserRepo,
) {

    fun signIn(signInDto: SignInDto): JwtToken {
        return getJwtToken(signInDto.username, signInDto.password)
    }

    fun getJwtToken(principal: String, loginPassword: String): JwtToken {
        val authenticationToken = UsernamePasswordAuthenticationToken(principal, loginPassword)
        val authentication: Authentication = authenticationManager.authenticate(authenticationToken)
        SecurityContextHolder.getContext().authentication = authentication

        val user = userRepo.findFirstByUsernameIgnoreCaseOrEmailIgnoreCase(principal, principal)
            .orElseThrow {
                BusinessException(MessageKey.NOT_FOUND)
            }

        if (user.status == UserStatusEnum.DELETED) {
            throw AccessDeniedException(MessageKey.BAD_CREDENTIAL)
        }

        val accessToken: String = tokenProvider.createToken(authentication)
        user.id?.let { tokenProvider.addToken(it, accessToken) }
        val refreshToken: String = tokenProvider.createRefreshToken(authentication)

        val userTokenToSave = UserToken(user.id!!, refreshToken, Date.from(tokenProvider.getRefreshTokenExpired()))
        userTokenRepo.save(userTokenToSave)
        return JwtToken(accessToken, refreshToken)
    }

    fun signOut(jwtToken: JwtToken) {
        val isTokenExist: Optional<UserToken> = userTokenRepo.findByRefreshToken(jwtToken.refreshToken)
        if (isTokenExist.isPresent) {
            userTokenRepo.deleteAllByRefreshToken(jwtToken.refreshToken)
            tokenProvider.revokeToken(isTokenExist.get().id!!)
        } else {
            throw BusinessException(MessageKey.INVALID_REFRESH_TOKEN)
        }
    }

    /**
     * Check and send new access token with refresh token provided
     *
     * @param jwtToken the given refresh token
     * @return Jwt token
     * @throws HttpClientErrorException if the exception was caught
     */
//    fun refresh(jwtToken: JwtToken): JwtToken {
//        val userToken: Optional<UserToken> = userTokenRepo.findByRefreshToken(jwtToken.refreshToken)
//        if (tokenProvider.validateToken(jwtToken.refreshToken, true) && userToken.isPresent) {
//            val authentication: Authentication = tokenProvider.getAuthentication(jwtToken.refreshToken)
//            val accessToken: String = tokenProvider.createToken(authentication)
//            tokenProvider.addToken(userToken.get().id, accessToken)
//            return JwtToken(accessToken, null)
//        }
//        SecurityContextHolder.clearContext()
//        userTokenRepo.deleteAllByRefreshToken(jwtToken.refreshToken)
//
//        throw UnauthorizedException(MessageKey.UNAUTHORIZED)
//    }

    fun refreshToken(jwtTokenReq: JwtToken): JwtToken {
        val userToken = userTokenRepo.findByRefreshToken(jwtTokenReq.refreshToken)
        if (tokenProvider.validateToken(jwtTokenReq.refreshToken)) {
            val authentication = tokenProvider.getAuthentication(jwtTokenReq.refreshToken)

            SecurityContextHolder.getContext().authentication = authentication

            val newAccessToken = tokenProvider.createToken(authentication)
            val newRefreshToken = tokenProvider.createRefreshToken(authentication)
            tokenProvider.addToken(userToken.get().id!!, newAccessToken)

            return JwtToken(newAccessToken, newRefreshToken)
        } else {
            throw IllegalArgumentException("Invalid refresh token")
        }
    }



    fun revokeToken(id: Long) {
        userTokenRepo.deleteById(id)
        tokenProvider.revokeToken(id)
    }
}
