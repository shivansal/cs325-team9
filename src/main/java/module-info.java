module org.scenebuilder.scenebuilder {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires commons.csv;


    opens org.productivityApp to javafx.fxml;
    exports org.productivityApp;
    exports org.productivityApp.money;
    opens org.productivityApp.money to javafx.fxml;
    exports org.productivityApp.persistence;
    opens org.productivityApp.persistence to javafx.fxml;
    exports org.productivityApp.screen;
    opens org.productivityApp.screen to javafx.fxml;
    exports org.productivityApp.custom;
    opens org.productivityApp.custom to javafx.fxml;
    exports org.productivityApp.todo;
    opens org.productivityApp.todo to javafx.fxml;
    exports org.productivityApp.settings;
    opens org.productivityApp.settings to javafx.fxml;
    exports org.productivityApp.money.moneyIn;
    opens org.productivityApp.money.moneyIn to javafx.fxml;
    exports org.productivityApp.money.transactions;
    opens org.productivityApp.money.transactions to javafx.fxml;
    exports org.productivityApp.money.moneyOut;
    opens org.productivityApp.money.moneyOut to javafx.fxml;
}