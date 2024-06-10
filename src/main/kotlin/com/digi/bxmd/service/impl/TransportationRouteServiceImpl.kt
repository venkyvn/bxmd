package com.digi.bxmd.service.impl

import com.digi.bxmd.base.repository.BaseSearchCriteria
import com.digi.bxmd.base.service.impl.BaseServiceImpl
import com.digi.bxmd.dto.TransportationRouteDto
import com.digi.bxmd.entity.TransportationRoute
import com.digi.bxmd.repository.TransportationRouteRepository
import com.digi.bxmd.service.TransportationRouteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TransportationRouteServiceImpl @Autowired constructor(packageRepository: TransportationRouteRepository) :
    BaseServiceImpl<TransportationRouteDto, TransportationRoute, BaseSearchCriteria<String>, TransportationRouteRepository, Long>(packageRepository),
    TransportationRouteService {

}