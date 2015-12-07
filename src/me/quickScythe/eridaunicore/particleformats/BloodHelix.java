package me.quickScythe.eridaunicore.particleformats;

import java.util.ArrayList;
import java.util.List;

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
		double y = 0;
		double up = 0;
		try{
			i = Utils.getParticleTimer(player);
		} catch(NullPointerException ex){
			Utils.setParticleTimer(player, 0);
			i=0;
			
		}
		Double[] info = Utils.helixMath(player);
		if(info == null){
			Utils.setHelixMath(player, 1, 0);
			return;
		}
		y = info[0];
		up = info[1];
		

		
		
		
		List<Player> players = new ArrayList<Player>();
		for(Entity e : player.getNearbyEntities(50, 50, 50)) if(e instanceof Player) players.add((Player) e);
		players.add(player);
		
		
		
		if(i<40){
			l = Utils.getCircleLocation(i, (i*0.025), new Location(player.getWorld(), player.getLocation().getX(), (player.getLocation().getY()+3) + up, player.getLocation().getZ()));
			l2 = Utils.getCircleLocation2(i, (i*0.025), new Location(player.getWorld(), player.getLocation().getX(), (player.getLocation().getY()+3) + up, player.getLocation().getZ()));
			up = up- (0.075);
		} else {
			l = Utils.getCircleLocation(i, (1+(y-1)*-0.025D), new Location(player.getWorld(), player.getLocation().getX(), (player.getLocation().getY()+3 ) + up, player.getLocation().getZ()));
			l2 = Utils.getCircleLocation2(i, (1+(y-1)*-0.025D), new Location(player.getWorld(), player.getLocation().getX(), (player.getLocation().getY()+3) + up, player.getLocation().getZ()));
			up = up + (0.075);
			y = y +1;
		}
		
		
		
		
		
		
		
		Utils.displayParticle(player, l, players);
		Utils.displayParticle(player, l2, players);
		
		Utils.setHelixMath(player, y, up);
		if(i==80){
			Utils.setHelixMath(player, 0, 0);
			Utils.setParticleTimer(player, 0);
		}
		else Utils.setParticleTimer(player, i+1);
		
		players = null;
	}

}
