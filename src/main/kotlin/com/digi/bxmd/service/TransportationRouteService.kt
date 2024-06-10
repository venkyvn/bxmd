package com.digi.bxmd.service

import com.digi.bxmd.base.repository.BaseSearchCriteria
import com.digi.bxmd.base.service.BaseService
import com.digi.bxmd.dto.TransportationRouteDto
import com.digi.bxmd.entity.TransportationRoute
import com.digi.bxmd.repository.TransportationRouteRepository

interface TransportationRouteService : BaseService<TransportationRouteDto, TransportationRoute, BaseSearchCriteria<String>, TransportationRouteRepository, Long> {
}