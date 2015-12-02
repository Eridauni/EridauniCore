package me.quickScythe.eridaunicore.core;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;

import me.quickScythe.eridaunicore.utils.Utils;

public class Gamer {
	UUID uid;
	ArrayList<Achievement> achievements = new ArrayList<>();
	
	public Gamer(UUID uid){
		this.uid = uid;
	}
	
	public Object getUUID() {
		return uid;
	}
	public void addAchievement(Achievement achievement){
		if(!hasAchievement(achievement)){
			achievements.add(achievement);
			Utils.addAchievement(Bukkit.getPlayer(uid), achievement);
		}
		
		
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
		Bukkit.getPlayer(uid).sendMessage(Utils.colorize(message));
	}
	

}
