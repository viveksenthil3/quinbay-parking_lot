package com.travel;

import com.github.cloudyrock.spring.v5.EnableMongock;
//import com.github.mongobee.Mongobee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;


//@ComponentScan(basePackages = {"com.travel.controller", "com.travel.entity", "com.travel.model", "com.travel.repository", "com.travel.service", "com.travel.service.impl"})
//@EntityScan("com.travel")
@SpringBootApplication
@EnableMongoRepositories("com.travel")
@EnableMongock
@EnableSwagger2
public class ParkingLotControllerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingLotControllerApplication.class, args);
	}

}
