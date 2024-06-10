package com.digi.bxmd.service.impl

import com.digi.bxmd.base.repository.BaseSearchCriteria
import com.digi.bxmd.base.service.impl.BaseServiceImpl
import com.digi.bxmd.constant.MessageKey
import com.digi.bxmd.dto.UserDto
import com.digi.bxmd.entity.User
import com.digi.bxmd.exception.BusinessException
import com.digi.bxmd.repository.UserRepository
import com.digi.bxmd.service.UserService
import com.digi.bxmd.util.SecurityUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class UserServiceImpl @Autowired constructor(userRepository: UserRepository) :
    BaseServiceImpl<UserDto, User, BaseSearchCriteria<String>, UserRepository, Long>(userRepository), UserService {

    override fun getCurrentUser(): Optional<User> {
        return SecurityUtils.getCurrentUser().flatMap { this.getRepository().findByUsernameIgnoreCase(it) }
    }

    override fun getCurrentUserDto(): UserDto {
        val user = this.getCurrentUser().orElseThrow{BusinessException(MessageKey.NOT_FOUND)}
        return toDTO(user)
    }

}