package org.productivityApp.screen;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.productivityApp.BasicApplication;
import org.productivityApp.money.MoneyController;
import org.productivityApp.settings.SettingsController;
import org.productivityApp.todo.TodoController;
import org.productivityApp.todo.TodoTask;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TabController extends ScreenController {

    public static Timeline clock;

    protected Label currentDateLabel;
    protected Label currentTimeLabel;
    private void initCurrentDay(double x, double y) {

        currentDateLabel = new Label();
        currentTimeLabel = new Label();

        currentDateLabel.setFont(new Font(18));
        currentDateLabel.setLayoutX(x);
        currentDateLabel.setLayoutY(y);
        currentDateLabel.setAlignment(Pos.CENTER);
        currentDateLabel.setStyle("-fx-border-color: black; -fx-border-radius: 5 5 5 5");
        currentDateLabel.setPrefWidth(270);
        currentDateLabel.setPrefHeight(25);

        currentTimeLabel.setFont(new Font(18));
        currentTimeLabel.setLayoutX(x);
        currentTimeLabel.setLayoutY(y + 35);
        currentTimeLabel.setAlignment(Pos.CENTER);
        currentTimeLabel.setStyle("-fx-border-color: black; -fx-border-radius: 5 5 5 5");
        currentTimeLabel.prefWidthProperty().bind(currentDateLabel.widthProperty());
        currentTimeLabel.prefHeightProperty().bind(currentDateLabel.heightProperty());

        anchorPane.getChildren().addAll(currentDateLabel, currentTimeLabel);
    }

    protected Label todoTabLabel;
    protected Label moneyTabLabel;
    protected Label settingsTabLabel;
    private void initTabs(double x, double y, String menu) {

        todoTabLabel = new Label("Todo");
        todoTabLabel.setFont(new Font(18));
        todoTabLabel.setLayoutX(x);
        todoTabLabel.setLayoutY(y);
        todoTabLabel.setAlignment(Pos.CENTER);
        todoTabLabel.setPrefWidth(150);
        todoTabLabel.setPrefHeight(30);
        todoTabLabel.setStyle("-fx-border-color: black; -fx-border-radius: 5 5 0 5");
        todoTabLabel.setOnMouseClicked(event -> {
            TodoController controller = new TodoController();
            controller.initialize(stage);
        });

        moneyTabLabel = new Label("My Money");
        moneyTabLabel.setFont(new Font(18));
        moneyTabLabel.setLayoutX(x);
        moneyTabLabel.setLayoutY(y + 29);
        moneyTabLabel.setAlignment(Pos.CENTER);
        moneyTabLabel.setPrefWidth(150);
        moneyTabLabel.setPrefHeight(30);
        moneyTabLabel.setStyle("-fx-border-color: black; -fx-border-radius: 5 0 0 5");
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
        settingsTabLabel.setStyle("-fx-border-color: black; -fx-border-radius: 5 0 5 5");
        settingsTabLabel.setOnMouseClicked(event -> {
            SettingsController controller = new SettingsController();
            controller.initialize(stage);
        });

        if(menu.equals("Todo")) {
            todoTabLabel.setLayoutX(x - 50);
            todoTabLabel.setPrefWidth(200);
            todoTabLabel.setStyle("-fx-border-color: black; -fx-background-color: #d7d6db; -fx-border-radius: 5 5 0 5");
        } else if(menu.equals("Money")) {
            moneyTabLabel.setLayoutX(x - 50);
            moneyTabLabel.setPrefWidth(200);
            moneyTabLabel.setStyle("-fx-border-color: black; -fx-background-color: #d7d6db; -fx-border-radius: 5 0 0 5");
        } else if(menu.equals("Settings")) {
            settingsTabLabel.setLayoutX(x - 50);
            settingsTabLabel.setPrefWidth(200);
            settingsTabLabel.setStyle("-fx-border-color: black; -fx-background-color: #d7d6db; -fx-border-radius: 5 0 5 5");
        } else {
            System.out.println("Invalid Menu Key");
        }

        anchorPane.getChildren().addAll(todoTabLabel, moneyTabLabel, settingsTabLabel);
    }

    public void initialize(Stage stage, String menu) {

        super.initialize(stage);

        initCurrentDay(10, 10);
        initTabs(360, 10, menu);

        // set timer for time and date
        if(clock != null) {
            clock.stop();
        }

        clock = new Timeline(new KeyFrame(Duration.ONE, e -> {
            updateDateTime();
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

        /*
        BasicApplication.timer = new Timer();
        BasicApplication.timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> updateDateTime());
            }
        }, 0, 60000);
        */
    }

    private void updateDateTime() {
        //System.out.println("Update Time: " + LocalDateTime.now());

        Locale locale = new Locale("en", "US");
        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(locale);
        dateFormatSymbols.setWeekdays(new String[]{"Unused", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"});

        String pattern = "EEEEE, MMMMM dd, yyyy";
        String patternTime = "hh:mm:ss aa";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, dateFormatSymbols);
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat(patternTime);

        Date myDate = new Date();
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(BasicApplication.getSettingsObject().getTimeZone()));
        simpleTimeFormat.setTimeZone(TimeZone.getTimeZone(BasicApplication.getSettingsObject().getTimeZone()));

        String date = simpleDateFormat.format(myDate);
        String time = simpleTimeFormat.format(myDate);

        currentDateLabel.setText(date);
        currentTimeLabel.setText(time);

        if(BasicApplication.getSettingsObject().getNotificationOption().equals("All")) {
            sendNotifications();
            BasicApplication.getSettingsObject().setLastNotificationCheck(LocalDateTime.now());
        }
    }

    private void sendNotifications() {

        int prior = BasicApplication.getSettingsObject().getNotificationsPrior();
        ChronoUnit unit = getChronoUnitFromString(BasicApplication.getSettingsObject().getUnits());

        LocalDateTime now = LocalDateTime.now();

        BasicApplication.getTodoTasks().forEach(task -> {

            LocalDateTime taskLocalDateTime = LocalDateTime.of(task.getTaskDate(), task.getTaskTime());

            // don't check tasks that have already received notifications
            int timeUntil = (int)BasicApplication.getSettingsObject().getLastNotificationCheck().until(taskLocalDateTime, unit);
            if(timeUntil < prior) {
                return;
            }

            double diff = now.until(taskLocalDateTime, unit);
            if (diff <= prior) {

                showNotification(task);
            }
        });
    }

    private ChronoUnit getChronoUnitFromString(String str) {

        // sec, min, hour, day
        if(str.equals("seconds")) {
            return ChronoUnit.SECONDS;
        } else if(str.equals("minutes")) {
            return ChronoUnit.MINUTES;
        } else if (str.equals("hours")) {
            return ChronoUnit.HOURS;
        } else if (str.equals("days")) {
            return ChronoUnit.DAYS;
        }

        System.out.print("Invalid Units: " + str);
        return null;
    }

    private void showNotification(TodoTask todoTask) {

        Stage newStage = new Stage();
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefWidth(400);
        anchorPane.setPrefHeight(200);

        VBox notifVBox = new VBox();
        notifVBox.setLayoutX(3);
        notifVBox.setLayoutY(3);
        notifVBox.setAlignment(Pos.CENTER);
        notifVBox.prefWidthProperty().bind(Bindings.subtract(anchorPane.widthProperty(), 6));
        notifVBox.prefHeightProperty().bind(Bindings.subtract(anchorPane.heightProperty(), 6));
        notifVBox.setStyle("-fx-border-color: black");

        Label notifLabel = new Label();
        notifLabel.setAlignment(Pos.CENTER);
        notifLabel.setWrapText(true);
        notifLabel.setText(todoTask.getTaskName() + " is due in " + BasicApplication.getSettingsObject().getNotificationsPrior() + " " + BasicApplication.getSettingsObject().getUnits());
        notifLabel.setFont(new Font(24));
        VBox.setMargin(notifLabel, new Insets(10, 10, 10, 10));
        VBox.setVgrow(notifLabel, Priority.ALWAYS);

        HBox buttonsHBox = new HBox();
        buttonsHBox.prefWidthProperty().bind(notifVBox.widthProperty());
        buttonsHBox.setAlignment(Pos.BOTTOM_RIGHT);
        VBox.setVgrow(buttonsHBox, Priority.ALWAYS);

        Button okButton = new Button();
        okButton.setText("Ok");
        okButton.setFont(new Font(24));
        HBox.setMargin(okButton, new Insets(10, 10, 10, 10));
        okButton.setOnAction(event -> {
            newStage.close();
        });

        buttonsHBox.getChildren().add(okButton);

        notifVBox.getChildren().addAll(notifLabel, buttonsHBox);
        anchorPane.getChildren().add(notifVBox);

        // set stage parameters
        Scene newScene = new Scene(anchorPane);
        newStage.setScene(newScene);
        newStage.setResizable(false);
        newStage.initStyle(StageStyle.UTILITY);
        newStage.show();
    }

}
