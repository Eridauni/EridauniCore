package me.quickScythe.eridaunicore.listeners;

import java.awt.Color;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import me.quickScythe.eridaunicore.Main;
import me.quickScythe.eridaunicore.core.Anvil;
import me.quickScythe.eridaunicore.utils.ParticleFormat;
import me.quickScythe.eridaunicore.utils.Utils;
import me.quickScythe.eridaunicore.utils.packets.ParticleEffect.ParticleProperty;


public class FriendListener implements Listener {
	Main plugin;

	public FriendListener(Main plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (ChatColor.stripColor(e.getInventory().getTitle()).equals("Friend Menu")){
			Material m = e.getCurrentItem().getType();
			ItemStack item = new ItemStack(Material.PAPER);
			ItemMeta im = item.getItemMeta();
			im.setDisplayName("Type friend name here...");
			item.setItemMeta(im);
			switch (m){
			case SKULL_ITEM:
				e.getWhoClicked().setMetadata("addfriend", new FixedMetadataValue(Main.getPlugin(), "yes"));
				Anvil.openAnvilInventory((Player) e.getWhoClicked(), item);
				break;
			case BARRIER:
				e.getWhoClicked().setMetadata("removefriend", new FixedMetadataValue(Main.getPlugin(), "yes"));
				Anvil.openAnvilInventory((Player) e.getWhoClicked(), item);
				break;
			case PAPER:
				Utils.openFriendListMenu((Player) e.getWhoClicked());
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
				((Player) e.getWhoClicked()).openInventory(Utils.getParticleInventory(((Player) e.getWhoClicked())));
				return;
				default:
					break;
			}
			
			if(Utils.getParticle(e.getWhoClicked().getUniqueId()).hasProperty(ParticleProperty.COLORABLE)){
				e.getWhoClicked().openInventory(Utils.getParticleColorInventory((Player) e.getWhoClicked()));
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
				Utils.setParticleFormat((Player) e.getWhoClicked(), ParticleFormat.CIRCLE_HEAD);
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
				break;
			case DIAMOND_BOOTS:
				Utils.setParticleFormat((Player) e.getWhoClicked(), ParticleFormat.CIRCLE_FEET);
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
				break;
			case DIAMOND_CHESTPLATE:
				Utils.setParticleFormat((Player) e.getWhoClicked(), ParticleFormat.CIRCLE_BODY);
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
				break;
			case EMERALD:
				Utils.setParticleFormat((Player) e.getWhoClicked(), ParticleFormat.HELIX);
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
				break;
			case NETHER_STAR:
				Utils.setParticleFormat((Player) e.getWhoClicked(), ParticleFormat.DOUBLE_HELIX);
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
				break;
			case IRON_SWORD:
				Utils.setParticleFormat((Player) e.getWhoClicked(), ParticleFormat.RANDOM);
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
				break;
			case BLAZE_POWDER:
				Utils.setParticleFormat((Player) e.getWhoClicked(), ParticleFormat.CROSS_FIRE);
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
				break;	
			case POTION:
				Utils.setParticleFormat((Player) e.getWhoClicked(), ParticleFormat.SPIRAL);
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
				break;
			case REDSTONE:
				Utils.setParticleFormat((Player) e.getWhoClicked(), ParticleFormat.BLOOD_HELIX);
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
				break;
			case SNOW_BALL:
				Utils.setParticleFormat((Player) e.getWhoClicked(), ParticleFormat.SPHERE);
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
				break;
			case SHIELD:
				Utils.setParticleFormat((Player) e.getWhoClicked(), ParticleFormat.FORCEFIELD);
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
				break;
			case SKULL_ITEM:
				Utils.setParticleFormat((Player) e.getWhoClicked(), ParticleFormat.CIRCLE_PLAYER);
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
							Utils.setParticleColor(player, Color.RED);
							e.getWhoClicked().closeInventory();
							e.setCancelled(true);
							break;
						case 14:
							Utils.setParticleColor(player, Color.ORANGE);
							e.getWhoClicked().closeInventory();
							e.setCancelled(true);
							break;
						case 11:
							Utils.setParticleColor(player, Color.YELLOW);
							e.getWhoClicked().closeInventory();
							e.setCancelled(true);
							break;	
						case 10:
							Utils.setParticleColor(player, Color.GREEN);
							e.getWhoClicked().closeInventory();
							e.setCancelled(true);
							break;	
						case 2:
							Utils.setParticleColor(player, Color.GREEN);
							e.getWhoClicked().closeInventory();
							e.setCancelled(true);
							break;	
						case 12:
							Utils.setParticleColor(player, Color.BLUE);
							e.getWhoClicked().closeInventory();
							e.setCancelled(true);
							break;	
						case 5:
							Utils.setParticleColor(player, Color.MAGENTA);
							e.getWhoClicked().closeInventory();
							e.setCancelled(true);
							break;	
						case 0:
							Utils.setParticleColor(player, Color.BLACK);
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
