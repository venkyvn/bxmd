package com.digi.bxmd.service

import com.digi.bxmd.base.repository.BaseSearchCriteria
import com.digi.bxmd.base.service.BaseService
import com.digi.bxmd.dto.RemainingPriceDto
import com.digi.bxmd.entity.RemainingPrice
import com.digi.bxmd.repository.RemainingPriceRepository

interface RemainingPriceService : BaseService<RemainingPriceDto, RemainingPrice, BaseSearchCriteria<String>, RemainingPriceRepository, Long> {
}