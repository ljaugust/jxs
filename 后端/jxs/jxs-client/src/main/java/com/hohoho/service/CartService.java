package com.hohoho.service;

import com.hohoho.dto.CartDTO;
import com.hohoho.entity.Cart;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LJ
 * @since 2020-05-05
 */
public interface CartService extends IService<Cart> {

    @Transactional
    void addToCart(Cart cart);

    @Transactional
    void deleteCart(CartDTO cartDTO);
}
