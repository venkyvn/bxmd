package com.digi.bxmd.entity

import com.digi.bxmd.base.entity.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "remaining_prices")
class RemainingPrice(
    @Column(name = "distance_name")
    var distanceName: String? = null,

    @Column(name = "distance_code")
    var distanceCode: String? = null,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "remaining_price_id")
    var prices: Set<Price> = mutableSetOf(),

    @Column(name = "time")
    var time: String? = null,
) : BaseEntity()