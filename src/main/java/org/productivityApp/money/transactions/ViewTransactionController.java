package org.productivityApp.money.transactions;

import javafx.stage.Stage;
import org.productivityApp.BasicApplication;
import org.productivityApp.money.MoneyController;
import org.productivityApp.money.MoneyObject;

import java.time.LocalDate;

public class ViewTransactionController extends TransactionController {

    @Override
    protected void setInitialDescription() {
        descriptionTextField.setText(selectedTransaction.getDescription());
    }

    @Override
    protected void setInitialValue() {
        valueTextField.setText(Double.toString(selectedTransaction.getValue()));
    }

    @Override
    protected void setInitialDate() {
        datePicker.setValue(selectedTransaction.getDate());
    }

    @Override
    protected void setSaveButtonOnAction() {
        saveButton.setOnAction(event -> {

            // reset error messages
            boolean errorThrown = false;
            errorDescriptionLabel.setVisible(false);
            errorValueLabel.setVisible(true);
            errorDateLabel.setVisible(true);


            LocalDate date = LocalDate.now();
            try {
                date = datePicker.getValue();
            } catch (Exception e) {
                errorDateLabel.setVisible(true);
                errorThrown = true;
            }

            String description = "default description";
            try {
                description = descriptionTextField.getText();
            } catch (Exception e) {
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

            BasicApplication.getMoneyObject().setTransaction(selectedTransactionIndex, date, description, value);
            MoneyController controller = new MoneyController();
            controller.initialize(stage);
        });
    }

    private MoneyObject.Transaction selectedTransaction;
    private int selectedTransactionIndex;

    public void initialize(Stage stage, int selectedTransactionIndex) {

        this.selectedTransactionIndex = selectedTransactionIndex;
        this.selectedTransaction = BasicApplication.getMoneyObject().getTransaction(selectedTransactionIndex);

        super.initialize(stage);
    }
}
