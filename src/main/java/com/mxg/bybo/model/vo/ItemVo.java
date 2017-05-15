package com.mxg.bybo.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemVo {
	
	private String TransactionDate;
	private String TransactionTime;
	private String TransactionAmount;
	private String TransactionType;
	private String BonusSummary;

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

	@JsonProperty("TransactionType")
	public String getTransactionType() {
		return TransactionType;
	}

	@JsonProperty("TransactionType")
	public void setTransactionType(String transactionType) {
		TransactionType = transactionType;
	}

	@JsonProperty("BonusSummary")
	public String getBonusSummary() {
		return BonusSummary;
	}

	@JsonProperty("BonusSummary")
	public void setBonusSummary(String bonusSummary) {
		BonusSummary = bonusSummary;
	}

}
