package com.digi.bxmd.util

import com.digi.bxmd.constant.RoleConstant
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

object SecurityUtils {

    /**
     * Get current user information.
     *
     * @return Optional of user details
     */
    fun getCurrentUser(): Optional<String> {
        val securityContext = SecurityContextHolder.getContext()
        return Optional.ofNullable(securityContext.authentication)
            .map { authentication ->
                when (val principal = authentication.principal) {
                    is UserDetails -> principal.username
                    is String -> principal
                    else -> null
                }
            }
    }

    /**
     * Get the JWT of the current user.
     *
     * @return Optional of the JWT of the current user
     */
    fun getCurrentUserJWT(): Optional<String> {
        val securityContext = SecurityContextHolder.getContext()
        return Optional.ofNullable(securityContext.authentication)
            .filter { authentication -> authentication.credentials is String }
            .map { authentication -> authentication.credentials as String }
    }

    /**
     * Check if a user is authenticated.
     *
     * @return true if the user is authenticated, false otherwise
     */
    fun isAuthenticated(): Boolean {
        val securityContext = SecurityContextHolder.getContext()
        return Optional.ofNullable(securityContext.authentication)
            .map { authentication ->
                authentication.authorities.none { grantedAuthority ->
                    grantedAuthority.authority == RoleConstant.ANONYMOUS
                }
            }.orElse(false)
    }

    /**
     * If the current user has a specific authority (security role).
     *
     * @param authority the authority to check
     * @return true if the current user has the authority, false otherwise
     */
    fun isCurrentUserInRole(authority: String): Boolean {
        val securityContext = SecurityContextHolder.getContext()
        return Optional.ofNullable(securityContext.authentication)
            .map { authentication ->
                authentication.authorities.any { grantedAuthority ->
                    grantedAuthority.authority == authority
                }
            }.orElse(false)
    }
}

