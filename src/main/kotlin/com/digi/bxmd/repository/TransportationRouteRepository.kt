package com.digi.bxmd.repository

import com.digi.bxmd.base.repository.BaseRepository
import com.digi.bxmd.entity.TransportationRoute
import org.springframework.stereotype.Repository

@Repository
interface TransportationRouteRepository : BaseRepository<TransportationRoute, Long> {
}