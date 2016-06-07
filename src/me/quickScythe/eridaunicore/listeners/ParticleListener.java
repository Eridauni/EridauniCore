package me.quickScythe.eridaunicore.listeners;

import java.awt.Color;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.quickScythe.eridaunicore.Main;
import me.quickScythe.eridaunicore.utils.ParticleFormat;
import me.quickScythe.eridaunicore.utils.CoreUtils;
import me.quickScythe.eridaunicore.utils.packets.ParticleEffect.ParticleProperty;


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
				break;
			case "CAULDRON_ITEM":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle SPELL_WITCH");
				e.getWhoClicked().closeInventory();
				break;
			case "NETHER_STAR":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle FIREWORKS_SPARK");
				e.getWhoClicked().closeInventory();
				break;
			case "SNOW_BLOCK":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle CLOUD");
				e.getWhoClicked().closeInventory();
				break;
			case "REDSTONE":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle REDSTONE");
				e.getWhoClicked().closeInventory();
				break;
			case "RECORD_9":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle NOTE");
				e.getWhoClicked().closeInventory();
				e.setCancelled(true);
				break;
			case "WATER_BUCKET":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle DRIP_WATER");
				e.getWhoClicked().closeInventory();
				break;
			case "EMERALD":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle VILLAGER_HAPPY");
				e.getWhoClicked().closeInventory();
				break;
			case "PAPER":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle ENCHANTMENT_TABLE");
				e.getWhoClicked().closeInventory();
				break;
			case "COAL":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle VILLAGER_ANGRY");
				e.getWhoClicked().closeInventory();
				break;
			case "NAME_TAG":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle HEART");
				e.getWhoClicked().closeInventory();
				break;
			case "LAVA_BUCKET":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle DRIP_LAVA");
				e.getWhoClicked().closeInventory();
				break;
			case "BARRIER":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle NULL");
				e.getWhoClicked().closeInventory();
				return;
			case "TNT":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle EXPLOSION_NORMAL");
				e.getWhoClicked().closeInventory();
				break;
			case "GOLD_SWORD":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle CRIT");
				e.getWhoClicked().closeInventory();
				break;
			case "REDSTONE_BLOCK":
				e.getWhoClicked().closeInventory();
				return;
			case "STAINED_GLASS_PANE":
				e.setCancelled(true);
				return;	
			case "ARROW":
				e.setCancelled(true);
				((Player) e.getWhoClicked()).openInventory(CoreUtils.getParticleInventoryPage2(((Player) e.getWhoClicked())));
				return;
				default:
					break;
			}
			if(CoreUtils.getParticle(e.getWhoClicked().getUniqueId()).hasProperty(ParticleProperty.COLORABLE)){
				e.getWhoClicked().openInventory(CoreUtils.getParticleColorInventory((Player) e.getWhoClicked()));
			} else {
				Bukkit.dispatchCommand(((Player) e.getWhoClicked()), "ParticleFormat");
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
				break;
			case "STAINED_GLASS_PANE":
				if(e.getCurrentItem().getDurability() != 10){
					e.setCancelled(true);
					return;
				}
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle PORTAL");
				e.getWhoClicked().closeInventory();
				break;
			case "FLINT_AND_STEEL":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle LAVA");
				e.getWhoClicked().closeInventory();
				break;
			case "SLIME_BALL":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle SLIME");
				e.getWhoClicked().closeInventory();
				break;
			case "SNOW_BALL":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle SNOWBALL");
				e.getWhoClicked().closeInventory();
				break;
			case "STONE":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle FOOTSTEP");
				e.getWhoClicked().closeInventory();
				break;
				
			case "BARRIER":
				Bukkit.dispatchCommand((Player) e.getWhoClicked(), "particle NULL");
				e.getWhoClicked().closeInventory();
				return;
			case "REDSTONE_BLOCK":
				e.getWhoClicked().closeInventory();
				return;
					
			case "ARROW":
				e.setCancelled(true);
				((Player) e.getWhoClicked()).openInventory(CoreUtils.getParticleInventory(((Player) e.getWhoClicked())));
				return;
				default:
					break;
			}
			
			if(CoreUtils.getParticle(e.getWhoClicked().getUniqueId()).hasProperty(ParticleProperty.COLORABLE)){
				e.getWhoClicked().openInventory(CoreUtils.getParticleColorInventory((Player) e.getWhoClicked()));
			} else {
				Bukkit.dispatchCommand(((Player) e.getWhoClicked()), "ParticleFormat");
			}
			e.setCancelled(true);
				
			
			
			return;
		}
		
		if (ChatColor.stripColor(e.getInventory().getTitle()).contains("Particle Formats")){
			Material m = e.getCurrentItem().getType();
			switch(m){
			case DIAMOND_HELMET:
				CoreUtils.setParticleFormat((Player) e.getWhoClicked(), ParticleFormat.CIRCLE_HEAD);
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
				break;
			case DIAMOND_BOOTS:
				CoreUtils.setParticleFormat((Player) e.getWhoClicked(), ParticleFormat.CIRCLE_FEET);
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
				break;
			case DIAMOND_CHESTPLATE:
				CoreUtils.setParticleFormat((Player) e.getWhoClicked(), ParticleFormat.CIRCLE_BODY);
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
				break;
			case EMERALD:
				CoreUtils.setParticleFormat((Player) e.getWhoClicked(), ParticleFormat.HELIX);
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
				break;
			case NETHER_STAR:
				CoreUtils.setParticleFormat((Player) e.getWhoClicked(), ParticleFormat.DOUBLE_HELIX);
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
				break;
			case IRON_SWORD:
				CoreUtils.setParticleFormat((Player) e.getWhoClicked(), ParticleFormat.RANDOM);
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
				break;
			case BLAZE_POWDER:
				CoreUtils.setParticleFormat((Player) e.getWhoClicked(), ParticleFormat.CROSS_FIRE);
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
				break;	
			case POTION:
				CoreUtils.setParticleFormat((Player) e.getWhoClicked(), ParticleFormat.SPIRAL);
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
				break;
			case REDSTONE:
				CoreUtils.setParticleFormat((Player) e.getWhoClicked(), ParticleFormat.BLOOD_HELIX);
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
				break;
			case SNOW_BALL:
				CoreUtils.setParticleFormat((Player) e.getWhoClicked(), ParticleFormat.SPHERE);
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
				break;
			case SHIELD:
				CoreUtils.setParticleFormat((Player) e.getWhoClicked(), ParticleFormat.FORCEFIELD);
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
				break;
			case SKULL_ITEM:
				CoreUtils.setParticleFormat((Player) e.getWhoClicked(), ParticleFormat.CIRCLE_PLAYER);
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
				Player player = (Player) e.getWhoClicked();
						switch(m){
						case 1:
							CoreUtils.setParticleColor(player, Color.RED);
							e.getWhoClicked().closeInventory();
							e.setCancelled(true);
							break;
						case 14:
							CoreUtils.setParticleColor(player, Color.ORANGE);
							e.getWhoClicked().closeInventory();
							e.setCancelled(true);
							break;
						case 11:
							CoreUtils.setParticleColor(player, Color.YELLOW);
							e.getWhoClicked().closeInventory();
							e.setCancelled(true);
							break;	
						case 10:
							CoreUtils.setParticleColor(player, Color.GREEN);
							e.getWhoClicked().closeInventory();
							e.setCancelled(true);
							break;	
						case 2:
							CoreUtils.setParticleColor(player, Color.GREEN);
							e.getWhoClicked().closeInventory();
							e.setCancelled(true);
							break;	
						case 12:
							CoreUtils.setParticleColor(player, Color.BLUE);
							e.getWhoClicked().closeInventory();
							e.setCancelled(true);
							break;	
						case 5:
							CoreUtils.setParticleColor(player, Color.MAGENTA);
							e.getWhoClicked().closeInventory();
							e.setCancelled(true);
							break;	
						case 0:
							CoreUtils.setParticleColor(player, Color.BLACK);
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
			if(!e.getCurrentItem().getType().equals(Material.STAINED_GLASS_PANE) && CoreUtils.getParticleFormat(((Player) e.getWhoClicked())) == null) Bukkit.dispatchCommand(((Player) e.getWhoClicked()), "ParticleFormat");
		}
	}
	
	
}
