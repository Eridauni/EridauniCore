package me.quickScythe.eridaunicore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.quickScythe.eridaunicore.Main;
import me.quickScythe.eridaunicore.utils.CoreUtils;

public class SpawnCommand implements CommandExecutor {
	Main plugin;
	public SpawnCommand(Main plugin, String cmd){
		this.plugin = plugin;
		plugin.getCommand(cmd).setExecutor(this);
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("spawn")){
			if(sender instanceof Player){
				Player player = (Player) sender;
				if(args.length == 0){
					player.teleport(CoreUtils.getSpawn());
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&lTeleport &f>&7 You have to been teleported to spawn."));
				}
				if(args.length == 1){
					if(args[0].equalsIgnoreCase("set") && sender.hasPermission("core.spawn.set")){
						CoreUtils.setSpawn(((Player) sender).getLocation());
						sender.sendMessage(CoreUtils.colorize("&e&lSpawn &f>&7 You just set the spawn."));
						return true;
					}
					
					if(args[0].equalsIgnoreCase("setsky") && sender.hasPermission("core.skyrings.spawn.set")){
						CoreUtils.setSkyRingsSpawn(((Player) sender).getLocation());
						sender.sendMessage(CoreUtils.colorize("&e&lSkyRings &f>&7 You just set the &fSky Rings&7 spawn."));
						return true;
					}
					
					if(args[0].equalsIgnoreCase("sky") && sender.hasPermission("core.skyrings.command.spawn")){
						((Player)sender).teleport(CoreUtils.getSkyRingsSpawn());
						return true;
					}
					if(player.hasPermission("core.spawn.other")){
						if(Bukkit.getPlayer(args[0]) == null){
							player.sendMessage(CoreUtils.colorize("&e&lError &f>&7 Sorry, that player isn't online."));
							return true;
						}
						Bukkit.getPlayer(args[0]).teleport(CoreUtils.getSpawn());
						player.sendMessage(CoreUtils.colorize("&e&lTeleport &f>&7 You have teleported &8" + args[0] + "&7 to spawn."));
						Bukkit.getPlayer(args[0]).sendMessage(CoreUtils.colorize("&e&lTeleport &f>&7 You have to been teleported to spawn."));
					}
				} 
			} else {
				sender.sendMessage(CoreUtils.colorize("&e&lError &f>&7 Only players can use that command."));
			}
		}
		return false;
	}

}
