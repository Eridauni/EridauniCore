package me.quickScythe.eridaunicore.core;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.quickScythe.eridaunicore.utils.Grenade;
import me.quickScythe.eridaunicore.utils.Utils;

public class MainTimer extends BukkitRunnable {
	
	public static List<Grenade> grenades = new ArrayList<>();

	public MainTimer(){
		
	}

	@Override
	public void run() {
		
		for(Grenade g : grenades){
			g.updateLocation();
		}
		
		for(Player player : Bukkit.getOnlinePlayers()){
			if(Utils.getWingedPlayers().contains(player.getUniqueId())){
				Utils.displayWings(player);
			}
			if(player.hasPermission("vip.particles") && Utils.getParticle(player.getUniqueId()) != null && Utils.getParticleFormat(player) != null){
//				Bukkit.broadcastMessage(player.getVelocity().length() +"");
				Utils.getParticleFormat(player).run(player);
			}
		}
	}
}
