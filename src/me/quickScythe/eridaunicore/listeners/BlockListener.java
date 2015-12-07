package me.quickScythe.eridaunicore.listeners;

import org.bukkit.Material;
import org.bukkit.block.Dispenser;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;

import me.quickScythe.eridaunicore.Main;

public class BlockListener implements Listener {
	Main plugin;
	public BlockListener(Main plugin){
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void onBlockDispence(BlockDispenseEvent e){
		if(e.getBlock().getType().equals(Material.DISPENSER)){
			Dispenser block = (Dispenser) e.getBlock().getState();
			if(!block.getInventory().getName().equalsIgnoreCase("Infa")) return;
			block.getInventory().addItem(e.getItem());
		}
	}
	
	
}
