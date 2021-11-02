package org.scenebuilder.scenebuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TodoTask {

    private String taskName;
    private LocalDateTime taskDateTime;
    private String taskRecurringKey;
    private boolean[] taskRecurringDays = {false, false, false, false, false, false, false}; // sun, mon, tue, wed, thu, fri, sat
    private String taskPriority;

    private ArrayList<String> taskCategories = new ArrayList<>();

    public TodoTask(String taskName, LocalDateTime taskDateTime, String taskRecurringKey, boolean[] taskRecurringDays, String taskPriority, ArrayList<String> taskCategories) {
        this.taskName = taskName;
        this.taskDateTime = taskDateTime;
        this.taskRecurringKey = taskRecurringKey;
        this.taskRecurringDays = taskRecurringDays;
        this.taskPriority = taskPriority;
        this.taskCategories = taskCategories;
    }

    public String getTaskName() {
        return taskName;
    }

    public LocalDateTime getLocalDateTime() {
        return taskDateTime;
    }

    public String getTaskRecurringKey() {
        return taskRecurringKey;
    }

    public boolean[] getTaskRecurringDays() {
        return taskRecurringDays;
    }

    public String getTaskPriority() {
        return taskPriority;
    }

    public void addTaskCategories(String category) {
        taskCategories.add(category);
    }

    public ArrayList<String> getTaskCategories() {
        return taskCategories;
    }
}
