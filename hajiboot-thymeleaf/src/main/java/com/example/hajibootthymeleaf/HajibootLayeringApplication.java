package com.example.hajibootthymeleaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import domain.Customer;
import service.CustomerService;

@SpringBootApplication
public class HajibootLayeringApplication implements CommandLineRunner{
	@Autowired
	CustomerService customerService;
	
	@Override
		public  void run(String... strings) throws Exception{
		// Data追加
		customerService.save(new Customer(1, "Nobita", "Nobi"));
		
		// Data表示
		customerService.findAll()
		.forEach(System.out::println);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(HajibootLayeringApplication.class, args);
	}

}