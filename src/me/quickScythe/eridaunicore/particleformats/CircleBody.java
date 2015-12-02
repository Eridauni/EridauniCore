package me.quickScythe.eridaunicore.particleformats;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import me.quickScythe.eridaunicore.utils.Utils;

public class CircleBody implements Runnable {
	Player player;
	Location l;
	Location l2;
	public CircleBody(Player player){
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
		l = Utils.getCircleLocation(Utils.getParticleTimer(player), 1.0, player.getEyeLocation());
		l2 = Utils.getCircleLocationBackwards(Utils.getParticleTimer(player), 1.0, player.getEyeLocation());
		
		l.setY(l.getY()-0.5);
		l2.setY(l2.getY()-0.5);
		
		Utils.displayParticle(player, l, players);
		Utils.displayParticle(player, l2, players);
		
		
		
		if(i==31) Utils.setParticleTimer(player, 0);
		else Utils.setParticleTimer(player, i+1);
		players = null;
	}

}
