package ru.unit.techno.ariss.barrier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableEurekaClient
@SpringBootApplication
@EntityScan(basePackages = {"ru.unit.techno.ariss.log.action.lib.entity"})
@EnableJpaRepositories(basePackages = {"ru.unit.techno.ariss.log.action.lib.repository"})
@EnableFeignClients(basePackages = {"ru.unit.techno.device.registration.api"})
public class ArissBarrierApplication {

    //TODO разобраться почему при отключении сервиса барьера не сразу эврика отдает 503
    public static void main(String[] args) {
        SpringApplication.run(ArissBarrierApplication.class, args);
    }
}
