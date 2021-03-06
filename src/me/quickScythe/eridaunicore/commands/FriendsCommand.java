package me.quickScythe.eridaunicore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.quickScythe.eridaunicore.Main;
import me.quickScythe.eridaunicore.utils.CoreUtils;

public class FriendsCommand implements CommandExecutor {
	Main plugin;
	public FriendsCommand(Main plugin, String cmd) {
		this.plugin = plugin;
		plugin.getCommand(cmd).setExecutor(this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("friends")) {
			if(sender instanceof Player){
				new Thread(new Runnable(){
					public void run(){
						CoreUtils.openFriendMenu(((Player) sender));
					}
				}).start();
			}
		}
		return true;
	}
	
	

}
