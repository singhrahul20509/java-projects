package com.turvo.flashsale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turvo.flashsale.inventory.OrdersInventory;
import com.turvo.flashsale.inventory.WatchesInventory;
import com.turvo.flashsale.pojo.Order;
import com.turvo.flashsale.pojo.OrderDetails;
import com.turvo.flashsale.pojo.Status;

@Service
public class PurchaseService {
	
	@Autowired
	private WatchesInventory watchesInventory;
	
	@Autowired
	private OrdersInventory ordersInventory;
	
	public String purchase(String registrationKey)
	{
		String purchaseStatus = watchesInventory.sold();
		if(purchaseStatus.equalsIgnoreCase(Status.SUCCESS)) {
			OrderDetails orderDetails = new OrderDetails();
			Order order = new Order(registrationKey, orderDetails);
			ordersInventory.add(registrationKey, order);
		}
		
		return purchaseStatus;
	}

}
