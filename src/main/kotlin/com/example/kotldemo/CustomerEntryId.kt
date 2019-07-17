package com.example.kotldemo

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey
import java.io.Serializable

/**
 * Composite primary key for the [CustomerEntryId] domain.
 */
@DynamoDBDocument
data class CustomerEntryId (

        @field:DynamoDBHashKey
        var customerCode: String? = null,

        @field:DynamoDBRangeKey
        var customerKey: String? = null

) : Serializable