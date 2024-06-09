package com.digi.bxmd.entity

import com.digi.bxmd.base.entity.BaseEntity
import org.hibernate.annotations.DynamicUpdate
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "access_token")
@DynamicUpdate
class AccessToken(
    @Column(name = "account_id")
    val accountId: Long? = null,

//    @javax.persistence.Column(name = "client_type")
//    @Enumerated(EnumType.STRING)
//    private val clientType: ClientType? = null,

    @Column(length = 600)
    var token: String? = null,

    @Column(length = 600)
    var refreshToken: String? = null,

    @Column(name = "user_agent")
    var userAgent: String? = null,

    @Temporal(TemporalType.TIMESTAMP)
    var expired: Date? = null,
) : BaseEntity()