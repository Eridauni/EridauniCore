package me.quickScythe.eridaunicore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import me.quickScythe.eridaunicore.Main;

public class MenuCommand implements CommandExecutor {
	Main plugin;
	public MenuCommand(Main plugin, String cmd){
		this.plugin = plugin;
		plugin.getCommand(cmd).setExecutor(this);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("menu") && sender instanceof Player){
//			me.quickScythe.eridaunicore.core.Anvil.openAnvilInventory(((Player) sender));
//			((Player) sender).setMetadata("anvil", new FixedMetadataValue(Main.getPlugin(), "yes"));
			
		}		
		return false;
	}

}
