package ru.appline.framework.managers;


import ru.appline.framework.pages.CartPage;
import ru.appline.framework.pages.HomePage;
import ru.appline.framework.pages.ProductPage;


public class PageManager {
    private PageManager() {
    }

    private static PageManager pageManager;

    private HomePage homePage;

    private ProductPage productPage;

    private CartPage cartPage;


    /**
     * Ленивая инициализация PageManager
     *
     * @return PageManager
     */
    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }
    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }
    public ProductPage getProductPage() {
        if (productPage == null) {
            productPage = new ProductPage();
        }
        return productPage;
    }
    public CartPage getCartPage() {
        if (cartPage == null) {
            cartPage = new CartPage();
        }
        return cartPage;
    }


}
