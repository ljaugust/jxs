package com.hohoho.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author LJ
 * @since 2020-05-05
 */
public class Cart extends Model<Cart> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "cartid", type = IdType.AUTO)
    private Integer cartid;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 水果编号
     */
    private String fruitnum;
    /**
     * 水果名称
     */
    private String fruitname;
    /**
     * 购买数量
     */
    private Integer count;
    /**
     * 本站价格
     */
    private BigDecimal siteprice;
    /**
     * 加入购物车时间
     */
    private Date addtime;

    @TableField(exist = false)
    private BigDecimal totalmoney;

    public BigDecimal getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(BigDecimal totalmoney) {
        this.totalmoney = totalmoney;
    }

    public Integer getCartid() {
        return cartid;
    }

    public void setCartid(Integer cartid) {
        this.cartid = cartid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFruitnum() {
        return fruitnum;
    }

    public void setFruitnum(String fruitnum) {
        this.fruitnum = fruitnum;
    }

    public String getFruitname() {
        return fruitname;
    }

    public void setFruitname(String fruitname) {
        this.fruitname = fruitname;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getSiteprice() {
        return siteprice;
    }

    public void setSiteprice(BigDecimal siteprice) {
        this.siteprice = siteprice;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public static final String CARTID = "cartid";

    public static final String USER_ID = "user_id";

    public static final String FRUITNUM = "fruitnum";

    public static final String FRUITNAME = "fruitname";

    public static final String COUNT = "count";

    public static final String SITEPRICE = "siteprice";

    public static final String ADDTIME = "addtime";

    @Override
    protected Serializable pkVal() {
        return this.cartid;
    }

    @Override
    public String toString() {
        return "Cart{" +
        ", cartid=" + cartid +
        ", userId=" + userId +
        ", fruitnum=" + fruitnum +
        ", fruitname=" + fruitname +
        ", count=" + count +
        ", siteprice=" + siteprice +
        ", addtime=" + addtime +
        "}";
    }
}
