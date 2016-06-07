package me.quickScythe.eridaunicore.particleformats;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import me.quickScythe.eridaunicore.utils.CoreUtils;

public class Random implements Runnable {
	Player player;
	Location l;
	public Random(Player player){
		this.player = player;
	}
	
	@Override
	public void run() {
		List<Player> players = new ArrayList<Player>();
		for(Entity e : player.getNearbyEntities(50, 50, 50)) if(e instanceof Player) players.add((Player) e);
		players.add(player);
		Location l = new Location(player.getLocation().getWorld(), 
				player.getLocation().getX() - 1 + Double.parseDouble(((getRandomInt(2)) + "." + (getRandomInt(99)))),
				player.getLocation().getY() + Double.parseDouble(((getRandomInt(3)) + "." + (getRandomInt(99)))),
				player.getLocation().getZ() - 1 + Double.parseDouble(((getRandomInt(2)) + "."+ (getRandomInt(99)))));
		
	
		CoreUtils.displayParticle(player, l, players);
		
		players = null;
	}
	public static Integer getRandomInt(Integer max) {
		java.util.Random ran = new java.util.Random();
		return ran.nextInt(max);
	}	
}
