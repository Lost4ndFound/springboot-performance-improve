package com.study.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author: lsw
 * @date: 2023/4/19 14:16
 */
public class Calc {

    public static Integer calc(Integer para){
        try {
            //模拟一个长时间的执行
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return para * para;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> calc(50))
                .thenApply((i) -> Integer.toString(i)).thenApply((str) -> "\"" + str + "\"").thenAccept(System.out::println);
        future.get();
    }

}
