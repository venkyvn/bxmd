package com.digi.bxmd.controller

import com.digi.bxmd.base.controller.BaseController
import com.digi.bxmd.base.repository.BaseSearchCriteria
import com.digi.bxmd.dto.RemainingPriceDto
import com.digi.bxmd.entity.RemainingPrice
import com.digi.bxmd.repository.RemainingPriceRepository
import com.digi.bxmd.service.RemainingPriceService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("\${apiBasePath}/remaining-price")
@Api(tags = ["RemainingPrice"], description = "RemainingPrice")
class RemainingPriceController @Autowired constructor(
    remainingPriceService: RemainingPriceService,
) : BaseController<RemainingPriceDto, RemainingPrice, BaseSearchCriteria<String>, RemainingPriceService, RemainingPriceRepository, Long>(
    remainingPriceService
) {
    

}
