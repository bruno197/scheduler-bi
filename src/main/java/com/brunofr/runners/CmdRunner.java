package com.brunofr.runners;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;

@Order(1)
public class CmdRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.print("Rodando....");
    }
}
