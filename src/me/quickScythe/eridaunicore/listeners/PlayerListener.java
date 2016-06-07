package me.quickScythe.eridaunicore.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.util.Vector;

import me.quickScythe.eridaunicore.Main;
import me.quickScythe.eridaunicore.utils.CoreUtils;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PlayerListener implements Listener {
	
	Main plugin;
	
	public PlayerListener(Main plugin){
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		PermissionUser player = PermissionsEx.getUser(e.getPlayer());
		e.setJoinMessage(CoreUtils.colorize(CoreUtils.getPlayerDisplayName(player) + "&7 has joined."));
	}
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e){
		PermissionUser player = PermissionsEx.getUser(e.getPlayer());
		e.setQuitMessage(CoreUtils.colorize(CoreUtils.getPlayerDisplayName(player) + "&7 has left."));
	}

}
