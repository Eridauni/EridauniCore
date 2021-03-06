package me.quickScythe.eridaunicore.utils;

import java.awt.Color;
import java.io.File;
import java.net.InetAddress;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.FireworkEffect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitTask;

import me.quickScythe.eridaunicore.Main;
import me.quickScythe.eridaunicore.core.Achievement;
import me.quickScythe.eridaunicore.core.FakeAnvil;
import me.quickScythe.eridaunicore.core.Gamer;
import me.quickScythe.eridaunicore.core.MainTimer;
import me.quickScythe.eridaunicore.utils.packets.ParticleEffect;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_10_R1.ContainerAnvil;
import net.minecraft.server.v1_10_R1.EntityPlayer;
import net.minecraft.server.v1_10_R1.PacketPlayOutOpenWindow;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class CoreUtils {

	private static Map<UUID, Integer> particleTimer = new HashMap<>();
	private static Map<UUID, Double[]> helixMathMap = new HashMap<>();
	private static Map<UUID, ParticleEffect> particles = new HashMap<>();
	private static Map<UUID, Color> RGBcolors = new HashMap<>();
	private static Map<UUID, Color> colors = new HashMap<>();
	private static Map<UUID, Gamer> gamers = new HashMap<>();
	private static Map<UUID, UUID> recents = new HashMap<>();
	private static FileConfiguration playerFile = YamlConfiguration
			.loadConfiguration(new File(Main.getPlugin().getDataFolder(), "players.yml"));

	private static Location spawn;
	private static Location skyringsspawn;
	private static Map<UUID, ParticleFormat> particleformats = new HashMap<>();
	private static Set<UUID> wings = new HashSet<>();
	private static Map<String, Achievement> achievements = new HashMap<>();
	private static Map<UUID, String> uuids = new HashMap<>();
	private static Map<String, String> ipPlayers = new HashMap<>();
	private static Map<UUID, ArrayList<Achievement>> playerAchievements = new HashMap<>();
	private static BukkitTask task;
	public static int note = 0;
	private static IDatabase connection;
	private static char[] alphebet = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
			'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
			'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	public static void start() {
		startMainTimer();

		connection = new IDatabase("db4free.net", "eridaunistats", 3306, "eridauniadmin", "password");
		if (connection.init())
			Main.getPlugin().getServer().getConsoleSender().sendMessage(colorize(("&e&lSQL &f>&7 SQL connected...")));
		else
			Main.getPlugin().getServer().getConsoleSender()
					.sendMessage(colorize(("&e&lSQL &f>&7 SQL not connected...")));

		try {
			String encodedSpawn = Main.getPlugin().getConfig().getString("Spawn");
			String[] ds = encodedSpawn.split(":");
			spawn = new Location(Bukkit.getWorld(ds[0]), Float.parseFloat(ds[1]), Float.parseFloat(ds[2]),
					Float.parseFloat(ds[3]), Float.parseFloat(ds[4]), Float.parseFloat(ds[5]));
		} catch (NullPointerException ex) {
			spawn = Bukkit.getWorlds().get(0).getSpawnLocation();
		}
		
		try {
			String encodedSpawn = Main.getPlugin().getConfig().getString("SkyRingSpawn");
			String[] ds = encodedSpawn.split(":");
			skyringsspawn = new Location(Bukkit.getWorld(ds[0]), Float.parseFloat(ds[1]), Float.parseFloat(ds[2]),
					Float.parseFloat(ds[3]), Float.parseFloat(ds[4]), Float.parseFloat(ds[5]));
		} catch (NullPointerException ex) {
			skyringsspawn = Bukkit.getWorlds().get(0).getSpawnLocation();
		}

		updateAchievements();
		for (Player player : Bukkit.getOnlinePlayers()) {
			loadAchievements(player);
		}
		createGamers();

		Main.getPlugin().getServer().getConsoleSender().sendMessage(colorize(("&e&lServer &f>&7 Hub enabled!")));

	}

	private static void createGamers() {
		for (Player player : Bukkit.getOnlinePlayers())
			createNewGamer(player);
	}

	public static void end() {
		unloadAchievements();

		String sspawn = spawn.getWorld().getName() + ":" + spawn.getX() + ":" + spawn.getY() + ":" + spawn.getZ() + ":"
				+ spawn.getYaw() + ":" + spawn.getPitch();
		Main.getPlugin().getConfig().set("Spawn", sspawn);
		
		String ssspawn = skyringsspawn.getWorld().getName() + ":" + skyringsspawn.getX() + ":" + skyringsspawn.getY() + ":" + skyringsspawn.getZ() + ":"
				+ skyringsspawn.getYaw() + ":" + skyringsspawn.getPitch();
		Main.getPlugin().getConfig().set("SkyRingsSpawn", ssspawn);
		
		Main.getPlugin().saveConfig();

		for (Entry<UUID, String> entry : uuids.entrySet()) {
			playerFile.set(entry.getKey() + "", entry.getValue());
		}
		for (Entry<String, String> entry : ipPlayers.entrySet()) {
			playerFile.set(entry.getKey() + "", entry.getValue());
		}
	}

	public static String colorize(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}

	public static int getParticleTimer(Player player) {
		return particleTimer.get(player.getUniqueId());
	}

	public static void setParticleTimer(Player player, int i) {
		particleTimer.put(player.getUniqueId(), i);
	}

	public static Double[] helixMath(Player player) {
		return helixMathMap.get(player.getUniqueId());
	}

	public static void setHelixMath(Player player, double y, double up) {
		Double[] coords = new Double[] { y, up };
		helixMathMap.put(player.getUniqueId(), coords);
	}

	public static Location getCircleLocation(int i, Double width, Location location) {
		double x = (double) (width * Math.cos(((i + 1) * 11.25) * Math.PI / 180F)) + location.getX();
		double z = (double) (width * Math.sin(((i + 1) * 11.25) * Math.PI / 180F)) + location.getZ();
		return new Location(location.getWorld(), x, location.getY(), z);
	}

	public static Location getVerticalCircleLocation(int i, Double width, Location location) {
		double x = (double) (width * Math.cos(((i + 1) * 11.25) * Math.PI / 180F)) + location.getX();
		double y = (double) (width * Math.sin(((i + 1) * 11.25) * Math.PI / 180F)) + location.getY();
		return new Location(location.getWorld(), x, y + 1, location.getZ());
	}

	public static Location getCircleLocation2(int i, Double width, Location location) {

		double x = (double) (width * Math.cos(((i + 16 - 31) * 11.25) * Math.PI / 180F)) + location.getX();
		double z = (double) (width * Math.sin(((i + 16 - 31) * 11.25) * Math.PI / 180F)) + location.getZ();
		return new Location(location.getWorld(), x, location.getY(), z);

	}

	public static Location getCircleLocationBackwards(int i, Double width, Location location) {

		double x = (double) (width * Math.cos((-1 * (i + 16 - 31) * 11.25) * Math.PI / 180F)) + location.getX();
		double z = (double) (width * Math.sin((-1 * (i + 16 - 31) * 11.25) * Math.PI / 180F)) + location.getZ();
		return new Location(location.getWorld(), x, location.getY(), z);

	}

	public static void displayParticle(Player player, Location l, List<Player> players) {
//
		Color color = null;
//
		if (RGBcolors.containsKey(player.getUniqueId())) {
			color = RGBcolors.get(player.getUniqueId());
		}
		if (colors.containsKey(player.getUniqueId())) {
			color = colors.get(player.getUniqueId());
		}
		int red;
		int green;
		int blue;

		try{
			red = color.getRed();
			green = color.getGreen();
			blue = color.getBlue();

			if (red == 0)
				red = 1;
			if (green == 0)
				green = 1;
			if (blue == 0)
				blue = 1;
		} catch(NullPointerException ex){
			red = 1;
			green = 1;
			blue = 1;
		}
		org.bukkit.Color bcolor = org.bukkit.Color.fromRGB(red, green, blue);
//		getParticle(player.getUniqueId()).display(0, 0, 0, 1, 1, l, 1);
//		getParticle(player.getUniqueId()).display(0,0,0,1,1, l, 30.0D);
//		getParticle(player.getUniqueId()).dis
		getParticle(player.getUniqueId()).display(null, l, bcolor, 30D, 0F, 0F, 0F, 0F, 1);
	}

