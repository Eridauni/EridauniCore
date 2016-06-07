package me.quickScythe.eridaunicore.listeners;

import java.sql.ResultSet;
import java.util.Random;

import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import me.quickScythe.eridaunicore.Main;
import me.quickScythe.eridaunicore.utils.SQLUtils;
import me.quickScythe.eridaunicore.utils.CoreUtils;

public class EntityListener implements Listener {
	Main plugin;
	String[] vmessages = {"Hey, %player%. I hope you're enjoying yourself.", "Have you heard about that QuickScythe person? I heard he's scary. Sky_General109 too...", "Hey. What's up?", "Please don't look at me. It makes me uncofmortable.", "Go away please. I like being alone", "GO AWAY! I DON'T ACCEPT YOUR KIND!", "..."};
	
	String[] smessages = {"Sorry. Shop's closed for now...", "Come back later. We're closed.", "Shoo. Shop is closed.","I'm busy. Come back later.","You don't look like you have the coin..."};
	
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
					e.getPlayer().sendMessage(CoreUtils.colorize("&7Villager &f> &8" + message));
				}
				if(v.getCustomName().equalsIgnoreCase("Shop")){
					e.setCancelled(true);
					String message = "";
					message = smessages[new Random().nextInt(smessages.length)];
					message = message.replaceAll("%player%", e.getPlayer().getName());
					e.getPlayer().sendMessage(CoreUtils.colorize("&7Shopkeep &f> &8" + message));
					
					ResultSet set = SQLUtils.sendQuery("SELECT * FROM Users");
					
				}
			} catch(NullPointerException ex){
				return;
			}
		}
			
		
	}
	
	
}
