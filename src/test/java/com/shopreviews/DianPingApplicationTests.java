package com.shopreviews;

import com.shopreviews.entity.Shop;
import com.shopreviews.service.impl.ShopServiceImpl;
import com.shopreviews.utils.CacheClient;
import com.shopreviews.utils.RedisConstants;
import com.shopreviews.utils.RedisWorker;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.shopreviews.utils.RedisConstants.SHOP_GEO_KEY;

@Slf4j
@SpringBootTest
class DianPingApplicationTests {

    @Autowired
    private ShopServiceImpl shopService;

    @Autowired
    private CacheClient cacheClient;

    @Autowired
    private RedisWorker redisWorker;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private ExecutorService es = Executors.newFixedThreadPool(500);

    @Test
    public void testIdWorker() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(300);

        Runnable task = () -> {
            for (int i = 0; i < 100; i++) {
                long id = redisWorker.nextId("order");
                System.out.println("id = " + id);
            }
            latch.countDown();
        };

        long begin = System.currentTimeMillis();
        for (int i = 0; i < 300; i++) {
            es.submit(task);
        }
        latch.await();
        long end = System.currentTimeMillis();
        System.out.println("time = " + (end - begin));
    }

    /**
     * 向redis插入店铺信息
     *
     * @throws InterruptedException
     */
    @Test
    public void test() throws InterruptedException {
        for (int i = 2; i < 15; i++) {
            shopService.saveShop2Redis((long) i, 10L);
        }
    }

    @Test
    public void testSaveShop() {
        Shop shop = shopService.getById(1L);

        cacheClient.setWithLogicExpire(RedisConstants.CACHE_SHOP_KEY + 1L, shop, 10L, TimeUnit.SECONDS);
    }

    /**
     * 向redis中插入店铺座标
     */
    @Test
    public void loadShopData() {
        //1.查询店铺信息
        List<Shop> list = shopService.list();
        //2.把店铺分组，按照typeId分组，typeId一致的放到一个集合
        Map<Long, List<Shop>> map = list.stream().collect(Collectors.groupingBy(Shop::getTypeId));
        //3.分批完成写入redis
        for (Map.Entry<Long, List<Shop>> entry : map.entrySet()) {
            //3.1.获取类型id
            Long typeId = entry.getKey();
            String key = SHOP_GEO_KEY + typeId;
            //3.2.获取同类型的店铺的集合
            List<Shop> value = entry.getValue();
            List<RedisGeoCommands.GeoLocation<String>> locations = new ArrayList<>(value.size());
            //3.3写入redis FEOADD key 经度 纬度 member
            for (Shop shop : value) {
                //多次写入，性能太低
                //redisTemplate.opsForGeo().add(key,new Point(shop.getX(),shop.getY()),shop.getId().toString());
                //一次写入
                locations.add(new RedisGeoCommands.GeoLocation<>(
                        shop.getId().toString(),
                        new Point(shop.getX(), shop.getY())
                ));
            }
            redisTemplate.opsForGeo().add(key, locations);
        }
    }


    @Test
    public void testHyperLogLoh() {
        String[] values = new String[1000];
        int j;
        for (int i = 0; i < 1000000; i++) {
            j = i % 1000;
            values[j] = "user_" + i;
            if (j == 999) {
                //发送到redis
                redisTemplate.opsForHyperLogLog().add("hl2", values);
            }
        }
        //统计数量
        Long count = redisTemplate.opsForHyperLogLog().size("hl2");
        System.out.println("count = " + count);
    }

}
