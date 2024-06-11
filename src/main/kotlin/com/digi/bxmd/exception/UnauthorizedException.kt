package com.digi.bxmd.exception

import javax.naming.AuthenticationException


data class UnauthorizedException(override var message: String?) : AuthenticationException(message)

