package com.turvo.flashsale.inventory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.stereotype.Component;

import com.turvo.flashsale.pojo.Status;

@Component
public class WatchesInventory {
	
	private AtomicInteger availableCount = new AtomicInteger(2);
	private Lock lock = new ReentrantLock();
	
	public String sold() 
	{
		String status = Status.FAILURE;
		
		if(availableCount.get() > 0) {
			try {
				if(lock.tryLock(10, TimeUnit.SECONDS)) {
					availableCount.decrementAndGet();
					status = Status.SUCCESS;
				}
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}finally {
				lock.unlock();
			}
		}
		
		return status;
	}

}
