package com.tao.springcloud.consul.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: JiantaoYan
 * @Description:
 * @Date: 14:09 2018/9/9
 */

@RestController
public class ConsulController {

    @Value("${spring.application.name}")
    private String name;

    @Value("${server.port}")
    private String port;

    @RequestMapping(value = "/" ,method = RequestMethod.GET)
    public String root(){
        return "Hello Consul!\n" + "name=" + name +"\nport=" + port;
    }
}
