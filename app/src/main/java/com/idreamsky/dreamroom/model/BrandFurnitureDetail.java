package com.idreamsky.dreamroom.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/4/16.
 * 品牌展厅 - 具体家具
 */
@Table(name = "BrandFurnitureDetail")
public class BrandFurnitureDetail implements Serializable {

    @Column(name = "sqlId", isId = true)
    private int sqlId;

    public String bigPic;

    @Column(name = "brand")
    public String brand;

    @Column(name = "description")
    public String description;

    public String id;

    @Column(name = "pic")
    public String pic;

    @Column(name = "price")
    public String price;
    public String proNo;
    public String productClass;

    @Column(name = "productName")
    public String productName;
    public String roomClass;

    @Column(name = "taobaolinks")
    public String taobaolinks;

    @Override
    public String toString() {
        return "BrandFurnitureDetail{" +
                "sqlId=" + sqlId +
                ", brand='" + brand + '\'' +
                ", description='" + description + '\'' +
                ", pic='" + pic + '\'' +
                ", price='" + price + '\'' +
                ", productName='" + productName + '\'' +
                ", taobaolinks='" + taobaolinks + '\'' +
                '}';
    }

    public String getBigPic() {
        return bigPic;
    }

    public void setBigPic(String bigPic) {
        this.bigPic = bigPic;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProNo() {
        return proNo;
    }

    public void setProNo(String proNo) {
        this.proNo = proNo;
    }

    public String getProductClass() {
        return productClass;
    }

    public void setProductClass(String productClass) {
        this.productClass = productClass;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(String roomClass) {
        this.roomClass = roomClass;
    }

    public String getTaobaolinks() {
        return taobaolinks;
    }

    public void setTaobaolinks(String taobaolinks) {
        this.taobaolinks = taobaolinks;
    }
}
