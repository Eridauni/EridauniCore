package me.quickScythe.eridaunicore.core;

import org.bukkit.Material;

public class Achievement {
	String name;
	String[] itemInfo;
	
	public Achievement(String name, String description, Material item, short data){
		this.name = name;
		setItemInfo(item, name, description, data);
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	private void setItemInfo(Material material, String name, String lore, short data){
		itemInfo = new String[] {material.toString(), name, lore, "" + data};
	}
	public String[] getItemInfo(){
		return itemInfo;
	}
	

}
