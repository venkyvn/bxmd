package com.digi.bxmd.dto

import com.digi.bxmd.constant.MessageKey
import com.digi.bxmd.constant.ValidatorConstant
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class ChangePasswordDto(
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = MessageKey.VALIDATION_NOT_EMPTY) @Size(
        min = ValidatorConstant.PASSWORD_MIN_LENGTH,
        max = ValidatorConstant.PASSWORD_MAX_LENGTH,
        message = MessageKey.VALIDATION_MAX_LENGTH
    )
    var curPassword: String? = null,

    @Pattern(
        regexp = ValidatorConstant.PASSWORD_REGEX_EXPRESSION,
        message = MessageKey.PASSWORD_NOT_MATCH_PATTERN
    ) @NotBlank(message = MessageKey.VALIDATION_NOT_EMPTY) @Size(
        min = ValidatorConstant.PASSWORD_MIN_LENGTH,
        max = ValidatorConstant.PASSWORD_MAX_LENGTH,
        message = MessageKey.VALIDATION_MAX_LENGTH
    )
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(
        min = ValidatorConstant.PASSWORD_MIN_LENGTH,
        max = ValidatorConstant.PASSWORD_MAX_LENGTH,
        message = MessageKey.VALIDATION_MAX_LENGTH
    )
    val newPassword: String? = null,
)