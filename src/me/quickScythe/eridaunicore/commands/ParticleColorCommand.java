package me.quickScythe.eridaunicore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.quickScythe.eridaunicore.Main;
import me.quickScythe.eridaunicore.utils.Utils;

public class ParticleColorCommand implements CommandExecutor {
	Main plugin;
	public ParticleColorCommand(Main plugin, String cmd){
		this.plugin = plugin;
		plugin.getCommand(cmd).setExecutor(this);
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("particlecolor") && sender.hasPermission("vip.particle") && sender instanceof Player && args.length == 3){
			Utils.setParticleColor((Player) sender, args[0] + " " + args[1] + " " + args[2]);
			return false;
		} if(args.length == 1 && args[0].equalsIgnoreCase("random"))
			Utils.setParticleColor((Player) sender, "random");
		if(args.length == 0)
			((Player) sender).openInventory(Utils.getParticleColorInventory((Player) sender));
		
		
		else {
			sender.sendMessage(Utils.colorize("&e&lParticle &f>&7 Sorry, there was an error... This command should only be used if you know what you're doing."));
		}
		if(!sender.hasPermission("vip.particle")) sender.sendMessage(Utils.colorize("&e&lParticle &f>&7 Sorry, but you don't have the permission to do that."));
		return true;	
	}
}
