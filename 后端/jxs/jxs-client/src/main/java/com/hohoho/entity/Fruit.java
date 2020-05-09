package com.hohoho.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author LJ
 * @since 2020-05-05
 */
public class Fruit extends Model<Fruit> {

    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private String base64Str;

    public String getBase64Str() {
        return base64Str;
    }

    public void setBase64Str(String base64Str) {
        this.base64Str = base64Str;
    }

    @TableId(value = "fruitid", type = IdType.AUTO)
    private Integer fruitid;
    /**
     * 名称
     */
    private String fruitname;
    /**
     * 商品编号
     */
    private String fruitnum;
    /**
     * 图片
     */
    private byte[] image;
    /**
     * 系列
     */
    private String series;
    /**
     * 子系列
     */
    private String childseries;
    /**
     * 本站价格
     */
    private BigDecimal siteprice;
    /**
     * 市场价格
     */
    private BigDecimal marketprice;
    /**
     * 库存
     */
    private Integer account;


    public Integer getFruitid() {
        return fruitid;
    }

    public void setFruitid(Integer fruitid) {
        this.fruitid = fruitid;
    }

    public String getFruitname() {
        return fruitname;
    }

    public void setFruitname(String fruitname) {
        this.fruitname = fruitname;
    }

    public String getFruitnum() {
        return fruitnum;
    }

    public void setFruitnum(String fruitnum) {
        this.fruitnum = fruitnum;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getChildseries() {
        return childseries;
    }

    public void setChildseries(String childseries) {
        this.childseries = childseries;
    }

    public BigDecimal getSiteprice() {
        return siteprice;
    }

    public void setSiteprice(BigDecimal siteprice) {
        this.siteprice = siteprice;
    }

    public BigDecimal getMarketprice() {
        return marketprice;
    }

    public void setMarketprice(BigDecimal marketprice) {
        this.marketprice = marketprice;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public static final String FRUITID = "fruitid";

    public static final String FRUITNAME = "fruitname";

    public static final String FRUITNUM = "fruitnum";

    public static final String IMAGE = "image";

    public static final String SERIES = "series";

    public static final String CHILDSERIES = "childseries";

    public static final String SITEPRICE = "siteprice";

    public static final String MARKETPRICE = "marketprice";

    public static final String ACCOUNT = "account";

    @Override
    protected Serializable pkVal() {
        return this.fruitid;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                ", fruitid=" + fruitid +
                ", fruitname=" + fruitname +
                ", fruitnum=" + fruitnum +
                ", image=" + image +
                ", series=" + series +
                ", childseries=" + childseries +
                ", siteprice=" + siteprice +
                ", marketprice=" + marketprice +
                ", account=" + account +
                "}";
    }
}
