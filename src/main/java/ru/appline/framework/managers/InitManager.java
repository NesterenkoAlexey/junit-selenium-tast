package ru.appline.framework.managers;

import java.util.concurrent.TimeUnit;

public class InitManager {

    private static final DriverManager driverManager = DriverManager.getDriverManager();

    public static void initFramework() {

        driverManager.getDriver().manage().window().maximize();
        driverManager.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driverManager.getDriver().manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
    }

    public static void quitFramework() {
        driverManager.quitDriver();
    }
}
