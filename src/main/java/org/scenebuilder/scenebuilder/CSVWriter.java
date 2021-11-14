package org.scenebuilder.scenebuilder;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class CSVWriter {

    private static final String CATEGORY_CSV_FILE = "./categories.csv";
    private static final String TODOTASKS_CSV_FILE = "./todoTasks.csv";


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
}
