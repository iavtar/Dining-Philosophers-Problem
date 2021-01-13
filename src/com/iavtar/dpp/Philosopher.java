package com.iavtar.dpp;

import java.util.Random;

/**
 * @author indra_singh
 */
public class Philosopher implements Runnable {

	private int id;
	private volatile boolean full;
	private Chopstick leftChopStick;
	private Chopstick rightChopStick;
	private Random random;
	private int eatingCounter;

	public Philosopher(int id, Chopstick leftChopstick, Chopstick rightChopstick) {
		this.id = id;
		this.leftChopStick = leftChopstick;
		this.rightChopStick = rightChopstick;
		this.random = new Random();
	}

	@Override
	public void run() {
		try {
			// terminated after 1000
			while (!full) {
				think();
				if (leftChopStick.pickUp(this, State.LEFT)) {
					// acquired left chopstick
					if (rightChopStick.pickUp(this, State.RIGHT)) {
						eat();
						rightChopStick.putDown(this, State.RIGHT);
					}
					leftChopStick.putDown(this, State.LEFT);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void think() throws InterruptedException {
		System.out.println(this + " is thinking...");
		// the philosopher takes time from [0-1000]
		Thread.sleep(random.nextInt(1000));
	}

	private void eat() throws InterruptedException {
		System.out.println(this + " is eating...");
		eatingCounter++;
		Thread.sleep(random.nextInt(1000));
	}

	public void setFull(boolean full) {
		this.full = full;
	}

	public boolean isFull() {
		return this.full;
	}
	
	public int getEatingCounter() {
		return this.eatingCounter;
	}

	@Override
	public String toString() {
		return "Philosopher " + id;
	}

}
