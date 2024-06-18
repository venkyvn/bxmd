package com.digi.bxmd.entity

import com.digi.bxmd.base.entity.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "transportation_routes")
data class TransportationRoute(
    @Column(name = "label")
    var label: String? = null,

    @Column(name = "transportation_route_code")
    var transportationRouteCode: String? = null,

    @Column(name = "distance_name")
    var distanceName: String? = null,

    @Column(name = "value")
    var value: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id")
    var province: Province? = null,
) : BaseEntity()
