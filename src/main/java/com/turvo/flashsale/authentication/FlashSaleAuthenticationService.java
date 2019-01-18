package com.turvo.flashsale.authentication;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.turvo.flashsale.pojo.RegistrationDetails;
import com.turvo.flashsale.pojo.Status;

@Service
public class FlashSaleAuthenticationService {
	
	private Map<String, RegistrationDetails> registeredUsersStore = new HashMap<>();
	private Set<String> alreadyRegisteredUsers = new HashSet<>();
	
	public boolean isUserRegisteredFotFlashSale(String registrationKey) {
		return registeredUsersStore.containsKey(registrationKey);
	}
	
	public String registerUser(RegistrationDetails details) {
		if(!alreadyRegisteredUsers.contains(details.getPhone())) {
			UUID id = UUID.randomUUID();
			registeredUsersStore.put(id.toString(), details);
			alreadyRegisteredUsers.add(details.getPhone());
			return id.toString();
		}else
			return Status.USER_ALREADY_REGISTERED;		
	}

}
