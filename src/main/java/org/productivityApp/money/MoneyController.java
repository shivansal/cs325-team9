package org.productivityApp.money;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.productivityApp.BasicApplication;
import org.productivityApp.money.moneyIn.NewMoneyInObjectController;
import org.productivityApp.money.moneyIn.ViewMoneyInObjectController;
import org.productivityApp.money.moneyOut.NewMoneyOutObjectController;
import org.productivityApp.money.moneyOut.ViewMoneyOutObjectController;
import org.productivityApp.money.transactions.NewTransactionController;
import org.productivityApp.money.transactions.ViewTransactionController;
import org.productivityApp.screen.TabController;

public class MoneyController extends TabController {

    VBox screenVBox;
    private void initScreenVBox() {

        screenVBox = new VBox();
        screenVBox.setPrefWidth(anchorPane.getPrefWidth() - 20);
        screenVBox.setPrefHeight(anchorPane.getPrefHeight() - 120);
        screenVBox.setLayoutX(10);
        screenVBox.setLayoutY(110);

        anchorPane.getChildren().addAll(screenVBox);
    }

    Label netEarningsLabel;
    private void initNetEarningsLabel() {
        netEarningsLabel = new Label("Net Earnings Label");
        netEarningsLabel.setAlignment(Pos.CENTER);
        netEarningsLabel.setFont(new Font(24));
        netEarningsLabel.prefWidthProperty().bind(screenVBox.widthProperty());
        netEarningsLabel.setStyle("-fx-border-color: black");
        netEarningsLabel.setPadding(new Insets(5, 5, 5, 5));
        VBox.setMargin(netEarningsLabel, new Insets(10, 0, 10, 0));

        screenVBox.getChildren().add(netEarningsLabel);
    }

    HBox moneyInHeaderHBox;
    Label moneyInLabel;
    ComboBox moneyInComboBox;
    private void initMoneyInHeader() {

        moneyInHeaderHBox = new HBox();
        VBox.setMargin(moneyInHeaderHBox, new Insets(5, 0, 5, 0));

        moneyInLabel = new Label("Money In: ");
        moneyInLabel.setFont(new Font(24));
        HBox.setMargin(moneyInLabel, new Insets(0, 40, 0, 0));

        moneyInComboBox = new ComboBox();
        moneyInComboBox.prefWidthProperty().bind(Bindings.subtract(Bindings.subtract(netEarningsLabel.widthProperty(), moneyInLabel.widthProperty()), 40));
        moneyInComboBox.prefHeightProperty().bind(moneyInLabel.heightProperty());
        HBox.setMargin(moneyInComboBox, new Insets(0, 0, 0, 0));

        moneyInHeaderHBox.getChildren().addAll(moneyInLabel, moneyInComboBox);
        screenVBox.getChildren().add(moneyInHeaderHBox);
    }

    HBox moneyInButtons;
    Label moneyInFillerLabel;
    Button moneyInAddItemButton;
    Button moneyInViewItemDetailsButton;
    Button moneyInRemoveItemButton;
    private void initMoneyInButtons() {

        moneyInButtons = new HBox();
        VBox.setMargin(moneyInButtons, new Insets(1, 0, 0, 0));

        moneyInFillerLabel = new Label();
        moneyInFillerLabel.prefWidthProperty().bind(moneyInLabel.widthProperty());
        HBox.setMargin(moneyInFillerLabel, HBox.getMargin(moneyInComboBox));

        moneyInAddItemButton = new Button();
        moneyInAddItemButton.setText("Add Item");
        moneyInAddItemButton.setFont(new Font(15));
        HBox.setMargin(moneyInAddItemButton, new Insets(0, 10, 0, 42));

        moneyInAddItemButton.setOnAction(event -> {
            NewMoneyInObjectController controller = new NewMoneyInObjectController();
            controller.initialize(stage);
        });

        moneyInViewItemDetailsButton = new Button();
        moneyInViewItemDetailsButton.setText("View Item Details");
        moneyInViewItemDetailsButton.setFont(new Font(15));
        HBox.setMargin(moneyInViewItemDetailsButton, new Insets(0, 10, 0, 0));

        moneyInViewItemDetailsButton.setOnAction(event -> {
            ViewMoneyInObjectController controller = new ViewMoneyInObjectController();
            controller.initialize(stage, moneyInComboBox.getSelectionModel().getSelectedIndex());
        });

        moneyInRemoveItemButton = new Button();
        moneyInRemoveItemButton.setText("Remove Item");
        moneyInRemoveItemButton.setFont(new Font(15));
        HBox.setMargin(moneyInRemoveItemButton, new Insets(0, 0, 0, 0));

        moneyInRemoveItemButton.setOnAction(event -> {

            int index = moneyInComboBox.getSelectionModel().getSelectedIndex();
            moneyInComboBox.getItems().remove(index);
            BasicApplication.getMoneyObject().removeMoneyInSource(index);

            if(moneyInComboBox.getItems().size() == 0) {
                moneyInRemoveItemButton.setDisable(true);
                moneyInViewItemDetailsButton.setDisable(true);
                moneyInComboBox.getItems().add("None");
            }

            // reselect
            moneyInComboBox.getSelectionModel().select(0);

            updateNetEarnings();
        });

        moneyInButtons.getChildren().addAll(moneyInFillerLabel, moneyInAddItemButton, moneyInViewItemDetailsButton, moneyInRemoveItemButton);
        screenVBox.getChildren().add(moneyInButtons);
    }

