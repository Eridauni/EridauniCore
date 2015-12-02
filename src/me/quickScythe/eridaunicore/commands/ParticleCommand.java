package me.quickScythe.eridaunicore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.quickScythe.eridaunicore.Main;
import me.quickScythe.eridaunicore.utils.Utils;
import me.quickScythe.eridaunicore.utils.packets.ParticleEffect;

public class ParticleCommand implements CommandExecutor {
	Main plugin;
	public ParticleCommand(Main plugin, String cmd){
		this.plugin = plugin;
		plugin.getCommand(cmd).setExecutor(this);
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("particle") && sender.hasPermission("vip.particle") && sender instanceof Player){
			if(args.length == 1){
				if(!((Player) sender).hasPermission("vip.particle."+ args[0].toLowerCase())){
					sender.sendMessage(Utils.colorize("&e&lParticle &f>&7 You don't have permissions to use that particle."));
				}
				if(args[0].equalsIgnoreCase("null")){
					Utils.setParticleEffect((Player) sender, null);
					return true;
				} else
					try{
						Utils.setParticleEffect((Player) sender, ParticleEffect.valueOf(args[0]));	
					} catch(Exception ex){
						sender.sendMessage(Utils.colorize("&e&lParticle &f>&7 There was an error setting your particle effect. Maybe it isn't valid?"));
					}
			}
//			if(args.length == 2 && ((Player) sender.hasPermission())){
//				if(!((Player) sender).hasPermission("vip.particle."+ args[0].toLowerCase())){
//					sender.sendMessage(Utils.colorize("&3Particle &8>&f You don't have permissions to use that particle."));
//				}
//				if(args[0].equalsIgnoreCase("null")){
//					Utils.setParticleEffect((Player) sender, null);
//					return true;
//				} else
//					try{
//						Utils.setParticleEffect((Player) sender, ParticleUtils.valueOf(args[0]));	
//					} catch(Exception ex){
//						sender.sendMessage(Utils.colorize("&3Particle &8>&f There was an error setting your particle effect. Maybe it isn't valid?"));
//					}
//			}
			if(args.length == 0){
				((Player) sender).openInventory(Utils.getParticleInventory((Player) sender));
			}
		}
		if(!sender.hasPermission("vip.particle")) sender.sendMessage(Utils.colorize("&e&lParticle &f>&7 Sorry, but you don't have the permission to do that."));
		return true;	
	}
}
