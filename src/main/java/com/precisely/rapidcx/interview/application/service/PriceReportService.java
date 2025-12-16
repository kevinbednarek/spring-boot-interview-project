package com.precisely.rapidcx.interview.application.service;

import com.opencsv.bean.CsvToBeanBuilder;
import com.precisely.rapidcx.interview.application.port.in.PriceReportUseCase;
import com.precisely.rapidcx.interview.application.port.out.PriceReportPort;
import com.precisely.rapidcx.interview.domain.PriceReportRow;
import com.precisely.rapidcx.interview.model.DrugEntry;
import com.precisely.rapidcx.interview.model.RequestObject;
import com.precisely.rapidcx.interview.model.ResponseObject;
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
public class PriceReportService implements PriceReportUseCase {
    private final PriceReportPort port;

    @Autowired
    public PriceReportService(PriceReportPort port) {
        this.port = port;
    }

    @Override
    public ResponseObject generatePriceReport(RequestObject requestObject) {
        try (InputStream inputStream = port.streamPriceReport(); InputStreamReader reader = new InputStreamReader(inputStream)) {
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
                if (topIncreases.size() >= requestObject.getNumberOfRecords()) {
                    topIncreases.poll();
                }
                topDecreases.offer(row);
                if (topDecreases.size() >= requestObject.getNumberOfRecords()) {
                    topDecreases.poll();
                }
            }

            ResponseObject responseObject = new ResponseObject();

            responseObject.setYear(requestObject.getYear());
            responseObject.setNumberOfRecords(requestObject.getNumberOfRecords());
            responseObject.setTopPriceIncreases(topIncreases.stream()
                    .map(this::mapToDrugEntry)
                    .sorted(Comparator.comparingDouble(DrugEntry::getPriceChange).reversed())
                    .toList());
            responseObject.setTopPriceDecreases(topDecreases.stream()
                    .map(this::mapToDrugEntry)
                    .sorted(Comparator.comparingDouble(DrugEntry::getPriceChange))
                    .toList());

            return responseObject;
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

    private DrugEntry mapToDrugEntry(PriceReportRow row) {
        DrugEntry drugEntry = new DrugEntry();

        drugEntry.setName(row.getNdcDescription());
        drugEntry.setPriceChange(getChange(row));
        drugEntry.setEffectiveDate(LocalDate.parse(row.getEffectiveDate(), DateTimeFormatter.ofPattern("MM/dd/yyyy")));

        return drugEntry;
    }
}
