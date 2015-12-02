package me.quickScythe.eridaunicore.particleformats;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import me.quickScythe.eridaunicore.utils.Utils;

public class BloodHelix implements Runnable {
	Player player;
	Location l;
	Location l2;
	public BloodHelix(Player player){
		this.player = player;
	}
	
	@Override
	public void run() {
		int i;
		try{
			i = Utils.getParticleTimer(player);
		} catch(NullPointerException ex){
			Utils.setParticleTimer(player, 0);
			i=0;
		}
		
		
		Double[] info = Utils.helixMath(player);
		if(info == null){
			Utils.setHelixMath(player, 0, 0);
			return;
		}
		double y = info[0];
		double up = info[1];
		
		List<Player> players = new ArrayList<Player>();
		for(Entity e : player.getNearbyEntities(50, 50, 50)) if(e instanceof Player) players.add((Player) e);
		players.add(player);
		
			l = Utils.getCircleLocation(i, (i*0.05), player.getLocation());
			l2 = Utils.getCircleLocationBackwards(i, (i*0.05), player.getLocation());
		
		
		
		
		
		Utils.displayParticle(player, new Location(l.getWorld(), l.getX(), l.getY()+y, l.getZ()), players);
		Utils.displayParticle(player, new Location(l2.getWorld(), l2.getX(), l2.getY()+y, l2.getZ()), players);
		
		
		
		Utils.setHelixMath(player, y, 0);
		
		if(i<=62){
			if(up == 0){
				Bukkit.broadcastMessage("NOT UP");
				Utils.setParticleTimer(player, i+1);
				Utils.setHelixMath(player, y+0.06451612903, 0);
			}
			if(up == 1){
				Bukkit.broadcastMessage("UP");
				Utils.setParticleTimer(player, i+1);
				Utils.setHelixMath(player, y-0.06451612903, 0);
			}
			
		} 
		if(i==31){
			Bukkit.broadcastMessage("31");
			Utils.setParticleTimer(player, i-1);
			Utils.setHelixMath(player, y-0.06451612903, 1);
		}
		if(i==-1){
			Bukkit.broadcastMessage("-1");
			Utils.setParticleTimer(player, 0);
			Utils.setHelixMath(player, 2, 0);
		}
		
//		Bukkit.broadcastMessage(i+ "");
		players = null;
	}

}
