package me.quickScythe.eridaunicore.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.quickScythe.eridaunicore.Main;
import me.quickScythe.eridaunicore.utils.Utils;

public class JoinListener implements Listener {
	Main plugin;
	public JoinListener(Main plugin){
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		e.setJoinMessage(Utils.getPlayerDisplayName(e.getPlayer()) + " &7has joined.");
		Utils.cachePlayer(e.getPlayer());
	}
	
	
}
