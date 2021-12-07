package org.productivityApp.settings;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.productivityApp.BasicApplication;
import org.productivityApp.screen.TabController;
import org.productivityApp.todo.TodoTask;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import static org.productivityApp.BasicApplication.getSettingsObject;

public class SettingsController extends TabController {

    VBox screenVBox;
    private void initScreenVBox() {

        screenVBox = new VBox();
        screenVBox.setPrefWidth(anchorPane.getPrefWidth() - 20);
        screenVBox.setPrefHeight(anchorPane.getPrefHeight() - 120);
        screenVBox.setLayoutX(10);
        screenVBox.setLayoutY(110);

        anchorPane.getChildren().addAll(screenVBox);
    }

    HBox notificationsHBox;
    Label notificationsLabel;
    ComboBox notificationsComboBox;
    private void initNotifications() {

        notificationsHBox = new HBox();
        notificationsHBox.setAlignment(Pos.CENTER_LEFT);
        notificationsHBox.prefWidthProperty().bind(screenVBox.widthProperty());
        VBox.setMargin(notificationsHBox, new Insets(10, 0, 0, 10));

        notificationsLabel = new Label("Notifications: ");
        notificationsLabel.setFont(new Font(26));
        notificationsLabel.setAlignment(Pos.CENTER_LEFT);
        HBox.setMargin(notificationsLabel, new Insets(0, 50, 0, 0));

        notificationsComboBox = new ComboBox();
        notificationsComboBox.prefHeightProperty().bind(notificationsLabel.heightProperty());
        notificationsComboBox.prefWidthProperty().bind(Bindings.multiply(notificationsHBox.widthProperty(), 0.4));

        notificationsComboBox.setOnAction(event -> {
           BasicApplication.getSettingsObject().setNotificationOption((String)notificationsComboBox.getValue());
        });

        ArrayList<String> notificationOptions = new ArrayList<>();
        notificationOptions.add("Off");
        notificationOptions.add("All");
        notificationsComboBox.setItems(FXCollections.observableList(notificationOptions));

        notificationsHBox.getChildren().addAll(notificationsLabel, notificationsComboBox);
        screenVBox.getChildren().add(notificationsHBox);
    }

    HBox priorHBox;
    Label priorLabel;
    VBox priorVBox;
    Label priorErrorLabel;
    TextField priorTextField;
    ComboBox priorUnitsComboBox;
    private void initPrior() {

        priorHBox = new HBox();
        priorHBox.setAlignment(Pos.CENTER_LEFT);
        priorHBox.prefWidthProperty().bind(screenVBox.widthProperty());
        VBox.setMargin(priorHBox, new Insets(0, 10, 0, 10));

        priorLabel = new Label("Prior: ");
        priorLabel.setFont(new Font(20));
        priorLabel.setAlignment(Pos.CENTER_LEFT);
        HBox.setMargin(priorLabel, new Insets(18, 0, 0, 0));

        priorVBox = new VBox();
        priorVBox.setAlignment(Pos.BOTTOM_CENTER);
        priorVBox.prefHeightProperty().bind(priorLabel.heightProperty());
        HBox.setMargin(priorVBox, new Insets(0, 0, 0, 20));

        priorErrorLabel = new Label("Please Enter An Integer");
        priorErrorLabel.setFont(new Font(12));
        priorErrorLabel.setTextFill(Color.RED);
        priorErrorLabel.setVisible(false);

        priorTextField = new TextField();
        priorTextField.setFont(new Font(priorLabel.getFont().getSize() - 5));
        priorTextField.setPromptText("####");
        priorTextField.prefWidthProperty().bind(notificationsComboBox.widthProperty());
        HBox.setMargin(priorTextField, new Insets(0, 0, 0, 10));

        priorTextField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {

            if (newPropertyValue) {}
            else {
                try {
                    BasicApplication.getSettingsObject().setNotificationsPrior(Integer.parseInt(priorTextField.getText()));
                    priorErrorLabel.setVisible(false);
                } catch(Exception e) {

                    priorErrorLabel.setVisible(true);
                    priorTextField.setPromptText(Integer.toString(BasicApplication.getSettingsObject().getNotificationsPrior()));
                }
            }
        });

        priorVBox.getChildren().addAll(priorErrorLabel, priorTextField);

