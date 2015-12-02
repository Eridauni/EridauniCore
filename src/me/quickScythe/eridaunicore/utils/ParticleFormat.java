package me.quickScythe.eridaunicore.utils;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.quickScythe.eridaunicore.Main;
import me.quickScythe.eridaunicore.particleformats.*;

public enum ParticleFormat {
	CIRCLE_FEET("CircleFeet"),
	CIRCLE_HEAD("CircleHead"),
	CIRCLE_BODY("CircleBody"),
	HELIX("Helix"),
	DOUBLE_HELIX("DoubleHelix"),
	RANDOM("Random"),
	CROSS_FIRE("CrossFire"),
	SPIRAL("Spiral"),
	SPHERE("Sphere"),
	BLOOD_HELIX("BloodHelix");
	
	String format;
	ParticleFormat(String format){
		this.format = format;
	}
	
	public void run(Player player){
		if(format == "CircleFeet") Bukkit.getScheduler().runTask(Main.getPlugin(), new CircleFeet(player));
		if(format == "CircleHead") Bukkit.getScheduler().runTask(Main.getPlugin(), new CircleHead(player));
		if(format == "CircleBody") Bukkit.getScheduler().runTask(Main.getPlugin(), new CircleBody(player));
		if(format == "Helix") Bukkit.getScheduler().runTask(Main.getPlugin(), new Helix(player));
		if(format == "DoubleHelix") Bukkit.getScheduler().runTask(Main.getPlugin(), new DoubleHelix(player));
		if(format == "Random") Bukkit.getScheduler().runTask(Main.getPlugin(), new Random(player));
		if(format == "CrossFire") Bukkit.getScheduler().runTask(Main.getPlugin(), new CrossFire(player));
		if(format == "Spiral") Bukkit.getScheduler().runTask(Main.getPlugin(), new Spiral(player));
		if(format == "BloodHelix") Bukkit.getScheduler().runTask(Main.getPlugin(), new BloodHelix(player));
		if(format == "Sphere") Bukkit.getScheduler().runTask(Main.getPlugin(), new Sphere(player));
	}

}
