package com.idreamsky.dreamroom.model;

/**
 * Created by magical on 2016/4/10.
 * 品牌的家具分类
 */
public class BrandClazz {

    public int id;
    public String productClass;
    public String brand;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductClass() {
        return productClass;
    }

    public void setProductClass(String productClass) {
        this.productClass = productClass;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
