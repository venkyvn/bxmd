package com.digi.bxmd.security

import com.digi.bxmd.constant.MessageKey
import com.digi.bxmd.entity.User
import com.digi.bxmd.repository.UserRepo
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import java.util.stream.Collectors

@Component("userDetailsService")
@Slf4j
class MyUserDetailsService @Autowired constructor(val userRepo: UserRepo) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return userRepo.findFirstByUsernameIgnoreCaseOrEmailIgnoreCase(username, username)
            .map { createSpringSecurityUser(it) }.orElseThrow { UsernameNotFoundException(MessageKey.NOT_FOUND) }
    }

    fun createSpringSecurityUser(user: User): org.springframework.security.core.userdetails.User {
        val grantedAuthorities: List<GrantedAuthority> = user.roles.stream()
            .map { authority -> SimpleGrantedAuthority(authority.name) }
            .collect(Collectors.toList())
        return org.springframework.security.core.userdetails.User(user.name, user.password, grantedAuthorities)
    }


}
