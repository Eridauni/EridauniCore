package me.skyGeneral.modeHub.signs;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import me.skyGeneral.modeHub.Main;
import me.skyGeneral.modeHub.utils.Utils;

public class SignListener implements Listener {
	Main plugin;
	public SignListener(Main plugin){
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void onSignChange(SignChangeEvent e){
		//SignUtils.colorize((Sign) e.getBlock().getState()); 
		e.setLine(0, Utils.colorize(e.getLine(0)));
		e.setLine(1, Utils.colorize(e.getLine(1)));
		e.setLine(2, Utils.colorize(e.getLine(2)));
		e.setLine(3, Utils.colorize(e.getLine(3)));
	}

}
