package com.digi.bxmd.entity

import com.digi.bxmd.base.entity.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "prices")
class Price(
    @Column(name = "price_name")
    var priceName: String? = null,

    @Column(name = "price_number")
    var priceNumber: String? = null,

    @Column(name = "price_code")
    var priceCode: String? = null,


    @Column(name = "min_kg")
    var minKG: Double? = null,

    @Column(name = "max_kg")
    var maxKG: Double? = null,

    @Column(name = "price_add")
    var priceAdd: Boolean? = null,
) : BaseEntity()