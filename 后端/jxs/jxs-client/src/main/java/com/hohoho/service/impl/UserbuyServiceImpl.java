package com.hohoho.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hohoho.dto.CartDTO;
import com.hohoho.dto.OrderDTO;
import com.hohoho.entity.Cart;
import com.hohoho.entity.Fruit;
import com.hohoho.entity.JUser;
import com.hohoho.entity.Userbuy;
import com.hohoho.mapper.UserbuyDao;
import com.hohoho.service.FruitService;
import com.hohoho.service.UserbuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LJ
 * @since 2020-05-05
 */
@Service
public class UserbuyServiceImpl extends ServiceImpl<UserbuyDao, Userbuy> implements UserbuyService {

    @Autowired
    FruitService fruitService;

    @Override
    @Transactional
    public void buy(CartDTO cartDTO) {
        Userbuy userbuy = new Userbuy();
        StringBuilder str = new StringBuilder();
        StringBuilder string = new StringBuilder();
        for (Cart cart : cartDTO.getCart()) {
            if (str.length() > 0) {
                str.append(",");
                string.append(",");
            }
            str.append(cart.getCartid());
            string.append(cart.getCount());
        }
        JUser user = (JUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userbuy.setUserId(user.getUserId());
        userbuy.setName(cartDTO.getName());
        userbuy.setFruitid(str.toString());
        userbuy.setFruitcount(string.toString());
        userbuy.setCount(cartDTO.getCount());
        userbuy.setAddress(cartDTO.getAddress());
        userbuy.setPhone(cartDTO.getPhone());
        userbuy.setMoney(new BigDecimal(cartDTO.getAllMoney()));
        if (cartDTO.getPayway() == 1) {
            userbuy.setPayway("微信支付");
        } else {
            userbuy.setPayway("支付宝支付");
        }
        Date date = new Date();
        userbuy.setBuytime(date);
        insert(userbuy);
        String order = date.getTime() + userbuy.getBuyid().toString() + userbuy.getUserId().toString();
        userbuy.setNumorder(order);
        updateById(userbuy);
    }

    @Override
    @Transactional
    public List<OrderDTO> findOrder(Integer userId) {
        List<Userbuy> userbuyList = selectList(new EntityWrapper<Userbuy>().eq("user_id", userId));
        List<OrderDTO> orderDTOList = new ArrayList<>();
        if (userbuyList != null) {
            for (Userbuy userbuy : userbuyList) {
                OrderDTO orderDTO = new OrderDTO();
                orderDTO.setBuyid(userbuy.getBuyid());
                orderDTO.setNumorder(userbuy.getNumorder());
                orderDTO.setName(userbuy.getName());
                orderDTO.setPhone(userbuy.getPhone());
                orderDTO.setAddress(userbuy.getAddress());
                orderDTO.setPayway(userbuy.getPayway());
                orderDTO.setCount(userbuy.getCount());
                orderDTO.setMoney(userbuy.getMoney());

                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = f.format(userbuy.getBuytime());
                orderDTO.setBuytime(time);

                String[] strarry = userbuy.getFruitid().split(",");
                String[] strarry2 = userbuy.getFruitcount().split(",");
                List<Cart> cartList = new ArrayList<>();
                for (int i = 0; i < strarry.length; i++) {
                    Fruit fruit = fruitService.selectOne(new EntityWrapper<Fruit>().eq("fruitid", Integer.parseInt(strarry[i])));
                    Cart cart = new Cart();
                    cart.setFruitnum(fruit.getFruitnum());
                    cart.setFruitname(fruit.getFruitname());
                    cart.setSiteprice(fruit.getSiteprice());
                    int c = Integer.parseInt(strarry2[i]);
                    cart.setTotalmoney(fruit.getSiteprice().multiply(new BigDecimal(c)));
                    cart.setCount(c);
                    cartList.add(cart);
                }
                orderDTO.setCarts(cartList);
                orderDTOList.add(orderDTO);
            }
        }
        return orderDTOList;
    }
}
