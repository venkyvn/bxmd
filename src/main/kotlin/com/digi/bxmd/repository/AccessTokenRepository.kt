package com.digi.bxmd.repository

import com.digi.bxmd.base.repository.BaseRepository
import com.digi.bxmd.entity.AccessToken
import org.springframework.stereotype.Repository

@Repository
interface AccessTokenRepository : BaseRepository<AccessToken, Long> {
    fun existsByToken(token: String): Boolean
}