package com.example.kotldemo

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableDynamoDBRepositories
class KotldemoApplication

fun main(args: Array<String>) {
	runApplication<KotldemoApplication>(*args)
}
