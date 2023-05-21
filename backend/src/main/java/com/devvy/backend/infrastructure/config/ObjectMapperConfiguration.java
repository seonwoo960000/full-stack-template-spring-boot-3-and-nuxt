package com.devvy.backend.infrastructure.config;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.zalando.problem.jackson.ProblemModule;
import org.zalando.problem.violations.ConstraintViolationProblemModule;

@Configuration
public class ObjectMapperConfiguration {

    private final ProblemModule problemModule;
    private final ConstraintViolationProblemModule constraintViolationProblemModule;

    public ObjectMapperConfiguration(ProblemModule problemModule,
                                     ConstraintViolationProblemModule constraintViolationProblemModule) {
        this.problemModule = problemModule;
        this.constraintViolationProblemModule = constraintViolationProblemModule;
    }

    @Bean
    @Primary
    public Jackson2ObjectMapperBuilderCustomizer problemObjectMapperModules() {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.modules(
            problemModule,
            constraintViolationProblemModule
        );
    }
}
