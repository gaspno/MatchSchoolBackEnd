package com.mathschool.MathSchoolBack;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MathSchoolBackApplication  implements CommandLineRunner {
	@Override
	public void run(String... args) throws Exception {
		System.out.println("-------------------------------");
		System.out.println(System.getenv("USER_DB"));
		System.out.println("-------------------------------");
		System.out.println(System.getenv("USER"));
		System.out.println("-------------------------------");
		System.out.println(System.getenv("PASSWORD_DB"));
		System.out.println("-------------------------------");
		System.out.println(System.getenv("PASSWORD"));
		System.out.println("-------------------------------");
	}

	public static void main(String[] args) {
		SpringApplication.run(MathSchoolBackApplication.class, args);
	}
}
