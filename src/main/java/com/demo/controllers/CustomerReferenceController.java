package com.demo.controllers;

import com.demo.exceptions.ApplicationException;
import com.demo.models.Customer;
import com.demo.service.CustomerReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerReferenceController {

    @Autowired
    CustomerReferenceService customerReferenceService;

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerData(@PathVariable("id") Long id) throws ApplicationException {
        Customer customer = customerReferenceService.getCustomerData(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers(){
        List<Customer> customers = customerReferenceService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping(path = "/customers", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        Customer cust = customerReferenceService.createCustomer(customer);
        return new ResponseEntity<>(cust, HttpStatus.CREATED);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity deleteCustomer(@PathVariable("id") Long id){
        customerReferenceService.deleteCustomer(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
