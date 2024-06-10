package com.digi.bxmd.repository

import com.digi.bxmd.base.repository.BaseRepository
import com.digi.bxmd.entity.RemainingPrice
import org.springframework.stereotype.Repository

@Repository
interface RemainingPriceRepository : BaseRepository<RemainingPrice, Long> {
}