package com.precisely.rapidcx.interview;

import com.opencsv.bean.CsvToBeanBuilder;
import com.precisely.rapidcx.interview.models.PriceChangeEntry;
import com.precisely.rapidcx.interview.models.PriceReportRequest;
import com.precisely.rapidcx.interview.models.PriceReportResponse;
import com.precisely.rapidcx.interview.models.PriceReportRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

@Service
public class PriceReportService {
    private final PriceReportGenerator priceReportGenerator;

    @Autowired
    public PriceReportService(PriceReportGenerator priceReportGenerator) {
        this.priceReportGenerator = priceReportGenerator;
    }

    public PriceReportResponse generatePriceReport(PriceReportRequest request) {
        try (InputStream inputStream = priceReportGenerator.streamPriceReport(); InputStreamReader reader = new InputStreamReader(inputStream)) {
            Iterator<PriceReportRow> rows = new CsvToBeanBuilder<PriceReportRow>(reader)
                    .withType(PriceReportRow.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .iterator();

            PriorityQueue<PriceReportRow> topIncreases = new PriorityQueue<>(Comparator.comparingDouble(this::getChange).reversed());
            PriorityQueue<PriceReportRow> topDecreases = new PriorityQueue<>(Comparator.comparingDouble(this::getChange));

            while (rows.hasNext()) {
                PriceReportRow row = rows.next();

                topIncreases.offer(row);
                if (topIncreases.size() >= request.getNumberOfRecords()) {
                    topIncreases.poll();
                }
                topDecreases.offer(row);
                if (topDecreases.size() >= request.getNumberOfRecords()) {
                    topDecreases.poll();
                }
            }

            PriceReportResponse response = new PriceReportResponse();

            response.setYear(request.getYear());
            response.setNumberOfRecords(request.getNumberOfRecords());
            response.setTopPriceIncreases(topIncreases.stream()
                    .map(this::mapToPriceChangeEntry)
                    .sorted(Comparator.comparingDouble(PriceChangeEntry::getPriceChange).reversed())
                    .toList());
            response.setTopPriceDecreases(topDecreases.stream()
                    .map(this::mapToPriceChangeEntry)
                    .sorted(Comparator.comparingDouble(PriceChangeEntry::getPriceChange))
                    .toList());

            return response;
        } catch (Exception e) {
            System.out.println("Error processing price report\n" + e);
            throw new RuntimeException(e);
        }
    }

    private Double getChange(PriceReportRow row) {
        String newPrice = row.getNewNadacPerUnit();
        String oldPrice = row.getOldNadacPerUnit();

        return Double.parseDouble(newPrice) - Double.parseDouble(oldPrice);
    }

    private PriceChangeEntry mapToPriceChangeEntry(PriceReportRow row) {
        PriceChangeEntry entry = new PriceChangeEntry();

        entry.setName(row.getNdcDescription());
        entry.setPriceChange(getChange(row));
        entry.setEffectiveDate(LocalDate.parse(row.getEffectiveDate(), DateTimeFormatter.ofPattern("MM/dd/yyyy")));

        return entry;
    }
}
