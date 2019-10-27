package com.jxau.test;

import java.util.concurrent.*;

public class CallableAndFuture {
    public static class CallableTest implements Callable<String>{

        @Override
        public String call() throws Exception {
            return "Hello World";
        }
    }

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<String> future = threadPool.submit(new CallableTest());
        Future<String> future1 = threadPool.submit(new CallableTest());
        System.out.println("waiting thread to finish");
        try {
            System.out.println("1" + future.get());
            System.out.println("2" + future1.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
