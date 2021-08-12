package ru.appline.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.appline.framework.classes.Conteiner;
import ru.appline.framework.classes.Converter;
import ru.appline.framework.classes.Product;


public class CartPage extends BasePage{

    @FindBy(xpath = "//div[@class = 'total-amount']//span[@class = 'price__current']")
    private WebElement totalyBuy;


    //Проверка гарантии
    public CartPage checkWarranty(String productName , String warrantyPeriod){
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
        Assertions.assertTrue(flag);
        return this;
    }

    //Проверка глобальной цены
    public CartPage checkTotalPrice() {
        if (driverManager.getDriver().findElement(By.xpath("//span[@class = 'cart-link__lbl']")).getText().contains("Корзина") && isElementPresent(By.xpath("//div[@class = 'total-amount']//span[@class = 'price__current']"))) {
            wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(driverManager.getDriver().findElement(By.xpath("//span[@class = 'cart-link__lbl']")), "Корзина")));
        }
        if (isElementPresent(By.xpath("//span[@class = 'cart-link__price']"))) {
            int sum = 0;
            for (Product item : Conteiner.getMapProduct().values()) {
                sum += item.getPrice() + item.getWarrantyCost();
            }
            Assertions.assertEquals(Converter.convertСurrencyFromStringToInteger(totalyBuy.getText()), sum);

        }
        return this;
    }
    public CartPage checkPrice(String... nameProduct){
        int sum = 0;
        for(String str : nameProduct){
            sum+= Conteiner.getMapProduct().get(str).getPrice() + Conteiner.getMapProduct().get(str).getWarrantyCost();
        }

        Assertions.assertEquals(Converter.convertСurrencyFromStringToInteger(totalyBuy.getText()), sum);
        return this;
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
