package org.productivityApp.todo;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.productivityApp.BasicApplication;
import org.productivityApp.custom.LimitedTextField;
import org.productivityApp.screen.ScreenController;

import java.time.LocalTime;
import java.util.ArrayList;

public abstract class TaskController extends ScreenController {

    protected Label taskNameLabel;
    protected TextField taskNameTextField;
    private void initName(double firstColMargin, double secondColMargin, double topMargin, Font font) {

        taskNameLabel = new Label("Name: ");
        taskNameLabel.setFont(font);
        taskNameLabel.setLayoutX(firstColMargin);
        taskNameLabel.setLayoutY(topMargin);
        taskNameLabel.setAlignment(Pos.CENTER);

        taskNameTextField = new TextField();
        taskNameTextField.setFont(font);
        taskNameTextField.setLayoutX(secondColMargin);
        taskNameTextField.setLayoutY(topMargin);
        taskNameTextField.setPromptText("Task Name");
        taskNameTextField.setStyle("-fx-border-color: black");

        taskNameLabel.prefHeightProperty().bind(taskNameTextField.heightProperty());

        anchorPane.getChildren().addAll(taskNameLabel, taskNameTextField);
    }
    protected abstract void setInitialNameValue();

    protected Label dateLabel;
    protected DatePicker datePicker;
    private void initDatePicker(double firstColMargin, double secondColMargin, double topMargin, Font font) {

        dateLabel = new Label("Due Date: ");
        dateLabel.setFont(font);
        dateLabel.setLayoutX(firstColMargin);
        dateLabel.setLayoutY(topMargin);
        dateLabel.setAlignment(Pos.CENTER);

        datePicker = new DatePicker();
        datePicker.setLayoutX(secondColMargin);
        datePicker.setLayoutY(topMargin);

        datePicker.prefHeightProperty().bind(dateLabel.heightProperty());

        anchorPane.getChildren().addAll(dateLabel, datePicker);
    }
    protected abstract void setInitialDateValue();

    protected Label timeLabel;
    protected HBox timeHBox;
    protected LimitedTextField hoursTextField;
    protected Label timeColonLabel;
    protected LimitedTextField minutesTextField;
    protected ComboBox timeComboBox;
    private void initTimePicker(double firstColMargin, double secondColMargin, double topMargin, Font font) {

        timeLabel = new Label("Due Time: ");
        timeLabel.setFont(font);
        timeLabel.setLayoutX(firstColMargin);
        timeLabel.setLayoutY(topMargin);
        timeLabel.setAlignment(Pos.CENTER);

        timeHBox = new HBox();
        timeHBox.setLayoutX(secondColMargin);
        timeHBox.setLayoutY(topMargin);
        timeHBox.setStyle("-fx-border-color: black");
        timeHBox.setPadding(new Insets(0, 10, 0, 10));
        timeHBox.setAlignment(Pos.CENTER_LEFT);
        timeHBox.prefWidthProperty().bind(taskNameTextField.widthProperty());

        timeLabel.prefHeightProperty().bind(timeHBox.heightProperty());

        hoursTextField = new LimitedTextField();
        hoursTextField.setFont(font);
        hoursTextField.setPromptText("00");
        hoursTextField.setStyle("-fx-background-color: transparent");
        hoursTextField.setAlignment(Pos.CENTER);
        hoursTextField.setMaxLength(2);

        timeColonLabel = new Label(" : ");
        timeColonLabel.setFont(font);
        timeColonLabel.setAlignment(Pos.CENTER);
        timeColonLabel.setMinWidth(20);

        minutesTextField = new LimitedTextField();
        minutesTextField.setFont(font);
        minutesTextField.setPromptText("00");
        minutesTextField.setStyle("-fx-background-color: transparent");
        minutesTextField.setAlignment(Pos.CENTER);
        minutesTextField.setMaxLength(2);

        timeComboBox = new ComboBox();
        timeComboBox.setItems(FXCollections.observableArrayList("AM", "PM"));
        timeComboBox.setMinWidth(65);

        timeHBox.getChildren().addAll(hoursTextField, timeColonLabel, minutesTextField, timeComboBox);

        anchorPane.getChildren().addAll(timeLabel, timeHBox);
    }
    protected abstract void setInitialTimeValue();

