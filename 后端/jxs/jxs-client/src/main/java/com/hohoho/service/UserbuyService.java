package com.hohoho.service;

import com.hohoho.dto.CartDTO;
import com.hohoho.dto.OrderDTO;
import com.hohoho.entity.Userbuy;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LJ
 * @since 2020-05-05
 */
public interface UserbuyService extends IService<Userbuy> {

    @Transactional
    void buy(CartDTO cartDTO);

    @Transactional
    List<OrderDTO> findOrder(Integer userId);
}
