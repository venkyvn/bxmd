package com.digi.bxmd.entity

import com.digi.bxmd.base.entity.BaseEntity
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import javax.persistence.*

@Entity
@Table(name = "provinces")
class Province(

    @Column(name = "label")
    var label: String? = null,

    @Column(name = "value")
    var value: String? = null,

    @Column(name = "licenseplates")
    var licenseplates: String? = null,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id", referencedColumnName = "id")
    @Fetch(FetchMode.JOIN)
    var transportationRoutes: Set<TransportationRoute> = mutableSetOf(),
) : BaseEntity()