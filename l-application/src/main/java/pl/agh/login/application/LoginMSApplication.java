package pl.agh.login.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(
        basePackages = {
                "pl.agh.login"
        }
)
@EnableEurekaClient
public class LoginMSApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginMSApplication.class, args);
    }

}
