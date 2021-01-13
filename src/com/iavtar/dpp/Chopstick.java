package com.iavtar.dpp;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author indra_singh
 */
public class Chopstick {

	private int id;
	private Lock lock;

	public Chopstick(int id) {
		this.id = id;
		this.lock = new ReentrantLock();
	}

	/**
	 * @param Philosopher
	 * @param State
	 * @return boolean
	 */
	public boolean pickUp(Philosopher philosopher, State state) throws InterruptedException {

		// Simulation of deadlock
		if (lock.tryLock(10, TimeUnit.MILLISECONDS)) {
			System.out.println(philosopher + " picked up " + state.toString() + " " + this);
			return true;
		}

		return false;

	}

	/**
	 * @param state
	 * @param Philosopher
	 */
	public void putDown(Philosopher philosopher, State state) {
		lock.unlock();
		System.out.println(philosopher + " puts down " + state.toString()+ " " +this);
	}

	@Override
	public String toString() {
		return "Chopstick " + id;
	}

}
