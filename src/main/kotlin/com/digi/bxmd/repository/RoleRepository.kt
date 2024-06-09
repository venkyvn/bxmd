package com.digi.bxmd.repository

import com.digi.bxmd.base.repository.BaseRepository
import com.digi.bxmd.entity.Role
import com.digi.bxmd.entity.User
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RoleRepository : BaseRepository<Role, Long> {
}