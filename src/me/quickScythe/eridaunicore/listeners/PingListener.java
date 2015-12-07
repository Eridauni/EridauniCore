package me.quickScythe.eridaunicore.listeners;

import org.bukkit.Bukkit;
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
		
		e.setMotd(Utils.colorize("&e&lEridauni\n&fSurvial in a friendly community."));
		
		try {
			e.setServerIcon(Bukkit.loadServerIcon(Images.toBufferedImage(Texture.loadTexture("/res/logo.png"))));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
