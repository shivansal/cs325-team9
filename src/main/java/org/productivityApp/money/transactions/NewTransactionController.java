package org.productivityApp.money.transactions;

import javafx.stage.Stage;
import org.productivityApp.BasicApplication;
import org.productivityApp.money.MoneyController;

import java.time.LocalDate;

public class NewTransactionController extends TransactionController {

    @Override
    protected void setInitialDescription() {
        descriptionTextField.setText("");
    }

    @Override
    protected void setInitialValue() {
        valueTextField.setText("");
    }

    @Override
    protected void setInitialDate() {
        datePicker.setValue(LocalDate.now());
    }

    @Override
    protected void setSaveButtonOnAction() {
        saveButton.setOnAction(event -> {

            // reset error messages
            boolean errorThrown = false;
            errorDescriptionLabel.setVisible(false);
            errorValueLabel.setVisible(false);
            errorDateLabel.setVisible(false);


            LocalDate date = LocalDate.now();
            try {
                date = datePicker.getValue();
            } catch (Exception e) {
                System.out.println(datePicker.getValue().toString());
                errorDateLabel.setVisible(true);
                errorThrown = true;
            }

            String description = descriptionTextField.getText();
            if (description.length() == 0) {
                errorDescriptionLabel.setVisible(true);
                errorThrown = true;
            }

            double value = -1;
            try {
                value = Double.parseDouble(valueTextField.getText());
            } catch (Exception e) {
                errorValueLabel.setVisible(true);
                errorThrown = true;
            }

            if (errorThrown == true) {
                return;
            }

            BasicApplication.getMoneyObject().addTransaction(date, description, value);
            MoneyController controller = new MoneyController();
            controller.initialize(stage);
        });
    }

    public void initialize(Stage stage) {
        super.initialize(stage);
    }
}
