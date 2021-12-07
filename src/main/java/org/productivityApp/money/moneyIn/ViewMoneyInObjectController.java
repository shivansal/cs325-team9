package org.productivityApp.money.moneyIn;

import javafx.stage.Stage;
import org.productivityApp.BasicApplication;
import org.productivityApp.money.MoneyController;
import org.productivityApp.money.MoneyObject;
import org.productivityApp.money.moneyIn.MoneyInObjectController;

public class ViewMoneyInObjectController extends MoneyInObjectController {

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
                // notify user of invalid value by showing error label
                errorValueLabel.setVisible(true);
                return;
            }

            BasicApplication.getMoneyObject().setMoneyInSource(moneyInObjectIndex, description, value);

            MoneyController controller = new MoneyController();
            controller.initialize(stage);
        });
    }

    @Override
    protected void initValues() {

        MoneyObject.MoneyInObject moneyInObject;
        moneyInObject = BasicApplication.getMoneyObject().getMoneyInSource(moneyInObjectIndex);

        // description
        descriptionTextField.setText(moneyInObject.getDescription());

        // value
        valueTextField.setText(Double.toString(moneyInObject.getValue()));

        // units
        // set in settings, unimplemented for now todo add units in settings and sync
        unitsComboBox.getSelectionModel().select(0);
    }

    private int moneyInObjectIndex;

    public void initialize(Stage stage, int moneyInObjectIndex) {

        this.moneyInObjectIndex = moneyInObjectIndex;
        super.initialize(stage);
    }
}
