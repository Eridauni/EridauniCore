package me.quickScythe.eridaunicore.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.quickScythe.eridaunicore.Main;
import me.quickScythe.eridaunicore.utils.Utils;


public class HouseListener implements Listener {
	Main plugin;

	public HouseListener(Main plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (ChatColor.stripColor(e.getInventory().getTitle()).equals("House Menu")){
			String m = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName());
			switch (m){
			case "Make House":
				Utils.registerHouse(((Player) e.getWhoClicked()));
				break;
			}
			e.setCancelled(true);
				
			
			
			return;
		}
	}
	
	
}
