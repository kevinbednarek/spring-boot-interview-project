package com.precisely.rapidcx.interview.application.port.out;

import java.io.IOException;
import java.io.InputStream;

public interface PriceReportPort {
    InputStream streamPriceReport() throws IOException;
}
