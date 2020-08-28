package org.pavel.amos.async;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public class PingHandler extends ScheduledThreadPoolExecutor {
	boolean isPaused;
	ReentrantLock pauseLock = new ReentrantLock();
	Condition unpaused = pauseLock.newCondition();

	public PingHandler() {
		super(10);
	}

	protected void beforeExecute(Thread t, Runnable r) {
		super.beforeExecute(t, r);
		pauseLock.lock();
		try {
			while (isPaused)
				unpaused.await();
		}
		catch (InterruptedException ie) {
			t.interrupt();
		}
		finally {
			pauseLock.unlock();
		}
	}

	public void pause() {
		pauseLock.lock();
		try {
			isPaused = true;
		}
		finally {
			pauseLock.unlock();
		}
	}

	public void resume() {
		pauseLock.lock();
		try {
			isPaused = false;
			unpaused.signalAll();
		}
		finally {
			pauseLock.unlock();
		}
	}
}