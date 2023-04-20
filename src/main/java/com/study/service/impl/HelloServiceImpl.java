package com.study.service.impl;

import com.study.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @author: lsw
 * @date: 2023/4/19 14:32
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "Hello," + name;
    }
}
