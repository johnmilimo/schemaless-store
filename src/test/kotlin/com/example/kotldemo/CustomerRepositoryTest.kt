package com.example.kotldemo

import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
class CustomerRepositoryTest {
    @Autowired
    private lateinit var customerRepository: CustomerRepository

    private val customerEntryId = CustomerEntryId(customerCode = "1", customerKey = "key")

    @Before
    fun setupTestData() {
        customerRepository.save(CustomerEntry(customerEntryId).apply {
            customerValue = "value"
        })
    }

    @After
    fun cleanTestData() {
        customerRepository.deleteById(customerEntryId)
    }

    @Test
    fun findAllByCustomerCode() {
        assertThat(customerRepository.findAllByCustomerCode("1")).hasOnlyOneElementSatisfying { entry ->
            assertThat(entry.id.customerCode).isEqualTo("1")
            assertThat(entry.id.customerKey).isEqualTo("key")
            assertThat(entry.getCustomerCode()).isEqualTo("1")
            assertThat(entry.getCustomerKey()).isEqualTo("key")
            assertThat(entry.customerValue).isEqualTo("value")
        }
    }
}
