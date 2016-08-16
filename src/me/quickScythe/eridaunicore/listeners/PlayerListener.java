package me.quickScythe.eridaunicore.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.quickScythe.eridaunicore.Main;
import me.quickScythe.eridaunicore.utils.CoreUtils;
import me.quickScythe.eridaunicore.utils.InventoryCreator;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PlayerListener implements Listener {

	Main plugin;
	Inventory hubinv;
	ItemStack elytra;

	public PlayerListener(Main plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		InventoryCreator i = new InventoryCreator("&6Holder", null, 36);
		i.addItem(new ItemStack(Material.NETHER_STAR), "&6Elytra Boost", 'A', null, (short) 0);
		i.addItem(new ItemStack(Material.AIR), "", 'X', null, (short) 0);

		i.setConfiguration(new char[] { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X',
				'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'A', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' });
		hubinv = i.getInventory();

		elytra = new ItemStack(Material.ELYTRA);
		elytra.addUnsafeEnchantment(Enchantment.DURABILITY, 10000);

	}

	@EventHandler
	public void onPlayerDamage(PlayerMoveEvent e) {
		if(e.getPlayer().getWorld().equals(CoreUtils.getSpawn().getWorld()) && e.getPlayer().getLocation().getY() < 0){
				
				e.getPlayer().setFallDistance(0);
				e.getPlayer().teleport(CoreUtils.getSpawn());
				
				Bukkit.getScheduler().runTaskLater(Main.getPlugin(), new Runnable() {
					
					@Override
					public void run() {
						CoreUtils.openSkyRingMenu(e.getPlayer());
						
					}
				}, 10);
			}
		
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerJoin(PlayerJoinEvent e) {
		PermissionUser player = PermissionsEx.getUser(e.getPlayer());
		e.setJoinMessage(CoreUtils.colorize(CoreUtils.getPlayerDisplayName(player) + "&7 has joined."));
		if (!e.getPlayer().hasPlayedBefore()) {
			e.getPlayer().teleport(CoreUtils.getSpawn());
			e.getPlayer().getInventory().clear();
			for (int i = 0; i != hubinv.getSize(); i++)
				e.getPlayer().getInventory().setItem(i, hubinv.getItem(i));
			e.getPlayer().getInventory().setChestplate(elytra);
		}
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		PermissionUser player = PermissionsEx.getUser(e.getPlayer());
		e.setQuitMessage(CoreUtils.colorize(CoreUtils.getPlayerDisplayName(player) + "&7 has left."));
	}

}
