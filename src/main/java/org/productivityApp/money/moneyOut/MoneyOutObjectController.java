package org.productivityApp.money.moneyOut;

import javafx.stage.Stage;
import org.productivityApp.money.MoneyObjectController;

public abstract class MoneyOutObjectController extends MoneyObjectController {

    @Override
    protected void setMoneyLabelText() {
        moneyLabel.setText("Money Out");
    }

    private int moneyOutSourceIndex;

    public void initialize(Stage stage) {

        super.initialize(stage);
    }
}