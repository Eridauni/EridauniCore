package me.quickScythe.eridaunicore;

import org.bukkit.plugin.java.JavaPlugin;

import me.quickScythe.eridaunicore.commands.*;
import me.quickScythe.eridaunicore.listeners.*;
import me.quickScythe.eridaunicore.utils.*;

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
		new GrenadeCommand(this, "grenade");
		new HouseCommand(this, "house");
		new UpdateCommand(this, "update");
		new AchievementListener(this);
		new ParticleListener(this);
		new GrenadeListener(this);
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
