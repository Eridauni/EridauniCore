package me.quickScythe.eridaunicore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.quickScythe.eridaunicore.Main;

public class HelpCommand implements CommandExecutor {
	Main plugin;
	public HelpCommand(Main plugin, String cmd) {
		this.plugin = plugin;
		plugin.getCommand(cmd).setExecutor(this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("help")) {
			// sender.sendMessage("");
		}
		return true;
	}

}
