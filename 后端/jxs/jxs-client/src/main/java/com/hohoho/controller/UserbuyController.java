package com.hohoho.controller;


import com.hohoho.dto.CartDTO;
import com.hohoho.dto.OrderDTO;
import com.hohoho.service.CartService;
import com.hohoho.service.UserbuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LJ
 * @since 2020-05-05
 */
@RestController
@RequestMapping()
public class UserbuyController {

    @Autowired
    UserbuyService userbuyService;

    @Autowired
    CartService cartService;

    @PostMapping("/buy")
    @PreAuthorize("hasAnyAuthority('p2')")
    public String buy(@RequestBody CartDTO cart) {
        userbuyService.buy(cart);
        cartService.deleteCart(cart);
        return "success";
    }

    @GetMapping("/findOrder")
    @PreAuthorize("hasAnyAuthority('p2')")
    public List<OrderDTO> findOrder(@RequestParam Integer userId) {
        List<OrderDTO> orderDTOList = userbuyService.findOrder(userId);
        return orderDTOList;
    }

    @GetMapping("/delOrder")
    @PreAuthorize("hasAnyAuthority('p2')")
    public String delOrder(@RequestParam Integer buyid) {
        userbuyService.deleteById(buyid);
        return "success";
    }
}

