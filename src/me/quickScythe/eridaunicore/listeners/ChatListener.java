package me.quickScythe.eridaunicore.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.quickScythe.eridaunicore.Main;
import me.quickScythe.eridaunicore.utils.Utils;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class ChatListener implements Listener {
	Main plugin;
	public ChatListener(Main plugin){
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e){
		PermissionUser player = PermissionsEx.getUser(e.getPlayer());
		String message = e.getMessage();
		if(player.has("chat.color")){
			message = Utils.colorize(message);
		}
		e.setFormat(Utils.colorize(player.getPrefix() + player.getName() + " &f> &8" + player.getSuffix()) + message);
	}
	
	
}
