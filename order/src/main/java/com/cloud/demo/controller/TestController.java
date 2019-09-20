package com.cloud.demo.controller;

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
    public String test(HttpServletRequest request) {
        String id = request.getHeader("userId");
        String content = request.getParameter("content");
        return "hello，this is client-order:" + port + "-id:" + id;
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
}
