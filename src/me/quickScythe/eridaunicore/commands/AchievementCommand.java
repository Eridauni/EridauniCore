package me.quickScythe.eridaunicore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.quickScythe.eridaunicore.Main;
import me.quickScythe.eridaunicore.utils.Utils;

public class AchievementCommand implements CommandExecutor {
	Main plugin;
	public AchievementCommand(Main plugin, String cmd){
		this.plugin = plugin;
		plugin.getCommand(cmd).setExecutor(this);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("achievement")){
			if(args.length == 2 && sender.hasPermission("admin.acheivement")){
				if(Bukkit.getPlayer(args[0]) == null) return false;
				if(!Utils.getGamer(Bukkit.getPlayer(args[0])).hasAchievement(Utils.getAchievement(args[1]))){
					Utils.getGamer(Bukkit.getPlayer(args[0])).sendMessage("&e&lAchievements &f>&7 You have just got the achievement &f&l" + Utils.getAchievement(args[1]).getName() + "&7!");
					Utils.getGamer(Bukkit.getPlayer(args[0])).addAchievement(Utils.getAchievement(args[1]));
				}
			}
			
		}
		return false;
	}

}
