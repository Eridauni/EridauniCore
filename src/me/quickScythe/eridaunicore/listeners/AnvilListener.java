package me.quickScythe.eridaunicore.listeners;

import java.awt.List;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;

import me.quickScythe.eridaunicore.Main;
import me.quickScythe.eridaunicore.utils.IDatabase;
import me.quickScythe.eridaunicore.utils.Utils;

public class AnvilListener implements Listener {
	Main plugin;
	public AnvilListener(Main plugin){
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent e){
		if(e.getInventory().getType() == InventoryType.ANVIL){
			if(e.getPlayer().hasMetadata("anvil")){
				e.getInventory().clear();
			}
			e.getPlayer().removeMetadata("anvil", Main.getPlugin());
		}
	}
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e){
		if(e.getClickedInventory() == null) return;
		if(e.getClickedInventory().getType() != InventoryType.ANVIL) return;
		if(!(e.getWhoClicked() instanceof Player)) return;
		Player player = (Player) e.getWhoClicked();
		
		if(player.hasMetadata("addfriend")){
			Bukkit.broadcastMessage("1");
			if(e.getSlot() == 2){
				if(player.hasMetadata("anvil")){
					if(Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getDisplayName()) == null){
						e.setCancelled(true);
						player.closeInventory();
						player.sendMessage(Utils.colorize("&e&lFriends &f>&7 Sorry. That player must be online to add them as a friend."));
					} else {
						IDatabase sql = Utils.getConnection();
						int r = sql.update("UPDATE Users SET UUID='"+player.getUniqueId()+"',COIN="+Utils.getCoins(player)+",FRIENDS='"+Utils.getFriends(player) + "," + e.getCurrentItem().getItemMeta().getDisplayName()+"',NAME='"+player.getName()+"',IP='"+Utils.getPlayerAddress(player)+"' WHERE UUID='"+player.getUniqueId()+"'");
						if(r!=1)
							player.sendMessage(Utils.colorize("&e&lFriends &f>&7 There was an error adding your friend."));	
					}
					e.setCancelled(true);
					player.closeInventory();
					player.removeMetadata("addfriend", Main.getPlugin());
				}
			}
		}
		if(player.hasMetadata("removefriend")){
			Bukkit.broadcastMessage("2");
			if(e.getSlot() == 2){
				if(player.hasMetadata("anvil")){
					ArrayList<String> l = new ArrayList<String>();
					for(String f : Utils.getFriends(player).split(","))
						if(f!="")l.add(f);
					if(!l.contains(e.getCurrentItem().getItemMeta().getDisplayName())){
						e.setCancelled(true);
						player.closeInventory();
						player.sendMessage(Utils.colorize("&e&lFriends &f>&7 Sorry. That player is not in your friends list."));
					} else {
						l.remove(e.getCurrentItem().getItemMeta().getDisplayName());
						String f = "";
						for(String a : l)
							f = f+a + ",";
						f = f.substring(0, f.length());
						IDatabase sql = Utils.getConnection();
						int r = sql.update("UPDATE Users SET UUID='"+player.getUniqueId()+"',COIN="+Utils.getCoins(player)+",FRIENDS='"+f+"',NAME='"+player.getName()+"',IP='"+Utils.getPlayerAddress(player)+"' WHERE UUID='"+player.getUniqueId()+"'");
						if(r!=1)
							player.sendMessage(Utils.colorize("&e&lFriends &f>&7 There was an error adding your friend."));	
					}
					e.setCancelled(true);
					player.closeInventory();
					player.removeMetadata("removefriend", Main.getPlugin());
				}
			}
		}
		
	}
	
	
}
