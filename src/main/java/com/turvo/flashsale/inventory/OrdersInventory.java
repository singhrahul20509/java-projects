package com.turvo.flashsale.inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.turvo.flashsale.pojo.Order;

@Component
public class OrdersInventory {

	private Map<String, List<Order>> inventory = new ConcurrentHashMap<>();
	
	public void add(String key, Order order) {
		if(inventory.containsKey(key)) {
			inventory.get(key).add(order);
		}else {
			List<Order> list = new ArrayList<>();
			list.add(order);
			inventory.put(key, list);
		}
	}
}
