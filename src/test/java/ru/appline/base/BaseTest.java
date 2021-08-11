package ru.appline.base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.appline.framework.managers.DriverManager;
import ru.appline.framework.managers.InitManager;
import ru.appline.framework.managers.PageManager;

public class BaseTest {

     // Менеджер страничек
    protected PageManager app = PageManager.getPageManager();

    // Менеджер WebDriver

    private final DriverManager driverManager = DriverManager.getDriverManager();

    @BeforeAll
    public static void beforeAll() {
        InitManager.initFramework();
    }

    @BeforeEach
    public void beforeEach() {
        driverManager.getDriver().get("https://www.dns-shop.ru/");
    }

    @AfterAll
    public static void afterAll() {
        InitManager.quitFramework();
    }
}
