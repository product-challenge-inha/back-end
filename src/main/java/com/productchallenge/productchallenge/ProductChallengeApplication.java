package com.productchallenge.productchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProductChallengeApplication {

	public static void main(String[] args) {
		System.out.println("실행을 무사히 시작합니다.");
		SpringApplication.run(ProductChallengeApplication.class, args);
	}

}
