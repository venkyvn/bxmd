package com.digi.bxmd.entity

import com.digi.bxmd.base.entity.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "provinces")
class Province(

    @Column(name = "label", nullable = false)
    val label: String? = null,

    @Column(name = "value", nullable = false)
    val value: String? = null,

    @Column(name = "licenseplates", nullable = false)
    val licenseplates: String? = null,

    @OneToMany(mappedBy = "province", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val transportationRoutes: List<TransportationRoute>? = mutableListOf(),
) : BaseEntity()