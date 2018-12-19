package cn.axy.xc.xcsleuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class XcSleuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(XcSleuthApplication.class, args);
    }
}
