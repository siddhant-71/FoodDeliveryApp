package com.siddhant.foodDelivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class FoodDeliveryApplication {

	public static void main(String[] args) {

		SpringApplication.run(FoodDeliveryApplication.class, args);
		System.out.println("Food Delivery App Has been Started at "+LocalDateTime.now());
	}

}
