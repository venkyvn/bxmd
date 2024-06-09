package com.digi.bxmd.dto

import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import javax.validation.constraints.NotEmpty

@JsonInclude(JsonInclude.Include.NON_NULL)
data class JwtToken(
    val token: String? = null,

    @field:NotEmpty(message = "Refresh token is empty!")
    val refreshToken: String,

    val expired: Long? = null,
) : Serializable