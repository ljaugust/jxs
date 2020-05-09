package com.hohoho.controller;

import com.hohoho.entity.Fruit;
import com.hohoho.filter.FruitNum;
import com.hohoho.service.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/admincenter")
public class AdminCenter {

    @Autowired
    private FruitService fruitService;

    @PostMapping("/addFru")
    @PreAuthorize("hasAnyAuthority('p3')")
    public String handleFileUpload(HttpServletRequest request) {

        MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");

        String fruitname = params.getParameter("fruitname");
        String series = params.getParameter("series");
        String childseries = params.getParameter("childseries");
        String account = params.getParameter("account");
        String marketprice = params.getParameter("marketprice");
        String siteprice = params.getParameter("siteprice");

        Fruit fruit = new Fruit();
        fruit.setFruitname(fruitname);
        fruit.setSeries(series);
        fruit.setChildseries(childseries);
        fruit.setAccount(Integer.parseInt(account));
        fruit.setMarketprice(new BigDecimal(marketprice));
        fruit.setSiteprice(new BigDecimal(siteprice));

        for (MultipartFile multipartFile : files) {
            if (!multipartFile.isEmpty()) {
                try {
                    byte[] bytes = multipartFile.getBytes();
                    fruit.setImage(bytes);
                } catch (Exception e) {
                    return "error";
                }
            } else {
                return "empty";
            }
        }

        fruitService.insert(fruit);

        Integer i = fruit.getFruitid();
        String fid = String.valueOf(i);
        FruitNum fruitNum = new FruitNum();
        String fn = fruitNum.isFruitNum(series, childseries);
        String fno = fn + "-" + fid;
        fruit.setFruitnum(fno);
        fruitService.updateById(fruit);

        return "success";
    }
}
