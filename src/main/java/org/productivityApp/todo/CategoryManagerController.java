package org.productivityApp.todo;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.productivityApp.BasicApplication;

import java.util.ArrayList;

public class CategoryManagerController {

    AnchorPane anchorPane;
    private void initAnchorPane() {
        anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(800.0);
        anchorPane.setPrefWidth(520.0);
    }

    Label categoriesLabel;
    TextField categoriesTextField;
    Button addNewCategoryButton;
    private void initHeading(double x, double y) {

        categoriesLabel = new Label("Categories:");
        categoriesLabel.setLayoutX(x);
        categoriesLabel.setLayoutY(y);
        categoriesLabel.setFont(new Font(24));

        categoriesTextField = new TextField();
        categoriesTextField.setFont(new Font(18));
        categoriesTextField.setLayoutX(x);
        categoriesTextField.setLayoutY(y + 50);

        addNewCategoryButton = new Button();
        addNewCategoryButton.setText("Add New Category");
        addNewCategoryButton.setFont(new Font(18));
        addNewCategoryButton.setLayoutX(x + 250);
        addNewCategoryButton.setLayoutY(y + 50);
        addNewCategoryButton.prefHeightProperty().bind(categoriesTextField.heightProperty());
        addNewCategoryButton.setOnAction(event -> {
            addCategory(categoriesTextField.getText());
        });

        anchorPane.getChildren().addAll(categoriesLabel, categoriesTextField, addNewCategoryButton);
    }

    ScrollPane categoriesScrollPane;
    VBox categoriesVBox;
    private void initScrollPane(double x, double y) {

        categoriesScrollPane = new ScrollPane();
        categoriesScrollPane.setLayoutX(x);
        categoriesScrollPane.setLayoutY(y);
        categoriesScrollPane.setPrefWidth(500);
        categoriesScrollPane.setPrefHeight(600);

        categoriesVBox = new VBox();
        categoriesVBox.setPrefWidth(490);
        categoriesVBox.setPrefHeight(590);
        categoriesScrollPane.setContent(categoriesVBox);

        categoryTypes.forEach((category) -> {
            if(!category.equals("None"))
                addCategoryNode(category);
        });

        anchorPane.getChildren().addAll(categoriesScrollPane);
    }

    Button backButton;
    Button saveButton;
    Button removeCategoryButton;
    private void initBottomButtons(int x, int y) {

        int buttonWidth = 100;
        int buttonHeight = 40;

        backButton = new Button();
        backButton.setText("Back");
        backButton.setFont(new Font(18));
        backButton.setLayoutX(x);
        backButton.setLayoutY(y);
        backButton.setPrefWidth(buttonWidth);
        backButton.setPrefHeight(buttonHeight);
        backButton.setOnAction(event -> {
            stage.close();
        });

        saveButton = new Button();
        saveButton.setText("Save");
        saveButton.setFont(new Font(18));
        saveButton.setLayoutX(x + 110);
        saveButton.setLayoutY(y);
        saveButton.setPrefWidth(buttonWidth);
        saveButton.setPrefHeight(buttonHeight);
        saveButton.setOnAction(event -> {
            BasicApplication.setCategoryTypes(categoryTypes);
            stage.close();
        });

        removeCategoryButton = new Button();
        removeCategoryButton.setText("Remove Category");
        removeCategoryButton.setFont(new Font(18));
        removeCategoryButton.setLayoutX(x + 300);
        removeCategoryButton.setLayoutY(y);
        removeCategoryButton.setPrefWidth(buttonWidth * 2);
        removeCategoryButton.setPrefHeight(buttonHeight);
        removeCategoryButton.setDisable(true);
        removeCategoryButton.setOnAction(event -> {
            removeCategory(selectedCategoryIndex);
        });

        anchorPane.getChildren().addAll(backButton, saveButton, removeCategoryButton);
    }

    private Stage stage;

    private ArrayList<String> categoryTypes;
    private int selectedCategoryIndex;
    private TaskController controller;

    public void initialize(Stage stage, TaskController controller) {

        this.stage = stage;

        this.categoryTypes = new ArrayList<>(BasicApplication.getCategoryTypes());
        this.selectedCategoryIndex = -1;

        this.controller = controller;

        initAnchorPane();
        initHeading(10, 10);
        initScrollPane(10, 120);
        initBottomButtons(10, 740);

        // set stage parameters
        Scene newScene = new Scene(anchorPane);
        stage.setScene(newScene);
        stage.setResizable(false);
        stage.show();
    }

    private void addCategory(String categoryString) {

        // do nothing if string is empty
        if(categoryString.length() == 0) {
            return;
        }

        // do nothing if string is "None"
        if(categoryString.equals("None") || categoryString.equals("none")) {
            return;
        }

        // do nothing if string is already in list
        if(categoryTypes.indexOf(categoryString) != -1) {
            return;
        }

        // add category to task
        categoryTypes.add(categoryString);

        // add category node
        addCategoryNode(categoryString);
    }

    private void addCategoryNode(String categoryString) {

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
            selectedCategoryIndex = categoriesVBox.getChildren().indexOf(tempHBox) + 1;
        });

        categoriesVBox.getChildren().add(tempHBox);
    }

    private void removeCategory(int categoryIndex) {

        removeCategoryButton.setDisable(true);

        categoryTypes.remove(categoryIndex);
        categoriesVBox.getChildren().remove(categoryIndex - 1);
    }
}
