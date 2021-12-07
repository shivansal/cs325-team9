package org.productivityApp.todo;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
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

    VBox screenVBox;
    private void initScreenVBox() {

        screenVBox = new VBox();
        screenVBox.setLayoutX(5);
        screenVBox.setLayoutY(5);
        screenVBox.prefWidthProperty().bind(Bindings.subtract(anchorPane.prefWidthProperty(), 10));
        screenVBox.prefHeightProperty().bind(Bindings.subtract(anchorPane.prefHeightProperty(), 10));
        //screenVBox.setStyle("-fx-border-color: black; -fx-border-radius: 10 10 10 10;");

        anchorPane.getChildren().add(screenVBox);
    }

    protected HBox nameHBox;
    protected Label taskNameLabel;
    protected TextField taskNameTextField;
    private void initName() {

        nameHBox = new HBox();
        nameHBox.setAlignment(Pos.CENTER);
        VBox.setMargin(nameHBox, new Insets(10, 10, 10, 10));

        taskNameLabel = new Label("Name: ");
        taskNameLabel.setFont(new Font(30));
        taskNameLabel.setAlignment(Pos.CENTER);
        HBox.setMargin(taskNameLabel, new Insets(0, 20, 0, 0));

        taskNameTextField = new TextField();
        taskNameTextField.setFont(taskNameLabel.getFont());
        taskNameTextField.setPromptText("Task Name");
        taskNameTextField.setStyle("-fx-border-color: black");

        taskNameLabel.prefHeightProperty().bind(taskNameTextField.heightProperty());

        nameHBox.getChildren().addAll(taskNameLabel, taskNameTextField);
        screenVBox.getChildren().add(nameHBox);
    }
    protected abstract void setInitialNameValue();

    protected HBox dueLabelHBox;
    protected Label dueLabel;
    protected Pane fillerPane;
    private void initDue() {

        dueLabelHBox = new HBox();
        dueLabelHBox.setAlignment(Pos.CENTER);
        VBox.setMargin(dueLabelHBox, new Insets(30, 10, 10, 10));

        dueLabel = new Label("Due:");
        dueLabel.setFont(taskNameLabel.getFont());
        dueLabel.prefWidthProperty().bind(taskNameLabel.widthProperty());
        HBox.setMargin(dueLabel, HBox.getMargin(taskNameLabel));

        fillerPane = new Pane();
        fillerPane.prefWidthProperty().bind(taskNameTextField.widthProperty());

        dueLabelHBox.getChildren().addAll(dueLabel, fillerPane);
        screenVBox.getChildren().add(dueLabelHBox);
    }

    protected HBox dateHBox;
    protected Label dateLabel;
    protected DatePicker datePicker;
    private void initDatePicker() {

        dateHBox = new HBox();
        dateHBox.setAlignment(Pos.CENTER);
        VBox.setMargin(dateHBox, new Insets(10, 10, 10, 20));

        dateLabel = new Label("Date: ");
        dateLabel.setFont(taskNameLabel.getFont());
        dateLabel.setAlignment(Pos.CENTER);

        datePicker = new DatePicker();
        datePicker.prefHeightProperty().bind(dateLabel.heightProperty());
        HBox.setMargin(datePicker, new Insets(0, 0, 0, 0));

        dateHBox.getChildren().addAll(datePicker);
        screenVBox.getChildren().add(dateHBox);
    }
    protected abstract void setInitialDateValue();

    protected HBox timeOuterHBox;
    protected Label timeLabel;
    protected HBox timeHBox;
    protected LimitedTextField hoursTextField;
    protected Label timeColonLabel;
    protected LimitedTextField minutesTextField;
    protected ComboBox timeComboBox;
    private void initTimePicker() {

        timeOuterHBox = new HBox();
        timeOuterHBox.setAlignment(Pos.CENTER);
        VBox.setMargin(timeOuterHBox, VBox.getMargin(dateHBox));

        timeLabel = new Label("Time: ");
        timeLabel.setMinWidth(100);
        timeLabel.setFont(taskNameLabel.getFont());
        timeLabel.setAlignment(Pos.CENTER);

        dateLabel.prefWidthProperty().bind(timeLabel.widthProperty());

        timeHBox = new HBox();
        timeHBox.setStyle("-fx-border-color: #bebebe; -fx-border-radius: 10 10 10 10;");

        timeHBox.setPadding(new Insets(0, 10, 0, 0));
        timeHBox.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(timeHBox, Priority.NEVER);
        //HBox.setMargin(timeHBox, HBox.getMargin(datePicker));

        timeLabel.prefHeightProperty().bind(timeHBox.heightProperty());
        datePicker.prefWidthProperty().bind(timeHBox.widthProperty());

        hoursTextField = new LimitedTextField();
        hoursTextField.setFont(taskNameLabel.getFont());
        hoursTextField.setPromptText("00");
        hoursTextField.setStyle("-fx-background-color: transparent");
        hoursTextField.setAlignment(Pos.CENTER);
        hoursTextField.setMaxLength(2);
        hoursTextField.setMaxWidth(80);
        HBox.setMargin(hoursTextField, new Insets(2, 2, 2, 2));

        timeColonLabel = new Label(" : ");
        timeColonLabel.setFont(taskNameLabel.getFont());
        timeColonLabel.setAlignment(Pos.CENTER);
        timeColonLabel.setMinWidth(20);

        minutesTextField = new LimitedTextField();
        minutesTextField.setFont(taskNameLabel.getFont());
        minutesTextField.setPromptText("00");
        minutesTextField.setStyle("-fx-background-color: transparent");
        minutesTextField.setAlignment(Pos.CENTER);
        minutesTextField.setMaxLength(2);
        minutesTextField.setMaxWidth(hoursTextField.getMaxWidth());
        HBox.setMargin(minutesTextField, HBox.getMargin(hoursTextField));
        HBox.setHgrow(minutesTextField, Priority.NEVER);

        timeComboBox = new ComboBox();
        timeComboBox.setItems(FXCollections.observableArrayList("AM", "PM"));
        timeComboBox.setMinWidth(65);

        timeHBox.getChildren().addAll(hoursTextField, timeColonLabel, minutesTextField, timeComboBox);

        timeOuterHBox.getChildren().addAll(timeHBox);
        screenVBox.getChildren().add(timeOuterHBox);
    }
    protected abstract void setInitialTimeValue();

    // todo currently commented out
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
//        recurringLabel.setFont(font);
//        recurringLabel.setLayoutX(firstColMargin);
//        recurringLabel.setLayoutY(topMargin);
//        recurringLabel.setAlignment(Pos.CENTER);
//
        recurringComboBox = new ComboBox();