    HBox moneyOutHeaderHBox;
    Label moneyOutLabel;
    ComboBox moneyOutComboBox;
    private void initMoneyOutHeader() {
        moneyOutHeaderHBox = new HBox();
        VBox.setMargin(moneyOutHeaderHBox, new Insets(15, 0, 5, 0));

        moneyOutLabel = new Label("Money Out: ");
        moneyOutLabel.setFont(new Font(20));
        HBox.setMargin(moneyOutLabel, HBox.getMargin(moneyInLabel));

        moneyOutComboBox = new ComboBox();
        moneyOutComboBox.prefWidthProperty().bind(moneyInComboBox.widthProperty());
        moneyOutComboBox.prefHeightProperty().bind(moneyOutLabel.heightProperty());
        HBox.setMargin(moneyOutComboBox, HBox.getMargin(moneyInComboBox));

        moneyOutHeaderHBox.getChildren().addAll(moneyOutLabel, moneyOutComboBox);
        screenVBox.getChildren().add(moneyOutHeaderHBox);
    }

    HBox moneyOutButtons;
    Label moneyOutFillerLabel;
    Button moneyOutAddItemButton;
    Button moneyOutViewItemDetailsButton;
    Button moneyOutRemoveItemButton;
    private void initMoneyOutButtons() {

        moneyOutButtons = new HBox();
        VBox.setMargin(moneyOutButtons, new Insets(1, 0, 0, 0));

        moneyOutFillerLabel = new Label();
        moneyOutFillerLabel.prefWidthProperty().bind(moneyOutLabel.widthProperty());
        HBox.setMargin(moneyOutFillerLabel, HBox.getMargin(moneyInComboBox));

        moneyOutAddItemButton = new Button();
        moneyOutAddItemButton.setText("Add Item");
        moneyOutAddItemButton.setFont(new Font(15));
        HBox.setMargin(moneyOutAddItemButton, HBox.getMargin(moneyInAddItemButton));

        moneyOutAddItemButton.setOnAction(event -> {
            NewMoneyOutObjectController controller = new NewMoneyOutObjectController();
            controller.initialize(stage);
        });

        moneyOutViewItemDetailsButton = new Button();
        moneyOutViewItemDetailsButton.setText("View Item Details");
        moneyOutViewItemDetailsButton.setFont(new Font(15));
        HBox.setMargin(moneyOutViewItemDetailsButton, HBox.getMargin(moneyInViewItemDetailsButton));

        moneyOutViewItemDetailsButton.setOnAction(event -> {
            ViewMoneyOutObjectController controller = new ViewMoneyOutObjectController();
            controller.initialize(stage, moneyOutComboBox.getSelectionModel().getSelectedIndex());
        });

        moneyOutRemoveItemButton = new Button();
        moneyOutRemoveItemButton.setText("Remove Item");
        moneyOutRemoveItemButton.setFont(new Font(15));
        HBox.setMargin(moneyOutRemoveItemButton, HBox.getMargin(moneyInRemoveItemButton));

        moneyOutRemoveItemButton.setOnAction(event -> {
            int index = moneyOutComboBox.getSelectionModel().getSelectedIndex();
            moneyOutComboBox.getItems().remove(index);
            BasicApplication.getMoneyObject().removeMoneyOutSource(index);

            if(moneyOutComboBox.getItems().size() == 0) {
                moneyOutRemoveItemButton.setDisable(true);
                moneyOutViewItemDetailsButton.setDisable(true);
                moneyOutComboBox.getItems().add("None");
            }

            // reselect
            moneyOutComboBox.getSelectionModel().select(0);

            updateNetEarnings();
        });

        moneyOutButtons.getChildren().addAll(moneyOutFillerLabel, moneyOutAddItemButton, moneyOutViewItemDetailsButton, moneyOutRemoveItemButton);
        screenVBox.getChildren().add(moneyOutButtons);
    }

