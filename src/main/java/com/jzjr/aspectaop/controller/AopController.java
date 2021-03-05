package com.jzjr.aspectaop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/aop")
@RestController
@Slf4j
public class AopController {

    @RequestMapping("/name")
    public String getName(String name){
        log.info(name);
        return "hello\t"+name;
    }
}
