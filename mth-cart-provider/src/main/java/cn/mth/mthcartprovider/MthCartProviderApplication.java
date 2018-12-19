package cn.mth.mthcartprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MthCartProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MthCartProviderApplication.class, args);
    }

}

