package com.cloud.demo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/3/1 14:57.
 */
@Service
public class RedisValueService {
    @Autowired
    protected RedisTemplate<String, Object> redisTemplate;
    @Resource
    protected ValueOperations<String, Object> valueOperations;

    /**
     * 添加
     *
     * @param key    key
     * @param value  对象
     */
    public void set(String key, Object value) {
        valueOperations.set(key, value);
    }



    /**
     * 查询
     *
     * @param key 查询的key
     * @return
     */
    public Object get(Object key) {
        return valueOperations.get(key);
    }

}