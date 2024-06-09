package com.digi.bxmd.dto

data class UserExistDto(
    var username: String? = null,
    var email: String? = null,
    var status: String? = null,
) : BaseDto()