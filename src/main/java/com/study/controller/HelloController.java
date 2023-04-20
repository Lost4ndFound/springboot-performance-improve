package com.study.controller;

import com.study.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

/**
 * @author: lsw
 * @date: 2023/4/19 14:30
 */
@RestController
@RequestMapping("hello")
@Slf4j
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/set/{name}")
    public String hello(@PathVariable("name") String name){
        return helloService.sayHello(name);
    }

    /**
     * 异步调用restful
     * 当controller返回值是Callable的时候，springmvc就会启动一个线程将Callable交给TaskExecutor去处理
     * 然后DispatcherServlet还有所有的spring拦截器都退出主线程，然后把response保持打开的状态
     * 当Callable执行结束之后，springmvc就会重新启动分配一个request请求，然后DispatcherServlet就重新
     * 调用和处理Callable异步执行的返回结果， 然后返回视图
     *
     * @return
     */
    @GetMapping("/helloWorld")
    public Callable<String> helloWorld() {
        log.info(Thread.currentThread().getName() + " 进入helloWorld方法");
        Callable<String> callable = new Callable<String>() {

            @Override
            public String call() throws Exception {
                log.info(Thread.currentThread().getName() + " 进入call方法");
                String say = helloService.sayHello("world");
                log.info(Thread.currentThread().getName() + " 从helloService方法返回");
                return say;
            }
        };
        log.info(Thread.currentThread().getName() + " 从helloWorld方法返回");
        return callable;
    }

    @GetMapping("world")
    public WebAsyncTask<String> world(){
        log.info(Thread.currentThread().getName() + "进入world方法");
        WebAsyncTask<String> webAsyncTask = new WebAsyncTask<>(3000, new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info(Thread.currentThread().getName() + " 进入call方法");
                String say = helloService.sayHello("World");
                log.info(Thread.currentThread().getName() + " 从helloService方法返回");
                return say;
            }
        });
        log.info(Thread.currentThread().getName() + " 从helloController方法返回");
        webAsyncTask.onCompletion(new Runnable() {
            @Override
            public void run() {
                log.info(Thread.currentThread().getName() + " 执行完毕");
            }
        });
        webAsyncTask.onTimeout(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info(Thread.currentThread().getName() + " onTimeout");
                // 超时的时候，直接抛异常，让外层统一处理超时异常
                throw new TimeoutException("调用超时");
            }
        });
        return webAsyncTask;
    }

    /**
     * 异步调用，异常处理，详细的处理流程见MyExceptionHandler类
     *
     * @return
     */
    @GetMapping("/exception")
    public WebAsyncTask<String> exceptionController() {
        log.info(Thread.currentThread().getName() + " 进入helloController方法");
        Callable<String> callable = new Callable<String>() {

            @Override
            public String call() throws Exception {
                log.info(Thread.currentThread().getName() + " 进入call方法");
                throw new TimeoutException("调用超时!");
            }
        };
        log.info(Thread.currentThread().getName() + " 从helloController方法返回");
        return new WebAsyncTask<>(20000, callable);
    }

}
