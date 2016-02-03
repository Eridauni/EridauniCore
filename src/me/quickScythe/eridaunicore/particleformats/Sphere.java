package me.quickScythe.eridaunicore.particleformats;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import me.quickScythe.eridaunicore.utils.Utils;

public class Sphere implements Runnable {
	Player player;
	Location l;
	Location l2;
	public Sphere(Player player){
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
		
		l = Utils.getCircleLocation(Utils.getParticleTimer(player), 1.0, player.getLocation());
		l2 = Utils.getCircleLocationBackwards(Utils.getParticleTimer(player), 1.0, player.getLocation());
		
		Double[] info = Utils.helixMath(player);
		if(info == null){
			Utils.setHelixMath(player, 0, 0);
			return;
		}
		double y = info[0];
		double up = info[1];
		if(y>=2){
			up=1;
		}
		if(y<=0){
			up=0;
		}
		
		if(up<=0){
			y=y+(0.0625*2);
		}
		
		if(up>=1){
			y=y-(0.0625*2);
		}
		
		
		
		Utils.displayParticle(player, new Location(l.getWorld(), l.getX(),l.getY()+y, l.getZ()), players);
		Utils.displayParticle(player, new Location(l2.getWorld(), l2.getX(),(l2.getY())+y, l2.getZ()), players);
		
		Utils.setHelixMath(player, y, up);
		if(i==63) Utils.setParticleTimer(player, 0);
		else Utils.setParticleTimer(player, i+1);
		players = null;
	}

}
