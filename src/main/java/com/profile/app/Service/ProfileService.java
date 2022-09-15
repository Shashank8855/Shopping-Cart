package com.profile.app.Service;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.profile.app.impl.AddUserException;
import com.profile.app.model.UserProfile;

@RestController
public interface ProfileService {

	public UserProfile addNewCustomerProfile(UserProfile userProfile) throws AddUserException;
	
	public List<UserProfile> getAllProfiles();
	
	public UserProfile getByProfileId(String Id);
	
	public UserProfile updateProfile(UserProfile userProfile,String userId);
	
	public String deleteProfile(String userId);
	
	public List<UserProfile> findByMobileNo(Long mobileNo);
	
	public List<UserProfile> findByEmail(String email);
}

