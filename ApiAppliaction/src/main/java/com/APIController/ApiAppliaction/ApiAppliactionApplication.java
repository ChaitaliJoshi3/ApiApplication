package com.APIController.ApiAppliaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@SpringBootApplication
@RestController
public class ApiAppliactionApplication {
	
	private List<Customer> customerDatabase = new ArrayList<>();


	public static void main(String[] args) {
		SpringApplication.run(ApiAppliactionApplication.class, args);
	}
		
		
		@PostMapping("/customers")
	    public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
	        customerDatabase.add(customer);
	        return new ResponseEntity<>("Customer added successfully", HttpStatus.CREATED);
	    }

	    @GetMapping("/customers/{customerRef}")
	    public ResponseEntity<Customer> getCustomer(@PathVariable String customerRef) {
	        List<Customer> matchedCustomers = customerDatabase.stream()
	                .filter(customer -> customer.getCustomerRef().equals(customerRef))
	                .collect(Collectors.toList());

	        if (matchedCustomers.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        } else {
	            return new ResponseEntity<>(matchedCustomers.get(0), HttpStatus.OK);
	        }
	    }
	

}
