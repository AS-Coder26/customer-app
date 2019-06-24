package com.tisticswebcos.tistics_signup;

public class ItemInitializor {

    String prodName,prodType;
    int productPrice;
public ItemInitializor()
{}
    public ItemInitializor(String name,String type, int price )
    {
        prodName = name;
        prodType = type;
        productPrice = price;


        //initialize,get and set values of imageview and textviews
    }

    public int getImage() {
        return R.drawable.ic_person;
    }

    public String getName() {
        return prodName;
        //return "Outdoor Furniture Polyester linen Fabric";
    }

    public String getProductType() {
        return prodType;
        //return "Textile Material Fabric";
    }

    public int getPrice(){
    return productPrice;
    }
}
