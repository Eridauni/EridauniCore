package me.quickScythe.eridaunicore.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import me.quickScythe.eridaunicore.utils.Utils;

import me.quickScythe.eridaunicore.Main;

public class GrenadeCommand implements CommandExecutor {
	Main plugin;
	public GrenadeCommand(Main plugin, String cmd) {
		this.plugin = plugin;
		plugin.getCommand(cmd).setExecutor(this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("grenade")) {
			if(sender instanceof Player && sender.hasPermission("eridauni.grenade")){
				ItemStack g;
				if(args.length > 0)
					g = new ItemStack(Material.FIREWORK_CHARGE, Integer.parseInt(args[0]), (short)0);
				else g = new ItemStack(Material.FIREWORK_CHARGE, 1, (short)0);
				
				ItemMeta meta = g.getItemMeta();
				meta.setDisplayName(Utils.colorize("&cGrenade"));
				g.setItemMeta(meta);
				((Player) sender).getInventory().addItem(g);
			}
		}
		return true;
	}

}
