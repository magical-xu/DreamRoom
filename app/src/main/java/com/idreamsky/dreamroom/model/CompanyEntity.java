package com.idreamsky.dreamroom.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;

/**
 * Created by magical on 2016/4/18.
 * 家装公司
 */
@Table(name = "CompanyEntity")
public class CompanyEntity implements Serializable {

    /**
     * area : 广东省深圳市
     * areaDetail : 深圳市宝安区西乡街道宝源路1053号资信达大厦903室
     * companyCode : 584077473
     * companyIntroduction :
     * 深圳市名艺源装饰设计工程有限公司始建于2010年注册资金500
     * 万，经过几年的稳健发展，名艺源已成为家居设计服务，工程施工服务，公共装饰设计服务及售后服务一体的全国装饰企业。公司主营大、中户型豪宅设计和工装，提供一体化装饰服务。有国内知名的设计师主笔，旗下拥有宝安分公司，龙岗分公司及四川成都分公司，总部设在深圳。企业坚持“以人为本，用户至上，名出自于工艺，艺来源于品质”的原则，深受广大客户和社会各界的高度认可与信赖。 在企业经营和管理方面，名艺源秉承ISO90001:2000国际质量管理体系， ISO14000国际环境管理体系和OHSAS18000职业健康安全管理体系的精神指引下，制定和贯彻一系列程序化的工程管理体系，大大提高了工程质量，使广大客户的利益得到有力的保障。
     * companyName : 深圳市名艺源装饰设计工程有限公司
     * cover : http://img04.taobaocdn.com/tfscom/T1M5QIFnVXXXcUawbX_790x10000.jpg
     * goodHouseStyle : 平层 复式 商业空间
     * goodStyle : 现代简约 欧式 混搭
     * id : 804
     * legalPerson : 张洪元
     * logo : http://wwc.taobaocdn.com/avatar/getAvatar
     * .do?userId=911719615&width=100&height=100&type=sns
     * modelCount : 5
     * personIntroduction : 无
     * phone : 13723789528 / 0755-29961619
     * trueName : 无
     * userName : 张航
     * wangwangName : qq1036732702
     */

    @Column(name = "sqlId", isId = true)
    private int sqlId;

    private String area;
    @Column(name = "areaDetail")
    private String areaDetail;
    private String companyCode;

    @Column(name = "companyIntroduction")
    private String companyIntroduction;

    @Column(name = "companyName")
    private String companyName;

    @Column(name = "cover")
    private String cover;
    private String goodHouseStyle;
    private String goodStyle;
    private String id;

    @Column(name = "legalPerson")
    private String legalPerson;

    @Column(name = "logo")
    private String logo;
    private String modelCount;
    private String personIntroduction;

    @Column(name = "phone")
    private String phone;
    private String trueName;
    private String userName;
    private String wangwangName;

    @Override
    public String toString() {
        return "CompanyEntity{" +
                "sqlId=" + sqlId +
                ", areaDetail='" + areaDetail + '\'' +
                ", companyIntroduction='" + companyIntroduction + '\'' +
                ", companyName='" + companyName + '\'' +
                ", cover='" + cover + '\'' +
                ", legalPerson='" + legalPerson + '\'' +
                ", logo='" + logo + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaDetail() {
        return areaDetail;
    }

    public void setAreaDetail(String areaDetail) {
        this.areaDetail = areaDetail;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyIntroduction() {
        return companyIntroduction;
    }

    public void setCompanyIntroduction(String companyIntroduction) {
        this.companyIntroduction = companyIntroduction;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getGoodHouseStyle() {
        return goodHouseStyle;
    }

    public void setGoodHouseStyle(String goodHouseStyle) {
        this.goodHouseStyle = goodHouseStyle;
    }

    public String getGoodStyle() {
        return goodStyle;
    }

    public void setGoodStyle(String goodStyle) {
        this.goodStyle = goodStyle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getModelCount() {
        return modelCount;
    }

    public void setModelCount(String modelCount) {
        this.modelCount = modelCount;
    }

    public String getPersonIntroduction() {
        return personIntroduction;
    }

    public void setPersonIntroduction(String personIntroduction) {
        this.personIntroduction = personIntroduction;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWangwangName() {
        return wangwangName;
    }

    public void setWangwangName(String wangwangName) {
        this.wangwangName = wangwangName;
    }
}
