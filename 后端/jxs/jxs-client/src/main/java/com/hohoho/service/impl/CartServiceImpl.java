package com.hohoho.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hohoho.dto.CartDTO;
import com.hohoho.entity.Cart;
import com.hohoho.mapper.CartDao;
import com.hohoho.service.CartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LJ
 * @since 2020-05-05
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartDao, Cart> implements CartService {

    @Override
    @Transactional
    public void addToCart(Cart cart) {
        int n = selectCount(new EntityWrapper<Cart>().eq("fruitnum", cart.getFruitnum()));
        if (n == 0) {
            insert(cart);
        } else if (n == 1) {
            Cart cart1 = selectOne(new EntityWrapper<Cart>().eq("fruitnum", cart.getFruitnum()));
            int count = cart.getCount() + cart1.getCount();
            cart1.setCount(count);
            updateById(cart1);
        }
    }

    @Override
    @Transactional
    public void deleteCart(CartDTO cartDTO) {
        for (Cart cart : cartDTO.getCart()) {
            deleteById(cart.getCartid());
        }
    }
}
