package com.precisely.rapidcx.interview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class PriceReportGenerator {
    private final ResourceLoader resourceLoader;

    @Autowired
    public PriceReportGenerator(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public InputStream streamPriceReport() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:nadac-comparison-11-19-2025-small.csv");
        return resource.getInputStream();
    }
}