//	private static int getNoteColor(Color color) {
//		String ccolor = color.toString();
//		switch (ccolor) {
//		case "red":
//			return 6;
//		case "orange":
//			return 8;
//		case "yellow":
//			return 7;
//		case "green":
//			return 22;
//		case "blue":
//			return 0;
//		case "magenta":
//			return 5;
//		default:
//			return 0;
//
//		}
//	}

	public static ParticleEffect getParticle(UUID uuid) {
		return particles.get(uuid);
	}

	public static void setParticleColor(Player player, Color color) {
		RGBcolors.remove(player.getUniqueId());
		colors.put(player.getUniqueId(), color);
	}

	public static void setParticleRGBColor(Player player, String colorRGB) {
		colors.remove(player.getUniqueId());
		String[] values = colorRGB.split(" ");
		if (Integer.parseInt(values[0]) > 255) {
			player.sendMessage(colorize("&e&lParticles &7> &fYour RED value is above 255."));
			return;
		}
		if (Integer.parseInt(values[1]) > 255) {
			player.sendMessage(colorize("&e&lParticles &7> &fYour GREEN value is above 255."));
			return;
		}
		if (Integer.parseInt(values[2]) > 255) {
			player.sendMessage(colorize("&e&lParticles &7> &fYour BLUE value is above 255."));
			return;
		}
		if (Integer.parseInt(values[0]) < 0) {
			player.sendMessage(colorize("&e&lParticles &7> &fYour RED value is below 0."));
			return;
		}
		if (Integer.parseInt(values[1]) < 0) {
			player.sendMessage(colorize("&e&lParticles &7> &fYour GREEN value is below 0."));
			return;
		}
		if (Integer.parseInt(values[2]) < 0) {
			player.sendMessage(colorize("&e&lParticles &7> &fYour BLUE value is below 0."));
			return;
		}

		RGBcolors.put(player.getUniqueId(),
				new Color(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2])));

	}

	public static Gamer createNewGamer(Player player) {
		if (!gamers.containsKey(player.getUniqueId())) {
			gamers.put(player.getUniqueId(), new Gamer(player.getUniqueId()));
			return gamers.get(player.getUniqueId());
		}

		return gamers.get(player);
	}

	public static Gamer getGamer(Player player) {
		if (gamers.get(player.getUniqueId()) == null) {
			return createNewGamer(player);
		}
		return gamers.get(player.getUniqueId());
	}

	public static void updateAchievements() {
		achievements.put("Treehouse",
				new Achievement("Treehouse", "Find the hidden treehouse!", Material.SAPLING, (short) 0));
		achievements.put("Clubhouse",
				new Achievement("Clubhouse", "Find the hidden clubhouse!", Material.WOOD, (short) 0));
		achievements.put("Prison", new Achievement("Prison", "Find the prison!", Material.IRON_FENCE, (short) 0));
		achievements.put("Button",
				new Achievement("Button", "Find the secret button!", Material.STONE_BUTTON, (short) 0));

	}

	public static Achievement getAchievement(String string) {
		return achievements.get(string);
	}

	public static void addAchievement(Player player, Achievement achievement) {
		ArrayList<Achievement> ach = playerAchievements.get(player.getUniqueId());
		try {
			ach.add(achievement);
		} catch (NullPointerException ex) {
			ArrayList<Achievement> achs = new ArrayList<>();
			playerAchievements.put(player.getUniqueId(), achs);
		}
		playerAchievements.put(player.getUniqueId(), ach);
	}

	public static void loadAchievements(Player player) {
		ArrayList<Achievement> achs = new ArrayList<Achievement>();
		for (String ach : Main.getPlugin().getConfig().getStringList(player.getUniqueId() + ".Achievements")) {
			achs.add(getAchievement(ach));
			getGamer(player).addAchievement(getAchievement(ach));
		}
		playerAchievements.put(player.getUniqueId(), achs);
	}

	public static void storeAchievements(Player player) {
		playerAchievements.put(player.getUniqueId(), getGamer(player).getAchievements());
	}

	public static void unloadAchievements() {
		for (Player player : Bukkit.getOnlinePlayers())
			storeAchievements(player);
		HashMap<UUID, ArrayList<String>> temp__ = new HashMap<>();

		for (Entry<UUID, ArrayList<Achievement>> entry : playerAchievements.entrySet()) {
			temp__.put(entry.getKey(), convertAchievementToString(entry.getValue()));
		}

		for (Entry<UUID, ArrayList<String>> entry : temp__.entrySet())
			Main.getPlugin().getConfig().set(entry.getKey() + ".Achievements", entry.getValue());
		Main.getPlugin().saveConfig();
	}

	public static ArrayList<String> convertAchievementToString(ArrayList<Achievement> list) {
		if (list == null)
			return new ArrayList<String>();
		ArrayList<String> listS = new ArrayList<>();
		for (Achievement ach : list) {
			try {
				listS.add(ach.getName());
			} catch (NullPointerException ex) {
				listS.add("");
			}
		}
		return listS;
	}

	public static void setSpawn(Location location) {
		spawn = location;
	}
	
	public static void setSkyRingsSpawn(Location location) {
		skyringsspawn = location;
		
		String ssspawn = skyringsspawn.getWorld().getName() + ":" + skyringsspawn.getX() + ":" + skyringsspawn.getY() + ":" + skyringsspawn.getZ() + ":"
				+ skyringsspawn.getYaw() + ":" + skyringsspawn.getPitch();
		Main.getPlugin().getConfig().set("SkyRingsSpawn", ssspawn);
		
		Main.getPlugin().saveConfig();
	}

	public static void toggleWings(Player player) {
		player.closeInventory();
		if (!player.hasPermission("core.wings")) {
			player.sendMessage(colorize("&e&lWings &f>&7 You don't have permission to use that."));
			return;
		}
		if (wings.contains(player.getUniqueId())) {
			wings.remove(player.getUniqueId());
			if (player.getGameMode().equals(GameMode.SURVIVAL)) {
				player.setAllowFlight(false);
			}
			player.setFlying(false);
			player.sendMessage(colorize("&e&lGadgets &f>&7 You have lost your wings."));
		} else {
			wings.add(player.getUniqueId());
			player.playSound(player.getLocation(), Sound.ENTITY_BAT_TAKEOFF, 10, 10);
			player.setAllowFlight(true);
			player.setFlying(true);

			player.teleport(new Location(player.getWorld(), player.getLocation().getX(),
					player.getLocation().getY() + 0.1, player.getLocation().getZ(), player.getLocation().getYaw(),
					player.getLocation().getPitch()));
			player.sendMessage(colorize("&e&lGadgets &f>&7 You have been granted the ability to &f&lfly&7!"));
		}
	}

	public static Set<UUID> getWingedPlayers() {
		return wings;
	}

	public static void displayWings(Player player) {
		Location loc = player.getLocation().clone();
		loc.setPitch(0.0F);
		loc.add(0.0D, 1.8D, 0.0D);
		loc.add(loc.getDirection().multiply(-0.2D));

		Location loc1R = loc.clone();
		loc1R.setYaw(loc1R.getYaw() + 110.0F);
		Location loc2R = loc1R.clone().add(loc1R.getDirection().multiply(1));
		
		
		ParticleEffect.REDSTONE.display(0,0,0,1,1, loc2R.add(0.0D, 0.8D, 0.0D), 30.0D);

		Location loc3R = loc1R.clone().add(loc1R.getDirection().multiply(0.8D));
		ParticleEffect.REDSTONE.display(0,0,0,1,1, loc3R.add(0.0D, 0.6D, 0.0D), 30.0D);
		Location loc4R = loc1R.clone().add(loc1R.getDirection().multiply(0.6D));
		ParticleEffect.REDSTONE.display(0,0,0,1,1, loc4R.add(0.0D, 0.4D, 0.0D), 30.0D);
		Location loc5R = loc1R.clone().add(loc1R.getDirection().multiply(0.4D));
		ParticleEffect.REDSTONE.display(0,0,0,1,1, loc5R.clone().add(0.0D, -0.2D, 0.0D), 30.0D);
		Location loc6R = loc1R.clone().add(loc1R.getDirection().multiply(0.2D));
		ParticleEffect.REDSTONE.display(0,0,0,1,1, loc6R.add(0.0D, -0.2D, 0.0D), 30.0D);

		int zu = 0;
		while (zu <= 3) {
			zu++;

			ParticleEffect.REDSTONE.display(0,0,0,1,1, loc2R.add(0.0D, -0.2D, 0.0D), 30.0D);
			ParticleEffect.REDSTONE.display(0,0,0,1,1, loc3R.add(0.0D, -0.2D, 0.0D), 30.0D);
			ParticleEffect.REDSTONE.display(0,0,0,1,1, loc4R.add(0.0D, -0.2D, 0.0D), 30.0D);
			ParticleEffect.REDSTONE.display(0,0,0,1,1, loc5R.add(0.0D, -0.2D, 0.0D), 30.0D);
			ParticleEffect.REDSTONE.display(0,0,0,1,1, loc6R.add(0.0D, -0.2D, 0.0D), 30.0D);
		}

		Location loc1L = loc.clone();
		loc1L.setYaw(loc1L.getYaw() - 110.0F);
		Location loc2L = loc1L.clone().add(loc1L.getDirection().multiply(1));
		ParticleEffect.REDSTONE.display(0,0,0,1,1, loc2L.add(0.0D, 0.8D, 0.0D), 30.0D);

		Location loc3L = loc1L.clone().add(loc1L.getDirection().multiply(0.8D));
		ParticleEffect.REDSTONE.display(0,0,0,1,1, loc3L.add(0.0D, 0.6D, 0.0D), 30.0D);
		Location loc4L = loc1L.clone().add(loc1L.getDirection().multiply(0.6D));
		ParticleEffect.REDSTONE.display(0,0,0,1,1, loc4L.add(0.0D, 0.4D, 0.0D), 30.0D);
		Location loc5L = loc1L.clone().add(loc1L.getDirection().multiply(0.4D));
		ParticleEffect.REDSTONE.display(0,0,0,1,1, loc5L.clone().add(0.0D, -0.2D, 0.0D), 30.0D);
		Location loc6L = loc1L.clone().add(loc1L.getDirection().multiply(0.2D));
		ParticleEffect.REDSTONE.display(0,0,0,1,1, loc6L.add(0.0D, -0.2D, 0.0D), 30.0D);

		zu = 0;

		while (zu <= 3) {
			zu++;

			ParticleEffect.REDSTONE.display(0,0,0,1,1, loc2L.add(0.0D, -0.2D, 0.0D), 30.0D);
			ParticleEffect.REDSTONE.display(0,0,0,1,1, loc3L.add(0.0D, -0.2D, 0.0D), 30.0D);
			ParticleEffect.REDSTONE.display(0,0,0,1,1, loc4L.add(0.0D, -0.2D, 0.0D), 30.0D);
			ParticleEffect.REDSTONE.display(0,0,0,1,1, loc5L.add(0.0D, -0.2D, 0.0D), 30.0D);
		}
	}

	public static Location getSpawn() {
		return spawn;
	}
	
	public static Location getSkyRingsSpawn() {
		return skyringsspawn;
	}

	public static Inventory getParticleInventory(Player player) {
		InventoryCreator inv = new InventoryCreator("&6Particles &7(Page 1)", player, 45);
		inv.addItem(new ItemStack(Material.STAINED_GLASS_PANE), "&7Click an effect.", 'X', null, (short) 0);
		
		if(player.hasPermission("vip.particle.spellwitch"))inv.addItem(new ItemStack(Material.CAULDRON_ITEM), "&5&lWitch Magic", 'A', null, (short) 0);
		else inv.addItem(new ItemStack(Material.STAINED_GLASS_PANE), "&e&lWitch Magic", 'A', new String[] {"&c&lVIP Exclusive"}, (short) 15);
		if(player.hasPermission("vip.particle.flame"))inv.addItem(new ItemStack(Material.FIREBALL), "&6&lFlame", 'B', null, (short) 0);
		else inv.addItem(new ItemStack(Material.STAINED_GLASS_PANE), "&e&lFlame", 'B', new String[] {"&c&lVIP Exclusive"}, (short) 15);
		if(player.hasPermission("vip.particle.fireworksspark"))inv.addItem(new ItemStack(Material.NETHER_STAR), "&f&lSparks", 'C', null, (short) 0);
		else inv.addItem(new ItemStack(Material.STAINED_GLASS_PANE), "&e&lSparks", 'C', new String[] {"&c&lVIP Exclusive"}, (short) 15);
		if(player.hasPermission("vip.particle.cloud"))inv.addItem(new ItemStack(Material.SNOW_BLOCK), "&f&lCloud", 'D', null, (short) 0);
		else inv.addItem(new ItemStack(Material.STAINED_GLASS_PANE), "&e&lCloud", 'D', new String[] {"&c&lVIP Exclusive"}, (short) 15);
		if(player.hasPermission("vip.particle.redstone"))inv.addItem(new ItemStack(Material.REDSTONE), "&c&lRedstone Dust", 'E', null, (short) 0);
		else inv.addItem(new ItemStack(Material.STAINED_GLASS_PANE), "&e&lRedstone Dust", 'E', new String[] {"&c&lVIP Exclusive"}, (short) 15);
		if(player.hasPermission("vip.particle.note"))inv.addItem(new ItemStack(Material.RECORD_9), "&3&lNotes", 'F', null, (short) 0);
		else inv.addItem(new ItemStack(Material.STAINED_GLASS_PANE), "&e&lNotes", 'F', new String[] {"&c&lVIP Exclusive"}, (short) 15);
		if(player.hasPermission("vip.particle.dripwater"))inv.addItem(new ItemStack(Material.WATER_BUCKET), "&1&lWater Drip", 'G', null, (short) 0);
		else inv.addItem(new ItemStack(Material.STAINED_GLASS_PANE), "&e&lWater Drip", 'G', new String[] {"&c&lVIP Exclusive"}, (short) 15);
		if(player.hasPermission("vip.particle.villagerhappy"))inv.addItem(new ItemStack(Material.EMERALD), "&a&lGreen Tinkle", 'H', null, (short) 0);
		else inv.addItem(new ItemStack(Material.STAINED_GLASS_PANE), "&e&lGreen Tinkle", 'H', new String[] {"&c&lVIP Exclusive"}, (short) 15);
		if(player.hasPermission("vip.particle.enchantmenttable"))inv.addItem(new ItemStack(Material.PAPER), "&7&lAncient Ruins", 'I', null, (short) 0);
		else inv.addItem(new ItemStack(Material.STAINED_GLASS_PANE), "&e&lAncient Ruins", 'I', new String[] {"&c&lVIP Exclusive"}, (short) 15);
		if(player.hasPermission("vip.particle.heart"))inv.addItem(new ItemStack(Material.NAME_TAG), "&c&lLove! <3", 'J', null, (short) 0);
		else inv.addItem(new ItemStack(Material.STAINED_GLASS_PANE), "&e&lLove! <3", 'J', new String[] {"&c&lVIP Exclusive"}, (short) 15);
		if(player.hasPermission("vip.particle.villagerangry"))inv.addItem(new ItemStack(Material.COAL), "&8&lStorm Cloud", 'K', null, (short) 0);
		else inv.addItem(new ItemStack(Material.STAINED_GLASS_PANE), "&e&lStorm Cloud", 'K', new String[] {"&c&lVIP Exclusive"}, (short) 15);
		if(player.hasPermission("vip.particle.driplava"))inv.addItem(new ItemStack(Material.LAVA_BUCKET), "&6&lLava Drip", 'L', null, (short) 0);
		else inv.addItem(new ItemStack(Material.STAINED_GLASS_PANE), "&e&lLava Drip", 'L', new String[] {"&c&lVIP Exclusive"}, (short) 15);
		inv.addItem(new ItemStack(Material.BARRIER), "&4Remove Trail", 'M', null, (short) 0);
		
		
		
		if(player.hasPermission("vip.particle.explosionnormal"))inv.addItem(new ItemStack(Material.TNT), "&c&lExplosion", 'O', null, (short) 0);
		else inv.addItem(new ItemStack(Material.STAINED_GLASS_PANE), "&c&lExplosion", 'O', new String[] {"&c&lVIP Exclusive"}, (short) 15);
		if(player.hasPermission("vip.particle.particlename"))inv.addItem(new ItemStack(Material.GOLD_SWORD), "&e&lCrit", 'P', null, (short) 0);
		else inv.addItem(new ItemStack(Material.STAINED_GLASS_PANE), "&e&lCrit", 'P', new String[] {"&c&lVIP Exclusive"}, (short) 15);
		
		inv.addItem(new ItemStack(Material.ARROW), "&7Next Page ->", 'Y', null, (short) 0);

		inv.addItem(new ItemStack(Material.REDSTONE_BLOCK), "&4Exit", 'N', null, (short) 0);
		inv.setConfiguration(new char[] { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'A', 'B', 'C', 'D', 'E',
				'F', 'G', 'X', 'X', 'O', 'H', 'I', 'J', 'K', 'L', 'P', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X',
				'X', 'X', 'X', 'M', 'X', 'N', 'X', 'Y', 'X',

		});
		return inv.getInventory();
	}

	public static Inventory getParticleInventoryPage2(Player player) {
		InventoryCreator inv = new InventoryCreator("&6Particles&7 (Page 2)", player, 45);
		inv.addItem(new ItemStack(Material.STAINED_GLASS_PANE), "&7Click an effect.", 'X', null, (short) 0);
		inv.addItem(new ItemStack(Material.STAINED_GLASS_PANE), "&cComing Soon", 'Z', null, (short) 14);
		inv.addItem(new ItemStack(Material.BARRIER), "&4Remove Trail", 'M', null, (short) 0);
		inv.addItem(new ItemStack(Material.REDSTONE_BLOCK), "&4Exit", 'N', null, (short) 0);
		inv.addItem(new ItemStack(Material.ARROW), "&7<- Go Back", 'Y', null, (short) 0);

		
		if(player.hasPermission("vip.particle.critmagic"))inv.addItem(new ItemStack(Material.DIAMOND_SWORD), "&3&lMagic Crit", '1', null, (short) 0);
		else inv.addItem(new ItemStack(Material.STAINED_GLASS_PANE), "&e&lMagic Crit", '1', new String[] {"&c&lVIP Exclusive"}, (short) 15);
		if(player.hasPermission("vip.particle.portal"))inv.addItem(new ItemStack(Material.STAINED_GLASS_PANE), "&5&lPortal", '2', null, (short) 10);
		else inv.addItem(new ItemStack(Material.STAINED_GLASS_PANE), "&e&lPortal", '2', new String[] {"&c&lVIP Exclusive"}, (short) 15);
		if(player.hasPermission("vip.particle.lava"))inv.addItem(new ItemStack(Material.FLINT_AND_STEEL), "&c&lHot Sparks", '3', null, (short) 0);
		else inv.addItem(new ItemStack(Material.STAINED_GLASS_PANE), "&e&lHot Sparks", '3', new String[] {"&c&lVIP Exclusive"}, (short) 15);
		if(player.hasPermission("vip.particle.slime"))inv.addItem(new ItemStack(Material.SLIME_BALL), "&a&lSlime Poof", '4', null, (short) 0);
		else inv.addItem(new ItemStack(Material.STAINED_GLASS_PANE), "&e&lSlime Poof", '4', new String[] {"&c&lVIP Exclusive"}, (short) 15);
		if(player.hasPermission("vip.particle.snowball"))inv.addItem(new ItemStack(Material.SNOW_BALL), "&f&lSnow Poof", '5', null, (short) 0);
		else inv.addItem(new ItemStack(Material.STAINED_GLASS_PANE), "&e&lSnow Poof", '5', new String[] {"&c&lVIP Exclusive"}, (short) 15);
		if(player.hasPermission("vip.particle.footstep"))inv.addItem(new ItemStack(Material.STONE), "&7&lFoot Print", '6', null, (short) 0);
		else inv.addItem(new ItemStack(Material.STAINED_GLASS_PANE), "&e&lFoot Print", '6', new String[] {"&c&lVIP Exclusive"}, (short) 15);
		if(player.hasPermission("vip.particle.dragonbreath"))inv.addItem(new ItemStack(Material.DRAGONS_BREATH), "&5&lDragon Fire", '7', null, (short) 0);
		else inv.addItem(new ItemStack(Material.STAINED_GLASS_PANE), "&5&lDragon Breath", '7', new String[] {"&6&lAdmin Exclusive"}, (short) 15);

		inv.setConfiguration(new char[] { 
				'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X',
				'X', '1', '2', '3', '4', '5', '6', '7', 'X',
				'X', 'Z', 'Z', 'Z', 'Z', 'Z', 'Z', 'Z', 'X',
				'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X',
				'X', 'Y', 'X', 'M', 'X', 'N', 'X', 'X', 'X',

		});
		return inv.getInventory();
	}

	public static void setParticleEffect(Player player, ParticleEffect particle) {
		if (particle == null) {
			particles.put(player.getUniqueId(), null);
			player.sendMessage(colorize("&e&lParticle &f>&7 Removed your particle effects"));
		} else {
			particles.put(player.getUniqueId(), particle);
			String particleName = particle.toString().replaceAll("_", " ");
			particleName = particleName.toLowerCase();
			player.sendMessage(colorize("&e&lParticle &f>&7 Particle effect \"" + particleName + "\" added."));
		}
	}

	public static Inventory getParticleFormatInventory(Player player) {
		InventoryCreator inv = new InventoryCreator("&3Particle Formats", player, 36);
		inv.addItem(new ItemStack(Material.STAINED_GLASS_PANE), "&7Click an effect.", 'X', null, (short) 0);
		inv.addItem(new ItemStack(Material.DIAMOND_HELMET), "&a&lCircle Head", 'A', null, (short) 0);
		inv.addItem(new ItemStack(Material.DIAMOND_BOOTS), "&c&lCircle Feet", 'C', null, (short) 0);
		inv.addItem(new ItemStack(Material.DIAMOND_CHESTPLATE), "&7&lCircle Body", 'B', null, (short) 0);
		inv.addItem(new ItemStack(Material.EMERALD), "&3&lHelix", 'D', null, (short) 0);
		inv.addItem(new ItemStack(Material.NETHER_STAR), "&4&lDouble Helix", 'E', null, (short) 0);
		inv.addItem(new ItemStack(Material.IRON_SWORD), "&6&lRandom", 'F', null, (short) 0);
		inv.addItem(new ItemStack(Material.BLAZE_POWDER), "&b&lCrossFire", 'G', null, (short) 0);
		inv.addItem(new ItemStack(Material.POTION), "&a&lSpiral", 'H', null, (short) 0);
		inv.addItem(new ItemStack(Material.REDSTONE), "&c&lBlood Helix", 'I', null, (short) 0);
		inv.addItem(new ItemStack(Material.SNOW_BALL), "&e&lShpere", 'J', null, (short) 0);
		inv.addItem(new ItemStack(Material.SHIELD), "&c&lForcefield", 'K', null, (short) 0);

		inv.addItem(getGamer(player).getSkull(), "&a&lCircle Player", 'L',
				new String[] { "&7Suggested by &oLord_Hyperion&7." }, (short) 3);

		inv.setConfiguration(new char[] { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'A', 'B', 'C', 'D', 'E',
				'F', 'G', 'X', 'X', 'X', 'I', 'H', 'L', 'J', 'K', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'

		});
		return inv.getInventory();
	}

	public static void setParticleFormat(Player player, ParticleFormat format) {
		particleformats.put(player.getUniqueId(), format);
		particleTimer.put(player.getUniqueId(), 0);
		setHelixMath(player, 0, 0);
		restartMainTimer();
	}

	public static ParticleFormat getParticleFormat(Player player) {
		return particleformats.get(player.getUniqueId());
	}

	@SuppressWarnings("deprecation")
	public static void startMainTimer() {
//		Bukkit.broadcastMessage("3");
		task = Bukkit.getScheduler().runTaskTimer(Main.getPlugin(), new MainTimer(), 1, 1);
//		Bukkit.broadcastMessage("6");
	}

	public static void stopMainTimer() {
		task.cancel();
	}

	public static void restartMainTimer() {
		stopMainTimer();
		startMainTimer();
	}

	public static Inventory getParticleColorInventory(Player player) {
		InventoryCreator inv = new InventoryCreator("&3Color Picker", player, 54);

		inv.addItem(new ItemStack(Material.STAINED_GLASS_PANE), "&7Click an option.", 'X', null, (short) 0);
		inv.addItem(new ItemStack(Material.INK_SACK), "&c&lRed", 'A', null, (short) 1);
		inv.addItem(new ItemStack(Material.INK_SACK), "&6&lOrange", 'B', null, (short) 14);
		inv.addItem(new ItemStack(Material.INK_SACK), "&e&lYellow", 'C', null, (short) 11);
		inv.addItem(new ItemStack(Material.INK_SACK), "&a&lLime", 'D', null, (short) 10);
		inv.addItem(new ItemStack(Material.INK_SACK), "&2&lGreen", 'E', null, (short) 2);
		inv.addItem(new ItemStack(Material.INK_SACK), "&b&lBlue", 'F', null, (short) 12);
		inv.addItem(new ItemStack(Material.INK_SACK), "&5&lPurple", 'G', null, (short) 5);
		inv.addItem(new ItemStack(Material.INK_SACK), "&8&lBlack", 'H', null, (short) 0);
		inv.addItem(new ItemStack(Material.NETHER_STAR), "&cR&6a&en&bd&bo&5m", 'R',
				new String[] { "&7Random colors every time the particle updates." }, (short) 0);

		inv.setConfiguration(new char[] { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'A', 'B', 'C', 'D', 'E',
				'F', 'G', 'X', 'X', 'X', 'X', 'H', 'X', 'R', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X',
				'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', });

		return inv.getInventory();

	}

	public static Inventory getAchievementInventory(Player player) {
		InventoryCreator inv = new InventoryCreator("&1&lAchievements", player, 45);

		inv.addItem(new ItemStack(Material.STAINED_GLASS_PANE), "&7Nothing.", 'X',
				new String[] { "&7Nothing here yet. Check back layer. :-)" }, (short) 0);
		int i = 1;
		ArrayList<Character> storage = new ArrayList<>();
		for (Achievement achievement : getAchievements()) {
			String[] info = achievement.getItemInfo();
			if (getGamer(player).hasAchievement(achievement))
				inv.addItem(new ItemStack(Material.getMaterial(info[0])), colorize("&a" + info[1]), getAlphebet(i),
						new String[] { colorize("&7" + info[2]) }, Short.parseShort(info[3]));
			else
				inv.addItem(new ItemStack(Material.STAINED_GLASS_PANE), colorize("&c" + info[1]), getAlphebet(i),
						new String[] { colorize("&7" + info[2]) }, (short) 14);
			storage.add(getAlphebet(i));
			i = i + 1;
		}
		if (i < 45)
			for (int ii = 0; ii != 46 - i;) {
				storage.add('X');
				ii = ii + 1;
			}
		inv.setConfiguration(storage);

		return inv.getInventory();
	}

	private static ArrayList<Achievement> getAchievements() {
		ArrayList<Achievement> ach = new ArrayList<>();
		for (Entry<String, Achievement> entry : achievements.entrySet()) {
			ach.add(entry.getValue());
		}

		return ach;
	}

	public static char getAlphebet(int i) {
		return alphebet[i - 1];
	}

	public static void openAnvil(Player player, Inventory inventory) {

		// Get our EntityPlayer
		EntityPlayer p = ((CraftPlayer) player).getHandle();

		// Create the AnvilContainer
		ContainerAnvil container = new FakeAnvil(p);

		if (inventory != null) {
			// Set the items to the items from the inventory given
			container.getBukkitView().getTopInventory().setItem(0, inventory.getItem(0));
			container.getBukkitView().getTopInventory().setItem(1, inventory.getItem(1));
			// container.getBukkitView().getTopInventory().setItem(2,
			// inventory.getItem(2));
		}

		// Counter stuff that the game uses to keep track of inventories
		int c = p.nextContainerCounter();

		// Send the packet
		p.playerConnection.sendPacket(new PacketPlayOutOpenWindow());

		// Set their active container to the container
		p.activeContainer = container;

		// Set their active container window id to that counter stuff
		p.activeContainer.windowId = c;

		// Add the slot listener
		p.activeContainer.addSlotListener(p);

	}

	public static void updateRecents(Player player, Player sender) {
		recents.put(player.getUniqueId(), sender.getUniqueId());
		recents.put(sender.getUniqueId(), player.getUniqueId());
	}

	public static Player getRecent(Player sender) {
		return Bukkit.getPlayer(recents.get(sender.getUniqueId()));
	}

	public static String checkAddress(InetAddress address) {
		String ip = address.toString().replaceFirst("/", "").split(":")[0];
		try {
			ResultSet set = connection.query("SELECT NAME FROM Users WHERE IP='" + ip + "'");
			set.next();
			if (set.getObject(1) == null)
				return "NO";
			else
				return "YES";
		} catch (Exception ex) {
			return "NO";
		}

	}

	public static String getPlayerFromAddress(InetAddress address) {
		String ip = address.toString().replaceFirst("/", "").split(":")[0];
		try {
			ResultSet set = connection.query("SELECT NAME FROM Users WHERE IP='" + ip + "'");
			set.next();
			return ((String) set.getObject(1));
		} catch (Exception ex) {
			return "N/A";
		}
	}

	public static void cachePlayer(Player player) {
		IDatabase sql = getConnection();
		int r = sql.update("UPDATE Users SET UUID='" + player.getUniqueId() + "',COIN=" + getCoins(player)
				+ ",FRIENDS='" + getFriends(player) + "',NAME='" + player.getName() + "',IP='"
				+ getPlayerAddress(player) + "' WHERE UUID='" + player.getUniqueId() + "'");

		if (r <= 0) {
			sql.update("INSERT INTO Users (UUID,COIN,FRIENDS,NAME,IP) VALUES ('" + player.getUniqueId() + "',0,'','"
					+ player.getName() + "','" + getPlayerAddress(player) + "')");

		}
	}

	public static String getPlayerAddress(Player player) {
		return ((String) player.getAddress().toString()).replaceFirst("/", "").split(":")[0];
	}

	public static String getFriends(Player player) {
		try {
			ResultSet set = connection.query("SELECT FRIENDS FROM Users WHERE UUID='" + player.getUniqueId() + "'");
			set.next();
			String friends = ((String) set.getObject(1));
			return friends;
		} catch (Exception e) {
			return "";
		}

	}

	public static Object getCoins(Player player) {
		try {
			ResultSet set = connection.query("SELECT COINS FROM Users WHERE UUID='?'", player.getUniqueId());

			return set.getObject(1);
		} catch (Exception e) {
			return 0;
		}
	}

	public static IDatabase getConnection() {
		return connection;
	}

	public static void openFriendMenu(Player player) {
		InventoryCreator inv = new InventoryCreator("&1&lFriend Menu", player, 27);

		inv.addItem(new ItemStack(Material.SKULL_ITEM), "&a&lAdd Friend", 'A', null, (short) 3);
		inv.addItem(new ItemStack(Material.PAPER), "&3&lShow All Friends", 'B', null, (short) 0);
		inv.addItem(new ItemStack(Material.BARRIER), "&c&lRemove Friend", 'C', null, (short) 0);

		inv.addItem(new ItemStack(Material.STAINED_GLASS_PANE), "&Click an option.", 'X', null, (short) 0);
		char[] config = new char[] { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'A', 'X', 'X', 'B', 'X', 'X',
				'C', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' };
		inv.setConfiguration(config);
		player.openInventory(inv.getInventory());
	}

	public static void openFriendListMenu(Player player) {
		InventoryCreator inv = new InventoryCreator("&1&lFriends", player, 45);

		inv.addItem(new ItemStack(Material.STAINED_GLASS_PANE), "&7Nothing.", 'X',
				new String[] { "&7Nothing here yet. Check back layer. :-)" }, (short) 0);
		int i = 1;
		ArrayList<Character> storage = new ArrayList<>();

		String[] friends = getFriends(player).split(",");
		for (String f : friends) {
			ItemStack head = new ItemStack(Material.SKULL_ITEM);
			SkullMeta meta = (SkullMeta) head.getItemMeta();
			meta.setOwner(f);
			head.setItemMeta(meta);
			inv.addItem(head, "&e&l" + f, getAlphebet(i), null, (short) 3);
			storage.add(getAlphebet(i));
			i = i + 1;
		}
		if (i < 45)
			for (int ii = 0; ii != 46 - i;) {
				storage.add('X');
				ii = ii + 1;
			}
		inv.setConfiguration(storage);
		player.openInventory(inv.getInventory());
	}

	public static String getPlayerDisplayName(PermissionUser player) {
		return colorize(player.getPrefix() + player.getName());
	}

	public static String getPlayerDisplayName(Player player) {
		PermissionUser user = PermissionsEx.getUser(player);
		return colorize(user.getPrefix() + user.getName());
	}

	public static void openHouseMenu(Player player) {

		InventoryCreator inv = new InventoryCreator(colorize("&6House Menu"), player, 27);

		inv.addItem(new ItemStack(Material.STAINED_GLASS_PANE), "&7Click Option", 'X', null, (short) 15);

		inv.addItem(new ItemStack(Material.SIGN), "&eMake House", 'A', null, (short) 0);
		inv.addItem(new ItemStack(Material.DIAMOND), "&cUpgrade House", 'B', null, (short) 0);
		inv.addItem(new ItemStack(Material.BARRIER), "&4&lClose", 'Z', null, (short) 0);

		inv.setConfiguration(new char[] { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'A', 'X', 'X', 'X', 'X',
				'X', 'B', 'X', 'X', 'X', 'X', 'X', 'Z', 'X', 'X', 'X', 'X' });

		player.openInventory(inv.getInventory());
	}

	public static void registerHouse(Player player) {
		// if(getConnection().init()){
		// getConnection().update("")
		// }
	}

	public static Firework createFirework(Location location, FireworkEffect ef) {

		Firework f = location.getWorld().spawn(location, Firework.class);

		FireworkMeta fw = f.getFireworkMeta();

		fw.clearEffects();

		fw.addEffect(ef);

		f.setFireworkMeta(fw);
		return f;

	}

	public static void openSkyRingMenu(Player player) {

		InventoryCreator inv = new InventoryCreator(colorize("&c&lTravel to Sky Rings?"), player, 27);

		inv.addItem(new ItemStack(Material.STAINED_GLASS_PANE), "&7Click Option", 'X', null, (short) 8);

		inv.addItem(new ItemStack(Material.EMERALD), "&a&lYes", 'A', new String[] {"&a&lTake me to SkyRings!"}, (short) 0);
		inv.addItem(new ItemStack(Material.INK_SACK), "&c&lNo", 'B', new String[] {"&c&lI want to stay here!"}, (short) 1);

		inv.setConfiguration(new char[] { 
				'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X',
				'X', 'A', 'X', 'X', 'X', 'X', 'X', 'B', 'X',
				'X', 'X', 'X', 'X', 'Z', 'X', 'X', 'X', 'X' 
		});

		player.openInventory(inv.getInventory());
	}

	

}
