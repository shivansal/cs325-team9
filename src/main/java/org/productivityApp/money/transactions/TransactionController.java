package org.productivityApp.money.transactions;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.productivityApp.money.MoneyController;
import org.productivityApp.screen.ScreenController;

import java.util.ArrayList;

public abstract class TransactionController extends ScreenController {

    protected VBox screenVBox;
    private void initScreenVBox() {

        screenVBox = new VBox();
        screenVBox.setLayoutX(5);
        screenVBox.setLayoutY(5);
        screenVBox.prefWidthProperty().bind(Bindings.subtract(anchorPane.prefWidthProperty(), 10));
        screenVBox.prefHeightProperty().bind(Bindings.subtract(anchorPane.prefHeightProperty(), 10));

        anchorPane.getChildren().add(screenVBox);
    }

    protected HBox transactionLabelHBox;
    protected Label transactionLabel;
    private void initTransactionLabel() {

        transactionLabelHBox = new HBox();
        transactionLabelHBox.setAlignment(Pos.CENTER);
        transactionLabelHBox.prefWidthProperty().bind(Bindings.subtract(screenVBox.widthProperty(), 0));
        transactionLabelHBox.setStyle("-fx-border-color: black; -fx-border-radius: 5 5 5 5");
        VBox.setMargin(transactionLabelHBox, new Insets(10, 5, 10, 5));

        transactionLabel = new Label();
        transactionLabel.setText("Transaction");
        transactionLabel.setFont(new Font(36));
        HBox.setMargin(transactionLabel, new Insets(10, 10, 10, 10));

        transactionLabelHBox.getChildren().add(transactionLabel);
        screenVBox.getChildren().add(transactionLabelHBox);
    }

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
        setInitialDescription();

        descriptionVBox.getChildren().addAll(descriptionLabel, errorDescriptionLabel, descriptionTextField);
        descriptionHBox.getChildren().add(descriptionVBox);
        screenVBox.getChildren().add(descriptionHBox);
    }
    protected abstract void setInitialDescription();

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
        setInitialValue();

        valueVBox.getChildren().addAll(valueLabel, errorValueLabel, valueTextField);
        valueHBox.getChildren().add(valueVBox);
        screenVBox.getChildren().add(valueHBox);
    }
    protected abstract void setInitialValue();

    protected HBox dateHBox;
    protected Label dateLabel;
    protected Label errorDateLabel;
    protected DatePicker datePicker;
    private void initDate() {

        dateHBox = new HBox();
        dateHBox.setAlignment(Pos.CENTER);
        dateHBox.prefWidthProperty().bind(screenVBox.widthProperty());
        VBox.setMargin(dateHBox, new Insets(40, 10, 20, 10));

        dateLabel = new Label("Date:");
        dateLabel.setFont(new Font(36));
        HBox.setMargin(dateLabel, new Insets(0, 30, 0, 0));

        errorDateLabel = new Label("Invalid Date");
        errorDateLabel.setFont(new Font(12));
        errorDateLabel.setTextFill(Color.RED);
        errorDateLabel.setVisible(false);
        HBox.setMargin(errorDateLabel, new Insets(10, 10, 10, 10));

        datePicker = new DatePicker();
        setInitialDate();

        dateHBox.getChildren().addAll(dateLabel, datePicker, errorDateLabel);
        screenVBox.getChildren().add(dateHBox);
    }
    protected abstract void setInitialDate();

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

    public void initialize(Stage stage) {
        super.initialize(stage);

        initScreenVBox();
        initTransactionLabel();
        initDescription();
        initValue();
        initDate();
        initButtons();
    }
}
