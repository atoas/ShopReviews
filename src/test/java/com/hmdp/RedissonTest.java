package com.hmdp;

import cn.hutool.core.lang.UUID;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;

import static com.hmdp.utils.RedisConstants.LOGIN_USER_KEY;

/**
 * @author zyh
 * @data 2022/5/21 21:56
 */
@Slf4j
@SpringBootTest
public class RedissonTest {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Test
    public void test() throws IOException {
        FileOutputStream fos = new FileOutputStream(new File("./tokens.txt"));

        PrintStream printStream = new PrintStream(fos);
        System.setOut(printStream);
        for (int i = 0; i < 1000; i++) {
            HashMap<String, String> userMap = new HashMap<>();
            userMap.put("id", String.valueOf(i));
            userMap.put("nickName", UUID.randomUUID().toString(true));
            String uuid = UUID.randomUUID().toString(true);
            String tokenKey = LOGIN_USER_KEY + uuid;
            redisTemplate.opsForHash().putAll(tokenKey,userMap);
            System.out.println(uuid);
        }
    }

    @Test
    public void test2() {

        for (int i = 0; i < 100; i++) {
            HashMap<String, String> map = new HashMap<>();
            map.put("id", String.valueOf(i));
            System.out.println(map.toString());
        }

    }





    //@Resource
    //private RedissonClient redissonClient;

    //@Resource
    //private RedissonClient redissonClient2;
    //
    //@Resource
    //private RedissonClient redissonClient3;

    //private RLock lock;

   /* @BeforeEach
    void setUp() {
        RLock lock1 = redissonClient.getLock("order");
        RLock lock2 = redissonClient2.getLock("order");
        RLock lock3 = redissonClient3.getLock("order");

        //创建multiLock
        lock = redissonClient.getMultiLock(lock1,lock2,lock3);
    }



    @Test
    void method1() throws InterruptedException {
        // 尝试获取锁
        boolean isLock = lock.tryLock(5L, TimeUnit.SECONDS);
        if (!isLock) {
            log.error("获取锁失败 .... 1");
            return;
        }
        try {
            log.info("获取锁成功 .... 1");
            method2();
            log.info("开始执行业务 ... 1");
        } finally {
            log.warn("准备释放锁 .... 1");
            lock.unlock();
        }
    }
    void method2() {
        // 尝试获取锁
        boolean isLock = lock.tryLock();
        if (!isLock) {
            log.error("获取锁失败 .... 2");
            return;
        }
        try {
            log.info("获取锁成功 .... 2");
            log.info("开始执行业务 ... 2");
        } finally {
            log.warn("准备释放锁 .... 2");
            lock.unlock();
        }
    }*/

}
