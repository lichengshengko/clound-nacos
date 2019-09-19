package com.cloud.demo.controller;

import com.cloud.demo.service.TestFeginServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    TestFeginServiceClient testService;

    /**
     * 普通Restful
     * @return
     */
    @RequestMapping(value = "/local", method = RequestMethod.GET)
    public String local() {
        return "本地local调用";

    }

    /**
     * 利用Fegin客户端实现RPC调用order服务
     * @return
     */
    @RequestMapping(value = "/order/test", method = RequestMethod.GET)
    public String test(){
        return testService.test();
    }

}
