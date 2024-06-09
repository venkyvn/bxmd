package com.digi.bxmd.dto

import java.io.Serializable

class JwtBody(
    var id: Long,
    var email: String,
    var phone: String,
    var name: String,
    var clientType: String,
    var roles: MutableSet<String> = HashSet()
) : Serializable