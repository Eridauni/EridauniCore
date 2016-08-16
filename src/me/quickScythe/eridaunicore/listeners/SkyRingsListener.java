package me.quickScythe.eridaunicore.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.quickScythe.eridaunicore.Main;
import me.quickScythe.eridaunicore.utils.CoreUtils;

public class SkyRingsListener implements Listener {
	Main plugin;
	public SkyRingsListener(Main plugin){
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e){
		if (ChatColor.stripColor(e.getInventory().getTitle()).equals("Travel to Sky Rings?")){
			Material m = e.getCurrentItem().getType();
			switch (m){
			case EMERALD:
				e.getWhoClicked().teleport(CoreUtils.getSkyRingsSpawn());
				break;
			case INK_SACK:
				e.getWhoClicked().closeInventory();
				break;
			default:
				break;
			}
			e.setCancelled(true);
				
			
			
			return;
		}
	}
	
	
}

