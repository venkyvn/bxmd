package com.digi.bxmd.repository

import com.digi.bxmd.base.repository.BaseRepository
import com.digi.bxmd.entity.Price
import org.springframework.stereotype.Repository

@Repository
interface PriceRepository : BaseRepository<Price, Long> {
}