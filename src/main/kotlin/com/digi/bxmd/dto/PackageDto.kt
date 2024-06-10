package com.digi.bxmd.dto

data class PackageDto(
    var label: String? = null,
    var packagingName: String? = null,
    var price: String? = null,
    var uom: String? = null,
    var value: String? = null,
) : BaseDto()