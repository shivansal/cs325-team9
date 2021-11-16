package org.scenebuilder.scenebuilder;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

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

        moneyInViewItemDetailsButton = new Button();
        moneyInViewItemDetailsButton.setText("View Item Details");
        moneyInViewItemDetailsButton.setFont(new Font(15));
        HBox.setMargin(moneyInViewItemDetailsButton, new Insets(0, 10, 0, 0));

        moneyInRemoveItemButton = new Button();
        moneyInRemoveItemButton.setText("Remove Item");
        moneyInRemoveItemButton.setFont(new Font(15));
        HBox.setMargin(moneyInRemoveItemButton, new Insets(0, 0, 0, 0));

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

        moneyOutViewItemDetailsButton = new Button();
        moneyOutViewItemDetailsButton.setText("View Item Details");
        moneyOutViewItemDetailsButton.setFont(new Font(15));
        HBox.setMargin(moneyOutViewItemDetailsButton, HBox.getMargin(moneyInViewItemDetailsButton));

        moneyOutRemoveItemButton = new Button();
        moneyOutRemoveItemButton.setText("Remove Item");
        moneyOutRemoveItemButton.setFont(new Font(15));
        HBox.setMargin(moneyOutRemoveItemButton, HBox.getMargin(moneyInRemoveItemButton));

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

        transactionsVBox = new VBox();
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

        viewTransactionDetailsButton = new Button();
        viewTransactionDetailsButton.setFont(new Font(14));
        viewTransactionDetailsButton.setText("View Transaction Details");
        HBox.setMargin(viewTransactionDetailsButton, new Insets(0, 10, 0, 0));

        removeTransactionButton = new Button();
        removeTransactionButton.setFont(new Font(14));
        removeTransactionButton.setText("Remove Transaction");
        HBox.setMargin(removeTransactionButton, new Insets(0, 0, 0, 0));

        transactionButtonsHBox.getChildren().addAll(newTransactionButton, viewTransactionDetailsButton, removeTransactionButton);
        screenVBox.getChildren().add(transactionButtonsHBox);
    }

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
    }
}
