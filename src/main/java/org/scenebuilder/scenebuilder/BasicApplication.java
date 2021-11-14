package org.scenebuilder.scenebuilder;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.time.LocalDate;
import java.util.ArrayList;

public class BasicApplication extends Application {

    private static ArrayList<String> categoryTypes = new ArrayList<>();
    private static ArrayList<TodoTask> todoTasks = new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception {

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent e) {
                try {
                    CSVWriter.writeCategoryCSV(categoryTypes);
                    CSVWriter.writeTodoTaskCSV(todoTasks);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
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

    public static void main(String[] args) throws Exception {

        // load categories and todoTasks from file
        categoryTypes = CSVReader.readCategoryCSV();
        todoTasks = CSVReader.readTodoTasksCSV();

        launch();
    }


}