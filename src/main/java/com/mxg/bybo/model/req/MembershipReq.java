package com.mxg.bybo.model.req;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MembershipReq {
	
	private String Name;
	

	private String Birthday;
	
	private String Mobile;
	
	private String Address;
	
	private String PreferredStoreId;

	@JsonProperty("Name")
	public String getName() {
		return Name;
	}

	@JsonProperty("Name")
	public void setName(String name) {
		Name = name;
	}

	@JsonProperty("Birthday")
	public String getBirthday() {
		return Birthday;
	}

	@JsonProperty("Birthday")
	public void setBirthday(String birthday) {
		Birthday = birthday;
	}

	@JsonProperty("Mobile")
	public String getMobile() {
		return Mobile;
	}

	@JsonProperty("Mobile")
	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	@JsonProperty("Address")
	public String getAddress() {
		return Address;
	}

	@JsonProperty("Address")
	public void setAddress(String address) {
		Address = address;
	}

	@JsonProperty("PreferredStoreId")
	public String getPreferredStoreId() {
		return PreferredStoreId;
	}
	
	@JsonProperty("PreferredStoreId")
	public void setPreferredStoreId(String preferredStoreId) {
		PreferredStoreId = preferredStoreId;
	}

	@Override
	public String toString() {
		return "MembershipReq [Name=" + Name + ", Birthday=" + Birthday
				+ ", Mobile=" + Mobile + ", Address=" + Address
				+ ", PreferredStoreId=" + PreferredStoreId + "]";
	}

}
