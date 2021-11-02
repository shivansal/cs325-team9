package org.scenebuilder.scenebuilder;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
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
import javafx.scene.control.ComboBox;
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
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class todoFXMLController {

    @FXML
    VBox taskVBox;
    @FXML
    Button removeTaskButton;
    @FXML
    Button viewTaskButton;
    @FXML
    Label currentTimeLabel;
    @FXML
    Label currentDateLabel;
    @FXML
    ComboBox categoryComboBox;

    private Stage stage;

    private ArrayList<TodoTask> todoTasks = new ArrayList<>();

    private int selectedTask = -1;

    public void initialize() {

        Locale locale = new Locale("en", "US");
        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(locale);

        dateFormatSymbols.setWeekdays(new String[]{
                "Unused",
                "Sunday",
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "Saturday",
        });

        String pattern = "EEEEE, MMMMM dd, yyyy";
        String patternTime = "hh:mm aa";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, dateFormatSymbols);
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat(patternTime);
        String date = simpleDateFormat.format(new Date());
        String time = simpleTimeFormat.format(new Date());

        // update date - top left
        currentDateLabel.setText(date.toString());

        // update time - top left
        currentTimeLabel.setText(time.toString());

        // populate category drop down
        List<String> dropDownItems = BasicApplication.getTaskCategories();
        categoryComboBox.setItems(FXCollections.observableList(dropDownItems));

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

        Label tempLabelDate = new Label();
        tempLabelDate.setText(LocalDateTimeToString(task.getLocalDateTime()));
        tempLabelDate.setPrefHeight(30.0);
        tempLabelDate.setPrefWidth(250);
        tempLabelDate.setFont(new Font(18));
        tempLabelDate.setStyle("-fx-border-color: black;");
        tempLabelDate.setPadding(new Insets(0, 0, 0, 10));

        HBox.setMargin(tempLabelDate, new Insets(0, 10, 0, 10));

        tempHBox.getChildren().addAll(tempCircle, tempLabel, tempLabelDate);

        taskVBox.getChildren().add(tempHBox);

        tempHBox.setOnMouseClicked(event -> {

            // deselect all tasks
            taskVBox.getChildren().forEach((n) -> n.setStyle(null));

            // select clicked on task
            tempHBox.setStyle("-fx-border-color: blue;");

            // enable relevant buttons
            removeTaskButton.setDisable(false);
            viewTaskButton.setDisable(false);

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

    public void viewTaskNode(ActionEvent event) throws IOException {

        // get selected task
        TodoTask task = todoTasks.get(selectedTask);

        // update todoTasks
        todoTasks = BasicApplication.getTodoTasks();

        // give todoTask to ViewTaskFXMLController
        ViewTaskFXMLController.setTodoTask(todoTasks.get(selectedTask), selectedTask);

        switchScene(event, "viewTaskFXML.fxml");

        selectedTask = -1;
        removeTaskButton.setDisable(true);
        viewTaskButton.setDisable(true);
    }

    public void switchToMoneyScene(MouseEvent event) throws IOException {
        switchScene(event, "moneyFXML.fxml");
    }

    public void newTaskEvent(ActionEvent event) throws IOException {
        switchScene(event, "newTaskFXML.fxml");
    }

    public String LocalDateTimeToString(LocalDateTime localDateTime) {

        LocalDateTime to = localDateTime;
        LocalDateTime from = LocalDateTime.now();

        Duration duration = Duration.between(from, to);

        String resultantString = "";
        resultantString += String.format("%02d", (int)duration.toDaysPart());
        resultantString += " : ";
        resultantString += String.format("%02d", duration.toHoursPart());
        resultantString += " : ";
        resultantString += String.format("%02d", duration.toMinutesPart());

        return resultantString;
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