package be.pxl.ja.streamingservice.model;

import be.pxl.ja.streamingservice.exception.TooManyProfilesException;
import be.pxl.ja.streamingservice.util.PasswordUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TooManyListenersException;

public class Account {
	private String email;
	private String password;
	private PaymentInfo paymentInfo;
	private StreamingPlan streamingPlan;
	private HashMap<String, Profile> profiles = new HashMap<>();

	public Account(String email, String password) {
		this.email = email;
		setPassword(password);
		Profile profile = new Profile("Profile1");
		profiles.put(profile.getName(), profile);
	}

	public void setStreamingPlan(StreamingPlan streamingPlan) {
		this.streamingPlan = streamingPlan;
	}

	public void addProfile(Profile profile) {
		if (streamingPlan.getNumberOfScreens() > profiles.size()){
			profiles.put(profile.getName(), profile);
		} else {
			throw new TooManyProfilesException("You can't add more profiles!");
		}
	}

	public Profile getProfile(String name){
		return profiles.get(name);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean verifyPassword(String password) {
		return PasswordUtil.isValid(password, this.password);
	}

	public void setPaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	public void setPassword(String password) {
		this.password = PasswordUtil.encodePassword(password);
	}

	public Profile getFirstProfile() {
		return new Profile("Onbekend");
	}
}
