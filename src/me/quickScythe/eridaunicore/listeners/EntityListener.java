package me.quickScythe.eridaunicore.listeners;

import java.util.Random;

import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import me.quickScythe.eridaunicore.Main;
import me.quickScythe.eridaunicore.utils.Utils;

public class EntityListener implements Listener {
	Main plugin;
	String[] vmessages = {"Hey, %player%. I hope you're enjoying yourself.", "Have you heard about that QuickScythe person? I heard he's scary. Sky_General109 too...", "Hey. What's up?", "Please don't look at me. It makes me uncofmortable.", "Go away please. I like being alone", "GO AWAY! I DON'T ACCEPT YOUR KIND!", "..."};
	public EntityListener(Main plugin){
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent e){
		if(e.getRightClicked() instanceof Villager){
			Villager v = (Villager) e.getRightClicked();
			try{
				if(v.getCustomName().equalsIgnoreCase("Villager")){
					e.setCancelled(true);
					String message = "";
					message = vmessages[new Random().nextInt(vmessages.length)];
					message = message.replaceAll("%player%", e.getPlayer().getName());
					e.getPlayer().sendMessage(Utils.colorize("&7Villager &f> &8" + message));
				}
			} catch(NullPointerException ex){
				return;
			}
		}
			
		
	}
	
	
}
