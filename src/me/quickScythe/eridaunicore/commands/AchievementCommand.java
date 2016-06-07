package me.quickScythe.eridaunicore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.quickScythe.eridaunicore.Main;
import me.quickScythe.eridaunicore.utils.CoreUtils;

public class AchievementCommand implements CommandExecutor {
	Main plugin;
	public AchievementCommand(Main plugin, String cmd){
		this.plugin = plugin;
		plugin.getCommand(cmd).setExecutor(this);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("achievement")){
			if(args.length == 2 && sender.hasPermission("admin.acheivement")){
				if(Bukkit.getPlayer(args[0]) == null) {
					sender.sendMessage(CoreUtils.colorize("&e&lAcheivement &f>&7 That player isn't online."));
					sender.sendMessage(CoreUtils.colorize("&e&lHelp &f>&7 Give player achievement: \"/achievement <player> <achievement>\""));
					return false;
				}
				if(!CoreUtils.getGamer(Bukkit.getPlayer(args[0])).hasAchievement(CoreUtils.getAchievement(args[1]))){
					CoreUtils.getGamer(Bukkit.getPlayer(args[0])).sendMessage("&e&lAchievements &f>&7 You have just got the achievement &f&l" + CoreUtils.getAchievement(args[1]).getName() + "&7!");
					CoreUtils.getGamer(Bukkit.getPlayer(args[0])).addAchievement(CoreUtils.getAchievement(args[1]));
				}
			}
			if(args.length == 0){
				if(sender instanceof Player)
					((Player) sender).openInventory(CoreUtils.getAchievementInventory(((Player) sender)));
				else sender.sendMessage(CoreUtils.colorize("&e&lHelp &f>&7 Give player achievement: \"/achievement <player> <achievement>\""));
			}
			
		}
		return false;
	}

}
