package com.rekover.robotfactory

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder




@SpringBootApplication
@Configuration
class RobotFactoryApplication

fun main(args: Array<String>) {
    runApplication<RobotFactoryApplication>(*args)
}

@Bean
fun jacksonBuilder(): Jackson2ObjectMapperBuilder {
    val b = Jackson2ObjectMapperBuilder()
    b.propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
    return b
}
