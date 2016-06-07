package me.quickScythe.eridaunicore.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.quickScythe.eridaunicore.Main;
import me.quickScythe.eridaunicore.utils.CoreUtils;

public class JoinListener implements Listener {
	Main plugin;
	public JoinListener(Main plugin){
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		e.setJoinMessage(CoreUtils.getPlayerDisplayName(e.getPlayer()) + " &7has joined.");
		CoreUtils.cachePlayer(e.getPlayer());
	}
	
	
}
