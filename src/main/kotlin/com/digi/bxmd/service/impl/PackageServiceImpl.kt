package com.digi.bxmd.service.impl

import com.digi.bxmd.base.repository.BaseSearchCriteria
import com.digi.bxmd.base.service.impl.BaseServiceImpl
import com.digi.bxmd.dto.PackageDto
import com.digi.bxmd.entity.Package
import com.digi.bxmd.repository.PackageRepository
import com.digi.bxmd.service.PackageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PackageServiceImpl @Autowired constructor(packageRepository: PackageRepository) :
    BaseServiceImpl<PackageDto, Package, BaseSearchCriteria<String>, PackageRepository, Long>(packageRepository),
    PackageService {

}