package ru.appline.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.appline.base.BaseTest;
import ru.appline.framework.classes.Product;

public class ItemInCartTest extends BaseTest {

    @Test
    public void startTest() {

        Product monitor = app.getHomePage()
                .searchProduct("Монитор Samsung Odyssey G3 F24G35TFWI")
                .chooseAdditionalGuarantee("24").getProduct();
        Product game = app.getProductPage()
                .addToCart()
                .searchProduct("Detroit become human").getProduct();

        Integer totalyPrice = app.getProductPage()
                .addToCart()
                .goToCart()
                .getTotalPrice();
        //Сравниваем цены
        Assertions.assertEquals(totalyPrice , monitor.getWarrantyCost() + monitor.getPrice() + game.getPrice() + game.getWarrantyCost());
        Assertions.assertTrue(app.getCartPage().checkWarranty("Монитор Samsung", "24"));

        totalyPrice = app.getCartPage().deleteProduct("Detroit").getTotalPrice();

        Assertions.assertEquals(totalyPrice , monitor.getWarrantyCost() + monitor.getPrice());

        //Добавляем 2 монитора
        app.getCartPage().addProduct("Монитор Samsung" , 2);

        //Сравниваем цену для х3
        Assertions.assertEquals( app.getCartPage().getTotalPrice() , 3*(monitor.getWarrantyCost() + monitor.getPrice()));

        //Возвращаем удаленный товар и сравниваем цену
        app.getCartPage().reternDeleteItem();
        Assertions.assertEquals( app.getCartPage().getTotalPrice() , 3*(monitor.getWarrantyCost() + monitor.getPrice())  + game.getPrice());

    }

}
