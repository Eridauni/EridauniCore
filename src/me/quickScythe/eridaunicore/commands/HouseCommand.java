package me.quickScythe.eridaunicore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.quickScythe.eridaunicore.Main;
import me.quickScythe.eridaunicore.utils.CoreUtils;

public class HouseCommand implements CommandExecutor {
	Main plugin;
	public HouseCommand(Main plugin, String cmd) {
		this.plugin = plugin;
		plugin.getCommand(cmd).setExecutor(this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("house")) {
			if(sender instanceof Player){
				CoreUtils.openHouseMenu(((Player)sender));
			}
		}
		return true;
	}

}
