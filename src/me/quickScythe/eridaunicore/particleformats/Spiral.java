package me.quickScythe.eridaunicore.particleformats;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import me.quickScythe.eridaunicore.utils.Utils;

public class Spiral implements Runnable {
	Player player;
	Location l;
	Location l2;
	Location l3;
	Location l4;
	public Spiral(Player player){
		this.player = player;
	}
	
	@Override
	public void run() {
		int i;
		double y = 0;
		try{
			i = Utils.getParticleTimer(player);
		} catch(NullPointerException ex){
			Utils.setParticleTimer(player, 0);
			i=0;
			
		}
		if(i>60){
			Double[] info = Utils.helixMath(player);
			if(info == null){
				Utils.setHelixMath(player, 1, 0);
				return;
			}
			y = info[0];
			
		}
		
		
		
		List<Player> players = new ArrayList<Player>();
		for(Entity e : player.getNearbyEntities(50, 50, 50)) if(e instanceof Player) players.add((Player) e);
		players.add(player);
		
		if(i<60){
			l = Utils.getCircleLocation(i, (i*0.025), player.getLocation());
			l2 = Utils.getCircleLocation2(i, (i*0.025), player.getLocation());
		} else {
			l = Utils.getCircleLocation(i, (1.475+(y-1)*-0.025D), player.getLocation());
			l2 = Utils.getCircleLocation2(i, (1.475+(y-1)*-0.025D), player.getLocation());
		}
		
		
		
		
		
		
		
		Utils.displayParticle(player, l, players);
		Utils.displayParticle(player, l2, players);
		
		Utils.setHelixMath(player, y+1, 0);
		if(i==120){
			Utils.setHelixMath(player, 0, 0);
			Utils.setParticleTimer(player, 0);
		}
		else Utils.setParticleTimer(player, i+1);
		
		players = null;
	}

}