    protected Label recurringLabel;
    protected ComboBox recurringComboBox;
    protected HBox buttonsHBox;
    protected ToggleButton sunToggleButton;
    protected ToggleButton monToggleButton;
    protected ToggleButton tueToggleButton;
    protected ToggleButton wedToggleButton;
    protected ToggleButton thuToggleButton;
    protected ToggleButton friToggleButton;
    protected ToggleButton satToggleButton;
    private void initRecurring(double firstColMargin, double secondColMargin, double topMargin, Font font) {

        recurringLabel = new Label("Recurring: ");
        recurringLabel.setFont(font);
        recurringLabel.setLayoutX(firstColMargin);
        recurringLabel.setLayoutY(topMargin);
        recurringLabel.setAlignment(Pos.CENTER);

        recurringComboBox = new ComboBox();
        recurringComboBox.setLayoutX(secondColMargin);
        recurringComboBox.setLayoutY(topMargin);
        recurringComboBox.setItems(FXCollections.observableArrayList("Never", "Weekly", "Bi-Weekly"));
        recurringComboBox.prefHeightProperty().bind(recurringLabel.heightProperty());

        buttonsHBox = new HBox();
        buttonsHBox.setLayoutX(firstColMargin);
        buttonsHBox.setLayoutY(topMargin + 50.0);
        buttonsHBox.setAlignment(Pos.CENTER);
        buttonsHBox.setPadding(new Insets(5, 10, 5, 10));

        Insets buttonInsets = new Insets(0, 3, 0, 3);
        Font buttonFont = new Font(18);
        sunToggleButton = new ToggleButton("Sun");
        sunToggleButton.setFont(buttonFont);
        HBox.setMargin(sunToggleButton, buttonInsets);
        monToggleButton = new ToggleButton("Mon");
        monToggleButton.setFont(buttonFont);
        HBox.setMargin(monToggleButton, buttonInsets);
        tueToggleButton = new ToggleButton("Tue");
        tueToggleButton.setFont(buttonFont);
        HBox.setMargin(tueToggleButton, buttonInsets);
        wedToggleButton = new ToggleButton("Wed");
        wedToggleButton.setFont(buttonFont);
        HBox.setMargin(wedToggleButton, buttonInsets);
        thuToggleButton = new ToggleButton("Thu");
        thuToggleButton.setFont(buttonFont);
        HBox.setMargin(thuToggleButton, buttonInsets);
        friToggleButton = new ToggleButton("Fri");
        friToggleButton.setFont(buttonFont);
        HBox.setMargin(friToggleButton, buttonInsets);
        satToggleButton = new ToggleButton("Sat");
        satToggleButton.setFont(buttonFont);
        HBox.setMargin(satToggleButton, buttonInsets);

        buttonsHBox.getChildren().addAll(sunToggleButton, monToggleButton, tueToggleButton, wedToggleButton, thuToggleButton, friToggleButton, satToggleButton);



        anchorPane.getChildren().addAll(recurringLabel, recurringComboBox, buttonsHBox);
    }
    protected abstract void setInitialRecurringValue();
    protected abstract void setInitialRecurringDayValues();

    protected Label priorityLabel;
    protected ComboBox priorityComboBox;
    private void initPriority(double firstColMargin, double secondColMargin, double topMargin, Font font) {

        priorityLabel = new Label("Priority");
        priorityLabel.setFont(font);
        priorityLabel.setLayoutX(firstColMargin);
        priorityLabel.setLayoutY(topMargin);
        priorityLabel.setAlignment(Pos.CENTER);

        priorityComboBox = new ComboBox();
        priorityComboBox.setLayoutX(secondColMargin);
        priorityComboBox.setLayoutY(topMargin);
        priorityComboBox.setItems(FXCollections.observableArrayList("Highest", "High", "Medium", "Low", "Lowest"));
        priorityComboBox.prefHeightProperty().bind(priorityLabel.heightProperty());

        anchorPane.getChildren().addAll(priorityLabel, priorityComboBox);
    }
    protected abstract void setInitialPriorityValue();

