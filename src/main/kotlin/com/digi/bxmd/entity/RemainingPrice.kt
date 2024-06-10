package com.digi.bxmd.entity

import com.digi.bxmd.base.entity.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "remaining_prices")
class RemainingPrice(
    @Column(name = "distance_name", nullable = false)
    var distanceName: String? = null,

    @Column(name = "distance_code", nullable = false)
    var distanceCode: String? = null,

    @OneToMany(mappedBy = "remainingPrice", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var prices: List<Price>? = mutableListOf(),

    @Column(name = "time")
    var time: String? = null,
) : BaseEntity()