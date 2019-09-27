/**
 * 
 */
package com.ss.four.pc;

import java.util.ArrayList;

/**
 * @author sj
 *
 */
public class ProducerConsumer {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException{
		final PC pc = new PC();
		
		Thread thread1 = new Thread(new Runnable() {
			public void run() {
				try {
					pc.produce();
				}catch(InterruptedException e) {}
				
			}
		});

		Thread thread2 = new Thread(new Runnable() {
			public void run() {
				try {
					pc.consume();
				}catch(InterruptedException e) {}
				
			}
		});
		thread1.start();
		thread2.start();
		
		//thread1.join();
		//thread2.join();
			
	}
	
	public static class PC{
		int capacity = 5;
		ArrayList<Integer> ints = new ArrayList<>();
		
		public void produce() throws InterruptedException{
			int value = 0;
			while(true) {
				synchronized(this) {
					while(ints.size()==capacity) {
						wait();
						notify();
					}
					System.out.println("Produced: " + value);
					ints.add(value++);
					notify();
					Thread.sleep(100);
				}
			}
		}
		public void consume() throws InterruptedException{
			while(true) {
				synchronized(this) {
					while(ints.size()==0) {
						wait();
						notify();
					}
					int val = ints.remove(0);
					
					System.out.println("Consumer: " + val);

					notify();
					Thread.sleep(200);
				}
			}
		}
	}
}
