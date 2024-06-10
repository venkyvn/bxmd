package com.digi.bxmd.entity

import com.digi.bxmd.base.entity.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "transportation_routes")
data class TransportationRoute(
    @Column(name = "label", nullable = false)
    val label: String? = null,

    @Column(name = "transportation_route_code", nullable = false)
    val transportationRouteCode: String? = null,

    @Column(name = "distance_name", nullable = false)
    val distanceName: String? = null,

    @Column(name = "value", nullable = false)
    val value: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id")
    val province: Province? = null,
) : BaseEntity()
