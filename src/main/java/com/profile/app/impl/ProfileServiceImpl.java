package com.profile.app.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.profile.app.Service.ProfileResource;
import com.profile.app.Service.ProfileService;
import com.profile.app.model.UserProfile;

@RestController
public class ProfileServiceImpl implements ProfileService{

	@Autowired
	private ProfileResource profileResource;
	
	@Override
	@PostMapping("/user")
	public UserProfile addNewCustomerProfile(@RequestBody UserProfile userProfile) throws AddUserException {
		
			List<UserProfile> mobileNo = profileResource.findByMobileNo(userProfile.getMobileNo());
			String check = "mobile no is present";
			if(mobileNo.isEmpty()) {
				check="Email is already present";
				List<UserProfile> email = profileResource.findByEmail(userProfile.getEmail());
				if(email.isEmpty()) {
					return profileResource.addNewCustomerProfile(userProfile);
				}
			}
		throw new AddUserException(check);

	}

	@Override
	@GetMapping("/user")
	public List<UserProfile> getAllProfiles() {
		List<UserProfile> profileList = profileResource.getAllProfiles();
		return profileList;
	}

	@Override
	@GetMapping("/user/{userId}")
	public UserProfile getByProfileId(@PathVariable("userId") String Id) {
	
		UserProfile userProfile = profileResource.getByProfileId(Id);
		return userProfile;
	}

	@Override
	@PutMapping("/user/{userId}")
	public UserProfile updateProfile(@RequestBody UserProfile userProfile,
										@PathVariable("userId") String userId) {
		return profileResource.updateProfile(userProfile,userId);
	}

	@Override
	@DeleteMapping("/user/{userId}")
	public String deleteProfile(@PathVariable("userId") String userId) {
		return profileResource.deleteProfile(userId);
	}

	@Override
	@GetMapping("/user/mobileno/{mobileNo}")
	public List<UserProfile> findByMobileNo(@PathVariable("mobileNo") Long mobileNo) {
		
		return profileResource.findByMobileNo(mobileNo);
	}

	@Override
	@GetMapping("/user/email/{email}")
	public List<UserProfile> findByEmail(@PathVariable("email") String email) {
		
		return profileResource.findByEmail(email);
	}

	

}
