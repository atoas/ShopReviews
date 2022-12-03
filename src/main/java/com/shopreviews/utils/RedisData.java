package com.shopreviews.utils;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zyh
 */
@Data
public class RedisData {
    private LocalDateTime expireTime;
    private Object data;
}
