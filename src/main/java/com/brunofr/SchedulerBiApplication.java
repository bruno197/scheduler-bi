package com.brunofr;

import com.brunofr.runners.CmdRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SchedulerBiApplication {

    public static void main(String [] args) {
        SpringApplication.run(SchedulerBiApplication.class, args);
    }

    @Bean
    public CmdRunner getCmdRunner() {
        return new CmdRunner();
    }
}
