package com.digi.bxmd.entity

import com.digi.bxmd.base.entity.BaseEntity
import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "packages")
class Package(
    @Column(name = "label")
    var label: String? = null,

    @Column(name = "packaging_name")
    var packagingName: String? = null,

    @Column(name = "price", precision = 19, scale = 4)
    var price: BigDecimal? = null,

    @Column(name = "uom")
    var uom: String? = null,

    @Column(name = "value")
    var value: String? = null,

    ) : BaseEntity()
