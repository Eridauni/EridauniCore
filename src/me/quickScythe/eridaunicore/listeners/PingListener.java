package me.quickScythe.eridaunicore.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import me.quickScythe.eridaunicore.Main;
import me.quickScythe.eridaunicore.utils.Images;
import me.quickScythe.eridaunicore.utils.CoreUtils;
import res.Texture;

public class PingListener implements Listener {
	
	Main plugin;
	
	public PingListener(Main plugin){
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onServerPingEvent(ServerListPingEvent e){
		
		e.setMotd(CoreUtils.colorize("&e&lEridauni\n&fSurvial in a friendly community. Join us " + CoreUtils.getPlayerFromAddress(e.getAddress())));
		
		for(Player player : Bukkit.getOnlinePlayers()){
			if(player.hasPermission("core.getpings")) {
				player.sendMessage(CoreUtils.colorize("&6-------[PING]-------"));
				player.sendMessage(CoreUtils.colorize("&6IP: &f" + e.getAddress()));
				player.sendMessage(CoreUtils.colorize("&6Cached: &f" + CoreUtils.checkAddress(e.getAddress())));
				player.sendMessage(CoreUtils.colorize("&6Player: &f" + CoreUtils.getPlayerFromAddress(e.getAddress())));
				player.sendMessage(CoreUtils.colorize("&6--------------------"));
			}
		}
		Main.getPlugin().getServer().getConsoleSender().sendMessage(CoreUtils.colorize("&6-------[PING]-------"));
		Main.getPlugin().getServer().getConsoleSender().sendMessage(CoreUtils.colorize("&6IP: &f" + e.getAddress()));
		Main.getPlugin().getServer().getConsoleSender().sendMessage(CoreUtils.colorize("&6Cached: &f" + CoreUtils.checkAddress(e.getAddress())));
		Main.getPlugin().getServer().getConsoleSender().sendMessage(CoreUtils.colorize("&6Player: &f" + CoreUtils.getPlayerFromAddress(e.getAddress())));
		Main.getPlugin().getServer().getConsoleSender().sendMessage(CoreUtils.colorize("&6--------------------"));
		
		try {
			e.setServerIcon(Bukkit.loadServerIcon(Images.toBufferedImage(Texture.loadTexture("/res/logo_4.png"))));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
