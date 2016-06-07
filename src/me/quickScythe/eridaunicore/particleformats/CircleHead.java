package me.quickScythe.eridaunicore.particleformats;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import me.quickScythe.eridaunicore.utils.CoreUtils;

public class CircleHead implements Runnable {
	Player player;
	Location l;
	Location l2;
	public CircleHead(Player player){
		this.player = player;
	}
	
	@Override
	public void run() {
		int i;
		try{
			i = CoreUtils.getParticleTimer(player);
		} catch(NullPointerException ex){
			CoreUtils.setParticleTimer(player, 0);
			i=0;
		}
		List<Player> players = new ArrayList<Player>();
		for(Entity e : player.getNearbyEntities(50, 50, 50)) if(e instanceof Player) players.add((Player) e);
		players.add(player);
		l = CoreUtils.getCircleLocation(CoreUtils.getParticleTimer(player), 1.0, player.getEyeLocation());
		l2 = CoreUtils.getCircleLocation2(CoreUtils.getParticleTimer(player), 1.0, player.getEyeLocation());
		
		l.setY(l.getY()+0.5);
		l2.setY(l2.getY()+0.5);
		
		CoreUtils.displayParticle(player, l, players);
		CoreUtils.displayParticle(player, l2, players);
		
		
		
		if(i==31) CoreUtils.setParticleTimer(player, 0);
		else CoreUtils.setParticleTimer(player, i+1);
		players = null;
	}

}
