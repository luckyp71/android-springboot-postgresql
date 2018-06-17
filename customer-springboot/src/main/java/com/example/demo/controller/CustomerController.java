package com.example.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	CustomerService cusService;

	// Set success header value
	private static Map<String, String> successHeaderKV;
	static {
		successHeaderKV = new HashMap<>();
		successHeaderKV.put("successCode", "00");
		successHeaderKV.put("successDesc", "Success");
		successHeaderKV.put("contentType", "application/json");
	}

	// Get customers
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Customer>> getCustomers(){
		HttpHeaders headers = new HttpHeaders();
		headers.add("responseCode", successHeaderKV.get("successCode"));
		headers.add("successDesc", successHeaderKV.get("successDesc"));
		headers.add(HttpHeaders.CONTENT_TYPE, successHeaderKV.get("contentType"));
		
		CompletableFuture<Collection<Customer>> cf = cusService.getCustomers();
		Collection<Customer> customers = Collections.emptyList();
		
		try {
			customers = cf.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(customers);
	}
	
	
	// Get customers by name
	@GetMapping(value = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Customer>> getCustomersByName(@PathVariable String name) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("responseCode", successHeaderKV.get("successCode"));
		headers.add("successDesc", successHeaderKV.get("successDesc"));
		headers.add(HttpHeaders.CONTENT_TYPE, successHeaderKV.get("contentType"));

		CompletableFuture<Collection<Customer>> cf = cusService.getCustomersByName(name);
		Collection<Customer> customersByName = Collections.emptyList();
		
		try {
			customersByName = cf.get();
			System.out.println("CompletableFuture Status: " + cf.isDone());
			System.out.println("Finish Exceptionally Status: " + cf.isCompletedExceptionally());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(customersByName);
	}

	// Get customers by address
	@GetMapping(value = "/address/{address}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Customer>> getCustomersByAddress(@PathVariable("address") String address) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("responseCode", successHeaderKV.get("successCode"));
		headers.add("responseDesc", successHeaderKV.get("successDesc"));
		headers.add(HttpHeaders.CONTENT_TYPE, successHeaderKV.get("contentType"));
		
		CompletableFuture<Collection<Customer>> cf = cusService.getCustomersByAddress(address);
		Collection<Customer> customersByAddress = Collections.emptyList();
		try {
			customersByAddress = cf.get();
			System.out.println("CompletableFuture Status: " + cf.isDone());
			System.out.println("Finish Exceptionally Status" + cf.isCompletedExceptionally());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(customersByAddress);
	}
	
	// Get customer by id
	@GetMapping(value ="/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id){
		HttpHeaders headers = new HttpHeaders();
		headers.add("responseCode", successHeaderKV.get("successCode"));
		headers.add("responseDesc", successHeaderKV.get("responseDesc"));
		headers.add(HttpHeaders.CONTENT_TYPE, successHeaderKV.get("contentType"));
		
		CompletableFuture<Customer> cf = cusService.getCustomerById(id);
		Customer customer = null;
		
		try {
			customer = cf.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch(ExecutionException e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(customer);
	}

	// Insert new customer
	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> insertCustomer(@RequestBody Customer customer) throws InterruptedException {
		HttpHeaders headers = new HttpHeaders();
		headers.add("responseCode", successHeaderKV.get("successCode"));
		headers.add("responseDesc", successHeaderKV.get("successDesc"));
		headers.add(HttpHeaders.CONTENT_TYPE, successHeaderKV.get("contentType"));
		
		CompletableFuture<String> cf = cusService.insertCustomer(customer);
		
		String insertCustomer = null;
		try {
			insertCustomer = cf.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(insertCustomer);
	}
	
	// Update existing customer
	@PutMapping(value="", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateCustomer(@RequestBody Customer customer){
		HttpHeaders headers = new HttpHeaders();
		headers.add("responseCode", successHeaderKV.get("successCode"));
		headers.add("responseDesc", successHeaderKV.get("successDesc"));
		headers.add(HttpHeaders.CONTENT_TYPE, successHeaderKV.get("contentType"));
		
		CompletableFuture<String> cf = cusService.updateCustomer(customer);
		String updateCustomer = null;
		
		try {
			updateCustomer = cf.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(updateCustomer);
	}
	
	// Delete customer
	@DeleteMapping(value="", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteCustomer(@RequestParam("id") Long id){
		HttpHeaders headers = new HttpHeaders();
		headers.add("responseCode", successHeaderKV.get("successCode"));
		headers.add("responseDesc", successHeaderKV.get("successDesc"));
		headers.add(HttpHeaders.CONTENT_TYPE, successHeaderKV.get("contentType"));
		
		CompletableFuture<String> cf = cusService.deleteCustomer(id);
		String deleteCustomer = null;
		
		try {
			deleteCustomer = cf.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(deleteCustomer);
	}
}