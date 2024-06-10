package com.digi.bxmd.controller

import com.digi.bxmd.base.controller.BaseController
import com.digi.bxmd.base.repository.BaseSearchCriteria
import com.digi.bxmd.dto.TransportationRouteDto
import com.digi.bxmd.entity.TransportationRoute
import com.digi.bxmd.repository.TransportationRouteRepository
import com.digi.bxmd.service.TransportationRouteService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("transportation-route")
@Api(tags = ["TransportationRoute"], description = "TransportationRoute")
class TransportationRouteController @Autowired constructor(
    remainingPriceService: TransportationRouteService,
) : BaseController<TransportationRouteDto, TransportationRoute, BaseSearchCriteria<String>, TransportationRouteService, TransportationRouteRepository, Long>(
    remainingPriceService
) {
    

}
