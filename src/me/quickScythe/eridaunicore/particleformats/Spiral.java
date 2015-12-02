package me.quickScythe.eridaunicore.particleformats;

import java.util.ArrayList;
import java.util.List;

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
		try{
			i = Utils.getParticleTimer(player);
		} catch(NullPointerException ex){
			Utils.setParticleTimer(player, 0);
			i=0;
			
		}
		List<Player> players = new ArrayList<Player>();
		for(Entity e : player.getNearbyEntities(50, 50, 50)) if(e instanceof Player) players.add((Player) e);
		players.add(player);
		
		
		
		l = Utils.getCircleLocation(i, (i*0.025), player.getLocation());
		l2 = Utils.getCircleLocationBackwards(i, (i*0.025), player.getLocation());
		
		
		
		
		
		Utils.displayParticle(player, l, players);
		Utils.displayParticle(player, l2, players);
		
		
		if(i==60)
			Utils.setParticleTimer(player, 0);
		else Utils.setParticleTimer(player, i+1);
		
		players = null;
	}

}
