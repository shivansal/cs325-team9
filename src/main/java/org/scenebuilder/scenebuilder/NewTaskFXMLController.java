package org.scenebuilder.scenebuilder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class NewTaskFXMLController {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private VBox categoriesVbox;
    @FXML
    private ComboBox taskCategoriesDropdown;

    private Stage stage;
    private ArrayList<String> taskCategories = new ArrayList<>();

    public void initialize() {

        // load categories types from main
        taskCategories = BasicApplication.getTaskCategories();

        // add categories to dropdown
        taskCategoriesDropdown.setItems(FXCollections.observableArrayList(taskCategories));
        taskCategoriesDropdown.getSelectionModel().select(0);

        // clear vbox children
        categoriesVbox.getChildren().clear();

        // add categories to vbox
        taskCategories.forEach((n)-> {
            addCategoryNode(n);
        });

        // custom control with date + time
        DateTimePicker dateTimePicker = new DateTimePicker();
        dateTimePicker.setLayoutX(172.0);
        dateTimePicker.setLayoutY(85.0);

        anchorPane.getChildren().add(dateTimePicker);
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

    }

    @FXML
    public void removeCategory(ActionEvent event) {

    }

    @FXML
    public void newCategory(ActionEvent event) {

    }

    @FXML
    public void backFromNewTask(ActionEvent event) throws IOException {
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
