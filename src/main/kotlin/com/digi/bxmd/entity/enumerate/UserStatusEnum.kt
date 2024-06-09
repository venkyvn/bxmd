package com.digi.bxmd.entity.enumerate

import com.digi.bxmd.constant.MessageKey
import com.digi.bxmd.exception.BusinessException
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.deser.std.EnumDeserializer
import com.fasterxml.jackson.databind.ser.std.EnumSerializer

@JsonSerialize(using = EnumSerializer::class)
@JsonDeserialize(using = EnumDeserializer::class)
enum class UserStatusEnum : JsonEnum<UserStatusEnum> {
    ACTIVE, INACTIVE, DELETED;

    override fun toJSON(): String {
        return this.toString()
    }


    override fun fromJSON(value: String): UserStatusEnum {
        for (e in values()) {
            if (e.toString().equals(value, ignoreCase = true)) {
                return e
            }
        }
        throw BusinessException(MessageKey.CAST_ENUM_ERR, value)
    }
}
