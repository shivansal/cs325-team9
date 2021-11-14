package org.scenebuilder.scenebuilder;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class NewTaskFXMLController {

    // static components
    private static int selectedCategory = -1;

    private static TodoTask todoTask;
    private static int selectedTaskNum = -1;

    public static void setTodoTask(TodoTask task, int i) {
        todoTask = task;
        selectedTaskNum = i;
    }

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField taskNameTextField;
    @FXML
    private ToggleButton sunToggleButton;
    @FXML
    private ToggleButton monToggleButton;
    @FXML
    private ToggleButton tueToggleButton;
    @FXML
    private ToggleButton wedToggleButton;
    @FXML
    private ToggleButton thuToggleButton;
    @FXML
    private ToggleButton friToggleButton;
    @FXML
    private ToggleButton satToggleButton;
    @FXML
    private VBox categoriesVbox;
    @FXML
    private Button removeCategoryButton;

    private Stage stage;

    private ArrayList<String> categories = new ArrayList<>();
    private ArrayList<String> taskCategories = new ArrayList<>();
    @FXML
    private ComboBox taskCategoriesDropdown;
    public void initCategoriesDropdown() {
        // add categories to dropdown - None is selected initially
        taskCategoriesDropdown.setItems(FXCollections.observableArrayList(categories));
        taskCategoriesDropdown.getSelectionModel().select(categories.indexOf("None"));
    }

    private DatePicker datePicker;
    public void initDatePicker() {
        datePicker = new DatePicker();
        datePicker.setLayoutX(172.0);
        datePicker.setLayoutY(85.0);

        // set default value to right now
        LocalDateTime rightNow = LocalDateTime.now();
        int year = rightNow.getYear();
        int month = rightNow.getMonthValue();
        int day = rightNow.getDayOfMonth();
        datePicker.setValue(LocalDate.of(year, month, day));
        anchorPane.getChildren().add(datePicker);
    }

    @FXML
    private HBox timeHBox;
    @FXML
    private LimitedTextField hoursTextField;
    @FXML
    private LimitedTextField minutesTextField;
    @FXML
    private ComboBox timeComboBox;
    public void initTimeHBox() {
        LocalTime localTime = LocalTime.now();
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
        // instantiate time LimitedTextFields
        hoursTextField = new LimitedTextField();
        minutesTextField = new LimitedTextField();
        Label timeLabel = new Label();

        timeLabel.setText(" : ");
        timeLabel.setFont(new Font(24));

        hoursTextField.setAlignment(Pos.CENTER);
        hoursTextField.setPrefHeight(40.0);
        hoursTextField.setPrefWidth(50.0);
        hoursTextField.setPromptText("00");
        hoursTextField.setStyle("-fx-background-color: transparent;");
        hoursTextField.setFont(new Font(20));
        hoursTextField.setMaxLength(2);

        minutesTextField.setAlignment(Pos.CENTER);
        minutesTextField.setPrefHeight(40.0);
        minutesTextField.setPrefWidth(50.0);
        minutesTextField.setPromptText("00");
        minutesTextField.setStyle("-fx-background-color: transparent;");
        minutesTextField.setFont(new Font(20));
        minutesTextField.setMaxLength(2);

        timeHBox.getChildren().addAll(hoursTextField, timeLabel, minutesTextField);

        hoursTextField.setText(String.format("%02d", hour));
        minutesTextField.setText(Integer.toString(minute));

        // add AM and PM to combobox
        timeComboBox.setItems(FXCollections.observableArrayList("AM", "PM"));
        timeComboBox.getSelectionModel().select(amPMIndex);

    }

    @FXML
    private ComboBox recurringComboBox;
    public void initRecurringComboBox() {
        recurringComboBox.setItems(FXCollections.observableArrayList("Never", "Weekly", "Bi-Weekly"));
        recurringComboBox.getSelectionModel().select(0);
    }

    @FXML
    private ComboBox priorityComboBox;
    public void initPriorityComboBox() {
        priorityComboBox.setItems(FXCollections.observableArrayList("Highest", "High", "Medium", "Low", "Lowest"));
        priorityComboBox.getSelectionModel().select(2);
    }

    public void initialize() {

        // load category types from main
        categories = BasicApplication.getCategoryTypes();

        initCategoriesDropdown();

        initDatePicker();

        initTimeHBox();

        initRecurringComboBox();

        initPriorityComboBox();
    }

    @FXML
    public void assignCategory(ActionEvent event) {

        String categoryString = (String)taskCategoriesDropdown.getValue();

        // do nothing if string is empty or
        if(categoryString.length() == 0 || taskCategories.indexOf(categoryString) != -1 || categoryString.equals("None")) {
            return;
        }

        // add category to task
        taskCategories.add(categoryString);

        // add category to vbox
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
            categoriesVbox.getChildren().forEach((n) -> n.setStyle(null));

            // select clicked on task
            tempHBox.setStyle("-fx-border-color: blue;");

            // enable relevant buttons
            removeCategoryButton.setDisable(false);

            // update selected task
            selectedCategory = categoriesVbox.getChildren().indexOf(tempHBox);
        });

        categoriesVbox.getChildren().add(tempHBox);
    }

    @FXML
    public void removeCategory(ActionEvent event) {

        // disable remove button
        removeCategoryButton.setDisable(true);

        categoriesVbox.getChildren().remove(selectedCategory);
    }

    @FXML
    public void manageCategories(ActionEvent event) throws IOException {
        // open category screen - add or remove categories as options

        // store info from this screen so we can come back later
        CategoryManagerFXMLController.setSource("new");
        CategoryManagerFXMLController.setTodoTask(todoTask, selectedTaskNum);
        switchScene(event, "CategoryManagerFXML.fxml");
    }

    @FXML
    public void backFromNewTask(ActionEvent event) throws IOException {
        switchScene(event, "todoFXML.fxml");
    }

    @FXML
    public void saveNewTask(ActionEvent event) throws IOException {

        String taskName = taskNameTextField.getText();
        LocalDate taskDate = datePicker.getValue();
        String taskRecurringKey = (String)recurringComboBox.getValue();
        boolean[] taskRecurringDays = {sunToggleButton.isSelected(), monToggleButton.isSelected(), tueToggleButton.isSelected(),
                wedToggleButton.isSelected(), thuToggleButton.isSelected(), friToggleButton.isSelected(), satToggleButton.isSelected()};
        String taskPrio = (String)priorityComboBox.getValue();

        //BasicApplication.addTodoTask(new TodoTask(taskName, taskDate, taskRecurringKey, taskRecurringDays, taskPrio, taskCategories));
        switchScene(event, "todoFXML.fxml");
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


}
