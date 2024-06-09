package com.digi.bxmd.base.repository

import com.digi.bxmd.base.entity.BaseEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.NoRepositoryBean
import java.io.Serializable

@NoRepositoryBean
interface BaseRepository<E : BaseEntity, I: Long> : JpaRepository<E, I>, JpaSpecificationExecutor<E>