package com.digi.bxmd.service

import com.digi.bxmd.base.repository.BaseSearchCriteria
import com.digi.bxmd.base.service.BaseService
import com.digi.bxmd.dto.UserDto
import com.digi.bxmd.entity.User
import com.digi.bxmd.repository.UserRepo
import java.util.*

interface UserService : BaseService<UserDto, User, BaseSearchCriteria<String>, UserRepo, Long> {
    fun getCurrentUser(): Optional<User>
    fun getCurrentUserDto(): UserDto
}