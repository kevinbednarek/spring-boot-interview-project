package com.precisely.rapidcx.interview.adapter.out;

import com.precisely.rapidcx.interview.application.port.out.PriceReportPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Service
public class PriceReportAdapter implements PriceReportPort {
    private final ResourceLoader resourceLoader;

    @Autowired
    public PriceReportAdapter(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public InputStream streamPriceReport() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:nadac-comparison-11-19-2025-small.csv");
        return resource.getInputStream();
    }
}
