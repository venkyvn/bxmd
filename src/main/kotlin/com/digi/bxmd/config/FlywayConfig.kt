package com.digi.bxmd.config

import org.flywaydb.core.Flyway
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import javax.annotation.PostConstruct
import javax.sql.DataSource

@Configuration
class FlywayConfig(
    private val dataSource: DataSource,
    private val env: Environment
) {

    @Value("\${spring.flyway.enabled}")
    private var enableFlyWay: Boolean = false

    @PostConstruct
    fun migrate() {
        if (!enableFlyWay) return

        Flyway.configure()
            .dataSource(dataSource)
            .load()
            .migrate()
    }
}
