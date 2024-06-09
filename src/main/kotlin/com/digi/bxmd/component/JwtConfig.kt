package com.digi.bxmd.component

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class JwtConfig {
    @Value("\${security.jwt.auth.header:Authorization}")
    lateinit var authHeader: String

    @Value("\${security.jwt.auth.prefix:Bearer}")
    lateinit var authPrefix: String

    @Value("\${security.jwt.access-token-expiration-milliseconds:null}")
    var accessTokenExpirationInMilliseconds: Long? = 0

    @Value("\${security.jwt.refresh-token-expiration-milliseconds:null}")
    var refreshTokenExpirationInMilliseconds: Long? = 0

    @Value("\${security.jwt.secret:JwtSecretKey\$MaYzkSjmkzPC57L}")
    lateinit var secret: String

}
