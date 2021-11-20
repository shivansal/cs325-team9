package org.productivityApp.money.moneyOut;

import javafx.stage.Stage;
import org.productivityApp.BasicApplication;
import org.productivityApp.money.MoneyController;

public class NewMoneyOutObjectController extends MoneyOutObjectController {

    @Override
    protected void setSaveButtonOnAction() {

        saveButton.setOnAction(event -> {

            // reset error messages
            errorValueLabel.setVisible(false);

            String description = descriptionTextField.getText();

            double value;
            try {
                value = Double.parseDouble(valueTextField.getText());
            } catch(Exception e) {
                // notify user of invalid value by unhiding error label
                errorValueLabel.setVisible(true);
                return;
            }

            BasicApplication.getMoneyObject().addMoneyOutSource(description, value);

            MoneyController controller = new MoneyController();
            controller.initialize(stage);
        });
    }

    @Override
    protected void initValues() {
        // do nothing for new
    }

    public void initialize(Stage stage) {
        super.initialize(stage);
    }
}
