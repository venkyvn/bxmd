package com.digi.bxmd.dto

data class RemainingPriceDto(
    var distanceName: String? = null,
    var distanceCode: String? = null,
    var prices: Set<PriceDto>? = mutableSetOf(),
    var time: String? = null
): BaseDto()
