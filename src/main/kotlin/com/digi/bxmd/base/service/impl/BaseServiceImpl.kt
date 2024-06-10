package com.digi.bxmd.base.service.impl

import com.digi.bxmd.base.entity.BaseEntity
import com.digi.bxmd.base.repository.BaseRepository
import com.digi.bxmd.base.repository.BaseSearchCriteria
import com.digi.bxmd.base.service.BaseService
import com.digi.bxmd.constant.MessageKey
import com.digi.bxmd.dto.BaseDto
import com.digi.bxmd.exception.BusinessException
import org.modelmapper.ModelMapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import java.lang.reflect.ParameterizedType
import javax.transaction.Transactional


@Suppress("UNCHECKED_CAST")
@Transactional
abstract class BaseServiceImpl<D : BaseDto, E : BaseEntity, S : BaseSearchCriteria<*>, R : BaseRepository<E, I>, I : Long>(
    private val repository: BaseRepository<E, I>,
) : BaseService<D, E, S, R, I> {

    private val clazzDto: Class<D>
    private val clazzEntity: Class<E>

    @Autowired private lateinit var modelMapper: ModelMapper

    private lateinit var env: Environment

    init {
        this.clazzDto = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<D>
        this.clazzEntity = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<E>
    }

    protected fun toDTO(e: E): D {
        return modelMapper.map(e, clazzDto)
    }

    protected fun toEntity(dto: D): E {
        return modelMapper.map(dto, clazzEntity)
    }

    protected fun toEntity(dto: D, e: E): E {
        modelMapper.map(dto, e)
        return e
    }

    protected fun toListEntity(dto: List<D>?): List<E> {
        return dto?.stream()?.map { this.toEntity(it) }?.toList() ?: ArrayList()
    }

    protected fun toDTOs(entities: List<E>?): List<D> {
        return entities?.stream()?.map { this.toDTO(it) }?.toList() ?: ArrayList()
    }

    override fun findAll(): List<D> {
        return toDTOs(repository.findAll())
    }

    override fun find(dto: D): D {
        val entity = findEntityById(dto)
        onGetValidate(entity)
        return toDTO(entity)
    }

    @Suppress("UNCHECKED_CAST")
    override fun isExist(dto: D): Boolean {
        return repository.existsById(dto.id as I)
    }

    override fun add(dto: D): D {
        onCreateValidate(dto)
        val entity = toEntity(dto)
        val e = repository.save(entity)
        return toDTO(e)
    }

    override fun update(dto: D): D {
        onUpdateValidate(dto)
        var entity = findEntityById(dto)
        entity = toEntity(dto, entity)
        entity = repository.save(entity)
        return toDTO(entity)
    }

    override fun addAll(dtos: List<D>): List<D> {
        val es = toListEntity(dtos)
        repository.saveAll(es)
        return toDTOs(es)
    }

    override fun updateAll(dtos: List<D>): List<D> {
        val es = toListEntity(dtos)
        repository.saveAll(es)
        return dtos
    }

    override fun deleteAll(dtos: List<D>): List<D> {
        val es = toListEntity(dtos)
        repository.deleteInBatch(es)
        return dtos
    }

    override fun search(searchFilter: S): Page<D> {
        val direction = if (searchFilter.direction.equals(Sort.DEFAULT_DIRECTION.toString(), ignoreCase = true))
            Sort.DEFAULT_DIRECTION
        else
            Sort.Direction.DESC
        val pageable =
            PageRequest.of(searchFilter.page ?: 0, searchFilter.pageSize ?: 0, direction, searchFilter.sortBy)
        val page = repository.findAll(pageable)
        return PageImpl(toDTOs(page.content), pageable, page.totalElements)
    }

    protected fun findEntityById(dto: D): E {
        val entity = repository.findById(dto.id as I)
        if (!entity.isPresent)
            throw BusinessException(MessageKey.NOT_FOUND, dto.id.toString())
        return entity.get()
    }

    override fun findById(id: I): D {
        val entity = repository.findById(id)
        if (!entity.isPresent)
            throw BusinessException(MessageKey.NOT_FOUND, id.toString())
        return toDTO(entity.get())
    }

    override fun delete(id: I): D {
        val entity = onDeleteValidate(id)
        repository.deleteById(id)
        return toDTO(entity)
    }

    protected open fun onGetValidate(entity: E) {

    }

    protected open fun onCreateValidate(dto: D) {

    }

    protected open fun onUpdateValidate(dto: D): E? {
        LoggerFactory.getLogger(javaClass).debug("{}", dto)
        return null
    }

    protected open fun onDeleteValidate(id: I): E {
        if (id == null) {
            throw BusinessException(MessageKey.NOT_FOUND)
        }
        return repository.findById(id).orElseThrow { BusinessException(MessageKey.NOT_FOUND, id.toString()) }
    }

    @Suppress("UNCHECKED_CAST")
    protected open fun getRepository(): R {
        return repository as R
    }

//    protected open fun getPath(): String {
//        return env.getProperty("ride.path." + StringUtil.getOSKey()) + File.separator + "%s" + File.separator + "images"
//    }
}

