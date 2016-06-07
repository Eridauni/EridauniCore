package me.quickScythe.eridaunicore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

import me.quickScythe.eridaunicore.Main;
import me.quickScythe.eridaunicore.utils.CoreUtils;

public class RenameCommand implements CommandExecutor {
	Main plugin;
	public RenameCommand(Main plugin, String cmd){
		this.plugin = plugin;
		plugin.getCommand(cmd).setExecutor(this);
	}
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("rename") && sender instanceof Player){
			if(args.length == 0){
				CoreUtils.openAnvil(((Player) sender), null);
			}
			else {
				Player player = ((Player) sender);
				if(player.getItemInHand() != null){
					String n = "";
					for(String s : args)
						n = n+s+" ";
					
					n=n.substring(0, n.length()-1);
					ItemMeta m = player.getItemInHand().getItemMeta();
					m.setDisplayName(CoreUtils.colorize(n));
					player.getItemInHand().setItemMeta(m);
				}
			}
		
		}
		return false;
	}

}
