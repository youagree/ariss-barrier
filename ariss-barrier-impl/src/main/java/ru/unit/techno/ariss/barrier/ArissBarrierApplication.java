package ru.unit.techno.ariss.barrier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ArissBarrierApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArissBarrierApplication.class, args);
    }
}
