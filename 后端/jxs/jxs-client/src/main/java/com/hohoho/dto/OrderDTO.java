package com.hohoho.dto;

import com.hohoho.entity.Cart;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDTO {
    private Integer buyid;
    private String name;
    private String numorder;
    private Integer count;
    private BigDecimal money;
    private String payway;
    private String address;
    private String phone;
    private String buytime;
    private List<Cart> carts;
}
