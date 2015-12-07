package me.quickScythe.eridaunicore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.quickScythe.eridaunicore.Main;
import me.quickScythe.eridaunicore.utils.Utils;

public class ReplyCommand implements CommandExecutor {
	Main plugin;
	public ReplyCommand(Main plugin, String cmd){
		this.plugin = plugin;
		plugin.getCommand(cmd).setExecutor(this);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("reply") && sender instanceof Player){
			if(args.length == 0){
				sender.sendMessage(Utils.colorize("&e&lReply &f>&7 Try &f\"/reply <message>\"&7."));
				return false;
			}
			
			try{
				Player player = Utils.getRecent((Player) sender);
				String message = "";
				for(String m : args){
					message = message + " " + m;
				}
				message = message.replaceFirst(" ", "");
				Utils.updateRecents(player, (Player) sender); 
				player.sendMessage(Utils.colorize("&e&lWhisper from " + sender.getName() + " &f>&7 " + message));
				sender.sendMessage(Utils.colorize("&e&lWhisper to " + player.getName() + " &f>&7 " + message));
			} catch(NullPointerException ex){
				sender.sendMessage(Utils.colorize("&e&lReply &f>&7 You haven't talked to anyone recently."));
			}
		}
		return false;
	}

}
