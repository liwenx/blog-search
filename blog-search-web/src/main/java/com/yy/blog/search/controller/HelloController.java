package com.yy.blog.search.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liwenxing
 * @date 2018/1/17 15:13
 */
@RestController
public class HelloController {
    @RequestMapping(value = "/hello", method = {RequestMethod.POST, RequestMethod.GET })
    public String hello() {
        return "I am blog-search hello";
    }
}
