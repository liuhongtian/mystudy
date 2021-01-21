package com.bhz.lht.mystudy;
public class TestShutdown {
    public static void main(String ... args) throws InterruptedException {
        Runtime.getRuntime().addShutdownHook(new Thread(()->{System.out.println("Application Exited!");}));
        Thread.sleep(10000);
    }
}