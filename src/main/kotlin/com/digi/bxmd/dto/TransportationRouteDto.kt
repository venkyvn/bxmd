package com.digi.bxmd.dto

data class TransportationRouteDto(
    var label: String? = null,
    var transportationRouteCode: String? = null,
    var distanceName: String? = null,
    var value: String? = null,
) : BaseDto()