//        recurringComboBox.setLayoutX(secondColMargin);
//        recurringComboBox.setLayoutY(topMargin);
//        recurringComboBox.setItems(FXCollections.observableArrayList("Never", "Weekly", "Bi-Weekly"));
//        recurringComboBox.prefHeightProperty().bind(recurringLabel.heightProperty());
//
        buttonsHBox = new HBox();
//        buttonsHBox.setLayoutX(firstColMargin);
//        buttonsHBox.setLayoutY(topMargin + 50.0);
//        buttonsHBox.setAlignment(Pos.CENTER);
//        buttonsHBox.setPadding(new Insets(5, 10, 5, 10));
//
//        Insets buttonInsets = new Insets(0, 3, 0, 3);
//        Font buttonFont = new Font(18);
        sunToggleButton = new ToggleButton("Sun");
//        sunToggleButton.setFont(buttonFont);
//        HBox.setMargin(sunToggleButton, buttonInsets);
        monToggleButton = new ToggleButton("Mon");
//        monToggleButton.setFont(buttonFont);
//        HBox.setMargin(monToggleButton, buttonInsets);
        tueToggleButton = new ToggleButton("Tue");
//        tueToggleButton.setFont(buttonFont);
//        HBox.setMargin(tueToggleButton, buttonInsets);
        wedToggleButton = new ToggleButton("Wed");
//        wedToggleButton.setFont(buttonFont);
//        HBox.setMargin(wedToggleButton, buttonInsets);
        thuToggleButton = new ToggleButton("Thu");
//        thuToggleButton.setFont(buttonFont);
//        HBox.setMargin(thuToggleButton, buttonInsets);
        friToggleButton = new ToggleButton("Fri");
//        friToggleButton.setFont(buttonFont);
//        HBox.setMargin(friToggleButton, buttonInsets);
        satToggleButton = new ToggleButton("Sat");
//        satToggleButton.setFont(buttonFont);
//        HBox.setMargin(satToggleButton, buttonInsets);

