package me.quickScythe.eridaunicore.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.util.Vector;

import me.quickScythe.eridaunicore.core.MainTimer;
import me.quickScythe.eridaunicore.utils.packets.ParticleEffect;

public class Grenade {

	Location l;
	Vector v;
	int lifetime = 0;
	int MAX_LIFETIME = 40;

	public Grenade(Location loc, Vector v) {
		this.l = loc;
		this.v = v;

	}

	public Vector getVector() {
		return v;
	}

	public Location getLocation() {
		return l;
	}

	public void updateLocation() {
		if (this.l.getBlock().getType().equals(Material.AIR)) {

			ParticleEffect.FIREWORKS_SPARK.display(0.0F, 0.0F, 0.0F, 0.0F, 1, this.l, 1000.0D);
			if (lifetime < MAX_LIFETIME) {
				if (new Location(this.l.getWorld(), this.l.getX() + this.v.getX(), this.l.getY(), this.l.getZ())
						.getBlock().getType() != Material.AIR) {
					v = v.multiply(new Vector(-0.7D, 1.0D, 1.0D));
				}
				if (new Location(this.l.getWorld(), this.l.getX(), this.l.getY(), this.l.getZ() + this.v.getZ())
						.getBlock().getType() != Material.AIR) {
					v = v.multiply(new Vector(1.0D, 1.0D, -0.7D));
				}
				if (new Location(this.l.getWorld(), this.l.getX(), this.l.getY() + this.v.getY(), this.l.getZ())
						.getBlock().getType() != Material.AIR) {
					v = v.multiply(new Vector(1.0D, -0.7D, 1.0D));
				}
			} else {
				ParticleEffect.SPELL_WITCH.display(0.5F, 0.5F, 0.5F, 0.0F, 200, this.l, 1000.0D);
				MainTimer.removeGrenades.add(this);
				l.getWorld().createExplosion(l, 4F);

			}
			this.l = new Location(this.l.getWorld(), this.l.getX() + this.v.getX(), this.l.getY() + this.v.getY(),
					this.l.getZ() + this.v.getZ());
			v = v.subtract(new Vector(0.0D, 0.04D, 0.0D));

		}

		l = l.add(v);
		lifetime = lifetime + 1;
	}

}
