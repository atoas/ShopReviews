package com.hmdp.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zyh
 * @data 2022/5/21 17:14
 */
@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient(){
        //配置
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.65.132:6379");
        //创建RedissonClient对象
        return Redisson.create(config);
    }

/*    @Bean
    public RedissonClient redissonClient2(){
        //配置
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.65.132:6380");
        //创建RedissonClient对象
        return Redisson.create(config);
    }

    @Bean
    public RedissonClient redissonClient3(){
        //配置
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.65.132:6381");
        //创建RedissonClient对象
        return Redisson.create(config);
    }*/

}
