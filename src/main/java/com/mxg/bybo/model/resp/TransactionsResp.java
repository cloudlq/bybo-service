package com.mxg.bybo.model.resp;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mxg.bybo.model.vo.ItemVo;
import com.mxg.bybo.model.vo.ItemVo2;

public class TransactionsResp {
	private String Id;
	private String OpenId;
	private List<ItemVo2> Items;

	@JsonProperty("Id")
	public String getId() {
		return Id;
	}

	@JsonProperty("Id")
	public void setId(String id) {
		Id = id;
	}

	@JsonProperty("OpenId")
	public String getOpenId() {
		return OpenId;
	}

	@JsonProperty("OpenId")
	public void setOpenId(String openId) {
		OpenId = openId;
	}


	@JsonProperty("Items")
	public List<ItemVo2> getItems() {
		return Items;
	}

	@JsonProperty("Items")
	public void setItems(List<ItemVo2> items) {
		Items = items;
	}

}
