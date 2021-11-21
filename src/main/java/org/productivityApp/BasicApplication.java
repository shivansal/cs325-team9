package org.productivityApp;

import javafx.application.Application;
import javafx.stage.Stage;
import org.productivityApp.money.MoneyObject;
import org.productivityApp.money.MoneyObjectController;
import org.productivityApp.money.transactions.TransactionController;
import org.productivityApp.persistence.CSVReader;
import org.productivityApp.persistence.CSVWriter;
import org.productivityApp.todo.TodoController;
import org.productivityApp.todo.TodoTask;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

public class BasicApplication extends Application {

    private static ArrayList<String> categoryTypes = new ArrayList<>();
    private static ArrayList<TodoTask> todoTasks = new ArrayList<>();
    private static MoneyObject moneyObject;

    @Override
    public void start(Stage stage) {

        stage.setOnCloseRequest(e -> {
            try {
                CSVWriter.writeCategoryCSV(categoryTypes);
                CSVWriter.writeTodoTaskCSV(todoTasks);
                CSVWriter.writeMoneyCSV(moneyObject);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        TodoController controller = new TodoController();
        controller.initialize(stage);
    }


    // setters
    public static void setCategoryTypes(ArrayList<String> categories) {
        categoryTypes = new ArrayList<>(categories);
    }
    public static void setTodoTask(int i, TodoTask task) {
        todoTasks.set(i, task);
    }
    public static void setMoneyObject(MoneyObject moneyObject) { BasicApplication.moneyObject = moneyObject; }


    // getters
    public static ArrayList<String> getCategoryTypes() {
        return categoryTypes;
    }
    public static ArrayList<TodoTask> getTodoTasks() {
        return todoTasks;
    }
    public static MoneyObject getMoneyObject() { return moneyObject; }


    // modifiers
    public static ArrayList<TodoTask> addTodoTask(TodoTask task) {

        todoTasks.add(task);
        return todoTasks;
    }
    public static ArrayList<TodoTask> removeTodoTask(TodoTask task) {

        todoTasks.remove(task);
        return todoTasks;
    }
    public static ArrayList<TodoTask> removeTodoTask(int taskIndex) {
        todoTasks.remove(taskIndex);
        return todoTasks;
    }


    // equality between dates
    private static class DateComparator implements Comparator<TodoTask> {

        @Override
        public int compare(TodoTask t1, TodoTask t2) {

            LocalDateTime dateTime1 = LocalDateTime.of(t1.getTaskDate(), t1.getTaskTime());
            LocalDateTime dateTime2 = LocalDateTime.of(t2.getTaskDate(), t2.getTaskTime());
            return dateTime1.compareTo(dateTime2);
        }
    }

    // sort todotasks by date
    public static void sortTodoTasksByDate() {
        todoTasks.sort(new DateComparator());
    }

    private static MoneyObject getFillerMoneyObject() {

        MoneyObject moneyObject = new MoneyObject();
        return moneyObject;
    }

    public static void main(String[] args) throws Exception {

        // load categories and todoTasks from file
        categoryTypes = CSVReader.readCategoryCSV();
        todoTasks = CSVReader.readTodoTasksCSV();

        try {

            moneyObject = CSVReader.readMoneyCSV();
        } catch (Exception e) {

            moneyObject = getFillerMoneyObject();
        }

        launch();
    }


}