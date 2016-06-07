package me.quickScythe.eridaunicore.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.quickScythe.eridaunicore.Main;
import me.quickScythe.eridaunicore.core.MainTimer;
import me.quickScythe.eridaunicore.utils.Grenade;
import me.quickScythe.eridaunicore.utils.CoreUtils;
import net.minecraft.server.v1_9_R1.Material;

public class GrenadeListener implements Listener {
	Main plugin;
	public GrenadeListener(Main plugin){
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent e){
		if(e.getItem() == null) return;
		if(e.getItem().getItemMeta() == null) return;
		if(e.getItem().getItemMeta().getDisplayName() == null) return;
		if(e.getItem().getItemMeta().getDisplayName().equals(CoreUtils.colorize("&cGrenade"))){
			MainTimer.grenades.add(new Grenade(e.getPlayer().getEyeLocation(), e.getPlayer().getEyeLocation().getDirection()));
			if(!e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) e.getPlayer().setItemInHand(null);
		}
	}
	
}
