package com.digi.bxmd.repository

import com.digi.bxmd.base.repository.BaseRepository
import com.digi.bxmd.entity.UserToken
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserTokenRepo : BaseRepository<UserToken, Long> {
    fun findByRefreshToken(refreshToken: String): Optional<UserToken>

    fun deleteAllByRefreshToken(refreshToken: String)

    fun deleteAllByUserIdIn(ids: List<Long>)

}