package com.digi.bxmd.entity

import com.digi.bxmd.base.entity.BaseEntity
import com.digi.bxmd.entity.enumerate.UserStatusEnum
import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.BatchSize
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull

@Entity
@Table(name = "users")
class User(
    @Email
    @NotNull
    @Column(name = "email", unique = true)
    val email: String? = null,

    @NotNull
    @Column(name = "username", unique = true)
    val username: String? = null,

    @JsonIgnore
    @Column(name = "password_hash", nullable = false)
    @NotNull
    var password: String? = null,

    @Column(name = "name")
    var name: String? = null,

    @Column
    @Enumerated(EnumType.STRING)
    var status: UserStatusEnum? = null,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    var roles: Set<Role> = mutableSetOf()

    ) : BaseEntity()