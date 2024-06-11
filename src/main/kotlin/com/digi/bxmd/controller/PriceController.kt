package com.digi.bxmd.controller

import com.digi.bxmd.base.controller.BaseController
import com.digi.bxmd.base.repository.BaseSearchCriteria
import com.digi.bxmd.dto.PriceDto
import com.digi.bxmd.entity.Price
import com.digi.bxmd.repository.PriceRepository
import com.digi.bxmd.service.PriceService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("\${apiBasePath}/price")
@Api(tags = ["Price"], description = "Price")
class PriceController @Autowired constructor(
    priceService: PriceService,
) : BaseController<PriceDto, Price, BaseSearchCriteria<String>, PriceService, PriceRepository, Long>(
    priceService
) {
    

}
