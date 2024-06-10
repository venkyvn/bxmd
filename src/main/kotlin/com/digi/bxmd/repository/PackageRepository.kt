package com.digi.bxmd.repository

import com.digi.bxmd.base.repository.BaseRepository
import com.digi.bxmd.entity.Package
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PackageRepository : BaseRepository<Package, Long> {
    fun findByPackagingName(packageName: String): Optional<Package>
}