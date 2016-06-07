package me.quickScythe.eridaunicore.particleformats;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import me.quickScythe.eridaunicore.utils.CoreUtils;

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
			i = CoreUtils.getParticleTimer(player);
		} catch(NullPointerException ex){
			CoreUtils.setParticleTimer(player, 0);
			i=0;
			
		}
		if(i>60){
			Double[] info = CoreUtils.helixMath(player);
			if(info == null){
				CoreUtils.setHelixMath(player, 1, 0);
				return;
			}
			y = info[0];
			
		}
		
		
		
		List<Player> players = new ArrayList<Player>();
		for(Entity e : player.getNearbyEntities(50, 50, 50)) if(e instanceof Player) players.add((Player) e);
		players.add(player);
		
		if(i<60){
			l = CoreUtils.getCircleLocation(i, (i*0.025), player.getLocation());
			l2 = CoreUtils.getCircleLocation2(i, (i*0.025), player.getLocation());
		} else {
			l = CoreUtils.getCircleLocation(i, (1.475+(y-1)*-0.025D), player.getLocation());
			l2 = CoreUtils.getCircleLocation2(i, (1.475+(y-1)*-0.025D), player.getLocation());
		}
		
		
		
		
		
		
		
		CoreUtils.displayParticle(player, l, players);
		CoreUtils.displayParticle(player, l2, players);
		
		CoreUtils.setHelixMath(player, y+1, 0);
		if(i==120){
			CoreUtils.setHelixMath(player, 0, 0);
			CoreUtils.setParticleTimer(player, 0);
		}
		else CoreUtils.setParticleTimer(player, i+1);
		
		players = null;
	}

}
