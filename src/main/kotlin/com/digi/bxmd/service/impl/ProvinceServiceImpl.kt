package com.digi.bxmd.service.impl

import com.digi.bxmd.base.repository.BaseSearchCriteria
import com.digi.bxmd.base.service.impl.BaseServiceImpl
import com.digi.bxmd.constant.MessageKey
import com.digi.bxmd.dto.ProvinceDto
import com.digi.bxmd.entity.Province
import com.digi.bxmd.entity.TransportationRoute
import com.digi.bxmd.exception.BusinessException
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
    override fun update(dto: ProvinceDto): ProvinceDto {
        dto.id?.let {
            var province = getRepository().findById(it).orElseThrow { BusinessException(MessageKey.BAD_REQUEST) }

            province.apply {
                province.label = dto.label
                province.value = dto.value
                province.licenseplates = dto.licenseplates
            }

            var existingRoutes = province.transportationRoutes.toMutableSet()

            // remove old route
            existingRoutes.removeIf { route -> dto.transportationRoutes.none { it.id == route.id } }

            // update existing routes and add new routes
            dto.transportationRoutes.forEach { newRoute ->
                run {
                    val existingRoute = existingRoutes.find { it.id == newRoute.id }
                    if (existingRoute != null) {
                        newRoute.apply {
                            existingRoute.label = newRoute.label
                            existingRoute.distanceName = newRoute.distanceName
                            existingRoute.transportationRouteCode = newRoute.transportationRouteCode
                            existingRoute.value = newRoute.value
                        }
                    } else {
                        val entityRoute = TransportationRoute().apply {
                            label = newRoute.label
                            distanceName = newRoute.distanceName
                            transportationRouteCode = newRoute.transportationRouteCode
                            value = newRoute.value
                        }
                        existingRoutes.add(entityRoute)
                    }
                }
            }
            province.transportationRoutes = existingRoutes
            return toDTO(getRepository().save(province))
        } ?: throw BusinessException(MessageKey.BAD_REQUEST)
    }
}