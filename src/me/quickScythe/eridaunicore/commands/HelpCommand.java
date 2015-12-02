package me.quickScythe.eridaunicore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.quickScythe.eridaunicore.Main;
import me.quickScythe.eridaunicore.utils.Utils;

public class HelpCommand implements CommandExecutor {
	Main plugin;
	public HelpCommand(Main plugin, String cmd) {
		this.plugin = plugin;
		plugin.getCommand(cmd).setExecutor(this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("help")) {
			sender.sendMessage(Utils.colorize("&e&lHelp &f>&7 If you need help please ask an Mod or higher, or submit a request with &f\"request\"&7."));
		}
		return true;
	}

}