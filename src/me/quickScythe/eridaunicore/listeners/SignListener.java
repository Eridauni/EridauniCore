package me.quickScythe.eridaunicore.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import me.quickScythe.eridaunicore.Main;
import me.quickScythe.eridaunicore.utils.CoreUtils;

public class SignListener implements Listener {
	Main plugin;
	public SignListener(Main plugin){
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void onSignChange(SignChangeEvent e){
		//SignCoreUtils.colorize((Sign) e.getBlock().getState()); 
		e.setLine(0, CoreUtils.colorize(e.getLine(0)));
		e.setLine(1, CoreUtils.colorize(e.getLine(1)));
		e.setLine(2, CoreUtils.colorize(e.getLine(2)));
		e.setLine(3, CoreUtils.colorize(e.getLine(3)));
	}
	
	
}
