package me.quickScythe.eridaunicore.core;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.quickScythe.eridaunicore.Main;
import me.quickScythe.eridaunicore.utils.CoreUtils;
import me.quickScythe.eridaunicore.utils.Grenade;

public class MainTimer extends BukkitRunnable {
	
	

	public MainTimer(){
		Bukkit.broadcastMessage("4");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		Bukkit.broadcastMessage("5");
		
		Bukkit.broadcastMessage("Current cooldown number: " + Main.getPlugin().announcecooldown);
		Bukkit.broadcastMessage("Number trying to get to (~5minutes): " + ((5*60)*20));
		
		if(Main.getPlugin().announcecooldown >= (5*60)*20){
			Main.getPlugin().announcecooldown = 1;
		}
		
		if(Main.getPlugin().announcecooldown == 1){
			for(String s : Main.getPlugin().announcements.get(new Random().nextInt(Main.getPlugin().announcements.size())))
				Bukkit.broadcastMessage(CoreUtils.colorize(s));
		}
		
		Main.getPlugin().announcecooldown+=1;
		
		
		
		for(Grenade g : Main.getPlugin().grenades){
			g.updateLocation();
		}
		
		for(Grenade r : Main.getPlugin().removeGrenades)
			Main.getPlugin().grenades.remove(r);
		Main.getPlugin().removeGrenades.clear();
		
		for(Player player : Bukkit.getOnlinePlayers()){
			if(CoreUtils.getWingedPlayers().contains(player.getUniqueId()) && player.isFlying()){
				CoreUtils.displayWings(player);
			}
			if(player.hasPermission("vip.particles") && CoreUtils.getParticle(player.getUniqueId()) != null && CoreUtils.getParticleFormat(player) != null){
//				Bukkit.broadcastMessage(player.getVelocity().length() +"");
				CoreUtils.getParticleFormat(player).run(player);
			}
		}
		
		Bukkit.getScheduler().runTaskLater(Main.getPlugin(), new MainTimer(), 1);
	}
}
