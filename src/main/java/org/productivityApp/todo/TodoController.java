package org.productivityApp.todo;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.productivityApp.BasicApplication;
import org.productivityApp.screen.TabController;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TodoController extends TabController {

    private Label todoLabel;
    private Label categoryLabel;
    private ComboBox categoryComboBox;
    private void initHeading(double x, double y) {

        filteredTasks = new ArrayList<>(BasicApplication.getTodoTasks());

        todoLabel = new Label("Todo:");
        todoLabel.setFont(new Font(24));
        todoLabel.setAlignment(Pos.CENTER);
        todoLabel.setLayoutX(x);
        todoLabel.setLayoutY(y);

        categoryLabel = new Label("Category:");
        categoryLabel.setFont(new Font(24));
        categoryLabel.setAlignment(Pos.CENTER);
        categoryLabel.setLayoutX(310 - 120);
        categoryLabel.setLayoutY(y);

        categoryComboBox = new ComboBox();
        categoryComboBox.setLayoutX(310);
        categoryComboBox.setLayoutY(y);
        categoryComboBox.prefHeightProperty().bind(categoryLabel.heightProperty());
        categoryComboBox.prefWidthProperty().bind(todoTabLabel.widthProperty());

        List<String> dropDownItems = BasicApplication.getCategoryTypes();
        categoryComboBox.setItems(FXCollections.observableList(dropDownItems));
        categoryComboBox.getSelectionModel().select(0);

        categoryComboBox.setOnAction(event -> {

            // clear tasks
            taskVBox.getChildren().clear();
            filteredTasks.clear();

            String categoryFilter = (String)categoryComboBox.getValue();

            BasicApplication.getTodoTasks().forEach( (n) -> {
                if(n.getTaskCategories().indexOf(categoryFilter) != -1 || categoryFilter.equals("None")) {
                    addTaskNode(n);
                    filteredTasks.add(n);
                }
            });
        });

        anchorPane.getChildren().addAll(todoLabel, categoryLabel, categoryComboBox);
    }

    private ScrollPane taskScrollPane;
    private VBox taskVBox;
    private void initTodoList(double x, double y) {
        taskScrollPane = new ScrollPane();
        taskScrollPane.setLayoutX(x);
        taskScrollPane.setLayoutY(y);
        taskScrollPane.setPrefWidth(500);
        taskScrollPane.setPrefHeight(540);
        taskScrollPane.setStyle("-fx-background-color: transparent");

        taskVBox = new VBox();
        taskVBox.setPrefWidth(490);
        taskVBox.setPrefHeight(490);
        taskScrollPane.setContent(taskVBox);

        BasicApplication.getTodoTasks().forEach((task) -> {
           addTaskNode(task);
        });

        anchorPane.getChildren().addAll(taskScrollPane);
    }

    private Button newTaskButton;
    private Button removeTaskButton;
    private Button viewTaskDetailsButton;
    private void initButtons(double x, double y) {
        newTaskButton = new Button();
        newTaskButton.setText("New Task");
        newTaskButton.setFont(new Font(18));
        newTaskButton.setPrefHeight(40);
        newTaskButton.setPrefWidth(100);
        newTaskButton.setLayoutX(x);
        newTaskButton.setLayoutY(y);

        newTaskButton.setOnAction(event -> {
            NewTaskController controller = new NewTaskController();
            controller.initialize(stage);
        });

        removeTaskButton = new Button();
        removeTaskButton.setText("Remove Task");
        removeTaskButton.setFont(new Font(18));
        removeTaskButton.setPrefHeight(40);
        removeTaskButton.setPrefWidth(150);
        removeTaskButton.setLayoutX(x + 170);
        removeTaskButton.setLayoutY(y);
        removeTaskButton.setDisable(true);

        removeTaskButton.setOnAction(event -> {

            // remove node
            taskVBox.getChildren().remove(selectedTaskIndex);

            // remove task from task list
            BasicApplication.removeTodoTask(filteredTasks.get(selectedTaskIndex));

            // no selected tasks exist
            selectedTaskIndex = -1;
            removeTaskButton.setDisable(true);
        });

        viewTaskDetailsButton = new Button();
        viewTaskDetailsButton.setText("View Task Details");
        viewTaskDetailsButton.setFont(new Font(18));
        viewTaskDetailsButton.setPrefHeight(40);
        viewTaskDetailsButton.setPrefWidth(170);
        viewTaskDetailsButton.setLayoutX(x + 330);
        viewTaskDetailsButton.setLayoutY(y);
        viewTaskDetailsButton.setDisable(true);

        viewTaskDetailsButton.setOnAction(event -> {
            ViewTaskController controller = new ViewTaskController();
            controller.initialize(stage, filteredTasks.get(selectedTaskIndex), BasicApplication.getTodoTasks().indexOf(filteredTasks.get(selectedTaskIndex)));
        });

        anchorPane.getChildren().addAll(newTaskButton, removeTaskButton, viewTaskDetailsButton);
    }

    private int selectedTaskIndex;
    private ArrayList<TodoTask> filteredTasks;

    public void initialize(Stage stage) {

        super.initialize(stage, "Todo");

        BasicApplication.sortTodoTasksByDate();

        initHeading(10, 150);
        initTodoList(10, 200);
        initButtons(10, 750);
    }

    private void addTaskNode(TodoTask task) {

        // container for task (selection box)
        HBox tempHBox = new HBox();
        tempHBox.setAlignment(Pos.CENTER_LEFT);

        // bullet point
        Circle tempCircle = new Circle();
        tempCircle.setRadius(5.0);
        tempCircle.setFill(Color.BLACK);
        HBox.setMargin(tempCircle, new Insets(5, 5, 5, 5));

        // task name
        Label tempLabel = new Label(task.getTaskName());
        tempLabel.setPrefHeight(30.0);
        tempLabel.setPrefWidth(360.0);
        tempLabel.setFont(new Font(18));
        tempLabel.setStyle("-fx-border-color: black;");
        tempLabel.setPadding(new Insets(0, 0, 0, 10));
        HBox.setMargin(tempLabel, new Insets(10,10,10,10));

        // task due date countdown
        Label tempLabelDate = new Label();
        tempLabelDate.setPrefHeight(30.0);
        tempLabelDate.setPrefWidth(250);
        tempLabelDate.setFont(new Font(18));
        tempLabelDate.setAlignment(Pos.CENTER);
        tempLabelDate.setStyle("-fx-border-color: black;");
        tempLabelDate.setPadding(new Insets(0, 5, 0, 5));
        HBox.setMargin(tempLabelDate, new Insets(0, 10, 0, 10));

        tempLabelDate.setText(getCountDownString(task));

        tempHBox.getChildren().addAll(tempCircle, tempLabel, tempLabelDate);
        taskVBox.getChildren().add(tempHBox);

        tempHBox.setOnMouseClicked(event -> {

            // deselect all tasks
            taskVBox.getChildren().forEach((n) -> n.setStyle(null));

            // select clicked on task
            tempHBox.setStyle("-fx-border-color: blue;");

            // enable relevant buttons
            removeTaskButton.setDisable(false);
            viewTaskDetailsButton.setDisable(false);

            // update selected task
            selectedTaskIndex = taskVBox.getChildren().indexOf(tempHBox);
        });
    }

    private String getCountDownString(TodoTask task) {

        LocalDateTime rightNow = LocalDateTime.now();
        LocalDateTime taskDue = LocalDateTime.of(task.getTaskDate(), task.getTaskTime());

        Duration duration = Duration.between(rightNow, taskDue);
        int days = (int)duration.toDaysPart();
        int hours = duration.toHoursPart();
        int minutes = duration.toMinutesPart();

        String output = String.format("%02d", days) +
                " : " +
                String.format("%02d", hours) +
                " : " +
                String.format("%02d", minutes);

        return output;
    }
}
