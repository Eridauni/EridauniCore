package me.quickScythe.eridaunicore;

import org.bukkit.plugin.java.JavaPlugin;

import me.quickScythe.eridaunicore.commands.AchievementCommand;
import me.quickScythe.eridaunicore.commands.FriendsCommand;
import me.quickScythe.eridaunicore.commands.HelpCommand;
import me.quickScythe.eridaunicore.commands.MenuCommand;
import me.quickScythe.eridaunicore.commands.ParticleColorCommand;
import me.quickScythe.eridaunicore.commands.ParticleCommand;
import me.quickScythe.eridaunicore.commands.ParticleFormatCommand;
import me.quickScythe.eridaunicore.commands.ReplyCommand;
import me.quickScythe.eridaunicore.commands.SpawnCommand;
import me.quickScythe.eridaunicore.commands.TellCommand;
import me.quickScythe.eridaunicore.commands.WingCommand;
import me.quickScythe.eridaunicore.listeners.AchievementListener;
import me.quickScythe.eridaunicore.listeners.AnvilListener;
import me.quickScythe.eridaunicore.listeners.BlockListener;
import me.quickScythe.eridaunicore.listeners.ChatListener;
import me.quickScythe.eridaunicore.listeners.EntityListener;
import me.quickScythe.eridaunicore.listeners.FriendListener;
import me.quickScythe.eridaunicore.listeners.JoinListener;
import me.quickScythe.eridaunicore.listeners.ParticleListener;
import me.quickScythe.eridaunicore.listeners.PingListener;
import me.quickScythe.eridaunicore.listeners.PlayerListener;
import me.quickScythe.eridaunicore.listeners.SignListener;
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
		new MenuCommand(this, "menu");
		new TellCommand(this, "tell");
		new ReplyCommand(this, "reply");
		new FriendsCommand(this, "friends");
		new AchievementListener(this);
		new ParticleListener(this);
		new JoinListener(this);
		new SignListener(this);
		new PingListener(this);
		new EntityListener(this);
		new PlayerListener(this);
		new FriendListener(this);
		new ChatListener(this);
		new BlockListener(this);
		new AnvilListener(this);
		
		Utils.start();
		
		
		
		
	}
	
	public void onDisable(){
		Utils.end();
	}
	
	public static Main getPlugin(){
		return plugin;
	}

}
