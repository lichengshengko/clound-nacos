package com.cloud.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("provider-order")
public interface TestFeginServiceClient {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test();

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public String insert();
}
