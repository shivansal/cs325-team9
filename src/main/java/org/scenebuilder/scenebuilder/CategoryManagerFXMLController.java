package org.scenebuilder.scenebuilder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class CategoryManagerFXMLController {

    @FXML
    private VBox categoriesVBox;
    @FXML
    private Button removeCategoryButton;
    @FXML
    private TextField categoryTextField;
    @FXML
    private Button addCategoryButton;

    private Stage stage;
    private ArrayList<String> taskCategories;
    private int selectedCategory = -1;

    private static String source;
    public static void setSource(String s) {
        source = s;
    }

    private static TodoTask todoTask;
    private static int selectedTaskNum = -1;
    public static void setTodoTask(TodoTask task, int taskNum) {
        todoTask = task;
        selectedTaskNum = taskNum;
    }

    public void initialize() {

        // load category types from main
        taskCategories = new ArrayList<>(BasicApplication.getCategoryTypes());

        // add categories to vbox - no categories by default
        taskCategories.forEach((n)-> {
            addCategoryNode(n);
        });

        categoriesVBox.getChildren().remove(0);
    }

    public void addCategoryNode(String categoryString) {

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

            // select clicked on task
            tempHBox.setStyle("-fx-border-color: blue;");

            // enable relevant buttons
            removeCategoryButton.setDisable(false);

            // update selected task
            selectedCategory = categoriesVBox.getChildren().indexOf(tempHBox);
        });

        categoriesVBox.getChildren().add(tempHBox);
    }

    @FXML
    public void assignCategory(ActionEvent event) {

        String categoryString = (String)categoryTextField.getText();

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
            categoriesVBox.getChildren().forEach((n) -> n.setStyle(null));

            // select clicked on task
            tempHBox.setStyle("-fx-border-color: blue;");

            // enable relevant buttons
            removeCategoryButton.setDisable(false);

            // update selected task
            selectedCategory = categoriesVBox.getChildren().indexOf(tempHBox) + 1;
        });

        categoriesVBox.getChildren().add(tempHBox);
    }

    @FXML
    public void removeCategory(ActionEvent event) {

        // disable remove button
        removeCategoryButton.setDisable(true);

        taskCategories.remove(selectedCategory);
        categoriesVBox.getChildren().remove(selectedCategory);
    }

    public void backFromCategoryManager(ActionEvent event) throws IOException {

        if(source.equals("new")) {

            NewTaskFXMLController.setTodoTask(todoTask, selectedTaskNum);
            switchScene(event, "newTaskFXML.fxml");
        } else if (source.equals("view")) {

            ViewTaskFXMLController.setTodoTask(todoTask, selectedTaskNum);
            switchScene(event, "viewTaskFXML.fxml");
        } else {
            System.out.println("Invalid source in CategoryManagerController");
        }
    }

    public void saveFromCategoryManager(ActionEvent event) throws IOException {

        BasicApplication.setCategoryTypes(taskCategories);

        if(source.equals("new")) {

            NewTaskFXMLController.setTodoTask(todoTask, selectedTaskNum);
            switchScene(event, "newTaskFXML.fxml");
        } else if (source.equals("view")) {

            ViewTaskFXMLController.setTodoTask(todoTask, selectedTaskNum);
            switchScene(event, "viewTaskFXML.fxml");
        } else {
            System.out.println("Invalid source in CategoryManagerController");
        }
    }

    public void switchScene(ActionEvent event, String nextScene) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(nextScene));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}
