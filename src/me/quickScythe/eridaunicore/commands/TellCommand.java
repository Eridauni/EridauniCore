package me.quickScythe.eridaunicore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.quickScythe.eridaunicore.Main;
import me.quickScythe.eridaunicore.utils.Utils;

public class TellCommand implements CommandExecutor {
	Main plugin;
	public TellCommand(Main plugin, String cmd){
		this.plugin = plugin;
		plugin.getCommand(cmd).setExecutor(this);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("tell") && sender instanceof Player){
			if(args.length <= 1){
				sender.sendMessage(Utils.colorize("&e&lWhisper &f>&7 To whipser to a player, type &f\"/whisper <player> <message>\"&7."));
				return false;
			}
			if(Bukkit.getPlayer(args[0]) == null){
				sender.sendMessage(Utils.colorize("&e&lWhisper &f>&7 That player isn't online."));
				return false;
			}
			if(Bukkit.getPlayer(args[0]).equals((Player) sender)){
				return false;
			}
			
			String message = "";
			
			for(int i=0;i!=args.length;i++){
				if(i==0) continue;
				message = message + " " + args[i];
			}
			message = message.replaceFirst(" ", "");
			Bukkit.getPlayer(args[0]).sendMessage(Utils.colorize("&e&lWhisper from " + sender.getName() + "&f>&7 " + message));
			sender.sendMessage((Utils.colorize("&e&lWhisper to " + Bukkit.getPlayer(args[0]).getName() + "&f>&7 " + message)));
			Utils.updateRecents(Bukkit.getPlayer(args[0]), (Player) sender);
		}		
		return false;
	}

}
