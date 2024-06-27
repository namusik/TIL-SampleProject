package com.example.hateoas.service;

import com.example.hateoas.domain.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CustomerService {
    private final HashMap<String, Customer> customerMap;

    public CustomerService() {

        customerMap = new HashMap<>();

        final Customer customerOne = new Customer("10A", "Jane", "ABC Company");
        final Customer customerTwo = new Customer("20B", "Bob", "XYZ Company");
        final Customer customerThree = new Customer("30C", "Tim", "CKV Company");

        customerMap.put("10A", customerOne);
        customerMap.put("20B", customerTwo);
        customerMap.put("30C", customerThree);

    }

    public List<Customer> allCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    public Customer getCustomerDetail(final String customerId) {
        return customerMap.get(customerId);
    }
}
