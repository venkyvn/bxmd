package com.digi.bxmd.entity.enumerate

interface JsonEnum<T> {
    fun toJSON(): String
    fun fromJSON(value: String): UserStatusEnum
}