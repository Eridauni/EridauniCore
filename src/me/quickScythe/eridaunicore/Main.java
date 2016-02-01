package me.quickScythe.eridaunicore;

import org.bukkit.plugin.java.JavaPlugin;

import me.quickScythe.eridaunicore.commands.AchievementCommand;
import me.quickScythe.eridaunicore.commands.HelpCommand;
import me.quickScythe.eridaunicore.commands.ParticleColorCommand;
import me.quickScythe.eridaunicore.commands.ParticleCommand;
import me.quickScythe.eridaunicore.commands.ParticleFormatCommand;
import me.quickScythe.eridaunicore.commands.SpawnCommand;
import me.quickScythe.eridaunicore.listeners.AchievementListener;
import me.quickScythe.eridaunicore.listeners.ParticleListener;
import me.quickScythe.eridaunicore.utils.Utils;

public class Main extends JavaPlugin {
	
	static Main plugin;
	
	public void onEnable(){
		plugin = this;
		
		new AchievementCommand(this, "achievement");
		new HelpCommand(this, "help");
		new ParticleCommand(this, "particle");
		new ParticleFormatCommand(this, "particleformat");
		new SpawnCommand(this, "spawn");
		new ParticleColorCommand(this, "particlecolor");
		new WingCommand(this, "wings");
		new AchievementListener(this);
		new ParticleListener(this);
		
		Utils.start();
		
		
	}
	
	public static Main getPlugin(){
		return plugin;
	}

}
