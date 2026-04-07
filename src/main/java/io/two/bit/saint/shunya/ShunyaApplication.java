package io.two.bit.saint.shunya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.openapitools.api", "io.two.bit.saint.shunya"})
public class ShunyaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShunyaApplication.class, args);
    }

}
