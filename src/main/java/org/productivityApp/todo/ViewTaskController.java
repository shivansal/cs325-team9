package org.productivityApp.todo;

import javafx.stage.Stage;
import org.productivityApp.BasicApplication;
import org.productivityApp.todo.TaskController;
import org.productivityApp.todo.TodoController;
import org.productivityApp.todo.TodoTask;

import java.time.LocalTime;

public class ViewTaskController extends TaskController {

    @Override
    protected void setInitialNameValue() {
        taskNameTextField.setText(selectedTask.getTaskName());
    }

    @Override
    protected void setInitialDateValue() {
        datePicker.setValue(selectedTask.getTaskDate());
    }

    @Override
    protected void setInitialTimeValue() {

        LocalTime localTime = selectedTask.getTaskTime();
        loadInTime(localTime);
    }

    @Override
    protected void setInitialRecurringValue() {
        recurringComboBox.getSelectionModel().select(selectedTask.getTaskRecurringKey());
    }

    @Override
    protected void setInitialRecurringDayValues() {
        boolean[] selectedVals = selectedTask.getTaskRecurringDays();
        sunToggleButton.setSelected(selectedVals[0]);
        monToggleButton.setSelected(selectedVals[1]);
        tueToggleButton.setSelected(selectedVals[2]);
        wedToggleButton.setSelected(selectedVals[3]);
        thuToggleButton.setSelected(selectedVals[4]);
        friToggleButton.setSelected(selectedVals[5]);
        satToggleButton.setSelected(selectedVals[6]);
    }

    @Override
    protected void setInitialPriorityValue() {
        priorityComboBox.getSelectionModel().select(selectedTask.getTaskPriority());
    }

    @Override
    protected void setInitialCategories() {
        super.setInitialCategories();

        selectedTask.getTaskCategories().forEach((category) -> {
            addCategoryNode(category);
        });
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
            BasicApplication.setTodoTask(selectedTaskIndex, selectedTask);

            // switch screens
            TodoController controller = new TodoController();
            controller.initialize(stage);
        });
    }

    public void initialize(Stage stage, TodoTask task, int taskIndex) {

        selectedTask = new TodoTask(task);
        selectedTaskIndex = taskIndex;

        super.initialize(stage);

    }
}
