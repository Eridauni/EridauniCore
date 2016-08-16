package me.quickScythe.eridaunicore.listeners;

import java.awt.Color;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import me.quickScythe.eridaunicore.Main;
import me.quickScythe.eridaunicore.core.Anvil;
import me.quickScythe.eridaunicore.utils.ParticleFormat;
import me.quickScythe.eridaunicore.utils.CoreUtils;


public class FriendListener implements Listener {
	Main plugin;

	public FriendListener(Main plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (ChatColor.stripColor(e.getInventory().getTitle()).equals("Friend Menu")){
			Material m = e.getCurrentItem().getType();
			ItemStack item = new ItemStack(Material.PAPER);
			ItemMeta im = item.getItemMeta();
			im.setDisplayName("Type friend name here...");
			item.setItemMeta(im);
			switch (m){
			case SKULL_ITEM:
				e.getWhoClicked().setMetadata("addfriend", new FixedMetadataValue(Main.getPlugin(), "yes"));
				Anvil.openAnvilInventory((Player) e.getWhoClicked(), item);
				break;
			case BARRIER:
				e.getWhoClicked().setMetadata("removefriend", new FixedMetadataValue(Main.getPlugin(), "yes"));
				Anvil.openAnvilInventory((Player) e.getWhoClicked(), item);
				break;
			case PAPER:
				CoreUtils.openFriendListMenu((Player) e.getWhoClicked());
				break;
			default:
				break;
			}
			e.setCancelled(true);
				
			
			
			return;
		}
	}
	
	
}
