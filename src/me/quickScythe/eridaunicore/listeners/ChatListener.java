package me.quickScythe.eridaunicore.listeners;

import java.sql.ResultSet;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.quickScythe.eridaunicore.Main;
import me.quickScythe.eridaunicore.utils.IDatabase;
import me.quickScythe.eridaunicore.utils.CoreUtils;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class ChatListener implements Listener {
	Main plugin;
	public ChatListener(Main plugin){
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e){
		PermissionUser player = PermissionsEx.getUser(e.getPlayer());
		String message = e.getMessage();
		if(player.has("chat.color")){
			message = CoreUtils.colorize(message);
		}
		e.setFormat(CoreUtils.colorize(player.getPrefix() + player.getName() + " &f> &8" + player.getSuffix()) + message);
		
			String query = e.getMessage();
			
			if(query.equalsIgnoreCase("friends")){
				
				for(String f : CoreUtils.getFriends(e.getPlayer()).split(","))
					Bukkit.broadcastMessage(f);
				
				
				
			}
			
			if(query.startsWith("query:")){
				query = query.replaceFirst("query:", "");
				IDatabase sql = CoreUtils.getConnection();
				if(sql.init()){
					try{
						ResultSet set = sql.query(query);
						while(set.next()){
							for(int i=1;i!=set.getMetaData().getColumnCount()+1;i++)
							Bukkit.broadcastMessage("ROW " + set.getRow() + " COLUMN " + i + "= " + set.getObject(i));
						}
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}
			}
			if(query.startsWith("update:")){
				query = query.replaceFirst("update:", "");
				IDatabase sql = CoreUtils.getConnection();
				if(sql.init()){
					int result = sql.update(query);
					Bukkit.broadcastMessage(result + "");
				}
			}
			
			
			
			
			
			
		
		
		
		
	}
	
	
}
