package org.productivityApp.settings;

import java.time.LocalDateTime;

public class SettingsObject {

    private String notificationOption;
    private int notificationsPrior;
    private String units;   // seconds, minutes, hours, days

    private LocalDateTime lastNotificationCheck;

    private String theme;   // default, dark, more to come /todo unimplemented

    private String timeZone;

    public SettingsObject(String notificationOption, int notificationsPrior, String units, String theme, String timeZone) {

        this.notificationOption = notificationOption;
        this.notificationsPrior = notificationsPrior;
        this.units = units;

        this.theme = theme;

        this.timeZone = timeZone;
    }
    public SettingsObject() {

        this.notificationOption = "Off";
        this.notificationsPrior = 0;
        this.units = "seconds";

        this.lastNotificationCheck = LocalDateTime.MIN;

        this.theme = "Default";

        this.timeZone = "EST";
    }

    // setters
    public void setNotificationOption(String notificationOption) { this.notificationOption = notificationOption; }
    public void setNotificationsPrior(int notificationsPrior) { this.notificationsPrior = notificationsPrior; }
    public void setUnits(String units) { this.units = units; }
    public void setTheme(String theme) { this.theme = theme; }
    public void setTimeZone(String timeZone) { this.timeZone = timeZone; }
    public void setLastNotificationCheck(LocalDateTime localDateTime) { this.lastNotificationCheck = localDateTime; }

    // getters
    public String getNotificationOption() { return this.notificationOption; }
    public int getNotificationsPrior() { return this.notificationsPrior; }
    public String getUnits() { return this.units; }
    public String getTheme() { return this.theme; }
    public String getTimeZone() { return this.timeZone; }
    public LocalDateTime getLastNotificationCheck() { return this.lastNotificationCheck; }
}