    protected Label categoriesLabel;
    protected ComboBox categoriesComboBox;
    protected Button assignCategoryButton;
    protected ScrollPane categoriesScrollPane;
    protected VBox categoriesVBox;
    protected Button removeCategoryButton;
    protected Button manageCategoriesButton;
    private void initCategories(double firstColMargin, double secondColMargin, double topMargin, Font font) {

        categoriesLabel = new Label("Categories: ");
        categoriesLabel.setFont(font);
        categoriesLabel.setLayoutX(firstColMargin);
        categoriesLabel.setLayoutY(topMargin);
        categoriesLabel.setAlignment(Pos.CENTER);

        categoriesComboBox = new ComboBox();
        categoriesComboBox.setLayoutX(secondColMargin);
        categoriesComboBox.setLayoutY(topMargin);
        categoriesComboBox.prefWidthProperty().bind(datePicker.widthProperty());
        categoriesComboBox.prefHeightProperty().bind(categoriesLabel.heightProperty());

        assignCategoryButton = new Button();
        assignCategoryButton.setText("Assign Category");
        assignCategoryButton.setLayoutX(secondColMargin + 200);
        assignCategoryButton.setLayoutY(topMargin);
        assignCategoryButton.prefHeightProperty().bind(categoriesLabel.heightProperty());
        assignCategoryButton.setOnAction(event -> {
            addCategoryPressed((String)categoriesComboBox.getValue());
        });

        categoriesScrollPane = new ScrollPane();
        categoriesScrollPane.setLayoutX(firstColMargin);
        categoriesScrollPane.setLayoutY(topMargin + 50);
        categoriesScrollPane.setPrefHeight(250);
        categoriesScrollPane.setPrefWidth(500);
        categoriesScrollPane.setStyle("-fx-background-color: transparent");

        categoriesVBox = new VBox();
        categoriesVBox.setPrefHeight(240);
        categoriesVBox.setPrefWidth(490);

        categoriesScrollPane.setContent(categoriesVBox);

        int buttonWidth = 140;
        int buttonHeight = 40;

        removeCategoryButton = new Button();
        removeCategoryButton.setText("Remove Category");
        removeCategoryButton.setLayoutX(370);
        removeCategoryButton.setLayoutY(700);
        removeCategoryButton.setPrefWidth(buttonWidth);
        removeCategoryButton.setPrefHeight(buttonHeight);
        removeCategoryButton.setDisable(true);
        removeCategoryButton.setOnAction(event -> {
            removeCategory();
        });

        manageCategoriesButton = new Button();
        manageCategoriesButton.setText("Manage Categories");
        manageCategoriesButton.setLayoutX(370);
        manageCategoriesButton.setLayoutY(750);
        manageCategoriesButton.setPrefWidth(buttonWidth);
        manageCategoriesButton.setPrefHeight(buttonHeight);
        manageCategoriesButton.setOnAction(event -> {

            Stage newStage = new Stage();

            CategoryManagerController controller = new CategoryManagerController();
            controller.initialize(newStage, this);
        });

        anchorPane.getChildren().addAll(categoriesLabel, categoriesComboBox, assignCategoryButton, categoriesScrollPane, removeCategoryButton, manageCategoriesButton);
    }
    protected void setInitialCategories() {
        categoriesComboBox.setItems(FXCollections.observableArrayList(BasicApplication.getCategoryTypes()));
        categoriesComboBox.getSelectionModel().select(BasicApplication.getCategoryTypes().indexOf("None"));
    }

    protected Button backButton;
    protected Button saveTaskButton;
    private void initNavButtons(double x, double y) {

        int buttonWidth = 140;
        int buttonHeight = 40;

        backButton = new Button();
        backButton.setText("Back");
        backButton.setLayoutX(x);
        backButton.setLayoutY(y);
        backButton.setPrefWidth(buttonWidth);
        backButton.setPrefHeight(buttonHeight);
        backButton.setOnAction(event -> {
           TodoController controller = new TodoController();
           controller.initialize(stage);
        });

        saveTaskButton = new Button();
        saveTaskButton.setText("Save");
        saveTaskButton.setLayoutX(x + buttonWidth + 10);
        saveTaskButton.setLayoutY(y);
        saveTaskButton.setPrefWidth(buttonWidth);
        saveTaskButton.setPrefHeight(buttonHeight);
        setSaveTaskButtonOnAction();

        anchorPane.getChildren().addAll(backButton, saveTaskButton);
    }
    protected abstract void setSaveTaskButtonOnAction();

    protected void setInitialValues() {
        setInitialNameValue();
        setInitialDateValue();
        setInitialTimeValue();
        setInitialRecurringValue();
        setInitialRecurringDayValues();
        setInitialPriorityValue();
        setInitialCategories();

        selectedCategoryIndex = -1;
    }

    protected TodoTask selectedTask;
    protected int selectedTaskIndex;
    protected int selectedCategoryIndex;

