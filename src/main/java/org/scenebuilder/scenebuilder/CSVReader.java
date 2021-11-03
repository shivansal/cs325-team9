package org.scenebuilder.scenebuilder;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CSVReader {

    private static final String SAMPLE_CSV_FILE_PATH = "./categories.csv";

    public static ArrayList<String> readCategoryCSV() throws IOException {
        ArrayList<String> categories = new ArrayList<>();

        try (
                Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        ) {
            for (CSVRecord csvRecord : csvParser) {

                // Accessing Values by Column Index
                String categoryName = csvRecord.get(0);
                categories.add(categoryName);
            }
        }

        return categories;
    }
}
