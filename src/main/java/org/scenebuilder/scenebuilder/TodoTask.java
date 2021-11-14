package org.scenebuilder.scenebuilder;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class TodoTask {

    private String taskName;
    private LocalDate taskDate;
    private LocalTime taskTime;
    private String taskRecurringKey;
    private boolean[] taskRecurringDays = {false, false, false, false, false, false, false}; // sun, mon, tue, wed, thu, fri, sat
    private String taskPriority;
    private ArrayList<String> taskCategories;

    public TodoTask(String taskName, LocalDate taskDate, LocalTime taskTime, String taskRecurringKey, boolean[] taskRecurringDays, String taskPriority, ArrayList<String> taskCategories) {
        this.taskName = taskName;
        this.taskDate = taskDate;
        this.taskTime = taskTime;
        this.taskRecurringKey = taskRecurringKey;
        this.taskRecurringDays = taskRecurringDays;
        this.taskPriority = taskPriority;
        this.taskCategories = taskCategories;
    }

    // copy constructor
    public TodoTask(TodoTask task) {
        taskName = task.getTaskName();
        taskDate = task.getTaskDate();
        taskTime = task.getTaskTime();
        taskRecurringKey = task.getTaskRecurringKey();
        taskRecurringDays = task.getTaskRecurringDays().clone();
        taskPriority = task.getTaskPriority();
        taskCategories = new ArrayList<>(task.getTaskCategories());
    }

    // getters
    public String getTaskName() {
        return taskName;
    }
    public LocalDate getTaskDate() {
        return taskDate;
    }
    public LocalTime getTaskTime() { return taskTime; }
    public String getTaskRecurringKey() {
        return taskRecurringKey;
    }
    public boolean[] getTaskRecurringDays() {
        return taskRecurringDays;
    }
    public String getTaskPriority() {
        return taskPriority;
    }
    public ArrayList<String> getTaskCategories() {
        return taskCategories;
    }

    // setters
    public void setTaskName(String name) {
        taskName = name;
    }
    public void setTaskDate(LocalDate date) {
        taskDate = date;
    }
    public void setTaskTime(LocalTime time) {
        taskTime = time;
    }
    public void setTaskRecurringKey(String key) {
        taskRecurringKey = key;
    }
    public void setTaskRecurringDays(boolean[] days) {
        taskRecurringDays = days.clone();
    }
    public void setTaskPriority(String priority) {
        taskPriority = priority;
    }
    public void setTaskCategories(ArrayList<String> taskCategories) {
        this.taskCategories = taskCategories;
    }

    // modifiers
    public void addTaskCategory(String category) {
        taskCategories.add(category);
    }
    public void removeTaskCategory(String category) {
        taskCategories.remove(category);
    }
    public void removeTaskCategory(int categoryIndex) { taskCategories.remove(categoryIndex); }

}