    HBox availableFundsHBox;
    Label availableFundsLabel;
    Label availableFundsValueLabel;
    private void initAvailableFunds() {

        availableFundsHBox = new HBox();
        VBox.setMargin(availableFundsHBox, new Insets(30, 0, 0, 0));

        availableFundsLabel = new Label("Available Funds:");
        availableFundsLabel.setFont(new Font(24));
        availableFundsLabel.setAlignment(Pos.CENTER_LEFT);
        HBox.setMargin(availableFundsLabel, new Insets(0, 40, 0, 0));

        availableFundsValueLabel = new Label("Funds: $10000");
        availableFundsValueLabel.setFont(new Font(24));
        availableFundsValueLabel.setAlignment(Pos.CENTER);
        availableFundsValueLabel.setStyle("-fx-border-color: black");
        availableFundsValueLabel.setPadding(new Insets(5, 10, 5, 10));
        availableFundsValueLabel.prefWidthProperty().bind(Bindings.subtract(Bindings.subtract(netEarningsLabel.widthProperty(), availableFundsLabel.widthProperty()), 40));
        HBox.setMargin(availableFundsValueLabel, new Insets(0, 0, 0, 0));

        availableFundsLabel.prefHeightProperty().bind(availableFundsValueLabel.heightProperty());

        availableFundsHBox.getChildren().addAll(availableFundsLabel, availableFundsValueLabel);
        screenVBox.getChildren().add(availableFundsHBox);
    }

    HBox transactionsHeadingHBox;
    Label transactionsLabel;
    ScrollPane transactionsScrollPane;
    VBox transactionsVBox;
    private void initTransactions() {

        transactionsHeadingHBox = new HBox();
        VBox.setMargin(transactionsHeadingHBox, new Insets(20, 0, 0, 0));

        transactionsLabel = new Label("Transactions:");
        transactionsLabel.setFont(new Font(24));

        transactionsHeadingHBox.getChildren().add(transactionsLabel);

        transactionsScrollPane = new ScrollPane();
        transactionsScrollPane.setStyle("-fx-background-color: transparent");
        transactionsScrollPane.prefHeightProperty().bind(Bindings.multiply(transactionsLabel.heightProperty(), 7));
        transactionsScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        transactionsScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        transactionsVBox = new VBox();
        transactionsVBox.prefWidthProperty().bind(Bindings.subtract(transactionsScrollPane.widthProperty(), 10));
        transactionsVBox.prefHeightProperty().bind(Bindings.subtract(transactionsScrollPane.heightProperty(), 10));
        transactionsScrollPane.setContent(transactionsVBox);

        screenVBox.getChildren().addAll(transactionsHeadingHBox, transactionsScrollPane);
    }

    HBox transactionButtonsHBox;
    Button newTransactionButton;
    Button viewTransactionDetailsButton;
    Button removeTransactionButton;
    private void initTransactionButtons() {

        transactionButtonsHBox = new HBox();
        VBox.setMargin(transactionButtonsHBox, new Insets(30, 0, 0, 0));

        newTransactionButton = new Button();
        newTransactionButton.setFont(new Font(14));
        newTransactionButton.setText("New Transaction");
        HBox.setMargin(newTransactionButton, new Insets(0, 50, 0, 0));

        newTransactionButton.setOnAction(event -> {
            NewTransactionController controller = new NewTransactionController();
            controller.initialize(stage);
        });

        viewTransactionDetailsButton = new Button();
        viewTransactionDetailsButton.setFont(new Font(14));
        viewTransactionDetailsButton.setText("View Transaction Details");
        HBox.setMargin(viewTransactionDetailsButton, new Insets(0, 10, 0, 0));

        viewTransactionDetailsButton.setOnAction(event -> {
            ViewTransactionController controller = new ViewTransactionController();
            controller.initialize(stage, selectedTransactionIndex);
        });

        removeTransactionButton = new Button();
        removeTransactionButton.setFont(new Font(14));
        removeTransactionButton.setText("Remove Transaction");
        HBox.setMargin(removeTransactionButton, new Insets(0, 0, 0, 0));

        transactionButtonsHBox.getChildren().addAll(newTransactionButton, viewTransactionDetailsButton, removeTransactionButton);
        screenVBox.getChildren().add(transactionButtonsHBox);
    }

