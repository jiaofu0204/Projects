package edu.northeastern.cs5200;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("edu.northeastern.cs5200.mapper")
public class OnlineshoppingApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineshoppingApplication.class, args);
		System.out.println("success");
	}

}
