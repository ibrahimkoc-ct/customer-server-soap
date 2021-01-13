package com.ba.controller;

import java.util.ArrayList;

import com.ba.dto.CustomerDTO;
import com.ba.model.Customer;
import com.ba.service.CustomerService;

public class CustomerController {
	CustomerService service = new CustomerService();
	
	public Customer addCustomer(CustomerDTO dto) {
		return service.addCustomer(dto);
	}
	public boolean deleteCustomer(int id) {
		return service.deleteCustomer(id);
	}
	public CustomerDTO updateCustomer(CustomerDTO dto) {
		return service.updateCustomer(dto);
	}
	public ArrayList<Customer> getAllCustomer() {
		return service.getAllCustomer();
	
	}
}
