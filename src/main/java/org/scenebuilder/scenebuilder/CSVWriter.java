package org.scenebuilder.scenebuilder;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class CSVWriter {

    public static final String CATEGORY_CSV_FILE = "./categories.csv";
    public static final String TODOTASKS_CSV_FILE = "./todoTasks.csv";
    public static final String MONEY_CSV_FILE = "./money.csv";

    public static void writeCategoryCSV(List<String> categories) throws IOException {
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(CATEGORY_CSV_FILE));

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

    public static void writeTodoTaskCSV(List<TodoTask> todoTasks) throws IOException {
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(TODOTASKS_CSV_FILE));

                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
                ) {

            for (TodoTask todoTask : todoTasks) {

                ArrayList<String> tempRow = new ArrayList<>();

                // task name
                tempRow.add(todoTask.getTaskName());

                // taskDateTime

                // year
                tempRow.add(Integer.toString(todoTask.getTaskDate().getYear()));

                // month
                tempRow.add(Integer.toString(todoTask.getTaskDate().getMonthValue()));

                // day
                tempRow.add(Integer.toString(todoTask.getTaskDate().getDayOfMonth()));

                // hours, filler
                tempRow.add(Integer.toString(todoTask.getTaskTime().getHour()));

                // minutes, filler
                tempRow.add(Integer.toString(todoTask.getTaskTime().getMinute()));

                // taskRecurringKey
                tempRow.add(todoTask.getTaskRecurringKey());

                // taskRecurringDays
                tempRow.add(Boolean.toString(todoTask.getTaskRecurringDays()[0]));
                tempRow.add(Boolean.toString(todoTask.getTaskRecurringDays()[1]));
                tempRow.add(Boolean.toString(todoTask.getTaskRecurringDays()[2]));
                tempRow.add(Boolean.toString(todoTask.getTaskRecurringDays()[3]));
                tempRow.add(Boolean.toString(todoTask.getTaskRecurringDays()[4]));
                tempRow.add(Boolean.toString(todoTask.getTaskRecurringDays()[5]));
                tempRow.add(Boolean.toString(todoTask.getTaskRecurringDays()[6]));

                // task Priority
                tempRow.add(todoTask.getTaskPriority());

                // numCategories
                tempRow.add(Integer.toString(todoTask.getTaskCategories().size()));

                // task categories - numCategories iterations
                for(int i = 0; i < todoTask.getTaskCategories().size(); ++i) {
                    // add a category
                    tempRow.add(todoTask.getTaskCategories().get(i));
                }

                // add row to csv file
                csvPrinter.printRecord(tempRow);
            }

            // send buffered text to file
            csvPrinter.flush();
        }
    }

    public static void writeMoneyCSV(MoneyObject moneyObject) throws IOException {

        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(MONEY_CSV_FILE));
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
        ) {

            // text can be added as a list
            //csvPrinter.printRecord("3", "Tim cook", "CEO", "Apple");
            //csvPrinter.printRecord(Arrays.asList("4", "Mark Zuckerberg", "CEO", "Facebook"));

            //ArrayList<String> tempRow = new ArrayList<>();
            // add row to csv file
            //csvPrinter.printRecord(tempRow);

            final ArrayList<String> tempRow = new ArrayList<>();

            // netMoney
            tempRow.add(Double.toString(moneyObject.getNetMoney()));

            // moneyInSourcesCount
            tempRow.add(Integer.toString(moneyObject.getMoneyInSources().size()));

            // moneyInSources
            moneyObject.getMoneyInSources().forEach((moneyInSource) -> {

                // write description
                tempRow.add(moneyInSource.getDescription());

                // write value
                tempRow.add(Double.toString(moneyInSource.getValue()));
            });

            // moneyOutSourcesCount
            tempRow.add(Integer.toString(moneyObject.getMoneyOutSources().size()));

            // moneyOutSources
            moneyObject.getMoneyOutSources().forEach((moneyOutSource) -> {

                // write description
                tempRow.add(moneyOutSource.getDescription());

                // write value
                tempRow.add(Double.toString(moneyOutSource.getValue()));
            });

            // availableFunds
            tempRow.add(Double.toString(moneyObject.getAvailableFunds()));

            // transactionsCount
            tempRow.add(Integer.toString(moneyObject.getTransactions().size()));

            // transactions
            moneyObject.getTransactions().forEach((transaction) -> {

                // write date
                LocalDate date = transaction.getDate();
                int year = date.getYear();
                int month = date.getMonthValue();
                int day = date.getDayOfMonth();
                tempRow.add(Integer.toString(year));
                tempRow.add(Integer.toString(month));
                tempRow.add(Integer.toString(day));

                // write description
                tempRow.add(transaction.getDescription());

                // write value
                tempRow.add(Double.toString(transaction.getValue()));
            });

            csvPrinter.printRecord(tempRow);

            // send buffered text to file
            csvPrinter.flush();
        }
    }
}
