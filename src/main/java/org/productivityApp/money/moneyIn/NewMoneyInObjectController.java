package org.productivityApp.money.moneyIn;

import javafx.stage.Stage;
import org.productivityApp.BasicApplication;
import org.productivityApp.money.MoneyController;
import org.productivityApp.money.moneyIn.MoneyInObjectController;

public class NewMoneyInObjectController extends MoneyInObjectController {

    @Override
    protected void setSaveButtonOnAction() {

        saveButton.setOnAction(event -> {

            // reset error messages
            boolean errorThrown = false;
            errorDescriptionLabel.setVisible(false);
            errorValueLabel.setVisible(false);

            String description = descriptionTextField.getText();
            if (description.length() == 0) {
                errorDescriptionLabel.setVisible(true);
                errorThrown = true;
            }

            double value = 0;
            try {
                value = Double.parseDouble(valueTextField.getText());
            } catch(Exception e) {
                // notify user of invalid value by unhiding error label
                errorValueLabel.setVisible(true);
                errorThrown = true;
            }

            if(errorThrown == true) {
                return;
            }

            BasicApplication.getMoneyObject().addMoneyInSource(description, value);

            MoneyController controller = new MoneyController();
            controller.initialize(stage);
        });
    }

    @Override
    protected void initValues() {

    }

    public void initialize(Stage stage) {
        super.initialize(stage);
    }
}
