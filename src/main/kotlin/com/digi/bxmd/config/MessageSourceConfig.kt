package com.digi.bxmd.config

import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver
import java.util.*
import javax.servlet.http.HttpServletRequest

@Configuration
class MessageSourceConfig : AcceptHeaderLocaleResolver() {

    companion object {
        private val LOCALES = listOf(Locale("en"), Locale("vi"))
    }

    @Bean("messageSource")
    fun validationMessageSource(): MessageSource {
        val messageSource = ReloadableResourceBundleMessageSource()
        messageSource.setBasename("classpath:/message")
        messageSource.setUseCodeAsDefaultMessage(true)
        messageSource.setDefaultEncoding("UTF-8")
        return messageSource
    }

    override fun resolveLocale(request: HttpServletRequest): Locale {
        val headerLang = request.getHeader("Accept-Language")
        return if (headerLang == null || headerLang.isEmpty()) {
            Locale.getDefault()
        } else {
            Locale.lookup(Locale.LanguageRange.parse(headerLang), LOCALES)
        }
    }
}