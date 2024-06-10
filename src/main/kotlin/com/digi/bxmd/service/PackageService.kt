package com.digi.bxmd.service

import com.digi.bxmd.base.repository.BaseSearchCriteria
import com.digi.bxmd.base.service.BaseService
import com.digi.bxmd.dto.PackageDto
import com.digi.bxmd.entity.Package
import com.digi.bxmd.repository.PackageRepository

interface PackageService : BaseService<PackageDto, Package, BaseSearchCriteria<String>, PackageRepository, Long> {
}