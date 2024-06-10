package com.digi.bxmd.service.impl

import com.digi.bxmd.base.repository.BaseSearchCriteria
import com.digi.bxmd.base.service.impl.BaseServiceImpl
import com.digi.bxmd.dto.PriceDto
import com.digi.bxmd.entity.Price
import com.digi.bxmd.repository.PriceRepository
import com.digi.bxmd.service.PriceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PriceServiceImpl @Autowired constructor(packageRepository: PriceRepository) :
    BaseServiceImpl<PriceDto, Price, BaseSearchCriteria<String>, PriceRepository, Long>(packageRepository),
    PriceService {

}