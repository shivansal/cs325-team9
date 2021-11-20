package org.productivityApp.screen;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.productivityApp.todo.TodoController;
import org.productivityApp.money.MoneyController;
import org.productivityApp.settings.SettingsController;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TabController extends ScreenController {

    protected Label currentDateLabel;
    protected Label currentTimeLabel;
    private void initCurrentDay(double x, double y) {

        currentDateLabel = new Label();
        currentTimeLabel = new Label();

        Locale locale = new Locale("en", "US");
        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(locale);
        dateFormatSymbols.setWeekdays(new String[]{"Unused", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"});

        String pattern = "EEEEE, MMMMM dd, yyyy";
        String patternTime = "hh:mm aa";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, dateFormatSymbols);
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat(patternTime);
        String date = simpleDateFormat.format(new Date());
        String time = simpleTimeFormat.format(new Date());

        currentDateLabel.setText(date);
        currentDateLabel.setFont(new Font(18));
        currentDateLabel.setLayoutX(x);
        currentDateLabel.setLayoutY(y);
        currentDateLabel.setAlignment(Pos.CENTER);
        currentDateLabel.setStyle("-fx-border-color: black");
        currentDateLabel.setPrefWidth(270);
        currentDateLabel.setPrefHeight(25);

        currentTimeLabel.setText(time);
        currentTimeLabel.setFont(new Font(18));
        currentTimeLabel.setLayoutX(x);
        currentTimeLabel.setLayoutY(y + 35);
        currentTimeLabel.setAlignment(Pos.CENTER);
        currentTimeLabel.setStyle("-fx-border-color: black");
        currentTimeLabel.prefWidthProperty().bind(currentDateLabel.widthProperty());
        currentTimeLabel.prefHeightProperty().bind(currentDateLabel.heightProperty());

        anchorPane.getChildren().addAll(currentDateLabel, currentTimeLabel);
    }

    protected Label todoTabLabel;
    protected Label moneyTabLabel;
    protected Label settingsTabLabel;
    private void initTabs(double x, double y, String menu) {

        todoTabLabel = new Label("TODO");
        todoTabLabel.setFont(new Font(18));
        todoTabLabel.setLayoutX(x);
        todoTabLabel.setLayoutY(y);
        todoTabLabel.setAlignment(Pos.CENTER);
        todoTabLabel.setPrefWidth(150);
        todoTabLabel.setPrefHeight(30);
        todoTabLabel.setStyle("-fx-border-color: black;");
        todoTabLabel.setOnMouseClicked(event -> {
            TodoController controller = new TodoController();
            controller.initialize(stage);
        });

        moneyTabLabel = new Label("Money");
        moneyTabLabel.setFont(new Font(18));
        moneyTabLabel.setLayoutX(x);
        moneyTabLabel.setLayoutY(y + 29);
        moneyTabLabel.setAlignment(Pos.CENTER);
        moneyTabLabel.setPrefWidth(150);
        moneyTabLabel.setPrefHeight(30);
        moneyTabLabel.setStyle("-fx-border-color: black");
        moneyTabLabel.setOnMouseClicked(event -> {
            MoneyController controller = new MoneyController();
            controller.initialize(stage);
        });

        settingsTabLabel = new Label("Settings");
        settingsTabLabel.setFont(new Font(18));
        settingsTabLabel.setLayoutX(x);
        settingsTabLabel.setLayoutY(y + 58);
        settingsTabLabel.setAlignment(Pos.CENTER);
        settingsTabLabel.setPrefWidth(150);
        settingsTabLabel.setPrefHeight(30);
        settingsTabLabel.setStyle("-fx-border-color: black");
        settingsTabLabel.setOnMouseClicked(event -> {
            SettingsController controller = new SettingsController();
            controller.initialize(stage);
        });

        if(menu.equals("Todo")) {
            todoTabLabel.setLayoutX(x - 50);
            todoTabLabel.setPrefWidth(200);
            todoTabLabel.setStyle("-fx-border-color: black; -fx-background-color: #d7d6db");
        } else if(menu.equals("Money")) {
            moneyTabLabel.setLayoutX(x - 50);
            moneyTabLabel.setPrefWidth(200);
            moneyTabLabel.setStyle("-fx-border-color: black; -fx-background-color: #d7d6db");
        } else if(menu.equals("Settings")) {
            settingsTabLabel.setLayoutX(x - 50);
            settingsTabLabel.setPrefWidth(200);
            settingsTabLabel.setStyle("-fx-border-color: black; -fx-background-color: #d7d6db");
        } else {
            System.out.println("Invalid Menu Key");
        }

        anchorPane.getChildren().addAll(todoTabLabel, moneyTabLabel, settingsTabLabel);
    }

    public void initialize(Stage stage, String menu) {

        super.initialize(stage);

        initCurrentDay(10, 10);
        initTabs(360, 10, menu);
    }
}
