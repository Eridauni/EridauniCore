package me.quickScythe.eridaunicore.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.quickScythe.eridaunicore.Main;
import net.md_5.bungee.api.ChatColor;

public class AchievementListener implements Listener {
	
	Main plugin;
	
	public AchievementListener(Main plugin){
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e){
		if(ChatColor.stripColor(e.getInventory().getName()).equals("Achievements")){
			e.setCancelled(true);
		}
	}

}
