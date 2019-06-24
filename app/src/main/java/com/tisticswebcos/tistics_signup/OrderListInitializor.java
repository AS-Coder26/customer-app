package com.tisticswebcos.tistics_signup;

public class OrderListInitializor {
    String prodName,prodType,orderstatus;

    public OrderListInitializor(String name,String type, String status ){
        prodName = name;
        prodType = type;
        orderstatus = status;
    }
    public String getName() {
        return prodName;
        //return "Outdoor Furniture Polyester linen Fabric";
    }

    public String getProductType() {
        return prodType;
        //return "Textile Material Fabric";
    }

    public String getStatus()
    {
        return orderstatus;
    }
}
