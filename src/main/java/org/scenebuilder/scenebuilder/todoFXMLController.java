package org.scenebuilder.scenebuilder;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class todoFXMLController {

    @FXML
    VBox taskVBox;
    @FXML
    Button removeTaskButton;

    private Stage stage;

    private ArrayList<TodoTask> todoTasks = new ArrayList<>();

    private int selectedTask = -1;

    public void initialize() {

        // get list of tasks
        todoTasks = BasicApplication.getTodoTasks();

        // add tasks to todolist
        todoTasks.forEach((n) -> addTaskNode(n));
    }

    public void addTaskNode(TodoTask task) {

        HBox tempHBox = new HBox();
        tempHBox.setAlignment(Pos.CENTER_LEFT);

        Circle tempCircle = new Circle();
        tempCircle.setRadius(5.0);
        tempCircle.setFill(Color.BLACK);

        HBox.setMargin(tempCircle, new Insets(5, 5, 5, 5));

        Label tempLabel = new Label(task.getTaskName());
        tempLabel.setPrefHeight(30.0);
        tempLabel.setPrefWidth(360.0);
        tempLabel.setFont(new Font(18));
        tempLabel.setStyle("-fx-border-color: black;");
        tempLabel.setPadding(new Insets(0, 0, 0, 10));

        HBox.setMargin(tempLabel, new Insets(10,10,10,10));

        Label tempLabelDate = new Label("Date");
        tempLabelDate.setPrefHeight(30.0);
        tempLabelDate.setPrefWidth(150);
        tempLabelDate.setFont(new Font(18));
        tempLabelDate.setStyle("-fx-border-color: black;");
        tempLabelDate.setPadding(new Insets(0, 0, 0, 10));

        HBox.setMargin(tempLabelDate, new Insets(0, 10, 0, 10));

        tempHBox.getChildren().addAll(tempCircle, tempLabel, tempLabelDate);

        taskVBox.getChildren().add(tempHBox);

        tempHBox.setOnMouseClicked(event -> {

            // deselect all other tasks
            taskVBox.getChildren().forEach((n) -> n.setStyle(null));

            // select clicked on task
            tempHBox.setStyle("-fx-border-color: blue;");

            // enable remove button
            removeTaskButton.setDisable(false);

            // update selected task
            selectedTask = taskVBox.getChildren().indexOf(tempHBox);
        });
    }

    public void removeTaskNode(ActionEvent event) {

        // remove node
        taskVBox.getChildren().remove(selectedTask);

        // remove task from task list
        BasicApplication.removeTodoTask(todoTasks.get(selectedTask));
        //todoTasks.remove(selectedTask);

        // no selected task exists
        selectedTask = -1;
        removeTaskButton.setDisable(true);
    }

    public void switchToMoneyScene(MouseEvent event) throws IOException {
        switchScene(event, "moneyFXML.fxml");
    }

    public void newTaskEvent(ActionEvent event) throws IOException {
        switchScene(event, "newTaskFXML.fxml");
    }

    public void switchScene(ActionEvent event, String nextScene) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(nextScene));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void switchScene(MouseEvent event, String nextScene) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(nextScene));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void centerStage() {
        double width = stage.getScene().getWidth();
        double height = stage.getScene().getHeight();
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - width) / 2);
        stage.setY((screenBounds.getHeight() - height) / 2);
    }
}