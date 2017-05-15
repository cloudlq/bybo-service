package com.mxg.bybo.model.resp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MembershipResp {

	private String Name;
	private String Birthday;
	private String Mobile;
	private String Address;
	private String PreferredStoreId;

	private String OpenId ;
	private String Id ;
	private String Grade ;
	private String Bonus ;
	private String  Balance;
	private String Currency ;
	private String MembershipCard;
	private String MembershipSince;
	
	@JsonProperty("OpenId")
	public String getOpenId() {
		return OpenId;
	}

	@JsonProperty("OpenId")
	public void setOpenId(String openId) {
		OpenId = openId;
	}

	@JsonProperty("Id")
	public String getId() {
		return Id;
	}
	
	@JsonProperty("Id")
	public void setId(String id) {
		Id = id;
	}

	@JsonProperty("Grade")
	public String getGrade() {
		return Grade;
	}

	@JsonProperty("Grade")
	public void setGrade(String grade) {
		Grade = grade;
	}

	@JsonProperty("Bonus")
	public String getBonus() {
		return Bonus;
	}

	@JsonProperty("Bonus")
	public void setBonus(String bonus) {
		Bonus = bonus;
	}

	@JsonProperty("Balance")
	public String getBalance() {
		return Balance;
	}

	@JsonProperty("Balance")
	public void setBalance(String balance) {
		Balance = balance;
	}

	@JsonProperty("Currency")
	public String getCurrency() {
		return Currency;
	}

	@JsonProperty("Currency")
	public void setCurrency(String currency) {
		Currency = currency;
	}

	@JsonProperty("MembershipCard")
	public String getMembershipCard() {
		return MembershipCard;
	}

	@JsonProperty("MembershipCard")
	public void setMembershipCard(String membershipCard) {
		MembershipCard = membershipCard;
	}

	@JsonProperty("MembershipSince")
	public String getMembershipSince() {
		return MembershipSince;
	}

	@JsonProperty("MembershipSince")
	public void setMembershipSince(String membershipSince) {
		MembershipSince = membershipSince;
	}

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

}
