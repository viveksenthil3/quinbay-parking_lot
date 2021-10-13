package com.travel.config;

//import com.github.mongobee.Mongobee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParkingInit {
    @Value("${spring.data.mongodb.host}")
    private String MONGO_DB_HOST;

    @Value("${spring.data.mongodb.port}")
    private String MONGO_DB_PORT;

    @Value("${spring.data.mongodb.database}")
    private String MONGO_DB_DATABASE;


//    @Bean
//    public Mongobee mongobee(){
//        System.out.println("mongobee config");
//        Mongobee runner = new Mongobee(String.format("mongodb://%s:%d/%s", MONGO_DB_HOST, MONGO_DB_PORT, MONGO_DB_DATABASE));
//        runner.setDbName(MONGO_DB_DATABASE);         // host must be set if not set in URI
//        runner.setChangeLogsScanPackage(
//                "com.travel.mongoChangelogs"); // the package to be scanned for changesets
//
//
//        return runner;
//    }

}
