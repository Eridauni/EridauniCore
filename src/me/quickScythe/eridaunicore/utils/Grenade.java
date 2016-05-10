package me.quickScythe.eridaunicore.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import me.quickScythe.eridaunicore.utils.packets.ParticleEffect;

public class Grenade {
	
	Location l;
	Vector v;
	double yaw;
	double pitch;
	int lifetime = 0;
	int MAX_LIFETIME = 20;
	
	public Grenade(Location loc, double yaw, double pitch){
		this.l = loc;
		this.yaw = yaw;
		this.pitch = pitch;
		
	}
	
	public double getYaw(){
		return yaw;
	}
	
	public double getPitch(){
		return pitch;
	}
	
	public Location getLocation(){
		return l;
	}

	public void updateLocation(){
		if (this.l.getBlock().getType().equals(Material.AIR))
	    {
	      Entity[] arrayOfEntity;
	      int j = (arrayOfEntity = this.l.getChunk().getEntities()).length;
	      for (int i = 0; i < j; i++)
	      {
	        Entity e = arrayOfEntity[i];
	        if (e.getLocation().getBlock().getLocation().equals(this.l.getBlock().getLocation()))
	        {
	          ParticleEffect.REDSTONE.display(1.0F, 1.0F, 1.0F, 5.0F, 200, this.l, 1000.0D);
	          
	          return;
	        }
	      }
	      
	      ParticleEffect.FIREWORKS_SPARK.display(0.0F, 0.0F, 0.0F, 0.0F, 1, this.l, 1000.0D);
	      if (lifetime < MAX_LIFETIME)
	      {
	        if (new Location(this.l.getWorld(), this.l.getX() + this.v.getX(), this.l.getY(), this.l.getZ()).getBlock().getType() != Material.AIR) {
	          v = v.multiply(new Vector(-0.7D, 1.0D, 1.0D));
	        }
	        if (new Location(this.l.getWorld(), this.l.getX(), this.l.getY(), this.l.getZ() + this.v.getZ()).getBlock().getType() != Material.AIR) {
	          v = v.multiply(new Vector(1.0D, 1.0D, -0.7D));
	        }
	        if (new Location(this.l.getWorld(), this.l.getX(), this.l.getY() + this.v.getY(), this.l.getZ()).getBlock().getType() != Material.AIR) {
	          this.v = this.v.multiply(new Vector(1.0D, -0.7D, 1.0D));
	        }
	      }
	      this.l = new Location(this.l.getWorld(), this.l.getX() + this.v.getX(), this.l.getY() + this.v.getY(), this.l.getZ() + this.v.getZ());
	      v = v.subtract(new Vector(0.0D, 0.04D, 0.0D));
	      
	    }
	    else
	    {
	      ParticleEffect.SPELL_WITCH.display(0.5F, 0.5F, 0.5F, 0.0F, 200, this.l, 1000.0D);
	    }
	  }

}
