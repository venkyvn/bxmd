package com.digi.bxmd.dto

import com.digi.bxmd.constant.MessageKey
import com.digi.bxmd.constant.ValidatorConstant
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
open class BaseAccount(
    @field:Size(
        max = ValidatorConstant.NAME_MAX_LENGTH,
        message = MessageKey.VALIDATION_MAX_LENGTH,
    )
    @field:NotBlank(message = MessageKey.VALIDATION_NOT_EMPTY)
    var name: String? = null,

    @field:Size(
        min = ValidatorConstant.USERNAME_MIN_LENGTH,
        max = ValidatorConstant.USERNAME_MAX_LENGTH,
        message = MessageKey.VALIDATION_MAX_LENGTH,
    )
    var username: String? = null,

    @field:Pattern(
        regexp = ValidatorConstant.PASSWORD_REGEX_EXPRESSION,
        message = MessageKey.PASSWORD_NOT_MATCH_PATTERN,
    )
    @field:JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @field:Size(
        min = ValidatorConstant.PASSWORD_MIN_LENGTH,
        max = ValidatorConstant.PASSWORD_MAX_LENGTH,
        message = MessageKey.VALIDATION_MAX_LENGTH,
    )
    var password: String? = null,

    @field:Pattern(
        regexp = ValidatorConstant.EMAIL_REGEX_EXPRESSION,
        message = MessageKey.VALIDATION_INVALID_EMAIL,
    )
    @field:NotBlank(message = MessageKey.VALIDATION_NOT_EMPTY)
    var email: String? = null
) : BaseDto() // Assuming BaseDto is your superclass
