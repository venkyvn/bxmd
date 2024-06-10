package com.digi.bxmd.repository

import com.digi.bxmd.base.repository.BaseRepository
import com.digi.bxmd.entity.Province
import org.springframework.stereotype.Repository

@Repository
interface ProvinceRepository : BaseRepository<Province, Long> {
}