package com.mxg.bybo.model;

import java.util.List;

public class MenuVo {
	private String name;
	private String icon;
	private String link;
	private List<MenuVo> menuVos;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public List<MenuVo> getMenuVos() {
		return menuVos;
	}
	public void setMenuVos(List<MenuVo> menuVos) {
		this.menuVos = menuVos;
	}

}
