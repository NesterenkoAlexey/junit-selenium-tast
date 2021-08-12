package ru.appline.tests;


import org.junit.jupiter.api.Test;
import ru.appline.base.BaseTest;


public class ItemInCartTest extends BaseTest {

    @Test
    public void startTest() {

        app.getHomePage()
                .searchProduct("Монитор Samsung Odyssey G3 F24G35TFWI")
                .saveProduct()
                .chooseAdditionalGuarantee("24")
                .saveProduct()
                .addToCart()
                .searchProduct("Detroit become human")
                .saveProduct()
                .addToCart()
                .goToCart()
                .checkTotalPrice()
                .checkWarranty("Монитор Samsung" , "24")
                .deleteProduct("Detroit")
                .checkPrice( "24\" Монитор Samsung Odyssey G3 F24G35TFWI черный")
                .addProduct("Монитор Samsung" , 2)
                .checkPrice("24\" Монитор Samsung Odyssey G3 F24G35TFWI черный","24\" Монитор Samsung Odyssey G3 F24G35TFWI черный","24\" Монитор Samsung Odyssey G3 F24G35TFWI черный")
                .reternDeleteItem()
                .checkPrice("24\" Монитор Samsung Odyssey G3 F24G35TFWI черный","24\" Монитор Samsung Odyssey G3 F24G35TFWI черный","24\" Монитор Samsung Odyssey G3 F24G35TFWI черный" , "Игра Detroit: Стать человеком (PS4)");


        
   }

}
