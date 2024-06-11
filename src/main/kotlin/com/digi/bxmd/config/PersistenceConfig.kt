package com.digi.bxmd.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import java.util.*


@Configuration
@EnableJpaAuditing
class PersistenceConfig {
}


@Configuration
class AuditorAwareImpl : AuditorAware<String> {
    override fun getCurrentAuditor(): Optional<String> {
        return Optional.ofNullable(SecurityContextHolder.getContext().authentication)
            .map { authentication ->
                if (authentication.principal is UserDetails) {
                    return@map (authentication.principal as UserDetails).username
                } else {
                    return@map authentication.principal.toString()
                }
            }
    }
}

