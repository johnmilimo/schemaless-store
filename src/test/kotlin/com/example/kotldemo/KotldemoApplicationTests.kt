package com.example.kotldemo

import org.junit.Test
import org.junit.runner.RunWith
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
@EnableDynamoDBRepositories
class KotldemoApplicationTests {

	@Test
	fun contextLoads() {
	}

}
