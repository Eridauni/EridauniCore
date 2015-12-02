package me.quickScythe.eridaunicore.utils;

import java.util.List;

import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.Metadatable;
import org.bukkit.plugin.Plugin;

public class MetadataObjectHolder {
	public void setMetadata(Metadatable object, String key, Object value, Plugin plugin) {
		  object.setMetadata(key, new FixedMetadataValue(plugin,value));
		}
		 
		public Object getMetadata(Metadatable object, String key, Plugin plugin) {
		  List<MetadataValue> values = object.getMetadata(key);  
		  for (MetadataValue value : values) {
		     if (value.getOwningPlugin() == plugin) {
		        return value.value();
		     }
		  }
		  return null;
		}

}