    private void initValues() {

        MoneyObject moneyObject = BasicApplication.getMoneyObject();

        // Net Money
        updateNetEarnings();

        // moneyInSources
        moneyObject.getMoneyInSources().forEach(source -> {
            moneyInComboBox.getItems().add(source.toString());
        });
        if (moneyInComboBox.getItems().size() == 0) {
            moneyInComboBox.getItems().add("None");
        }
        moneyInComboBox.getSelectionModel().select(0);

        // moneyOut Sources
        moneyObject.getMoneyOutSources().forEach(source -> {
            moneyOutComboBox.getItems().add(source.toString());
        });
        if (moneyOutComboBox.getItems().size() == 0) {
            moneyOutComboBox.getItems().add("None");
        }
        moneyOutComboBox.getSelectionModel().select(0);

        // available funds
        availableFundsValueLabel.setText(String.format("$ %.02f", moneyObject.getAvailableFunds()));
        updateAvailableFunds();

        // transactions
        moneyObject.getTransactions().forEach(transaction -> {
            transactionsVBox.getChildren().add(getTransactionNode(transaction));
        });

        // disable relevant buttons
        if(moneyInComboBox.getItems().get(0).equals("None")) {
            moneyInViewItemDetailsButton.setDisable(true);
            moneyInRemoveItemButton.setDisable(true);
        }
        if(moneyOutComboBox.getItems().get(0).equals("None")) {
            moneyOutViewItemDetailsButton.setDisable(true);
            moneyOutRemoveItemButton.setDisable(true);
        }

        // always initially disable until selection is made
        viewTransactionDetailsButton.setDisable(true);
        removeTransactionButton.setDisable(true);
    }

    private int selectedTransactionIndex = -1;

    public void initialize(Stage stage) {

        super.initialize(stage, "Money");

        initScreenVBox();
        initNetEarningsLabel();
        initMoneyInHeader();
        initMoneyInButtons();
        initMoneyOutHeader();
        initMoneyOutButtons();
        initAvailableFunds();
        initTransactions();
        initTransactionButtons();

        initValues();
    }

    public HBox getTransactionNode(MoneyObject.Transaction transaction) {

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.prefWidthProperty().bind(Bindings.subtract(transactionsVBox.widthProperty(), 20));
        VBox.setMargin(hbox, new Insets(2, 0, 2, 0));

        // bullet point
        Circle circle = new Circle();
        circle.setFill(Color.BLACK);
        circle.setRadius(6);
        HBox.setMargin(circle, new Insets(0, 2, 0, 2));

        // date
        Label dateLabel = new Label();
        dateLabel.setAlignment(Pos.CENTER);
        dateLabel.setFont(new Font(18));
        dateLabel.prefWidthProperty().bind(Bindings.multiply(hbox.widthProperty(), 0.25));
        dateLabel.setText(transaction.getDate().toString());
        dateLabel.setStyle("-fx-border-color: black");
        dateLabel.setPadding(new Insets(5, 5, 5, 5));
        HBox.setMargin(dateLabel, new Insets(0, 2, 0, 2));

        // description
        Label descriptionLabel = new Label();
        descriptionLabel.setAlignment(Pos.CENTER);
        descriptionLabel.prefWidthProperty().bind(Bindings.multiply(hbox.widthProperty(), 0.5));
        descriptionLabel.setFont(dateLabel.getFont());
        descriptionLabel.setText(transaction.getDescription());
        descriptionLabel.setStyle("-fx-border-color: black");
        descriptionLabel.setPadding(dateLabel.getPadding());
        HBox.setMargin(descriptionLabel, HBox.getMargin(dateLabel));
        HBox.setHgrow(descriptionLabel, Priority.ALWAYS);

        // value
        Label valueLabel = new Label();
        valueLabel.setAlignment(Pos.CENTER);
        valueLabel.prefWidthProperty().bind(Bindings.multiply(hbox.widthProperty(), 0.2));
        valueLabel.setFont(dateLabel.getFont());
        valueLabel.setPadding(descriptionLabel.getPadding());
        valueLabel.setStyle("-fx-border-color: black");

        if(transaction.getValue() >= 0) {
            valueLabel.setText("+ " + transaction.getValue());
        } else {
            valueLabel.setText("- " + transaction.getValue() * (-1));
        }

        hbox.getChildren().addAll(circle, dateLabel, descriptionLabel, valueLabel);
        return hbox;
    }

    public void updateNetEarnings() {

        // add MoneyInSources
        double earningsIn = BasicApplication.getMoneyObject().getMoneyInSources().stream().mapToDouble(MoneyObject.MoneyInObject::getValue).sum();

        // subtract MoneyOutSources
        double earningsOut = BasicApplication.getMoneyObject().getMoneyOutSources().stream().mapToDouble(MoneyObject.MoneyOutObject::getValue).sum();

        // set label
        double net = earningsIn - earningsOut;
        String sign = net >= 0 ? " + " : " - ";
        netEarningsLabel.setText(sign + String.format("$ %.02f", Math.abs(net)));

        // set value in object
        BasicApplication.getMoneyObject().setNetMoney(net);
    }
    public void updateAvailableFunds() {
        double net = BasicApplication.getMoneyObject().getTransactions().stream().mapToDouble(transaction -> transaction.getValue()).sum();
        BasicApplication.getMoneyObject().setAvailableFunds(net);
        String sign = net >= 0 ? "+ " : "- ";
        availableFundsValueLabel.setText(sign + "$" + String.format("%.02f", Math.abs(net)));
    }
}
