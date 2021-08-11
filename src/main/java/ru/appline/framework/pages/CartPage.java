package ru.appline.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.appline.framework.classes.Converter;

public class CartPage extends BasePage{

    @FindBy(xpath = "//div[@class = 'total-amount']//span[@class = 'price__current']")
    private WebElement totalyBuy;



    public Integer getTotalPrice(){
        return Converter.convertСurrencyFromStringToInteger(totalyBuy.getText());
    }

    //Проверка гарантии
    public boolean checkWarranty(String productName , String warrantyPeriod){
        boolean flag = false;

        WebElement warrantyMark = driverManager.getDriver().findElement(By.xpath("//a[text()[contains(., '" + productName + "' )]]/../../../../../../..//span[@class = 'base-ui-radio-button__icon base-ui-radio-button__icon_checked']"));

        switch (warrantyPeriod){
            case "24" :
                flag = warrantyMark.getText().contains("24");
                break;
            case "12" :
                flag = warrantyMark.getText().contains("12");
                break;
            case "default" :
                flag = warrantyMark.getText().contains("Без");
                break;
        }
        return flag;
    }

    //Удаляем товар
    public CartPage deleteProduct(String productName){
        //Проверяем цену до удаления, чтобы потом дождаться после удаления
        String totalyPrice = totalyBuy.getText();

        WebElement deleteButton = driverManager.getDriver().findElement(By.xpath("//a[text()[contains(., '" + productName + "')]]/../../../..//button[text() = 'Удалить']"));
        deleteButton.click();
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(totalyBuy , totalyPrice)));
        return this;
    }
    //Добавляем товар
    public CartPage addProduct(String productName , int numberOfProducts){
        WebElement plusButton = driverManager.getDriver().findElement(By.xpath("//a[text()[contains(., '" + productName + "')]]/../../../../..//button[@class[contains(., 'button_plus')]]"));
        for (int i = 0; i < numberOfProducts; i++) {
            String totalyPrice = totalyBuy.getText();
            plusButton.click();
            wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(totalyBuy , totalyPrice)));
            }

        return this;
    }
    public CartPage reternDeleteItem(){
        WebElement removeButton = driverManager.getDriver().findElement(By.xpath("//div[@class = 'group-tabs']//span[@class = 'restore-last-removed']"));
        String totalyPrice = totalyBuy.getText();
        removeButton.click();
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(totalyBuy , totalyPrice)));
        return this;
    }

}
