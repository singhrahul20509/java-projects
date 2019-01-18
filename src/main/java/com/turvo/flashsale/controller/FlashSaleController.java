package com.turvo.flashsale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.turvo.flashsale.authentication.FlashSaleAuthenticationService;
import com.turvo.flashsale.pojo.PurchaseDetails;
import com.turvo.flashsale.pojo.RegistrationDetails;
import com.turvo.flashsale.pojo.Status;
import com.turvo.flashsale.service.PurchaseService;
import com.turvo.flashsale.service.RegistrationService;

@RestController
public class FlashSaleController {
	
	@Autowired
	private RegistrationService registrationService;
	
	@Autowired
	private PurchaseService purchaseService;
	
	@Autowired
	private FlashSaleAuthenticationService authenticationService;
	
	/**
	 * 
	 * @param registrationDetails personal details of the user who is registering himself
	 * @return registration key on successful registration of user which needs to passed in purchase api call
	 */
	@RequestMapping(value = "/register", method=RequestMethod.POST)
	public String register(@RequestBody RegistrationDetails registrationDetails) {
		try{
			String key = registrationService.registerUser(registrationDetails);
			if(!key.equals(Status.USER_ALREADY_REGISTERED)) {
				return key;
			}
			return Status.USER_ALREADY_REGISTERED;
		}catch(Exception e){
			e.printStackTrace();
			return "unable to register user";
		}
	}
	
	/**
	 * 
	 * @param purchaseDetails - registration key obtained during registration call
	 * @return success/failure message
	 */
	@RequestMapping(value="/purchase", method=RequestMethod.POST)
	public String purchase(@RequestBody PurchaseDetails purchaseDetails) 
	{
		if(authenticationService.isUserRegisteredFotFlashSale(purchaseDetails.getRegistrationKey())) {
			String status = purchaseService.purchase(purchaseDetails.getRegistrationKey());
			if(status.equals(Status.SUCCESS)) {
				return "succesfull purchase for registration id "+purchaseDetails.getRegistrationKey();
			}else {
				return "failed purchase for registration id "+purchaseDetails.getRegistrationKey();
			}
		}
		
		return "user with registration key "+purchaseDetails.getRegistrationKey()+" is not registered for flash sale";
	}

}
