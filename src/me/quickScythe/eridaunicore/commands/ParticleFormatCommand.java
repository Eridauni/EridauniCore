package me.quickScythe.eridaunicore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.quickScythe.eridaunicore.Main;
import me.quickScythe.eridaunicore.utils.CoreUtils;

public class ParticleFormatCommand implements CommandExecutor {
	Main plugin;
	public ParticleFormatCommand(Main plugin, String cmd){
		this.plugin = plugin;
		plugin.getCommand(cmd).setExecutor(this);
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("particleformat") && sender.hasPermission("vip.particle") && sender instanceof Player){
			((Player) sender).openInventory(CoreUtils.getParticleFormatInventory(((Player) sender)));
		}
		if(!sender.hasPermission("vip.particle")) sender.sendMessage(CoreUtils.colorize("&e&lParticle &f>&7 Sorry, but you don't have the permission to do that. \"vip.particle\""));
		return true;	
	}
}
