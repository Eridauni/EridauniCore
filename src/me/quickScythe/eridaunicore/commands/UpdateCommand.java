package me.quickScythe.eridaunicore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.quickScythe.eridaunicore.Main;
import me.quickScythe.eridaunicore.utils.IDatabase;
import me.quickScythe.eridaunicore.utils.Utils;

public class UpdateCommand implements CommandExecutor {
	Main plugin;
	public UpdateCommand(Main plugin, String cmd){
		this.plugin = plugin;
		plugin.getCommand(cmd).setExecutor(this);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("update")){
			IDatabase sql = Utils.getConnection();
			String query = "";
			for(int i=0;i!=args.length;i++){
				query = query + " " + args[i];
			}
			if(sql.init()){
				int result = sql.update(query);
				Bukkit.broadcastMessage(result + "");
			}
		}		
		return false;
	}

}
