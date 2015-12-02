package me.quickScythe.eridaunicore.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.quickScythe.eridaunicore.Main;
import me.quickScythe.eridaunicore.utils.ParticleFormat;
import me.quickScythe.eridaunicore.utils.Utils;


public class ParticleListener implements Listener {
	Main plugin;

	public ParticleListener(Main plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (ChatColor.stripColor(e.getInventory().getTitle()).equals("Particles (Page 1)")){
			String m = e.getCurrentItem().getType().toString();
			switch (m){
			case "FIREBALL":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle FLAME");
				e.getWhoClicked().closeInventory();
				e.setCancelled(true);
				Bukkit.dispatchCommand(((Player) e.getWhoClicked()), "ParticleFormat");
				break;
			case "CAULDRON_ITEM":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle SPELL_WITCH");
				e.getWhoClicked().closeInventory();
				e.setCancelled(true);
				Bukkit.dispatchCommand(((Player) e.getWhoClicked()), "ParticleFormat");
				break;
			case "NETHER_STAR":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle FIREWORKS_SPARK");
				e.getWhoClicked().closeInventory();
				e.setCancelled(true);
				Bukkit.dispatchCommand(((Player) e.getWhoClicked()), "ParticleFormat");
				break;
			case "SNOW_BLOCK":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle CLOUD");
				e.getWhoClicked().closeInventory();
				Bukkit.dispatchCommand(((Player) e.getWhoClicked()), "ParticleFormat");
				e.setCancelled(true);
				break;
			case "REDSTONE":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle REDSTONE");
				e.getWhoClicked().closeInventory();
				e.setCancelled(true);
				e.getWhoClicked().openInventory(Utils.getParticleColorInventory((Player) e.getWhoClicked()));
				break;
			case "RECORD_9":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle NOTE");
				e.getWhoClicked().closeInventory();
				e.setCancelled(true);
				Bukkit.dispatchCommand(((Player) e.getWhoClicked()), "ParticleFormat");
				e.setCancelled(true);
				break;
			case "WATER_BUCKET":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle DRIP_WATER");
				e.getWhoClicked().closeInventory();
				e.setCancelled(true);
				Bukkit.dispatchCommand(((Player) e.getWhoClicked()), "ParticleFormat");
				break;
			case "EMERALD":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle VILLAGER_HAPPY");
				e.getWhoClicked().closeInventory();
				e.setCancelled(true);
				Bukkit.dispatchCommand(((Player) e.getWhoClicked()), "ParticleFormat");
				break;
			case "PAPER":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle ENCHANTMENT_TABLE");
				e.getWhoClicked().closeInventory();
				e.setCancelled(true);
				Bukkit.dispatchCommand(((Player) e.getWhoClicked()), "ParticleFormat");
				break;
			case "COAL":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle VILLAGER_ANGRY");
				e.getWhoClicked().closeInventory();
				e.setCancelled(true);
				Bukkit.dispatchCommand(((Player) e.getWhoClicked()), "ParticleFormat");
				break;
			case "NAME_TAG":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle HEART");
				e.getWhoClicked().closeInventory();
				e.setCancelled(true);
				Bukkit.dispatchCommand(((Player) e.getWhoClicked()), "ParticleFormat");
				break;
			case "LAVA_BUCKET":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle DRIP_LAVA");
				e.getWhoClicked().closeInventory();
				e.setCancelled(true);
				Bukkit.dispatchCommand(((Player) e.getWhoClicked()), "ParticleFormat");
				break;
			case "BARRIER":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle NULL");
				e.getWhoClicked().closeInventory();
				break;
			case "TNT":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle EXPLOSION_NORMAL");
				e.getWhoClicked().closeInventory();
				e.setCancelled(true);
				Bukkit.dispatchCommand(((Player) e.getWhoClicked()), "ParticleFormat");
				break;
			case "GOLD_SWORD":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle CRIT");
				e.getWhoClicked().closeInventory();
				e.setCancelled(true);
				Bukkit.dispatchCommand(((Player) e.getWhoClicked()), "ParticleFormat");
				break;
			case "REDSTONE_BLOCK":
				e.getWhoClicked().closeInventory();
				break;
			case "STAINED_GLASS_PANE":
				e.setCancelled(true);
				break;	
			case "ARROW":
				e.setCancelled(true);
				((Player) e.getWhoClicked()).openInventory(Utils.getParticleInventoryPage2(((Player) e.getWhoClicked())));
				break;
				default:
					break;
			}
			e.setCancelled(true);
				
			
			
			return;
		}
		if (ChatColor.stripColor(e.getInventory().getTitle()).equals("Particles (Page 2)")){
			String m = e.getCurrentItem().getType().toString();
			switch (m){
			case "DIAMOND_SWORD":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle CRIT_MAGIC");
				e.getWhoClicked().closeInventory();
				e.setCancelled(true);
				Bukkit.dispatchCommand(((Player) e.getWhoClicked()), "ParticleFormat");
				break;
			case "STAINED_GLASS_PANE":
				if(e.getCurrentItem().getDurability() != 10){
					e.setCancelled(true);
					break;
				}
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle PORTAL");
				e.getWhoClicked().closeInventory();
				e.setCancelled(true);
				Bukkit.dispatchCommand(((Player) e.getWhoClicked()), "ParticleFormat");
				break;
			case "FLINT_AND_STEEL":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle LAVA");
				e.getWhoClicked().closeInventory();
				e.setCancelled(true);
				Bukkit.dispatchCommand(((Player) e.getWhoClicked()), "ParticleFormat");
				break;
			case "SLIME_BALL":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle SLIME");
				e.getWhoClicked().closeInventory();
				e.setCancelled(true);
				Bukkit.dispatchCommand(((Player) e.getWhoClicked()), "ParticleFormat");
				break;
			case "SNOW_BALL":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle SNOWBALL");
				e.getWhoClicked().closeInventory();
				e.setCancelled(true);
				Bukkit.dispatchCommand(((Player) e.getWhoClicked()), "ParticleFormat");
				break;
			case "STONE":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle FOOTSTEP");
				e.getWhoClicked().closeInventory();
				e.setCancelled(true);
				Bukkit.dispatchCommand(((Player) e.getWhoClicked()), "ParticleFormat");
				break;
				
			case "BARRIER":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle NULL");
				e.getWhoClicked().closeInventory();
				break;
			case "REDSTONE_BLOCK":
				e.getWhoClicked().closeInventory();
				break;
					
			case "ARROW":
				e.setCancelled(true);
				((Player) e.getWhoClicked()).openInventory(Utils.getParticleInventory(((Player) e.getWhoClicked())));
				break;
				default:
					break;
			}
			e.setCancelled(true);
				
			
			
			return;
		}
		
		if (ChatColor.stripColor(e.getInventory().getTitle()).contains("Particle Formats")){
			Material m = e.getCurrentItem().getType();
			switch(m){
			case SKULL_ITEM:
				Utils.setParticleFormat((Player) e.getWhoClicked(), ParticleFormat.CircleHead);
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
				break;
			case LEATHER_BOOTS:
				Utils.setParticleFormat((Player) e.getWhoClicked(), ParticleFormat.CircleFeet);
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
				break;
			case EMERALD:
				Utils.setParticleFormat((Player) e.getWhoClicked(), ParticleFormat.Helix);
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
				break;
			case NETHER_STAR:
				Utils.setParticleFormat((Player) e.getWhoClicked(), ParticleFormat.DoubleHelix);
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
				break;
			case IRON_SWORD:
				Utils.setParticleFormat((Player) e.getWhoClicked(), ParticleFormat.Random);
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
				break;
			case BLAZE_POWDER:
				Utils.setParticleFormat((Player) e.getWhoClicked(), ParticleFormat.CrossFire);
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
				break;	
			case POTION:
				Utils.setParticleFormat((Player) e.getWhoClicked(), ParticleFormat.Spiral);
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
				break;
			case REDSTONE:
				Utils.setParticleFormat((Player) e.getWhoClicked(), ParticleFormat.BloodHelix);
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
				break;
				default:
					break;
			}
		}
		if (ChatColor.stripColor(e.getInventory().getTitle()).contains("Color Picker")){
			if(e.getCurrentItem().getType().equals(Material.INK_SACK)){
				Short m = e.getCurrentItem().getDurability();
						switch(m){
						case 1:
							Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particlecolor 225 51 0");
							e.getWhoClicked().closeInventory();
							e.setCancelled(true);
							break;
						case 14:
							Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particlecolor 225 153 0");
							e.getWhoClicked().closeInventory();
							e.setCancelled(true);
							break;
						case 11:
							Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particlecolor 225 255 0");
							e.getWhoClicked().closeInventory();
							e.setCancelled(true);
							break;	
						case 10:
							Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particlecolor 153 255 51");
							e.getWhoClicked().closeInventory();
							e.setCancelled(true);
							break;	
						case 2:
							Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particlecolor 1 153 0");
							e.getWhoClicked().closeInventory();
							e.setCancelled(true);
							break;	
						case 12:
							Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particlecolor 102 255 255");
							e.getWhoClicked().closeInventory();
							e.setCancelled(true);
							break;	
						case 5:
							Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particlecolor 153 0 153");
							e.getWhoClicked().closeInventory();
							e.setCancelled(true);
							break;	
						case 0:
							Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particlecolor 1 1 1");
							e.getWhoClicked().closeInventory();
							e.setCancelled(true);
							break;
						default:
							break;	
						}			
			}
			if(e.getCurrentItem().getType().equals(Material.NETHER_STAR)){
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particlecolor random");
				e.getWhoClicked().closeInventory();
				e.setCancelled(true);
			}
			if(e.getCurrentItem().getType().equals(Material.STAINED_GLASS_PANE)){
				e.setCancelled(true);
				return;
			}
			if(!e.getCurrentItem().getType().equals(Material.STAINED_GLASS_PANE) && Utils.getParticleFormat(((Player) e.getWhoClicked())) == null) Bukkit.dispatchCommand(((Player) e.getWhoClicked()), "ParticleFormat");
		}
	}
	
	
}
