package com.brunofr.tasks;

import com.brunofr.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Scope("singleton")
@EnableScheduling
public class ExportProductsScheduled {
    private static final String TIME_ZONE = "America/Sao_Paulo";
    private final AtomicInteger counter = new AtomicInteger(0);

    private final ProductService productService;

    @Autowired
    public ExportProductsScheduled(final ProductService productService) {
        this.productService = productService;
    }

    @Scheduled(cron="0/1 * * * * *", zone = TIME_ZONE)
    public void exportProducts() {
        int jobId = counter.incrementAndGet();
        System.out.println("Job @ fixed rate " + new Date() + ", jobId: " + jobId);
        System.out.println(this.productService.getAll());
    }
}
