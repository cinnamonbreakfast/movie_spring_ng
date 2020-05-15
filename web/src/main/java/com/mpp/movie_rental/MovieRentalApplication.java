package com.mpp.movie_rental;

import com.mpp.movie_rental.config.WebConfig;
import config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = {"domain", "repository", "service", "com.mpp.movie_rental"})
@EntityScan("domain")
@Import({Config.class, WebConfig.class})
public class MovieRentalApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieRentalApplication.class, args);
    }

}
