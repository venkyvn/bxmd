package com.digi.bxmd.repository

import com.digi.bxmd.base.repository.BaseRepository
import com.digi.bxmd.entity.User
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepo : BaseRepository<User, Long> {

    fun findByUsernameIgnoreCase(username: String): Optional<User>

    fun findFirstByUsernameIgnoreCaseOrEmailIgnoreCase(username: String, email: String): Optional<User>

}