package com.digi.bxmd.controller

import com.digi.bxmd.base.controller.BaseController
import com.digi.bxmd.base.repository.BaseSearchCriteria
import com.digi.bxmd.dto.ProvinceDto
import com.digi.bxmd.entity.Province
import com.digi.bxmd.repository.ProvinceRepository
import com.digi.bxmd.service.ProvinceService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("\${apiBasePath}/province")
@Api(tags = ["Province"], description = "Province")
class ProvinceController @Autowired constructor(
    provinceService: ProvinceService,
) : BaseController<ProvinceDto, Province, BaseSearchCriteria<String>, ProvinceService, ProvinceRepository, Long>(
    provinceService
) {
    

}