        priorUnitsComboBox = new ComboBox();
        priorUnitsComboBox.prefHeightProperty().bind(priorTextField.heightProperty());
        HBox.setMargin(priorUnitsComboBox, new Insets(18, 0, 0, 10));

        priorUnitsComboBox.setOnAction(event -> {
            BasicApplication.getSettingsObject().setUnits((String)priorUnitsComboBox.getValue());
        });

        ArrayList<String> unitsOptions = new ArrayList<>();
        unitsOptions.add("seconds");
        unitsOptions.add("minutes");
        unitsOptions.add("hours");
        unitsOptions.add("days");
        priorUnitsComboBox.setItems(FXCollections.observableList(unitsOptions));

        priorLabel.prefHeightProperty().bind(priorTextField.heightProperty());

        priorHBox.getChildren().addAll(priorLabel, priorVBox, priorUnitsComboBox);
        screenVBox.getChildren().add(priorHBox);
    }

    HBox themeHBox;
    Label themeLabel;
    ComboBox themeComboBox;
    private void initTheme() {

        themeHBox = new HBox();
        themeHBox.setAlignment(Pos.CENTER_LEFT);
        themeHBox.prefWidthProperty().bind(screenVBox.widthProperty());
        VBox.setMargin(themeHBox, new Insets(60, 0, 0, 10));

        themeLabel = new Label("Theme: ");
        themeLabel.setAlignment(Pos.CENTER_LEFT);
        themeLabel.setFont(notificationsLabel.getFont());
        themeLabel.prefWidthProperty().bind(notificationsLabel.widthProperty());

        themeComboBox = new ComboBox();
        themeComboBox.prefHeightProperty().bind(themeLabel.heightProperty());
        themeComboBox.prefWidthProperty().bind(notificationsComboBox.widthProperty());
        HBox.setMargin(themeComboBox, new Insets(0, 0, 0, 20));

        ArrayList<String> themeOptions = new ArrayList<>();
        themeOptions.add("Default");
        themeOptions.add("Dark");
        themeComboBox.setItems(FXCollections.observableList(themeOptions));

        themeHBox.getChildren().addAll(themeLabel, themeComboBox);
        screenVBox.getChildren().add(themeHBox);
    }

    HBox timeZoneHBox;
    Label timeZoneLabel;
    ComboBox timeZoneComboBox;
    private void initTimeZone() {

        timeZoneHBox = new HBox();
        timeZoneHBox.setAlignment(Pos.CENTER_LEFT);
        timeZoneHBox.prefWidthProperty().bind(screenVBox.widthProperty());
        VBox.setMargin(timeZoneHBox, new Insets(20, 0, 0, 10));

        timeZoneLabel = new Label("Time Zone: ");
        timeZoneLabel.setAlignment(Pos.CENTER_LEFT);
        timeZoneLabel.setFont(themeLabel.getFont());
        timeZoneLabel.prefWidthProperty().bind(notificationsLabel.widthProperty());

        timeZoneComboBox = new ComboBox();
        timeZoneComboBox.prefHeightProperty().bind(timeZoneLabel.heightProperty());
        timeZoneComboBox.prefWidthProperty().bind(themeComboBox.widthProperty());
        HBox.setMargin(timeZoneComboBox, new Insets(0, 0, 0, 20));

        timeZoneComboBox.setItems(FXCollections.observableList(List.of(TimeZone.getAvailableIDs())));

        timeZoneHBox.getChildren().addAll(timeZoneLabel, timeZoneComboBox);
        screenVBox.getChildren().add(timeZoneHBox);
    }

    private void initValue() {

        // init notifications
        notificationsComboBox.getSelectionModel().select(BasicApplication.getSettingsObject().getNotificationOption());

        // init prior
        priorTextField.setText(Integer.toString(BasicApplication.getSettingsObject().getNotificationsPrior()));
        priorUnitsComboBox.getSelectionModel().select(BasicApplication.getSettingsObject().getUnits());

        // init theme
        themeComboBox.getSelectionModel().select(BasicApplication.getSettingsObject().getTheme());

        // init time zone
        timeZoneComboBox.getSelectionModel().select(BasicApplication.getSettingsObject().getTimeZone());
    }

    public void initialize(Stage stage) {

        super.initialize(stage, "Settings");

        initScreenVBox();
        initNotifications();
        initPrior();
        initTheme();
        initTimeZone();

        initValue();
    }
}
