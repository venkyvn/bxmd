package com.digi.bxmd.dto

data class ProvinceDto(
    var label: String? = null,
    var value: String? = null,
    var licenseplates: String? = null,
    var transportationRoutes: List<TransportationRouteDto>? = mutableListOf(),
): BaseDto()
