package ru.appline.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.appline.framework.classes.Conteiner;
import ru.appline.framework.classes.Converter;
import ru.appline.framework.classes.Product;

public class ProductPage extends BasePage {

    private Product product = new Product();

    @FindBy(xpath = "//div[text()[contains(.,'Гарантия')]]")
    private WebElement guaranteeMenu;

    // Блок элементов с гарантией
    @FindBy(xpath = "//input[@value ='default']/../span")
    private WebElement aditionalGuarantee;

    @FindBy(xpath = "//input[@value ='0']/../span")
    private WebElement aditionalGuarantee12;

    @FindBy(xpath = "//input[@value ='1']/../span")
    private WebElement aditionalGuarantee24;
    //

    //Элементы продукта
    @FindBy(xpath = "//h1")
    private WebElement productName;

    @FindBy(xpath = "//div[@class = 'product-buy product-buy_one-line']//div[@class[contains(., 'product-buy__price')]]//div[@class[contains(., 'product-buy__price')]]")
    private WebElement productPrice;

    @FindBy(xpath = "//input[@value ='default']/../span/..//span[@class = 'product-warranty__price']")
    private WebElement productWarrantyCost;

    @FindBy(xpath = "//input[@value ='0']/../span/..//span[@class = 'product-warranty__price']")
    private WebElement productWarrantyCost12;

    @FindBy(xpath = "//input[@value ='1']/../span/..//span[@class = 'product-warranty__price']")
    private WebElement productWarrantyCost24;
    //

    //Кнопка добавить в корзину
    @FindBy(xpath = "//div[@class = 'product-buy product-buy_one-line']//button[@class[contains(.,'button-ui buy-btn')]]")
    private WebElement cartButton;




    public ProductPage saveProduct(){
        Product temporaryVariable = new Product();
        temporaryVariable.setName(productName.getText());

        //Есть ли гарантия у продукта

        if (isElementPresent(By.xpath("//div[text()[contains(.,'Гарантия')]]"))){
            guaranteeMenu.click();
            if (driverManager.getDriver().findElement(By.xpath("//input[@value =  'default']")).isSelected()){
                temporaryVariable.setWarrantyCost(0);
            }
            if (driverManager.getDriver().findElement(By.xpath("//input[@value =  '0']")).isSelected()){
                temporaryVariable.setWarrantyCost(Converter.convertСurrencyFromStringToInteger(productWarrantyCost12.getText()));
            }
            if (driverManager.getDriver().findElement(By.xpath("//input[@value =  '1']")).isSelected()){
                temporaryVariable.setWarrantyCost(Converter.convertСurrencyFromStringToInteger(productWarrantyCost24.getText()));
            }

        } else {
            temporaryVariable.setWarrantyCost(0);
        }
        temporaryVariable.setPrice(Converter.convertСurrencyFromStringToInteger(productPrice.getText()) - temporaryVariable.getWarrantyCost());
        Conteiner.getMapProduct().put(productName.getText() , temporaryVariable);
        return this;
    }

    public ProductPage chooseAdditionalGuarantee(String guaranteePeriod){
        guaranteeMenu.click();
        switch (guaranteePeriod) {
            case "24":
                aditionalGuarantee24.click();
                break;
            case "12":
                aditionalGuarantee12.click();
                break;
            case "default":
                aditionalGuarantee.click();
                break;
        }
        return this;

    }

//    public Product getProduct(){
//
//
//
//        if (isElementPresent(By.xpath("//div[text()[contains(.,'Гарантия')]]"))){
//            switch (flag) {
//            case "24":
//                guaranteeMenu.click();
//
//                break;
//            case "12":
//                guaranteeMenu.click();
//
//                break;
//                case "default":
//
//                break;
//            }
//        } else {
//            flag = "default";
//
//        }
//
//         - temporaryVariable.getWarrantyCost());
//        return temporaryVariable;
//    }

    public ProductPage addToCart(){
        if (driverManager.getDriver().findElement(By.xpath("//span[@class = 'cart-link__lbl']")).getText().contains("Корзина")){
            cartButton.click();
        } else{
            String totalyPrice = driverManager.getDriver().findElement(By.xpath("//span[@class = 'cart-link__price']")).getText();
            cartButton.click();
            wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(driverManager.getDriver().findElement(By.xpath("//span[@class = 'cart-link__price']")) , totalyPrice)));
        }
        return this;
    }


}

