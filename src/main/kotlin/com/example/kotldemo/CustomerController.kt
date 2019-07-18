package com.example.kotldemo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class CustomerController {

    @Autowired
    private lateinit var customerRepository: CustomerRepository

    /** Get customer details */
    @GetMapping("/customer/{id}")
    fun getCustomer(@PathVariable("id") id: String): Iterable<CustomerEntry>  {
        return customerRepository.findAllByCustomerId(id)
    }

    @PostMapping("/customer")
    fun createCustomer(@RequestBody customer: CustomerEntry): CustomerEntry {

        val customerEntryId = CustomerEntryId( customerId = customer.getCustomerId(),
                customerKey = customer.getCustomerKey())

        return customerRepository.save(CustomerEntry(customerEntryId).apply {
            customerValue = customer.customerValue
        })
    }
}