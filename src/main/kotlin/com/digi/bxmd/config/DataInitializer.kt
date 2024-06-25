package com.digi.bxmd.config

import com.digi.bxmd.constant.RoleConstant
import com.digi.bxmd.entity.Role
import com.digi.bxmd.entity.User
import com.digi.bxmd.repository.RoleRepository
import com.digi.bxmd.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class DataInitializer : CommandLineRunner {
    @Autowired
    lateinit var roleRepository: RoleRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder
    override fun run(vararg args: String?) {
        if (roleRepository.findByName(RoleConstant.ADMIN).isEmpty) {
            val adminRole = Role(name = RoleConstant.ADMIN)
            adminRole.createdBy = RoleConstant.ADMIN
            adminRole.updatedBy = RoleConstant.ADMIN
            roleRepository.save<Role>(adminRole)
        }

        if (roleRepository.findByName(RoleConstant.USER).isEmpty) {
            val userRole = Role(name = RoleConstant.USER)
            userRole.createdBy = RoleConstant.ADMIN
            userRole.updatedBy = RoleConstant.ADMIN
            roleRepository.save<Role>(userRole)
        }
        if (roleRepository.findByName(RoleConstant.ANONYMOUS).isEmpty) {
            val anonymousRole = Role(name = RoleConstant.ANONYMOUS)
            anonymousRole.createdBy = RoleConstant.ADMIN
            anonymousRole.updatedBy = RoleConstant.ADMIN
            roleRepository.save<Role>(anonymousRole)
        }

        if (!userRepository.findByUsernameIgnoreCase("admin").isPresent) {
            val adminUser = User(
                username = "admin",
                email = "admin@example.com",
            ).apply {
                password = passwordEncoder.encode("admin")
                createdBy = RoleConstant.ADMIN
                updatedBy = RoleConstant.ADMIN
                roles = mutableSetOf(roleRepository.findByName(RoleConstant.ADMIN).get())
            }
            userRepository.save(adminUser)
        }
    }
}
