package com.mxg.bybo.model.resp;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mxg.bybo.model.vo.ItemVo;

public class BonustransResp {
	private String Id;
	private String OpenId;
	private String Bonus;
	private List<ItemVo> Items;

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

	@JsonProperty("Bonus")
	public String getBonus() {
		return Bonus;
	}

	@JsonProperty("Bonus")
	public void setBonus(String bonus) {
		Bonus = bonus;
	}

	@JsonProperty("Items")
	public List<ItemVo> getItems() {
		return Items;
	}

	@JsonProperty("Items")
	public void setItems(List<ItemVo> items) {
		Items = items;
	}

}
