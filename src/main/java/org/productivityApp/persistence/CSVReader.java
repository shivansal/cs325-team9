package org.productivityApp.persistence;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.productivityApp.settings.SettingsObject;
import org.productivityApp.todo.TodoTask;
import org.productivityApp.money.MoneyObject;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class CSVReader {

    public static ArrayList<String> readCategoryCSV() {
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
        } catch(Exception e) {

            System.out.println("File Not Found: Categories");
            categories.add("None");
        }

        return categories;
    }

    public static ArrayList<TodoTask> readTodoTasksCSV() {

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
        } catch (Exception e) {

            System.out.println("File Not Found: TodoTasks");
        }

        return todoTasks;
    }

    public static MoneyObject readMoneyCSV() {

        MoneyObject moneyObject = new MoneyObject();

        try (
                Reader reader = Files.newBufferedReader(Paths.get(CSVWriter.MONEY_CSV_FILE));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        ) {

            for (CSVRecord csvRecord : csvParser) {

                int i = 0;

                // netMoney
                double netMoney = 0;
                try {
                    netMoney = Double.parseDouble(csvRecord.get(i));
                } catch (Exception e) {
                    System.out.println("Invalid Net Money");
                    System.out.println(csvRecord.get(i));
                }

                moneyObject.setNetMoney(netMoney);
                i += 1;

                // moneyInSourcesCount
                int moneyInSourcesCount = 0;
                try {
                    moneyInSourcesCount = Integer.parseInt(csvRecord.get(i));
                } catch (Exception e) {
                    System.out.println("Invalid MoneyInSource count");
                    System.out.println(csvRecord.get(i));
                }
                i += 1;

                // moneyInSources
                int j;
                for(j = 0; j < (moneyInSourcesCount * 2); ++j) {

                    String description = csvRecord.get(i+j);
                    j += 1;

                    double value = 0;
                    try {
                        value = Double.parseDouble(csvRecord.get(i + j));
                    } catch (Exception e) {
                        System.out.println("Invalid moneyInSource value");
                        System.out.println(csvRecord.get(i+j));
                    }

                    moneyObject.addMoneyInSource(description, value);
                }
                i += j;

                // moneyOutSourcesCount
                int moneyOutSourcesCount = 0;
                try {
                    moneyOutSourcesCount = Integer.parseInt(csvRecord.get(i));
                } catch (Exception e) {
                    System.out.println("Invalid Money Out Source Count");
                    System.out.println(csvRecord.get(i+j));
                }
                i += 1;

                // moneyOutSources
                for(j = 0; j < (moneyOutSourcesCount * 2); ++j) {

                    String description = csvRecord.get(i+j);
                    j += 1;

                    double value = 0;
                    try {
                        value = Double.parseDouble(csvRecord.get(i + j));
                    } catch (Exception e) {
                        System.out.println("Invalid MoneyOutSource value");
                        System.out.println(csvRecord.get(i+j));
                    }

                    moneyObject.addMoneyOutSource(description, value);
                }
                i += j;

                // availableFunds
                double availableFunds = 0;
                try {
                    availableFunds = Double.parseDouble(csvRecord.get(i));
                } catch (Exception e) {
                    System.out.println("Invalid availableFunds");
                    System.out.println(csvRecord.get(i));
                }
                moneyObject.setAvailableFunds(availableFunds);
                i += 1;

                // transactionsCount
                int transactionsCount = 0;
                try {
                    transactionsCount = Integer.parseInt(csvRecord.get(i));
                } catch (Exception e) {
                    System.out.println("Invalid Transactions Count");
                    System.out.println(csvRecord.get(i));
                }
                i += 1;

                // transactions
                for(j = 0; j < (transactionsCount * 3); ++j) {

                    int year = Integer.parseInt(csvRecord.get(i + j));
                    j += 1;
                    int month = Integer.parseInt(csvRecord.get(i + j));
                    j += 1;
                    int day = Integer.parseInt(csvRecord.get(i + j));
                    j += 1;

                    LocalDate date = LocalDate.of(year, month, day);

                    String description = csvRecord.get(i+j);
                    j += 1;

                    double value = 0;
                    try {
                        value = Double.parseDouble(csvRecord.get(i + j));
                    } catch (Exception e) {
                        System.out.println("Invalid Transaction Value");
                        System.out.println(csvRecord.get(i+j));
                    }

                    moneyObject.addTransaction(date, description, value);
                }
            }
        } catch(Exception e) {
            System.out.println("File Not Found: Money");
        }

        return moneyObject;
    }

    public static SettingsObject readSettingsCSV() {

        return new SettingsObject();
    }


}
