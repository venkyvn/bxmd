package com.digi.bxmd.service.impl

import com.digi.bxmd.base.repository.BaseSearchCriteria
import com.digi.bxmd.base.service.impl.BaseServiceImpl
import com.digi.bxmd.dto.RemainingPriceDto
import com.digi.bxmd.entity.RemainingPrice
import com.digi.bxmd.repository.RemainingPriceRepository
import com.digi.bxmd.service.RemainingPriceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RemainingPriceServiceImpl @Autowired constructor(packageRepository: RemainingPriceRepository) :
    BaseServiceImpl<RemainingPriceDto, RemainingPrice, BaseSearchCriteria<String>, RemainingPriceRepository, Long>(packageRepository),
    RemainingPriceService {

}