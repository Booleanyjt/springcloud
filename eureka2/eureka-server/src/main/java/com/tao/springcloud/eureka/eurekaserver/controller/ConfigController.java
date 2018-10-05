package com.tao.springcloud.eureka.eurekaserver.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: JiantaoYan
 * @Description:
 * @Date: 17:17 2018/10/5
 */
@RestController
public class ConfigController {

    @Value("${server.port}")
    private String port;

    @Value("${spring.application.name}")
    private String name;

    @Value("${spring.profiles.active}")
    private String profiles;

    @Value("${eureka.client.service-url.defaultZone}")
    private String url;

    @RequestMapping(name = "/test/config" , method = RequestMethod.GET)
    public String testConfig(){
        return "port:" + port + " name:" + name + url;
//        return profiles;
    }
}
