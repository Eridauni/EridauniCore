package me.quickScythe.eridaunicore.core;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.quickScythe.eridaunicore.utils.Utils;

public class MainTimer extends BukkitRunnable {

	public MainTimer(){
		
	}

	@Override
	public void run() {
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
