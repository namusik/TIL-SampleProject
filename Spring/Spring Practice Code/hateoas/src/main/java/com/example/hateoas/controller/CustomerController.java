package com.example.hateoas.controller;

import com.example.hateoas.domain.Customer;
import com.example.hateoas.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/{customerId}")
    public EntityModel<Customer> getCustomerById(@PathVariable String customerId) {
        Customer customer = customerService.getCustomerDetail(customerId);
        EntityModel<Customer> customerModel = EntityModel.of(customer);

        customerModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class)
                .getCustomerById(customerId)).withSelfRel());

        customerModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class)
                .getAllCustomers()).withRel("customers"));

        return customerModel;
    }

    @GetMapping(produces = {"application/hal+json"})
    public CollectionModel<EntityModel<Customer>> getAllCustomers() {
        List<Customer> allCustomers = customerService.allCustomers();

        List<EntityModel<Customer>> customerModels = allCustomers.stream()
                .map(customer -> {
                    EntityModel<Customer> model = EntityModel.of(customer);
                    model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class)
                            .getCustomerById(customer.getCustomerId())).withSelfRel());
                    return model;
                })
                .toList();

        return CollectionModel.of(customerModels, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).getAllCustomers()).withSelfRel());
    }
}
