package org.productivityApp.money.moneyOut;

import javafx.stage.Stage;
import org.productivityApp.BasicApplication;
import org.productivityApp.money.MoneyController;
import org.productivityApp.money.MoneyObject;

public class ViewMoneyOutObjectController extends MoneyOutObjectController {

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

            BasicApplication.getMoneyObject().setMoneyOutSource(moneyOutObjectIndex, description, value);

            MoneyController controller = new MoneyController();
            controller.initialize(stage);
        });
    }

    @Override
    protected void initValues() {

        MoneyObject.MoneyOutObject moneyOutObject;
        moneyOutObject = BasicApplication.getMoneyObject().getMoneyOutSource(moneyOutObjectIndex);

        // description
        descriptionTextField.setText(moneyOutObject.getDescription());

        // value
        valueTextField.setText(Double.toString(moneyOutObject.getValue()));

        // units
        // set in settings, unimplemented for now todo add units in settings and sync
        unitsComboBox.getSelectionModel().select(0);
    }

    private int moneyOutObjectIndex;

    public void initialize(Stage stage, int moneyOutObjectIndex) {

        this.moneyOutObjectIndex = moneyOutObjectIndex;
        super.initialize(stage);
    }
}
