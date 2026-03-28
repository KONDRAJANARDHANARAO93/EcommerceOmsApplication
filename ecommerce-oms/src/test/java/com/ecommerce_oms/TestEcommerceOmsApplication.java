package com.ecommerce_oms;

import org.springframework.boot.SpringApplication;

public class TestEcommerceOmsApplication {

	public static void main(String[] args) {
		SpringApplication.from(EcommerceOmsApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
