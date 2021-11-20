package org.productivityApp.money.moneyIn;

import javafx.stage.Stage;
import org.productivityApp.money.MoneyObjectController;

public abstract class MoneyInObjectController extends MoneyObjectController {

    @Override
    protected void setMoneyLabelText() {
        moneyLabel.setText("Money In");
    }

    public void initialize(Stage stage) {

        super.initialize(stage);
    }
}
