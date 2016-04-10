package com.idreamsky.dreamroom.model;

import java.util.List;

/**
 * Created by magical on 2016/4/10.
 * 品牌展厅首页数据
 */
public class BrandHomeEntity {

    private List<BrandHomeModel> result;

    public List<BrandHomeModel> getResult() {
        return result;
    }

    public void setResult(List<BrandHomeModel> result) {
        this.result = result;
    }

    public class BrandHomeModel {

        public String id;
        public String logo;
        public String brand;
        public int brandtype;
        public String brandName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public int getBrandtype() {
            return brandtype;
        }

        public void setBrandtype(int brandtype) {
            this.brandtype = brandtype;
        }

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }
    }

}