//        buttonsHBox.getChildren().addAll(sunToggleButton, monToggleButton, tueToggleButton, wedToggleButton, thuToggleButton, friToggleButton, satToggleButton);
//
//
//
//        anchorPane.getChildren().addAll(recurringLabel, recurringComboBox, buttonsHBox);
    }
    protected abstract void setInitialRecurringValue();
    protected abstract void setInitialRecurringDayValues();

    // todo currently commented out
    protected Label priorityLabel;
    protected ComboBox priorityComboBox;
    private void initPriority(double firstColMargin, double secondColMargin, double topMargin, Font font) {

        priorityLabel = new Label("Priority");
//        priorityLabel.setFont(font);
//        priorityLabel.setLayoutX(firstColMargin);
//        priorityLabel.setLayoutY(topMargin);
//        priorityLabel.setAlignment(Pos.CENTER);
//
        priorityComboBox = new ComboBox();
//        priorityComboBox.setLayoutX(secondColMargin);
//        priorityComboBox.setLayoutY(topMargin);
//        priorityComboBox.setItems(FXCollections.observableArrayList("Highest", "High", "Medium", "Low", "Lowest"));
//        priorityComboBox.prefHeightProperty().bind(priorityLabel.heightProperty());
//
//        anchorPane.getChildren().addAll(priorityLabel, priorityComboBox);
    }
    protected abstract void setInitialPriorityValue();

    protected HBox categoriesHBox;
    protected Label categoriesLabel;
    protected ComboBox categoriesComboBox;
    protected Button assignCategoryButton;
    private void initCategoriesHeading() {

        categoriesHBox = new HBox();
        categoriesHBox.setAlignment(Pos.CENTER_LEFT);
        VBox.setMargin(categoriesHBox, new Insets(50, 10, 10, 10));

        categoriesLabel = new Label("Categories: ");
        categoriesLabel.setFont(taskNameLabel.getFont());
        categoriesLabel.setAlignment(Pos.CENTER);
        HBox.setMargin(categoriesLabel, new Insets(0, 20, 0, 0));

        categoriesComboBox = new ComboBox();
        categoriesComboBox.prefWidthProperty().bind(Bindings.multiply(categoriesLabel.widthProperty(), 2));
        HBox.setMargin(categoriesComboBox, new Insets(0, 20, 0, 0));

        assignCategoryButton = new Button();
        assignCategoryButton.setText("Assign Category");
        HBox.setMargin(assignCategoryButton, new Insets(0, 0, 0, 0));
        assignCategoryButton.setOnAction(event -> {
            addCategoryPressed((String)categoriesComboBox.getValue());
        });

        categoriesComboBox.prefWidthProperty().bind(assignCategoryButton.widthProperty());

        categoriesHBox.getChildren().addAll(categoriesLabel, categoriesComboBox, assignCategoryButton);
        screenVBox.getChildren().add(categoriesHBox);
    }

    protected HBox categoriesScrollPaneHBox;
    protected ScrollPane categoriesScrollPane;
    protected VBox categoriesVBox;
    private void initCategoriesScrollPane() {

        categoriesScrollPaneHBox = new HBox();
        categoriesScrollPaneHBox.setAlignment(Pos.CENTER_LEFT);
        VBox.setVgrow(categoriesScrollPaneHBox, Priority.ALWAYS);

        categoriesScrollPane = new ScrollPane();
        categoriesScrollPane.prefHeightProperty().bind(categoriesScrollPaneHBox.heightProperty());
        categoriesScrollPane.prefWidthProperty().bind(categoriesScrollPaneHBox.widthProperty());
        categoriesScrollPane.setStyle("-fx-background-color: transparent");

        categoriesVBox = new VBox();
        categoriesVBox.prefHeightProperty().bind(Bindings.subtract(categoriesScrollPane.heightProperty(), 10));
        categoriesVBox.prefWidthProperty().bind(Bindings.subtract(categoriesScrollPane.widthProperty(), 10));
        categoriesVBox.setStyle("-fx-border-color: #bebebe; -fx-border-radius: 10 10 10 10");

        categoriesScrollPane.setContent(categoriesVBox);

        categoriesScrollPaneHBox.getChildren().addAll(categoriesScrollPane);
        screenVBox.getChildren().addAll(categoriesScrollPaneHBox);
    }

    protected HBox bottomButtonsHBox;
    protected VBox navButtonsVBox;
    protected VBox categoryButtonsVBox;
    protected Pane bottomButtonsFillerPane;
    protected Button removeCategoryButton;
    protected Button manageCategoriesButton;
    protected HBox navButtonsHBox;
    protected Button backButton;
    protected Button saveTaskButton;
    private void initBottomButtons() {

        bottomButtonsHBox = new HBox();
        bottomButtonsHBox.setAlignment(Pos.CENTER);
        VBox.setMargin(bottomButtonsHBox, new Insets(0, 0, 0, 10));

        // group category buttons into one vbox
        categoryButtonsVBox = new VBox();
        categoryButtonsVBox.setAlignment(Pos.BOTTOM_RIGHT);

        removeCategoryButton = new Button();
        removeCategoryButton.setFont(new Font(20));
        removeCategoryButton.setText("Remove Category");
        removeCategoryButton.setDisable(true);
        VBox.setMargin(removeCategoryButton, new Insets(0, 10, 10, 0));
        removeCategoryButton.setOnAction(event -> {
            removeCategory();
        });

        manageCategoriesButton = new Button();
        manageCategoriesButton.setFont(removeCategoryButton.getFont());
        manageCategoriesButton.setText("Manage Categories");
        VBox.setMargin(manageCategoriesButton, new Insets(0, 10, 0, 0));
        manageCategoriesButton.setOnAction(event -> {

            Stage newStage = new Stage();

            CategoryManagerController controller = new CategoryManagerController();
            controller.initialize(newStage, this);
        });

        removeCategoryButton.prefWidthProperty().bind(manageCategoriesButton.widthProperty());
        removeCategoryButton.prefHeightProperty().bind(manageCategoriesButton.heightProperty());

        categoryButtonsVBox.getChildren().addAll(removeCategoryButton, manageCategoriesButton);

        // group nav buttons into one vbox
        navButtonsVBox = new VBox();
        navButtonsVBox.setAlignment(Pos.BOTTOM_LEFT);

        navButtonsHBox = new HBox();
        navButtonsHBox.setAlignment(Pos.BOTTOM_LEFT);

        backButton = new Button();
        backButton.setFont(manageCategoriesButton.getFont());
        backButton.setText("Back");
        backButton.prefWidthProperty().bind(Bindings.multiply(manageCategoriesButton.widthProperty(), 0.5));
        backButton.prefHeightProperty().bind(manageCategoriesButton.heightProperty());
        HBox.setMargin(backButton, new Insets(0, 10, 0, 0));
        backButton.setOnAction(event -> {
            TodoController controller = new TodoController();
            controller.initialize(stage);
        });

        saveTaskButton = new Button();
        saveTaskButton.setFont(backButton.getFont());
        saveTaskButton.setText("Save");
        saveTaskButton.prefWidthProperty().bind(backButton.widthProperty());
        saveTaskButton.prefHeightProperty().bind(backButton.heightProperty());
        setSaveTaskButtonOnAction();

        navButtonsHBox.getChildren().addAll(backButton, saveTaskButton);
        navButtonsVBox.getChildren().addAll(navButtonsHBox);

        // push right vbox to right with hgrow
        bottomButtonsFillerPane = new Pane();
        HBox.setHgrow(bottomButtonsFillerPane, Priority.ALWAYS);

        bottomButtonsHBox.getChildren().addAll(navButtonsVBox, bottomButtonsFillerPane, categoryButtonsVBox);
        screenVBox.getChildren().addAll(bottomButtonsHBox);
    }
    protected void setInitialCategories() {
        updateCategories();
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

        initScreenVBox();
        initName();
        initDue();
        initDatePicker();
        initTimePicker();
        initCategoriesHeading();
        initCategoriesScrollPane();
        initBottomButtons();

        setInitialValues();
    }

    protected void updateCategories() {
        categoriesComboBox.setItems(FXCollections.observableArrayList(BasicApplication.getCategoryTypes()));
        categoriesComboBox.getSelectionModel().select(BasicApplication.getCategoryTypes().indexOf("None"));
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
        tempLabel.setStyle("-fx-border-color: black; -fx-border-radius: 5 5 5 5");
        tempLabel.setPadding(new Insets(0, 0, 0, 10));

        HBox.setMargin(tempLabel, new Insets(10,10,10,10));

        tempHBox.getChildren().addAll(tempCircle, tempLabel);

        tempHBox.setOnMouseClicked(MouseEvent -> {

            // deselect all tasks
            categoriesVBox.getChildren().forEach((n) -> ((HBox)n).getChildren().get(1).setStyle("-fx-border-color: black; -fx-border-radius: 5 5 5 5"));

            // visually select task that was clicked on
            tempLabel.setStyle("-fx-border-color: blue; -fx-border-radius: 5 5 5 5");

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
