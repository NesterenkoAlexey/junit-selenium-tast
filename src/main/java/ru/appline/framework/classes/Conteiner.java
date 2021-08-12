package ru.appline.framework.classes;

import java.util.HashMap;

public class Conteiner {
    private static HashMap<String , Product> mapProduct = new HashMap<>();

    public static  HashMap<String , Product> getMapProduct(){
        return mapProduct;
    }
}
