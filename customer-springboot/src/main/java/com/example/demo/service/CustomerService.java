package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.Email;
import com.example.demo.service.Info;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository cusRepo;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	// Get customers
	public CompletableFuture<Collection<Customer>> getCustomers(){
		log.info("retrieving all customers");
		return cusRepo.getCustomers();
	}
	
	// Get customer by name
	public CompletableFuture<Collection<Customer>> getCustomersByName(String name){
		log.info("retrieving customers by name");
		return cusRepo.getCustomerByName(name);
	}
	
	// Get customers by address
	public CompletableFuture<Collection<Customer>> getCustomersByAddress(String address){
		log.info("retrieving customers by address");
		return cusRepo.getCustomersByAddress(address);
	}
	
	// Get customer by id
	public CompletableFuture<Customer> getCustomerById(Long id){
		log.info("retrieving customer by id");
		return cusRepo.getCustomerById(id);
	}
	
	// Insert new customer
	@Async
	public CompletableFuture<String> insertCustomer(Customer customer) throws InterruptedException {
		Email email = new Email(customer.getEmail());
		Info info = new Info(customer.getName());
		log.info("inserting new customer");
		cusRepo.save(customer);
		email.start();
		info.start();
		return CompletableFuture.supplyAsync(() -> "");
	}	
	
	// Update existing customer
	@Async
	public CompletableFuture<String> updateCustomer(Customer customer) {
		Email email = new Email(customer.getEmail());
		Info info = new Info(customer.getName());
		log.info("updating customer with id: "+customer.getId());
		cusRepo.save(customer);
		email.start();
		info.start();
		return CompletableFuture.supplyAsync(() -> "");
	}
	
	// Delete customer
	@Async
	public CompletableFuture<String> deleteCustomer(Long id) {
		log.info("deleting customer with id: "+String.valueOf(id));
		cusRepo.deleteById(id);
		return CompletableFuture.supplyAsync(() -> "");
	}
}