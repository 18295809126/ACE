package com.jk.utils;

import com.jk.service.UserThead;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {

    // 创建固定长度线程池
   private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

    public static void executor(Runnable runnable){
        fixedThreadPool.execute(runnable);
    }
}
