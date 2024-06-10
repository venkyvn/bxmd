package com.digi.bxmd.service.impl

import com.digi.bxmd.base.repository.BaseSearchCriteria
import com.digi.bxmd.base.service.impl.BaseServiceImpl
import com.digi.bxmd.dto.ProvinceDto
import com.digi.bxmd.entity.Province
import com.digi.bxmd.repository.ProvinceRepository
import com.digi.bxmd.service.ProvinceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ProvinceServiceImpl @Autowired constructor(packageRepository: ProvinceRepository) :
    BaseServiceImpl<ProvinceDto, Province, BaseSearchCriteria<String>, ProvinceRepository, Long>(packageRepository),
    ProvinceService {

}