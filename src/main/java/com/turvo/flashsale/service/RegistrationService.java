package com.turvo.flashsale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turvo.flashsale.authentication.FlashSaleAuthenticationService;
import com.turvo.flashsale.pojo.RegistrationDetails;

@Service
public class RegistrationService {
	
	@Autowired
	private FlashSaleAuthenticationService service;
	
	public String registerUser(RegistrationDetails details) {
		return service.registerUser(details);
	}

}
