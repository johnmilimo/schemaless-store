package com.example.kotldemo

import org.socialsignin.spring.data.dynamodb.repository.EnableScan
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@EnableScan
@Repository
interface CustomerRepository : CrudRepository<CustomerEntry, CustomerEntryId> {
    fun findAllByCustomerId(customerId: String): Iterable<CustomerEntry>
}
