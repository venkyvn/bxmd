package com.digi.bxmd.base.controller

import com.digi.bxmd.annotation.Create
import com.digi.bxmd.annotation.Update
import com.digi.bxmd.dto.BaseDto
import com.digi.bxmd.dto.ResponseDto
import com.digi.bxmd.base.entity.BaseEntity
import com.digi.bxmd.base.repository.BaseRepository
import com.digi.bxmd.base.repository.BaseSearchCriteria
import com.digi.bxmd.base.service.BaseService
import com.fasterxml.jackson.databind.ser.Serializers.Base
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.io.Serializable

abstract class BaseController<D : BaseDto, E : BaseEntity, S : BaseSearchCriteria<ID>, SV : BaseService<D, E, S, R, ID>, R : BaseRepository<E, ID>, ID : Long>(
    private val service: SV,
) {

    @PostMapping("/search")
    fun search(@Validated @RequestBody searchFilter: S): ResponseEntity<ResponseDto> {
        return ResponseDto.ok(service.search(searchFilter))
    }

    @PostMapping("/search/all")
    fun searchAll(): ResponseEntity<ResponseDto> {
        return ResponseDto.ok(service.findAll())
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: ID): ResponseEntity<ResponseDto> {
        return ResponseDto.ok(service.findById(id))
    }

    @PostMapping
    fun add(@Validated(Create::class) @RequestBody dto: D): ResponseEntity<ResponseDto> {
        return ResponseDto.ok(service.add(dto))
    }

    @PutMapping
    fun update(@Validated(Update::class) @RequestBody dto: D): ResponseEntity<ResponseDto> {
        return ResponseDto.ok(service.update(dto))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: ID): ResponseEntity<ResponseDto> {
        return ResponseDto.ok(service.delete(id))
    }

    @DeleteMapping
    fun deleteList(@RequestBody bannerDtos: List<D>): ResponseEntity<ResponseDto> {
        return ResponseDto.ok(service.deleteAll(bannerDtos))
    }
}
