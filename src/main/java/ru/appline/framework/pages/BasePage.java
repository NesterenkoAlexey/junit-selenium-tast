package ru.appline.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.appline.framework.classes.Converter;
import ru.appline.framework.managers.DriverManager;
import ru.appline.framework.managers.PageManager;
import ru.appline.framework.managers.TestPropManager;

import java.util.concurrent.TimeUnit;

import static ru.appline.framework.utils.PropConst.IMPLICITLY_WAIT;

public class BasePage {

    /**
     * Менеджер WebDriver
     *
     * @see DriverManager#getDriverManager()
     */


    private static final TestPropManager props = TestPropManager.getTestPropManager();
    protected final DriverManager driverManager = DriverManager.getDriverManager();


    /**
     * Менеджер страничек
     *
     * @see PageManager
     */
    protected PageManager pageManager = PageManager.getPageManager();
    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), 15, 1000);
    protected JavascriptExecutor js = (JavascriptExecutor) driverManager.getDriver();

    @FindBy(xpath = "//a[@id = 'header-logo']")
    private WebElement mainPageElement;

    public BasePage() {

        PageFactory.initElements(driverManager.getDriver(), this);

    }
    // Скролл до любого эл-та
    protected WebElement scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }

    /**
     * Функция позволяющая производить scroll до любого элемента с помощью js со смещение
     * Смещение задается количеством пикселей по вертикали и горизонтали, т.е. смещение до точки (x, y)
     *
     * @param element - веб-элемент странички
     * @param x       - параметр координаты по горизонтали
     * @param y       - параметр координаты по вертикали
     * @see JavascriptExecutor
     */
    public WebElement scrollWithOffset(WebElement element, int x, int y) {
        String code = "window.scroll(" + (element.getLocation().x + x) + ","
                + (element.getLocation().y + y) + ");";
        ((JavascriptExecutor) driverManager.getDriver()).executeScript(code, element, x, y);
        return element;
    }

    //Вернуться на страртовую страницу
    public void backHome(){
        mainPageElement.click();
    }

    //Поиск элемента
    @FindBy(xpath = "//div[@class = 'header-menu-wrapper']//input")
    private WebElement searchMenu;

    @FindBy(xpath = "//div[@class = 'header-menu-wrapper']//span[@class = 'ui-input-search__icon ui-input-search__icon_search ui-input-search__icon_presearch']")
    private WebElement searchButton;

    public ProductPage searchProduct(String nameOfProduct){
        searchMenu.click();
        searchMenu.sendKeys(nameOfProduct);
        searchButton.click();
        return pageManager.getProductPage();
    }

    //Переход к корзине
    @FindBy (xpath = "//span[@class = 'cart-link__icon']")
    private WebElement cartIcon;

    public  CartPage goToCart(){
        cartIcon.click();
        return pageManager.getCartPage();
    }


    /**
     * Явное ожидание состояния clickable элемента
     *
     * @param element - веб-элемент который требует проверки clickable
     * @return WebElement - возвращаем тот же веб элемент что был передан в функцию
     * @see WebDriverWait
     * @see org.openqa.selenium.support.ui.FluentWait
     * @see org.openqa.selenium.support.ui.Wait
     * @see ExpectedConditions
     */
    protected WebElement waitUtilElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Явное ожидание того что элемент станет видемым
     *
     * @param element - веб элемент который мы ожидаем что будет  виден на странице
     */
    protected WebElement waitUtilElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Общий метод по заполнения полей ввода
     *
     * @param field - веб-элемент поле ввода
     * @param value - значение вводимое в поле
     */
    protected void fillInputField(WebElement field, String value) {
        scrollToElementJs(field);
        waitUtilElementToBeClickable(field).click();
        field.sendKeys(value);
    }

    /**
     * Общий метод по заполнению полей с датой
     *
     * @param field - веб-элемент поле с датой
     * @param value - значение вводимое в поле с датой
     */
    protected void fillDateField(WebElement field, String value) {
        scrollToElementJs(field);
        field.sendKeys(value);
    }

    //Проверка существует ли элемент
    public boolean isElementPresent(By by) {
        boolean flag = false;
        try {
            driverManager.getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            driverManager.getDriver().findElement(by);
            flag = true;
        } catch (NoSuchElementException ignore) {

        }finally {
            driverManager.getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(props.getProperty(IMPLICITLY_WAIT)), TimeUnit.SECONDS);
        }
        return flag;
    }

}
