package com.digi.bxmd.exception

import java.io.Serializable

data class FieldValidation(
    var field: String? = null,
    var message: String? = null,
    var messageKey: String? = null
) : Serializable
