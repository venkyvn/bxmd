package com.digi.bxmd.dto

data class RemainingPriceDto(
    var distanceName: String? = null,
    var distanceCode: String? = null,
    var prices: List<PriceDto>? = mutableListOf(),
    var time: String? = null
): BaseDto()
