package com.bhz.lht.mystudy.zk.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderMain implements Runnable {

	private static final String ZK_STRING = "10.1.1.132:2181,10.1.1.133:2181,10.1.1.136:2181";
	private static final String ZK_LOCK_PATH = "/LLOOCCKK";

	private static OrderCodeGenerator ong = new OrderCodeGenerator(ZK_STRING);

	private Logger logger = LoggerFactory.getLogger(OrderMain.class);

	private static final int NUM = 200;
	// 按照线程数初始化倒计数器
	private static CountDownLatch cdl = new CountDownLatch(NUM);

//  private static Lock lock = new ReentrantLock();  // 加锁方式（单进程，多线程）
	private Lock lock = new CuratorLock(ZK_STRING, ZK_LOCK_PATH); // 加锁方式2（多进程，多线程，分布式锁，使用Curator访问Zookeeper实现）

	// 创建订单接口
	public void createOrder() {
		String orderCode = null;
		lock.lock();
		try {
			// ……业务代码：获取订单编号
			orderCode = ong.getOrderCode();
			logger.info(Thread.currentThread().getName() + " got lock: =======================>" + orderCode);
			// Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void run() {
		try {
			// 等待其他线程初始化
			cdl.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 创建订单
		createOrder();
	}

	public static void main(String[] args) {
		for (int i = 1; i <= NUM; i++) {
			// 按照线程数迭代实例化线程
			new Thread(new OrderMain()).start();
			// 创建一个线程，倒计数器减1
			cdl.countDown();
		}
	}

}
