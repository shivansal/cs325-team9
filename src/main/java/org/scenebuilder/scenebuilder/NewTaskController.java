package org.scenebuilder.scenebuilder;

import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class NewTaskController extends TaskController {

    @Override
    protected void setInitialNameValue() {
        int numTasks = BasicApplication.getTodoTasks().size();
        taskNameTextField.setText("Task " + numTasks);
    }

    @Override
    protected void setInitialDateValue() {
        LocalDate rightNow = LocalDate.now();
        datePicker.setValue(rightNow);
    }

    @Override
    protected void setInitialTimeValue() {

        LocalTime localTime = LocalTime.now();
        loadInTime(localTime);
    }

    @Override
    protected void setInitialRecurringValue() {
        recurringComboBox.getSelectionModel().select(0);
    }

    @Override
    protected void setInitialRecurringDayValues() {
        // nothing needs to be done for new
    }

    @Override
    protected void setInitialPriorityValue() {
        priorityComboBox.getSelectionModel().select(2);
    }

    @Override
    protected void setSaveTaskButtonOnAction() {
        saveTaskButton.setOnAction(event -> {

            // update values
            selectedTask.setTaskName(taskNameTextField.getText());
            selectedTask.setTaskDate(datePicker.getValue());
            selectedTask.setTaskTime(getTime());
            selectedTask.setTaskRecurringKey((String)recurringComboBox.getValue());
            boolean[] dayVals = {
                    sunToggleButton.isSelected(),
                    monToggleButton.isSelected(),
                    tueToggleButton.isSelected(),
                    wedToggleButton.isSelected(),
                    thuToggleButton.isSelected(),
                    friToggleButton.isSelected(),
                    satToggleButton.isSelected()
            };
            selectedTask.setTaskRecurringDays(dayVals);
            selectedTask.setTaskPriority((String)priorityComboBox.getValue());
            selectedTask.setTaskCategories(nodeListToCategoryList());

            // save task
            BasicApplication.addTodoTask(selectedTask);

            // switch screens
            TodoController controller = new TodoController();
            controller.initialize(stage);
        });
    }

    protected void initializeSelectedTask() {

        boolean[] toggleButtonVals =  { sunToggleButton.isSelected(),
                monToggleButton.isSelected(),
                tueToggleButton.isSelected(),
                wedToggleButton.isSelected(),
                thuToggleButton.isSelected(),
                friToggleButton.isSelected(),
                satToggleButton.isSelected()};

        selectedTask = new TodoTask(
                taskNameTextField.getText(),
                datePicker.getValue(),
                getTime(),
                (String)recurringComboBox.getValue(),
                toggleButtonVals,
                (String)priorityComboBox.getValue(),
                new ArrayList<>()
        );

        selectedTaskIndex = -1;
    }

    public void initialize(Stage stage) {
        super.initialize(stage);

        initializeSelectedTask();
    }

}
