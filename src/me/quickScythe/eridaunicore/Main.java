package me.quickScythe.eridaunicore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.quickScythe.eridaunicore.commands.*;
import me.quickScythe.eridaunicore.listeners.*;
import me.quickScythe.eridaunicore.utils.*;

public class Main extends JavaPlugin {
	
	static Main plugin;
	public int announcecooldown = 0;
	public int announcement = 1;
	public Map<Integer, List<String>> announcements = new HashMap<>();
	public List<Grenade> grenades = new ArrayList<>();
	public List<Grenade> removeGrenades = new ArrayList<>();
	
	public void onEnable(){
		plugin = this;
		
		Bukkit.broadcastMessage("1");

		List<String> a = new ArrayList<>();
		
		a.add("&e-------[Announcement]-------");
		a.add("&fCome join our Plug.dj at");
		a.add("&fhttps://plug.dj/eridauni");
		a.add("&e----------------------------");
		
		announcements.put(1, a);
		
		a = null;
		
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
		new RenameCommand(this, "rename");
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
		new SkyRingsListener(this);
		
		CoreUtils.start();
		
		
		
		
	}
	
	public void onDisable(){
		CoreUtils.end();
	}
	
	public static Main getPlugin(){
		return plugin;
	}

}
