package cn.mth.mthevaluateprovider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@MapperScan("cn.mth.mthseckillingprovider.dao")
public class MthEvaluateProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MthEvaluateProviderApplication.class, args);
    }

}

