package com.ssafy.funding;

import com.ssafy.funding.repository.MovieSearchRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@SpringBootApplication
@EnableJpaRepositories(
        excludeFilters =
                @ComponentScan.Filter(
                        type = FilterType.ASSIGNABLE_TYPE,
                        classes = MovieSearchRepository.class))
public class FundingApplication {

    public static void main(String[] args) {
        SpringApplication.run(FundingApplication.class, args);
    }
}
