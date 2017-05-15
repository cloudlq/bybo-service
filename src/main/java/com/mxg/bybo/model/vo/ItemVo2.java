package com.mxg.bybo.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemVo2 {

	private String TransactionId;
	private String TransactionDate;
	private String TransactionTime;
	private String TransactionAmount;
	private String TransactionCurrency;
	private String TransactionType;
	private String DoctorName;
	private String TransactionStoreId;
	private String TransactionStoreName;

	@JsonProperty("TransactionId")
	public String getTransactionId() {
		return TransactionId;
	}

	@JsonProperty("TransactionId")
	public void setTransactionId(String transactionId) {
		TransactionId = transactionId;
	}

	@JsonProperty("TransactionDate")
	public String getTransactionDate() {
		return TransactionDate;
	}

	@JsonProperty("TransactionDate")
	public void setTransactionDate(String transactionDate) {
		TransactionDate = transactionDate;
	}

	@JsonProperty("TransactionTime")
	public String getTransactionTime() {
		return TransactionTime;
	}

	@JsonProperty("TransactionTime")
	public void setTransactionTime(String transactionTime) {
		TransactionTime = transactionTime;
	}

	@JsonProperty("TransactionAmount")
	public String getTransactionAmount() {
		return TransactionAmount;
	}

	@JsonProperty("TransactionAmount")
	public void setTransactionAmount(String transactionAmount) {
		TransactionAmount = transactionAmount;
	}

	@JsonProperty("TransactionCurrency")
	public String getTransactionCurrency() {
		return TransactionCurrency;
	}

	@JsonProperty("TransactionCurrency")
	public void setTransactionCurrency(String transactionCurrency) {
		TransactionCurrency = transactionCurrency;
	}

	@JsonProperty("TransactionType")
	public String getTransactionType() {
		return TransactionType;
	}

	@JsonProperty("TransactionType")
	public void setTransactionType(String transactionType) {
		TransactionType = transactionType;
	}

	@JsonProperty("DoctorName")
	public String getDoctorName() {
		return DoctorName;
	}

	@JsonProperty("DoctorName")
	public void setDoctorName(String doctorName) {
		DoctorName = doctorName;
	}

	@JsonProperty("TransactionStoreId")
	public String getTransactionStoreId() {
		return TransactionStoreId;
	}

	@JsonProperty("TransactionStoreId")
	public void setTransactionStoreId(String transactionStoreId) {
		TransactionStoreId = transactionStoreId;
	}

	@JsonProperty("TransactionStoreName")
	public String getTransactionStoreName() {
		return TransactionStoreName;
	}

	@JsonProperty("TransactionStoreName")
	public void setTransactionStoreName(String transactionStoreName) {
		TransactionStoreName = transactionStoreName;
	}

}
