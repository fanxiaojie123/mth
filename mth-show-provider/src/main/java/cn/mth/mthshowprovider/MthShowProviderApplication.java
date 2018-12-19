package cn.mth.mthshowprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MthShowProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MthShowProviderApplication.class, args);
    }

}

