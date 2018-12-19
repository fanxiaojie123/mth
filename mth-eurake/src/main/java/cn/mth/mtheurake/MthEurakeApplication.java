package cn.mth.mtheurake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MthEurakeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MthEurakeApplication.class, args);
    }

}

