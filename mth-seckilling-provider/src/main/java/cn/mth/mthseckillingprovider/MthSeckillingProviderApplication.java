package cn.mth.mthseckillingprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MthSeckillingProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MthSeckillingProviderApplication.class, args);
    }

}

