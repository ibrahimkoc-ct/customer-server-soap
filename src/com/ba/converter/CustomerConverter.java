package com.ba.converter;

import com.ba.dto.CustomerDTO;
import com.ba.model.Customer;

public class CustomerConverter {

	public static Customer toEntity(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		customer.setAddress(customerDTO.getAddress());
		customer.setId(customerDTO.getId());
		customer.setName(customerDTO.getName());
		customer.setPhoneNumber(customerDTO.getPhoneNumber());
		customer.setSurname(customerDTO.getSurname());
	return customer;
	}
	public static CustomerDTO toDTO(Customer customer) {
		CustomerDTO dto= new CustomerDTO();
		dto.setAddress(customer.getAddress());
		dto.setId(customer.getId());
		dto.setName(customer.getName());
		dto.setPhoneNumber(customer.getPhoneNumber());
		dto.setSurname(customer.getSurname());
		return dto;
				
	}
	
}
