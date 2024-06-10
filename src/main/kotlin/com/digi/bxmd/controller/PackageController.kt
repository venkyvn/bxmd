package com.digi.bxmd.controller

import com.digi.bxmd.base.controller.BaseController
import com.digi.bxmd.base.repository.BaseSearchCriteria
import com.digi.bxmd.dto.PackageDto
import com.digi.bxmd.entity.Package
import com.digi.bxmd.repository.PackageRepository
import com.digi.bxmd.service.PackageService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("package")
@Api(tags = ["Package"], description = "Package")
class PackageController @Autowired constructor(
    private val packageService: PackageService,
) : BaseController<PackageDto, Package, BaseSearchCriteria<String>, PackageService, PackageRepository, Long>(
    packageService
) {


}
