package com.digi.bxmd.service

import com.digi.bxmd.base.repository.BaseSearchCriteria
import com.digi.bxmd.base.service.BaseService
import com.digi.bxmd.dto.PriceDto
import com.digi.bxmd.entity.Price
import com.digi.bxmd.repository.PriceRepository

interface PriceService : BaseService<PriceDto, Price, BaseSearchCriteria<String>, PriceRepository, Long> {
}