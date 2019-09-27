/**
 * 
 */
package com.ss.four.dl;

/**
 * @author sj
 *
 */
public class MyDeadlock {

	public static Object ob1 = new Object();
	public static Object ob2 = new Object();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ThreadTest thread1 = new ThreadTest();
		ThreadTest2 thread2 = new ThreadTest2();
		thread1.start();
		thread2.start();

	}

	private static class ThreadTest extends Thread{
		public void run() {
			while(true)
				synchronized(ob1){
					synchronized(ob2){
						System.out.println("Locked1");
					}
			}
		}
	}

	private static class ThreadTest2 extends Thread{
		public void run() {
			while(true)
				synchronized(ob2){
					synchronized(ob1){
						System.out.println("Locked2");
					}
			}
		}
	}
}
