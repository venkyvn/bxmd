package com.digi.bxmd.dto

import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import java.util.*

abstract class BaseDto : Serializable {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val id: Long? = null

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    var updatedDate: Date? = null

}
