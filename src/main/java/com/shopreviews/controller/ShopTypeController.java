package com.shopreviews.controller;


import com.shopreviews.dto.Result;
import com.shopreviews.entity.ShopType;
import com.shopreviews.service.IShopTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zyh
 */
@RestController
@RequestMapping("/shop-type")
public class ShopTypeController {
    @Resource
    private IShopTypeService typeService;

    @GetMapping("list")
    public Result queryTypeList() {
        List<ShopType> typeList = typeService
                .query().orderByAsc("sort").list();
        return Result.ok(typeList);

        //List<ShopType> typeList =  typeService.queryAllShop();
        //
        //return Result.ok(typeList);
    }
}
