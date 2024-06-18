package com.digi.bxmd.dto

data class PriceDto(
    var priceName: String? = null,
    var priceNumber: String? = null,
    var priceCode: String? = null,
    var minKG: Double? = null,
    var maxKG: Double? = null,
    var priceAdd: Boolean? = false,
) : BaseDto()
