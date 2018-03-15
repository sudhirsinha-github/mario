package io.vertx.mario.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserSessionDTO {
	private String userId;
	@JsonIgnore
	private String sessionToken;
	@JsonIgnore
	private String correlationId;
	private String email;
	private String firstName;
	private String lastName;
	private String phoneNumber; //TODO : Later consider for Contact collection
	private Boolean marketingPreferences;
	private String address; //TODO can be moved to separate ADDRESS collection and get linked with addresslookupID

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Boolean getMarketingPreferences() {
		return marketingPreferences;
	}

	public void setMarketingPreferences(Boolean marketingPreferences) {
		this.marketingPreferences = marketingPreferences;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserId() {
		return userId;
	}

	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}