package com.digi.bxmd.service.impl

import com.digi.bxmd.base.repository.BaseSearchCriteria
import com.digi.bxmd.base.service.impl.BaseServiceImpl
import com.digi.bxmd.constant.MessageKey
import com.digi.bxmd.dto.RemainingPriceDto
import com.digi.bxmd.entity.Price
import com.digi.bxmd.entity.RemainingPrice
import com.digi.bxmd.exception.BusinessException
import com.digi.bxmd.repository.RemainingPriceRepository
import com.digi.bxmd.service.RemainingPriceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RemainingPriceServiceImpl @Autowired constructor(packageRepository: RemainingPriceRepository) :
    BaseServiceImpl<RemainingPriceDto, RemainingPrice, BaseSearchCriteria<String>, RemainingPriceRepository, Long>(
        packageRepository
    ),
    RemainingPriceService {

    override fun update(dto: RemainingPriceDto): RemainingPriceDto {
        dto.id?.let {
            var remainingPrice = getRepository().findById(it).orElseThrow { BusinessException(MessageKey.BAD_REQUEST) }

            remainingPrice.apply {
                remainingPrice.distanceName = dto.distanceName
                remainingPrice.distanceCode = dto.distanceCode
                remainingPrice.time = dto.time
            }

            var existingRemainingPrices = remainingPrice.prices.toMutableSet()

            // remove old route
            existingRemainingPrices.removeIf { route -> dto.prices!!.none { it.id == route.id } }

            // update existing routes and add new routes
            dto.prices?.forEach { newPrice ->
                run {
                    val existingPrice = existingRemainingPrices.find { it.id == newPrice.id }
                    if (existingPrice != null) {
                        newPrice.apply {
                            existingPrice.priceName = newPrice.priceName
                            existingPrice.priceNumber = newPrice.priceNumber
                            existingPrice.priceCode = newPrice.priceCode
                            existingPrice.minKG = newPrice.minKG
                            existingPrice.maxKG = newPrice.maxKG
                            existingPrice.priceAdd = newPrice.priceAdd
                        }
                    } else {
                        val entityPrice = Price().apply {
                            priceName = newPrice.priceName
                            priceNumber = newPrice.priceNumber
                            priceCode = newPrice.priceCode
                            minKG = newPrice.minKG
                            maxKG = newPrice.maxKG
                            priceAdd = newPrice.priceAdd
                        }
                        existingRemainingPrices.add(entityPrice)
                    }
                }
            }
            remainingPrice.prices = existingRemainingPrices
            return toDTO(getRepository().save(remainingPrice))
        } ?: throw BusinessException(MessageKey.BAD_REQUEST)
    }
}