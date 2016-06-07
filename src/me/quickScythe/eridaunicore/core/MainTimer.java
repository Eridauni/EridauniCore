package me.quickScythe.eridaunicore.core;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.quickScythe.eridaunicore.utils.Grenade;
import me.quickScythe.eridaunicore.utils.CoreUtils;

public class MainTimer extends BukkitRunnable {
	
	public static List<Grenade> grenades = new ArrayList<>();
	public static List<Grenade> removeGrenades = new ArrayList<>();

	public MainTimer(){
		
	}

	@Override
	public void run() {
		
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
