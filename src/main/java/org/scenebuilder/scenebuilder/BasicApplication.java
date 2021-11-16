package org.scenebuilder.scenebuilder;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BasicApplication extends Application {

    private static ArrayList<String> categoryTypes = new ArrayList<>();
    private static ArrayList<TodoTask> todoTasks = new ArrayList<>();
    private static MoneyObject moneyObject;

    @Override
    public void start(Stage stage) throws Exception {

        stage.setOnCloseRequest(e -> {
            try {
                CSVWriter.writeCategoryCSV(categoryTypes);
                CSVWriter.writeTodoTaskCSV(todoTasks);
                //CSVWriter.writeMoneyCSV(moneyObject);
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

    // getters
    public static ArrayList<String> getCategoryTypes() {
        return categoryTypes;
    }
    public static ArrayList<TodoTask> getTodoTasks() {
        return todoTasks;
    }

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

    private static class DateComparator implements Comparator<TodoTask> {

        @Override
        public int compare(TodoTask t1, TodoTask t2) {

            LocalDateTime dateTime1 = LocalDateTime.of(t1.getTaskDate(), t1.getTaskTime());
            LocalDateTime dateTime2 = LocalDateTime.of(t2.getTaskDate(), t2.getTaskTime());
            return dateTime1.compareTo(dateTime2);
        }
    }

    public static void sortTodoTasksByDate() {
        todoTasks.sort(new DateComparator());
    }

    public static void main(String[] args) throws Exception {

        // load categories and todoTasks from file
        categoryTypes = CSVReader.readCategoryCSV();
        todoTasks = CSVReader.readTodoTasksCSV();

        launch();
    }


}