package com.digi.bxmd.entity

import com.digi.bxmd.base.entity.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "prices")
class Price(
    @Column(name = "price_name", nullable = false)
    var priceName: String? = null,

    @Column(name = "price_number", nullable = false)
    var priceNumber: String? = null,

    @Column(name = "min_kg", nullable = false)
    var minKG: Double? = null,

    @Column(name = "max_kg")
    var maxKG: Double? = null,

    @Column(name = "price_add")
    var priceAdd: Boolean? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "remaining_price_id")
    var remainingPrice: RemainingPrice? = null,
) : BaseEntity()