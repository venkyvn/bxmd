package com.digi.bxmd.config

import com.digi.bxmd.util.SecurityUtils
import org.springframework.data.domain.AuditorAware
import java.util.*

class AuditorAwareImpl : AuditorAware<String> {
    override fun getCurrentAuditor(): Optional<String> {
        return SecurityUtils.getCurrentUser()
    }
}