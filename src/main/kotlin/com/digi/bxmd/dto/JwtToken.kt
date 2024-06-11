package com.digi.bxmd.dto

import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import javax.validation.constraints.NotEmpty

@JsonInclude(JsonInclude.Include.NON_NULL)
data class JwtToken(
    var token: String? = null,

    @field:NotEmpty(message = "Refresh token is empty!")
    var refreshToken: String,

    var expired: Long? = null,
) : Serializable