package com.digi.bxmd.base.service

import com.digi.bxmd.dto.BaseDto
import com.digi.bxmd.base.entity.BaseEntity
import com.digi.bxmd.base.repository.BaseRepository
import com.digi.bxmd.base.repository.BaseSearchCriteria
import org.springframework.data.domain.Page
import java.io.Serializable

interface BaseService<D : BaseDto, E : BaseEntity, S : BaseSearchCriteria<*>, R : BaseRepository<E, I>, I : Long> {

    fun findAll(): List<D>

    fun find(dto: D): D

    fun findById(id: I): D

    fun isExist(dto: D): Boolean

    fun add(dto: D): D

    fun update(dto: D): D

    fun addAll(dtos: List<D>): List<D>

    fun updateAll(dtos: List<D>): List<D>

    fun deleteAll(dtos: List<D>): List<D>

    fun search(searchCriteria: S): Page<D>

    fun delete(id: I): D
}
