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
public class Userbuy extends Model<Userbuy> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "buyid", type = IdType.AUTO)
    private Integer buyid;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 收货方
     */
    private String name;
    /**
     * 订单号
     */
    private String numorder;
    /**
     * 水果id
     */
    private String fruitid;
    /**
     * 水果数量
     */
    private String fruitcount;
    /**
     * 购买数量
     */
    private Integer count;
    /**
     * 合计
     */
    private BigDecimal money;
    /**
     * 付款方式
     */
    private String payway;
    /**
     * 寄送地址
     */
    private String address;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 下单时间
     */
    private Date buytime;


    public Integer getBuyid() {
        return buyid;
    }

    public void setBuyid(Integer buyid) {
        this.buyid = buyid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumorder() {
        return numorder;
    }

    public void setNumorder(String numorder) {
        this.numorder = numorder;
    }

    public String getFruitid() {
        return fruitid;
    }

    public void setFruitid(String fruitid) {
        this.fruitid = fruitid;
    }

    public String getFruitcount() {
        return fruitcount;
    }

    public void setFruitcount(String fruitcount) {
        this.fruitcount = fruitcount;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getPayway() {
        return payway;
    }

    public void setPayway(String payway) {
        this.payway = payway;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBuytime() {
        return buytime;
    }

    public void setBuytime(Date buytime) {
        this.buytime = buytime;
    }

    public static final String BUYID = "buyid";

    public static final String USER_ID = "user_id";

    public static final String NAME = "name";

    public static final String NUMORDER = "numorder";

    public static final String FRUITID = "fruitid";

    public static final String FRUITCOUNT = "fruitcount";

    public static final String COUNT = "count";

    public static final String MONEY = "money";

    public static final String PAYWAY = "payway";

    public static final String ADDRESS = "address";

    public static final String PHONE = "phone";

    public static final String BUYTIME = "buytime";

    @Override
    protected Serializable pkVal() {
        return this.buyid;
    }

    @Override
    public String toString() {
        return "Userbuy{" +
        ", buyid=" + buyid +
        ", userId=" + userId +
        ", name=" + name +
        ", numorder=" + numorder +
        ", fruitid=" + fruitid +
        ", fruitcount=" + fruitcount +
        ", count=" + count +
        ", money=" + money +
        ", payway=" + payway +
        ", address=" + address +
        ", phone=" + phone +
        ", buytime=" + buytime +
        "}";
    }
}
