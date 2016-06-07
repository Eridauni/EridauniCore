package me.quickScythe.eridaunicore.particleformats;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import me.quickScythe.eridaunicore.utils.CoreUtils;

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
		double y = 0;
		double up = 0;
		try{
			i = CoreUtils.getParticleTimer(player);
		} catch(NullPointerException ex){
			CoreUtils.setParticleTimer(player, 0);
			i=0;
			
		}
		Double[] info = CoreUtils.helixMath(player);
		if(info == null){
			CoreUtils.setHelixMath(player, 1, 0);
			return;
		}
		y = info[0];
		up = info[1];
		

		
		
		
		List<Player> players = new ArrayList<Player>();
		for(Entity e : player.getNearbyEntities(50, 50, 50)) if(e instanceof Player) players.add((Player) e);
		players.add(player);
		
		
		
		if(i<40){
			l = CoreUtils.getCircleLocation(i, (i*0.025), new Location(player.getWorld(), player.getLocation().getX(), (player.getLocation().getY()+3) + up, player.getLocation().getZ()));
			l2 = CoreUtils.getCircleLocation2(i, (i*0.025), new Location(player.getWorld(), player.getLocation().getX(), (player.getLocation().getY()+3) + up, player.getLocation().getZ()));
			up = up- (0.075);
		} else {
			l = CoreUtils.getCircleLocation(i, (1+(y-1)*-0.025D), new Location(player.getWorld(), player.getLocation().getX(), (player.getLocation().getY()+3 ) + up, player.getLocation().getZ()));
			l2 = CoreUtils.getCircleLocation2(i, (1+(y-1)*-0.025D), new Location(player.getWorld(), player.getLocation().getX(), (player.getLocation().getY()+3) + up, player.getLocation().getZ()));
			up = up + (0.075);
			y = y +1;
		}
		
		
		
		
		
		
		
		CoreUtils.displayParticle(player, l, players);
		CoreUtils.displayParticle(player, l2, players);
		
		CoreUtils.setHelixMath(player, y, up);
		if(i==80){
			CoreUtils.setHelixMath(player, 0, 0);
			CoreUtils.setParticleTimer(player, 0);
		}
		else CoreUtils.setParticleTimer(player, i+1);
		
		players = null;
	}

}
