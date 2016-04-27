package com.idreamsky.dreamroom.model;

import java.util.List;

/**
 * Created by magical on 2016/4/27.
 * 精彩推荐
 */
public class Recommend {


    /**
     * id : 36
     * indexType : 0
     * price : 5.8万
     * style : 北欧风格
     * imageUrl : ["http://productpic.qiniudn.com/taotu_36_2014_08_21.NO001.jpg",
     * "http://productpic.qiniudn.com/taotu_36_2014_08_21.NO002.jpg","http://productpic.qiniudn
     * .com/taotu_36_2014_08_21.NO003.jpg","http://productpic.qiniudn.com/taotu_36_2014_08_21
     * .NO004.jpg","http://productpic.qiniudn.com/taotu_36_2014_08_21.NO005.jpg",
     * "http://productpic.qiniudn.com/taotu_36_2014_08_21.NO006.jpg","http://productpic.qiniudn
     * .com/taotu_36_2014_08_21.NO007.jpg"]
     * priceInclude : 包含基础建材
     * houseType : 小户型
     * name : 回归原始和自然
     * space : 68m²
     */

    private int id;
    private String indexType;
    private String price;
    private String style;
    private String priceInclude;
    private String houseType;
    private String name;
    private String space;
    private List<String> imageUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getPriceInclude() {
        return priceInclude;
    }

    public void setPriceInclude(String priceInclude) {
        this.priceInclude = priceInclude;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public List<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
    }
}
