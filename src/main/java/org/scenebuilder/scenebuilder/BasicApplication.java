package org.scenebuilder.scenebuilder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class BasicApplication extends Application {


    private static ArrayList<String> taskCategories = new ArrayList<>();
    private static ArrayList<TodoTask> todoTasks = new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception {

        // load fxml file (which specifies the controller)
        Parent root = FXMLLoader.load(getClass().getResource("todoFXML.fxml"));

        // create new instance of the controller class
        // inject all fx:id tagged objects from fxml file
        // and marked with @FXML annotation in controller

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void loadCategories() {
        taskCategories.add("None");
        taskCategories.add("Assignments");
        taskCategories.add("Other");
    }

    public static void loadTodoTasks() {

        LocalDateTime rightNow = LocalDateTime.now();

        boolean[] ar = {false, false, false, false, false, false, false};
        TodoTask task1 = new TodoTask("Task 1", rightNow, "Never", ar,"Medium", new ArrayList<String>());
        TodoTask task2 = new TodoTask("Task 2", rightNow, "Never", ar,"Medium", new ArrayList<String>());

        todoTasks.add(task1);
        todoTasks.add(task2);
    }

    public static ArrayList<String> getTaskCategories() {
        return taskCategories;
    }

    public static ArrayList<TodoTask> getTodoTasks() {
        return todoTasks;
    }

    public static ArrayList<TodoTask> addTodoTask(TodoTask task) {

        todoTasks.add(task);
        return todoTasks;
    }

    public static void setTodoTask(int i, TodoTask task) {
        todoTasks.set(i, task);
    }

    public static ArrayList<TodoTask> removeTodoTask(TodoTask task) {

        todoTasks.remove(task);
        return todoTasks;
    }

    public static void main(String[] args) {
        loadCategories();
        loadTodoTasks();
        launch();
    }
}