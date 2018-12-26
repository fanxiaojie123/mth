package cn.mth.mthuserprovider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@MapperScan("cn.mth.mthuserprovider.dao")
public class MthUserProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MthUserProviderApplication.class, args);
    }

}

