package org.productivityApp.money;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.productivityApp.screen.ScreenController;

import java.util.ArrayList;

public abstract class MoneyObjectController extends ScreenController {

    protected VBox screenVBox;
    private void initScreenVBox() {

        screenVBox = new VBox();
        screenVBox.setLayoutX(5);
        screenVBox.setLayoutY(5);
        screenVBox.prefWidthProperty().bind(Bindings.subtract(anchorPane.prefWidthProperty(), 10));
        screenVBox.prefHeightProperty().bind(Bindings.subtract(anchorPane.prefHeightProperty(), 10));
        screenVBox.setStyle("-fx-border-color: black");

        anchorPane.getChildren().add(screenVBox);
    }

    protected HBox moneyLabelHBox;
    protected Label moneyLabel;
    private void initMoneyLabel() {

        moneyLabelHBox = new HBox();
        moneyLabelHBox.setAlignment(Pos.CENTER);
        moneyLabelHBox.prefWidthProperty().bind(Bindings.subtract(screenVBox.widthProperty(), 0));
        moneyLabelHBox.setStyle("-fx-border-color: black");
        VBox.setMargin(moneyLabelHBox, new Insets(10, 5, 10, 5));

        moneyLabel = new Label();
        setMoneyLabelText();
        moneyLabel.setFont(new Font(36));
        HBox.setMargin(moneyLabel, new Insets(10, 10, 10, 10));

        moneyLabelHBox.getChildren().add(moneyLabel);
        screenVBox.getChildren().add(moneyLabelHBox);
    }
    protected abstract void setMoneyLabelText();

    protected HBox descriptionHBox;
    protected VBox descriptionVBox;
    protected Label descriptionLabel;
    protected Label errorDescriptionLabel;
    protected TextField descriptionTextField;
    private void initDescription() {

        descriptionHBox = new HBox();
        descriptionHBox.setAlignment(Pos.CENTER);
        descriptionHBox.prefWidthProperty().bind(screenVBox.widthProperty());
        VBox.setMargin(descriptionHBox, new Insets(20, 10, 20, 10));

        descriptionVBox = new VBox();
        descriptionVBox.setAlignment(Pos.CENTER);
        descriptionVBox.prefWidthProperty().bind(Bindings.subtract(descriptionHBox.widthProperty(), 10));
        HBox.setMargin(descriptionVBox, new Insets(0, 5, 0, 5));

        descriptionLabel = new Label("Description: ");
        descriptionLabel.setFont(new Font(36));
        VBox.setMargin(descriptionLabel, new Insets(0, 0, 10, 0));

        errorDescriptionLabel = new Label("Please Add A Description");
        errorDescriptionLabel.setFont(new Font(12));
        errorDescriptionLabel.setTextFill(Color.RED);
        errorDescriptionLabel.setVisible(false);

        descriptionTextField = new TextField();
        descriptionTextField.setAlignment(Pos.CENTER);
        descriptionTextField.setPromptText("description");
        descriptionTextField.setFont(new Font(24));
        descriptionTextField.prefHeightProperty().bind(descriptionLabel.heightProperty());

        descriptionVBox.getChildren().addAll(descriptionLabel, errorDescriptionLabel, descriptionTextField);
        descriptionHBox.getChildren().add(descriptionVBox);
        screenVBox.getChildren().add(descriptionHBox);
    }

    protected HBox valueHBox;
    protected VBox valueVBox;
    protected Label valueLabel;
    protected TextField valueTextField;
    protected Label errorValueLabel;
    private void initValue() {

        valueHBox = new HBox();
        valueHBox.setAlignment(Pos.CENTER);
        valueHBox.prefWidthProperty().bind(screenVBox.widthProperty());
        VBox.setMargin(valueHBox, new Insets(20, 10, 20, 10));

        valueVBox = new VBox();
        valueVBox.setAlignment(Pos.CENTER);
        valueVBox.prefWidthProperty().bind(Bindings.subtract(valueHBox.widthProperty(), 10));
        HBox.setMargin(valueVBox, new Insets(0, 5, 0, 5));

        valueLabel = new Label("Value: ");
        valueLabel.setFont(new Font(36));
        VBox.setMargin(valueLabel, new Insets(0, 0, 10, 0));

        errorValueLabel = new Label("Please Insert A Double.");
        errorValueLabel.setFont(new Font(12));
        errorValueLabel.setTextFill(Color.RED);
        errorValueLabel.setVisible(false);

        valueTextField = new TextField();
        valueTextField.setAlignment(Pos.CENTER);
        valueTextField.setPromptText("ex. 100000.0");
        valueTextField.setFont(new Font(24));
        valueTextField.prefHeightProperty().bind(valueLabel.heightProperty());

        valueVBox.getChildren().addAll(valueLabel, errorValueLabel, valueTextField);
        valueHBox.getChildren().add(valueVBox);
        screenVBox.getChildren().add(valueHBox);
    }

    protected HBox unitsHBox;
    protected Label unitsLabel;
    protected ComboBox unitsComboBox;
    private void initUnits() {

        unitsHBox = new HBox();
        unitsHBox.setAlignment(Pos.CENTER);
        unitsHBox.prefWidthProperty().bind(screenVBox.widthProperty());
        VBox.setMargin(unitsHBox, new Insets(40, 10, 20, 10));

        unitsLabel = new Label("Units:");
        unitsLabel.setFont(new Font(36));
        HBox.setMargin(unitsLabel, new Insets(0, 30, 0, 0));

        unitsComboBox = new ComboBox();
        ArrayList<String> unitItems = new ArrayList<>();
        unitItems.add("per Day");
        unitItems.add("per Week");
        unitItems.add("per Month");
        unitItems.add("per Year");
        unitsComboBox.setItems(FXCollections.observableList(unitItems));
        unitsComboBox.prefHeightProperty().bind(unitsLabel.heightProperty());
        unitsComboBox.prefWidthProperty().bind(Bindings.multiply(unitsLabel.widthProperty(), 2));
        unitsComboBox.getSelectionModel().select(0);

        unitsHBox.getChildren().addAll(unitsLabel, unitsComboBox);
        screenVBox.getChildren().add(unitsHBox);
    }

    protected HBox buttonsHBox;
    protected Button backButton;
    protected Button saveButton;
    private void initButtons() {

        buttonsHBox = new HBox();
        buttonsHBox.setAlignment(Pos.BOTTOM_LEFT);
        buttonsHBox.prefWidthProperty().bind(screenVBox.widthProperty());
        VBox.setVgrow(buttonsHBox, Priority.ALWAYS);

        backButton = new Button();
        backButton.setText("Back");
        backButton.setFont(new Font(24));
        backButton.setPadding(new Insets(5, 20, 5, 20));
        HBox.setMargin(backButton, new Insets(10, 10, 10, 10));

        backButton.setOnAction(event -> {
            MoneyController controller = new MoneyController();
            controller.initialize(stage);
        });

        saveButton = new Button();
        saveButton.setText("Save");
        saveButton.setFont(backButton.getFont());
        saveButton.setPadding(backButton.getPadding());
        HBox.setMargin(saveButton, new Insets(10, 10, 10, 10));

        setSaveButtonOnAction();

        buttonsHBox.getChildren().addAll(backButton, saveButton);
        screenVBox.getChildren().add(buttonsHBox);
    }

    protected abstract void setSaveButtonOnAction();
    protected abstract void initValues();

    public void initialize(Stage stage) {

        super.initialize(stage);

        initScreenVBox();
        initMoneyLabel();
        initDescription();
        initValue();
        initUnits();
        initButtons();

        initValues();
    }
}
