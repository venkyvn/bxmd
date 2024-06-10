package com.digi.bxmd.service

import com.digi.bxmd.base.repository.BaseSearchCriteria
import com.digi.bxmd.base.service.BaseService
import com.digi.bxmd.dto.ProvinceDto
import com.digi.bxmd.entity.Province
import com.digi.bxmd.repository.ProvinceRepository

interface ProvinceService : BaseService<ProvinceDto, Province, BaseSearchCriteria<String>, ProvinceRepository, Long> {
}