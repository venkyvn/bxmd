package com.digi.bxmd.dto

import com.digi.bxmd.constant.MessageKey
import com.digi.bxmd.constant.ValidatorConstant
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class SetPasswordDto(
    private var token: String? = null,

    @Pattern(regexp = ValidatorConstant.PASSWORD_REGEX_EXPRESSION, message = MessageKey.PASSWORD_NOT_MATCH_PATTERN)
    @NotBlank(message = MessageKey.VALIDATION_NOT_EMPTY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(
        min = ValidatorConstant.PASSWORD_MIN_LENGTH,
        max = ValidatorConstant.PASSWORD_MAX_LENGTH,
        message = MessageKey.VALIDATION_MAX_LENGTH
    )
    private var newPassword: String? = null,
)