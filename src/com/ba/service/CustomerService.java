package com.ba.service;

import java.util.ArrayList;

import com.ba.converter.CustomerConverter;
import com.ba.dao.CustomerDAO;
import com.ba.dto.CustomerDTO;
import com.ba.model.Customer;

public class CustomerService {
	CustomerDAO customerDAO = new CustomerDAO();
	public Customer addCustomer(CustomerDTO dto) {
		Customer customer= CustomerConverter.toEntity(dto);
		try {
			customerDAO.registerCustomer(customer);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customer;
	}
	public boolean deleteCustomer(int id) {
		customerDAO.deleteCustomer(id);
		return true;
	}
	public ArrayList<Customer> getAllCustomer(){
		return customerDAO.getAllCustomer();
	}
	public CustomerDTO updateCustomer(CustomerDTO dto) {
		Customer customer= CustomerConverter.toEntity(dto);
		customerDAO.updateCustomer(customer);
		return dto;
		
	}
	
}
