package com.digi.bxmd.entity

import com.digi.bxmd.base.entity.BaseEntity
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "user_token")
class UserToken(
    @Column(name = "user_id")
    var userId: Long,

    @Column(name = "refresh_token", length = 500)
    var refreshToken: String,

    @Column(name = "expired_time")
    var expiredTime: Date,
) : BaseEntity()
