package com.bhz.lht.mystudy;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 3个线程，第一、第二个线程同时启动，第三个线程在第一个线程完成工作、第二个线程完成2/3工作时启动。
 * 
 * @author liuhongtian
 *
 */
public class TestCount {

	public static void main(String[] args) throws InterruptedException {
		System.out.println(UUID.randomUUID().toString());
		System.out.println("now main thread begin!");

		CountDownLatch cl1 = new CountDownLatch(1);
		CountDownLatch cl2 = new CountDownLatch(20);

		ExecutorService exec = Executors.newFixedThreadPool(2);

		// thread1
		exec.submit(new Runnable() {

			@Override
			public void run() {
				System.out.println("thread1 sleep 5 seconds and exit!");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				cl1.countDown();
				System.out.println("thread1 exited!");
			}
		});

		// thread2
		exec.submit(new Runnable() {

			@Override
			public void run() {
				System.out.println("thread2 sleep 30 seconds and exit!");
				for (int i = 1; i <= 30; i++) {
					try {
						Thread.sleep(1000);
						System.out.printf("thread2 sleep %d second!\n", i);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					cl2.countDown();
				}
				System.out.println("thread2 exited!");
			}
		});

		// await thread1 exit and thread2 proceed 67%, then start thread3.
		cl1.await();
		cl2.await();

		// tread3
		exec.submit(new Runnable() {

			@Override
			public void run() {
				System.out.println("now thread3 started and sleep 5 seconds!");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("thread3 exited!");
			}

		});

		// main thread exit.
		exec.shutdown();
		// System.out.println("now main thread exited!");

	}

}
