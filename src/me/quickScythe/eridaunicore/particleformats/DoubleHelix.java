package me.quickScythe.eridaunicore.particleformats;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import me.quickScythe.eridaunicore.utils.CoreUtils;

public class DoubleHelix implements Runnable {
	Player player;
	Location l;
	Location l2;
	public DoubleHelix(Player player){
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
		l = CoreUtils.getCircleLocation(i, 1.0, player.getLocation());
		l2 = CoreUtils.getCircleLocation2(i, 1.0, player.getLocation());
		Double[] info = CoreUtils.helixMath(player);
		if(info == null){
			CoreUtils.setHelixMath(player, 0, 0);
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
			y=y+0.0625;
		}
		
		if(up>=1){
			y=y-0.0625;
		}
		
		
		
		CoreUtils.displayParticle(player, new Location(l.getWorld(), l.getX(), l.getY()+y, l.getZ()), players);
		CoreUtils.displayParticle(player, new Location(l2.getWorld(), l2.getX(), l2.getY()+y, l2.getZ()), players);
		
		
		
		CoreUtils.setHelixMath(player, y, up);
		if(i==31) CoreUtils.setParticleTimer(player, 0);
		else CoreUtils.setParticleTimer(player, i+1);
		players = null;
	}

}
