package me.quickScythe.eridaunicore.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.quickScythe.eridaunicore.Main;
import me.quickScythe.eridaunicore.utils.CoreUtils;
import me.quickScythe.eridaunicore.utils.Grenade;

public class MainTimer extends BukkitRunnable {
	
	public static List<Grenade> grenades = new ArrayList<>();
	public static List<Grenade> removeGrenades = new ArrayList<>();
	public static int announcecooldown = 1;
	public static int minute = 5;

	public MainTimer(){
	}

	@Override
	public void run() {
//		Bukkit.broadcastMessage("5");
		
		
		if(announcecooldown >= (minute*60)*20){
			announcecooldown = 1;
		}
		
		if(announcecooldown == 1){
			for(String s : Main.getPlugin().announcements.get(new Random().nextInt(Main.getPlugin().announcements.size())+1))
				Bukkit.broadcastMessage(CoreUtils.colorize(s));
		}
		
		announcecooldown = announcecooldown + 1;
		
		
//		Bukkit.broadcastMessage("Current cooldown number: " + announcecooldown);
//		Bukkit.broadcastMessage("Number trying to get to (~" + minute + " minutes): " + ((minute*60)*20));
		
		
		for(Grenade g : grenades){
			g.updateLocation();
		}
		
		for(Grenade r : removeGrenades)
			grenades.remove(r);
		removeGrenades.clear();
		
		for(Player player : Bukkit.getOnlinePlayers()){
			if(CoreUtils.getWingedPlayers().contains(player.getUniqueId()) && player.isFlying()){
				CoreUtils.displayWings(player);
			}
			if(player.hasPermission("vip.particles") && CoreUtils.getParticle(player.getUniqueId()) != null && CoreUtils.getParticleFormat(player) != null){
//				Bukkit.broadcastMessage(player.getVelocity().length() +"");
				CoreUtils.getParticleFormat(player).run(player);
			}
		}
		
	}
}
