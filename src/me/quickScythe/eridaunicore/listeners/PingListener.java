package me.quickScythe.eridaunicore.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import me.quickScythe.eridaunicore.Main;
import me.quickScythe.eridaunicore.utils.Images;
import me.quickScythe.eridaunicore.utils.Utils;
import res.Texture;

public class PingListener implements Listener {
	
	Main plugin;
	
	public PingListener(Main plugin){
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onServerPingEvent(ServerListPingEvent e){
		
		e.setMotd(Utils.colorize("&e&lEridauni\n&fSurvial in a friendly community. Join us " + Utils.getPlayerFromAddress(e.getAddress())));
		
		for(Player player : Bukkit.getOnlinePlayers()){
			if(player.hasPermission("core.getpings")) {
				player.sendMessage(Utils.colorize("&6-------[PING]-------"));
				player.sendMessage(Utils.colorize("&6IP: &f" + e.getAddress()));
				player.sendMessage(Utils.colorize("&6Cached: &f" + Utils.checkAddress(e.getAddress())));
				player.sendMessage(Utils.colorize("&6Player: &f" + Utils.getPlayerFromAddress(e.getAddress())));
				player.sendMessage(Utils.colorize("&6--------------------"));
			}
		}
		Main.getPlugin().getServer().getConsoleSender().sendMessage(Utils.colorize("&6-------[PING]-------"));
		Main.getPlugin().getServer().getConsoleSender().sendMessage(Utils.colorize("&6IP: &f" + e.getAddress()));
		Main.getPlugin().getServer().getConsoleSender().sendMessage(Utils.colorize("&6Cached: &f" + Utils.checkAddress(e.getAddress())));
		Main.getPlugin().getServer().getConsoleSender().sendMessage(Utils.colorize("&6Player: &f" + Utils.getPlayerFromAddress(e.getAddress())));
		Main.getPlugin().getServer().getConsoleSender().sendMessage(Utils.colorize("&6--------------------"));
		
		try {
			e.setServerIcon(Bukkit.loadServerIcon(Images.toBufferedImage(Texture.loadTexture("/res/logo_4.png"))));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
