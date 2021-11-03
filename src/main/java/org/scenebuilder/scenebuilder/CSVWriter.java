package org.scenebuilder.scenebuilder;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class CSVWriter {

    private static final String SAMPLE_CSV_FILE = "./categories.csv";

    public static void writeCategoryCSV(List<String> categories) throws IOException {
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE));

                //CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("categoryCount", "Name", "Designation", "Company"));
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
        ) {

            for (String category : categories) {
                csvPrinter.printRecord(category);
            }

            // text can be added as a list
            //csvPrinter.printRecord("3", "Tim cook", "CEO", "Apple");
            //csvPrinter.printRecord(Arrays.asList("4", "Mark Zuckerberg", "CEO", "Facebook"));

            // send buffered text to file
            csvPrinter.flush();
        }
    }
}
