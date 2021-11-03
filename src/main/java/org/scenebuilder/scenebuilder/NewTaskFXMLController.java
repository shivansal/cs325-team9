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
import java.time.LocalDateTime;
import java.util.ArrayList;

public class NewTaskFXMLController {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private VBox categoriesVbox;
    @FXML
    private TextField taskNameTextField;
    @FXML
    private ComboBox taskCategoriesDropdown;
    @FXML
    private ComboBox recurringComboBox;
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
    private ComboBox priorityComboBox;
    @FXML
    private Button removeCategoryButton;
    @FXML
    private Button manageCategoriesButton;


    private Stage stage;
    private DateTimePicker dateTimePicker;

    private ArrayList<String> categories = new ArrayList<>();
    private ArrayList<String> taskCategories = new ArrayList<>();

    private static int selectedCategory = -1;

    private static TodoTask todoTask;
    private static int selectedTaskNum = -1;

    public static void setTodoTask(TodoTask task, int i) {
        todoTask = task;
        selectedTaskNum = i;
    }


    public void initialize() {

        // load category types from main
        categories = BasicApplication.getCategoryTypes();

        // add categories to dropdown - None is selected initially
        taskCategoriesDropdown.setItems(FXCollections.observableArrayList(categories));
        taskCategoriesDropdown.getSelectionModel().select(categories.indexOf("None"));

        // add categories to vbox - no categories by default
        //taskCategories.forEach((n)-> {
        //    addCategoryNode(n);
        //});

        // custom control with date + time
        dateTimePicker = new DateTimePicker();
        dateTimePicker.setLayoutX(172.0);
        dateTimePicker.setLayoutY(85.0);

        // set default value to right now

        LocalDateTime rightNow = LocalDateTime.now();
        dateTimePicker.setDateTimeValue(rightNow);
        anchorPane.getChildren().add(dateTimePicker);

        // recurring ComboBox
        recurringComboBox.setItems(FXCollections.observableArrayList("Never", "Weekly", "Bi-Weekly"));

        // set default recurring to never
        recurringComboBox.getSelectionModel().select(0);

        // priority ComboBox
        priorityComboBox.setItems(FXCollections.observableArrayList("Highest", "High", "Medium", "Low", "Lowest"));

        // default - medium
        priorityComboBox.getSelectionModel().select(2);
    }

    public void addCategoryNode(String categoryLabel) {

        HBox tempHBox = new HBox();
        tempHBox.setAlignment(Pos.CENTER_LEFT);
        tempHBox.setPrefHeight(35);
        tempHBox.setPrefWidth(480);

        Circle tempCircle = new Circle();
        tempCircle.setRadius(5.0);
        tempCircle.setFill(Color.BLACK);
        HBox.setMargin(tempCircle, new Insets(5, 10, 5, 10));

        Label tempLabel = new Label(categoryLabel);
        tempLabel.setFont(new Font(18));
        HBox.setMargin(tempLabel, new Insets(1, 1, 1, 1));

        tempHBox.getChildren().addAll(tempCircle, tempLabel);

        categoriesVbox.getChildren().add(tempHBox);
    }

    @FXML
    public void assignCategory(ActionEvent event) {

        String categoryString = (String)taskCategoriesDropdown.getValue();

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
        LocalDateTime taskDateTime = dateTimePicker.getDateTimeValue();
        String taskRecurringKey = (String)recurringComboBox.getValue();
        boolean[] taskRecurringDays = {sunToggleButton.isSelected(), monToggleButton.isSelected(), tueToggleButton.isSelected(),
                wedToggleButton.isSelected(), thuToggleButton.isSelected(), friToggleButton.isSelected(), satToggleButton.isSelected()};
        String taskPrio = (String)priorityComboBox.getValue();

        BasicApplication.addTodoTask(new TodoTask(taskName, taskDateTime, taskRecurringKey, taskRecurringDays, taskPrio, taskCategories));
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
