package com.cloud.demo.controller;

import com.cloud.demo.entity.User;
import com.cloud.demo.mapper.UserMapper;
import com.cloud.demo.redis.RedisModel;
import com.cloud.demo.redis.RedisModelService;
import com.cloud.demo.redis.RedisValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

@RestController
public class TestController {

    @Value("${server.port}")
    String port;
    @Autowired
    RedisModelService redisService;
    @Autowired
    RedisValueService redisValueService;
    @Autowired
    UserMapper userMapper;

    @RequestMapping(value = "/redis/hash", method = RequestMethod.GET)
    public Object redis(HttpServletRequest request) {
        RedisModel m = new RedisModel();
        m.setName("张三");
        m.setTel("1111");
        m.setAddress("深圳1");
        m.setRedisKey("zhangsanKey01");
        redisService.put(m.getRedisKey(), m, -1);
        return redisService.get(m.getRedisKey());
    }

    @RequestMapping(value = "/redis/value", method = RequestMethod.GET)
    public Object value(HttpServletRequest request) {
        redisValueService.set("lcs", "李成胜");
        return redisValueService.get("lcs");
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "hello，this is client-order:" + port;
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String test(HttpServletRequest request, @RequestBody List<Integer> ids) {
        request.getHeader("userId");
        Enumeration<String> stringEnumeration = request.getHeaders("userId");
        String s = "";
        while (stringEnumeration.hasMoreElements()) {
            s += stringEnumeration.nextElement() + ",";
        }
        return "hello，this is client-order:" + port + "-id:" + s;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Object list() {
        return userMapper.selectAll();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public Integer insert() {
        User user = new User();
        user.setName(System.currentTimeMillis()+"");
        userMapper.insert(user);
        return user.getId();
    }
}
