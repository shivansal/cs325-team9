package org.scenebuilder.scenebuilder;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class CSVReader {

    public static ArrayList<String> readCategoryCSV() throws IOException {
        ArrayList<String> categories = new ArrayList<>();

        try (
                Reader reader = Files.newBufferedReader(Paths.get(CSVWriter.CATEGORY_CSV_FILE));
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

    public static ArrayList<TodoTask> readTodoTasksCSV() throws IOException {

        ArrayList<TodoTask> todoTasks = new ArrayList<>();

        try (
                Reader reader = Files.newBufferedReader(Paths.get(CSVWriter.TODOTASKS_CSV_FILE));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
                ) {

            for (CSVRecord csvRecord : csvParser) {

                String taskName = csvRecord.get(0);

                // task localdatetime

                int year = Integer.parseInt(csvRecord.get(1));
                int month = Integer.parseInt(csvRecord.get(2));
                int day = Integer.parseInt(csvRecord.get(3));
                int hour = Integer.parseInt(csvRecord.get(4));
                int minute = Integer.parseInt(csvRecord.get(5));

                LocalDate localDate = LocalDate.of(year, month, day);

                LocalTime localTime = LocalTime.of(hour, minute);

                String taskRecurringKey = csvRecord.get(6);

                boolean sun = Boolean.parseBoolean(csvRecord.get(7));
                boolean mon = Boolean.parseBoolean(csvRecord.get(8));
                boolean tue = Boolean.parseBoolean(csvRecord.get(9));
                boolean wed = Boolean.parseBoolean(csvRecord.get(10));
                boolean thu = Boolean.parseBoolean(csvRecord.get(11));
                boolean fri = Boolean.parseBoolean(csvRecord.get(12));
                boolean sat = Boolean.parseBoolean(csvRecord.get(13));

                boolean[] taskRecurringDays = {sun, mon, tue, wed, thu, fri, sat};

                String taskPriority = csvRecord.get(14);

                int numCategories = Integer.parseInt(csvRecord.get(15));

                ArrayList<String> taskCategories = new ArrayList<>();
                int i;
                for(i = 0; i < numCategories; ++i) {
                    taskCategories.add(csvRecord.get(16 + i));
                }

                TodoTask todoTask = new TodoTask(taskName, localDate, localTime, taskRecurringKey, taskRecurringDays, taskPriority, taskCategories);
                todoTasks.add(todoTask);
            }
        }

        return todoTasks;
    }

    public static MoneyObject readMoneyCSV() throws IOException {

        MoneyObject moneyObject = new MoneyObject();

        try (
                Reader reader = Files.newBufferedReader(Paths.get(CSVWriter.TODOTASKS_CSV_FILE));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        ) {

            for (CSVRecord csvRecord : csvParser) {

                int i = 0;
                // netMoney
                double netMoney = Double.parseDouble(csvRecord.get(i));
                moneyObject.setNetMoney(netMoney);
                i += 1;

                // moneyInSourcesCount
                int moneyInSourcesCount = Integer.parseInt(csvRecord.get(i));
                i += 1;

                // moneyInSources
                int j;
                for(j = 0; j < (moneyInSourcesCount * 2); ++j) {

                    String description = csvRecord.get(i+j);
                    j += 1;

                    double value = Double.parseDouble(csvRecord.get(i + j));

                    moneyObject.addMoneyInSource(description, value);
                }

                i += j;

                // moneyOutSourcesCount
                int moneyOutSourcesCount = Integer.parseInt(csvRecord.get(i));
                i += 1;

                // moneyOutSources
                for(j = 0; j < (moneyOutSourcesCount * 2); ++j) {

                    String description = csvRecord.get(i+j);
                    j += 1;

                    double value = Double.parseDouble(csvRecord.get(i + j));

                    moneyObject.addMoneyOutSource(description, value);
                }

                i += j;

                // availableFunds
                double availableFunds = Double.parseDouble(csvRecord.get(i));
                i += 1;

                // transactionsCount
                int transactionsCount = Integer.parseInt(csvRecord.get(i));
                i += 1;

                // transactions
                for(j = 0; j < (transactionsCount * 3); ++j) {

                    LocalDate date;
                    String description = csvRecord.get(i+j);
                    j += 1;

                    double value = Double.parseDouble(csvRecord.get(i + j));

                    moneyObject.addMoneyOutSource(description, value);
                }


            }
        }

        return moneyObject;
    }


}
