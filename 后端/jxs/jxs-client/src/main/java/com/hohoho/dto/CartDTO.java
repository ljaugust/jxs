package com.hohoho.dto;

import com.hohoho.entity.Cart;
import lombok.Data;

import java.util.List;

@Data
public class CartDTO {
    private String name;
    private List<Cart> cart;
    private Integer payway;
    private String address;
    private Integer count;
    private String allMoney;
    private String phone;
}
