package com.hmdp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.entity.ShopType;
import com.hmdp.mapper.ShopTypeMapper;
import com.hmdp.service.IShopTypeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class ShopTypeServiceImpl extends ServiceImpl<ShopTypeMapper, ShopType> implements IShopTypeService {

    //@Autowired
    //private RedisTemplate<String, String> redisTemplate;
    //
    //@Override
    //public List<ShopType> queryAllShop() {
    //    String key = RedisConstants.CACHE_SHOP_TYPE_KEY;
    //
    //    //1.从redis查询商铺缓存
    //    List<String> list = redisTemplate.opsForList().range(key, 0L, -1L);
    //
    //    //2.判断是否存在
    //    assert list != null;
    //    if (!list.isEmpty()) {
    //        //3.存在，直接返回
    //        return null;
    //        //return list;
    //    }
    //
    //    //4.不存在，根据id查询数据库
    //    List<ShopType> sort = query().orderByAsc("sort").list();
    //
    //    //5.不存在，返回错误
    //    if (sort == null) {
    //        return Result.fail("店铺不存在！");
    //    }
    //
    //    //6.存入，写入redis
    //
    //
    //    //7.返回
    //    return null;
    //}
}
