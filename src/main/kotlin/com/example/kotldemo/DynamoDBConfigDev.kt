package com.example.kotldemo

import org.springframework.context.annotation.Configuration
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.auth.AWSCredentials
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean

import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput
import com.amazonaws.services.dynamodbv2.model.ResourceInUseException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.reflect.KClass


@Configuration
class DynamoDBConfigDev {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @Value("\${amazon.dynamodb.endpoint}")
    private val amazonDynamoDBEndpoint: String? = null

    @Value("\${amazon.aws.accesskey}")
    private val amazonAWSAccessKey: String? = null

    @Value("\${amazon.aws.secretkey}")
    private val amazonAWSSecretKey: String? = null

    @Value("\${amazon.dynamodb.region}")
    private val amazonDynamoDBRegion: String? = null


    @Bean
    fun amazonDynamoDB(): AmazonDynamoDB {
        val client =  AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(
                        AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, amazonDynamoDBRegion))
                .build()
        createTableForEntity(client, CustomerEntry::class)
        return client
    }

    @Bean
    fun amazonAWSCredentials(): AWSCredentials {
        return BasicAWSCredentials(
                amazonAWSAccessKey!!, amazonAWSSecretKey!!)
    }

    @Bean
    fun dynamoDB(): DynamoDB {
        return DynamoDB(amazonDynamoDB())
    }

    private fun createTableForEntity(amazonDynamoDB: AmazonDynamoDB, entity: KClass<*>) {

        val tableRequest = DynamoDBMapper(amazonDynamoDB)
                .generateCreateTableRequest(entity.java)
                .withProvisionedThroughput(ProvisionedThroughput(1L, 1L))

        try {
            DynamoDB(amazonDynamoDB).createTable(tableRequest).waitForActive()
            log.info("Table created! [entity={}]", entity)
        } catch (e: ResourceInUseException) {
            log.info("Table already exists - skip creation! [entity={}]", entity)
        }
    }
}
