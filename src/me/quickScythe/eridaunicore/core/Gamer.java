package me.quickScythe.eridaunicore.core;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import me.quickScythe.eridaunicore.utils.CoreUtils;

public class Gamer {
	UUID uid;
	boolean walking;
	ItemStack skull = new ItemStack(Material.SKULL_ITEM, (short) 1, (short) 3);
	ArrayList<Achievement> achievements = new ArrayList<>();
	
	public Gamer(UUID uid){
		this.uid = uid;
		
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwner(Bukkit.getPlayer(uid).getName());
		skull.setItemMeta(meta);
		
	}
	
	public Object getUUID() {
		return uid;
	}
	public void addAchievement(Achievement achievement){
		if(!hasAchievement(achievement)){
			achievements.add(achievement);
			CoreUtils.addAchievement(Bukkit.getPlayer(uid), achievement);
		}
		
		
	}
	public void setWalking(boolean w){
		walking = w;
	}
	public boolean hasAchievement(Achievement achievement){
		try{
			return achievements.contains(achievement);
		} catch(NullPointerException ex){
			return false;
		}
	}
	public ArrayList<Achievement> getAchievements(){
		return achievements;
	}
	public void sendMessage(String message){
		Bukkit.getPlayer(uid).sendMessage(CoreUtils.colorize(message));
	}

	public boolean isWalking() {
		return walking;
	}

	public ItemStack getSkull() {
		return skull;
	}
	

}