    public void initialize(Stage stage) {

        super.initialize(stage);

        Font font = new Font(24);

        double firstColMargin = 10.0;
        double secondColMargin = 140.0;

        initName(firstColMargin, secondColMargin, 10, font);
        initDatePicker(firstColMargin, secondColMargin, 80, font);
        initTimePicker(firstColMargin, secondColMargin, 150, font);
        initRecurring(firstColMargin, secondColMargin, 220, font);
        initPriority(firstColMargin, secondColMargin, 350, font);
        initCategories(firstColMargin, secondColMargin, 420, font);
        initNavButtons(firstColMargin, 750);

        setInitialValues();

        System.out.println(BasicApplication.getTodoTasks().get(selectedTaskIndex).getTaskCategories().size());
    }

    protected void loadInTime(LocalTime localTime) {

        int hour = localTime.getHour();
        int minute = localTime.getMinute();
        int amPMIndex = 0; // 0 = am, 1 = pm

        // modify hours to 1-12, from 1-23
        if(hour > 12) {
            hour -= 12;
            amPMIndex = 1;
        } else if (hour == 12) {
            amPMIndex = 1;
        } else {
            amPMIndex = 0;
        }

        hoursTextField.setText(String.format("%02d", hour));
        minutesTextField.setText(String.format("%02d", minute));

        timeComboBox.getSelectionModel().select(amPMIndex);
    }

    // translate time from difficult input format to LocalTime object
    protected LocalTime getTime() {
        int hour;
        int hourValue = -1;
        try {
            hourValue = Integer.parseInt(hoursTextField.getText());
        } catch(Exception e) {
            System.out.println(hoursTextField.getText());
        }

        if(timeComboBox.getValue().equals("PM") && hourValue != 12) {
            hour = hourValue + 12;
        } else {
            hour = hourValue;
        }

        int minute = Integer.parseInt(minutesTextField.getText());

        return LocalTime.of(hour, minute);
    }

    // translate list of task categories (as nodes) to list of categories
    protected ArrayList<String> nodeListToCategoryList() {
        ArrayList<String> categories = new ArrayList<>();

        categoriesVBox.getChildren().forEach( (hbox) -> {
            Label label = (Label)((HBox)hbox).getChildren().get(1);
            categories.add(label.getText());
        });

        return categories;
    }

    protected void addCategoryPressed(String categoryString) {

        // do nothing if string is none
        if(categoryString.equals("None")) {
            return;
        }

        // do nothing if category is already assigned
        if(selectedTask.getTaskCategories().indexOf(categoryString) != -1) {
            return;
        }

        // after checks for validity, add category to task
        selectedTask.addTaskCategory(categoryString);

        addCategoryNode(categoryString);
    }

    protected void addCategoryNode(String categoryString) {

        HBox tempHBox = new HBox();
        tempHBox.setAlignment(Pos.CENTER_LEFT);

        Circle tempCircle = new Circle();
        tempCircle.setRadius(5.0);
        tempCircle.setFill(Color.BLACK);

        HBox.setMargin(tempCircle, new Insets(5, 5, 5, 5));

        Label tempLabel = new Label(categoryString);
        tempLabel.setPrefHeight(30.0);
        tempLabel.setPrefWidth(360.0);
        tempLabel.setFont(new Font(18));
        tempLabel.setStyle("-fx-border-color: black;");
        tempLabel.setPadding(new Insets(0, 0, 0, 10));

        HBox.setMargin(tempLabel, new Insets(10,10,10,10));

        tempHBox.getChildren().addAll(tempCircle, tempLabel);

        tempHBox.setOnMouseClicked(MouseEvent -> {

            // deselect all tasks
            categoriesVBox.getChildren().forEach((n) -> n.setStyle(null));

            // visually select task that was clicked on
            tempHBox.setStyle("-fx-border-color: blue;");

            // enable relevant buttons
            removeCategoryButton.setDisable(false);

            // update selected task
            selectedCategoryIndex = categoriesVBox.getChildren().indexOf(tempHBox);
        });

        categoriesVBox.getChildren().add(tempHBox);
    }

    protected void removeCategory() {

        selectedTask.removeTaskCategory(selectedCategoryIndex);

        categoriesVBox.getChildren().remove(selectedCategoryIndex);
        selectedCategoryIndex = -1;

        removeCategoryButton.setDisable(true);
    }

}
