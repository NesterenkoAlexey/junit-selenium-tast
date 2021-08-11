package ru.appline.framework.classes;

public class Product {
    private String name;
    private Integer price;
    private Integer warrantyCost = 0;
    private  String productDescription = "Это товар";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getWarrantyCost() {
        return warrantyCost;
    }

    public void setWarrantyCost(Integer warrantyCost) {
        this.warrantyCost = warrantyCost;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
}
