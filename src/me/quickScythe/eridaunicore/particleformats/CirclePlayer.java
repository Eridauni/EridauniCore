package me.quickScythe.eridaunicore.particleformats;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import me.quickScythe.eridaunicore.utils.Utils;

public class CirclePlayer implements Runnable {
	Player player;
	List<Location> particles = new ArrayList<>();
	public CirclePlayer(Player player){
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
		
		Location eyes = player.getEyeLocation();
		
		Location body = new Location(eyes.getWorld(), eyes.getX(), eyes.getY()-0.75, eyes.getZ());
		
		Location feet = new Location(eyes.getWorld(), eyes.getX(), eyes.getY()-1.5, eyes.getZ());
		
		particles.add(Utils.getCircleLocation(Utils.getParticleTimer(player), 1.0, eyes));
		particles.add(Utils.getCircleLocation2(Utils.getParticleTimer(player), 1.0, eyes));
		particles.add(Utils.getCircleLocation(Utils.getParticleTimer(player), 1.0, body));
		particles.add(Utils.getCircleLocation2(Utils.getParticleTimer(player), 1.0, body));
		particles.add(Utils.getCircleLocation(Utils.getParticleTimer(player), 1.0, feet));
		particles.add(Utils.getCircleLocation2(Utils.getParticleTimer(player), 1.0, feet));
		
		
		for(Location l : particles)
			Utils.displayParticle(player, l, players);
		
		
		
		if(i==31) Utils.setParticleTimer(player, 0);
		else Utils.setParticleTimer(player, i+1);
		players = null;
		feet = null;
		eyes = null;
		body = null;
	}

}
