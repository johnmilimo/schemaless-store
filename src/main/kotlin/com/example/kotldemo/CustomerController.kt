package com.example.kotldemo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class CustomerController {

    @Autowired
    private lateinit var customerRepository: CustomerRepository

    /** Get customer details */
    @GetMapping("/customer/{code}")
    fun getCustomer(@PathVariable("code") code: String): Iterable<CustomerEntry>  {
        return customerRepository.findAllByCustomerCode(code)
    }

    @PostMapping("/customer")
    fun createCustomer(@RequestBody customer: CustomerEntry): CustomerEntry {

        val customerEntryId = CustomerEntryId( customerCode = customer.getCustomerCode(),
                customerKey = customer.getCustomerKey())

        return customerRepository.save(CustomerEntry(customerEntryId).apply {
            customerValue = customer.customerValue
        })
    }
}