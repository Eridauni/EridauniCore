package me.quickScythe.eridaunicore.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import me.quickScythe.eridaunicore.Main;
import me.quickScythe.eridaunicore.core.Achievement;
import me.quickScythe.eridaunicore.core.Gamer;
import me.quickScythe.eridaunicore.core.MainTimer;
import me.quickScythe.eridaunicore.utils.packets.ParticleEffect;
import me.quickScythe.eridaunicore.utils.packets.ParticleEffect.OrdinaryColor;
import net.md_5.bungee.api.ChatColor;

public class Utils {

	private static Map<UUID, Integer> particleTimer = new HashMap<>();
	private static Map<UUID, Double[]> helixMathMap = new HashMap<>();
	private static Map<UUID, ParticleEffect> particles = new HashMap<>();
	private static Map<UUID, String> colors = new HashMap<>();
	private static Map<UUID, Gamer> gamers = new HashMap<>();
	private static Location spawn;
	private static Map<UUID, ParticleFormat> particleformats = new HashMap<>();
	private static Set<UUID> wings = new HashSet<>();
	private static Map<String, Achievement> achievements = new HashMap<>();
	private static Map<UUID, ArrayList<Achievement>> playerAchievements = new HashMap<>();
	private static BukkitTask task;
	private static char[] alphebet = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
			'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
			'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

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

	public static Location getCircleLocationBackwards(int i, Double width, Location location) {

		double x = (double) (width * Math.cos(((i + 16 - 31) * 11.25) * Math.PI / 180F)) + location.getX();
		double z = (double) (width * Math.sin(((i + 16 - 31) * 11.25) * Math.PI / 180F)) + location.getZ();
		return new Location(location.getWorld(), x, location.getY(), z);

	}

	public static void displayParticle(Player player, Location l, List<Player> players) {

		if (getParticle(player.getUniqueId()).equals(ParticleEffect.REDSTONE)) {
			if (getParticleColor(player) == "random") {
				getParticle(player.getUniqueId()).display(0, 0, 0, 1, 1, l, players);
				return;
			}
			if (getParticleColor(player) == null) {
				getParticle(player.getUniqueId()).display(0, 0, 0, 0, 1, l, players);
				return;
			}
			String[] values = getParticleColor(player).split(" ");
			getParticle(player.getUniqueId()).display(new OrdinaryColor((int) Integer.parseInt(values[0]),
					(int) Integer.parseInt(values[1]), (int) Integer.parseInt(values[2])), l, players);
			return;
		}
		getParticle(player.getUniqueId()).display(0, 0, 0, 0, 1, l, players);
	}

	public static ParticleEffect getParticle(UUID uuid) {
		return particles.get(uuid);
	}

	private static String getParticleColor(Player player) {
		return colors.get(player.getUniqueId());
	}

	public static void setParticleColor(Player player, String colorRGB) {
		if (colorRGB.contains(" ")) {
			String[] values = colorRGB.split(" ");
			if (Integer.parseInt(values[0]) > 255) {
				player.sendMessage(Utils.colorize("&3Particles &7> &fYour RED value is above 255."));
				return;
			}
			if (Integer.parseInt(values[1]) > 255) {
				player.sendMessage(Utils.colorize("&3Particles &7> &fYour GREEN value is above 255."));
				return;
			}
			if (Integer.parseInt(values[2]) > 255) {
				player.sendMessage(Utils.colorize("&3Particles &7> &fYour BLUE value is above 255."));
				return;
			}
			if (Integer.parseInt(values[0]) < 0) {
				player.sendMessage(Utils.colorize("&3Particles &7> &fYour RED value is below 0."));
				return;
			}
			if (Integer.parseInt(values[1]) < 0) {
				player.sendMessage(Utils.colorize("&3Particles &7> &fYour GREEN value is below 0."));
				return;
			}
			if (Integer.parseInt(values[2]) < 0) {
				player.sendMessage(Utils.colorize("&3Particles &7> &fYour BLUE value is below 0."));
				return;
			}

		}
		colors.put(player.getUniqueId(), colorRGB);

	}

	public static void start() {
		startMainTimer();

		try {
			String encodedSpawn = Main.getPlugin().getConfig().getString("Spawn");
			String[] ds = encodedSpawn.split(":");
			spawn = new Location(Bukkit.getWorld(ds[0]), Float.parseFloat(ds[1]), Float.parseFloat(ds[2]),
					Float.parseFloat(ds[3]), Float.parseFloat(ds[4]), Float.parseFloat(ds[5]));
		} catch (NullPointerException ex) {
			spawn = Bukkit.getWorld("world").getSpawnLocation();
		}

		updateAchievements();
		for (Player player : Bukkit.getOnlinePlayers()) {
			loadAchievements(player);
		}
		createGamers();

		Main.getPlugin().getServer().getConsoleSender().sendMessage(("&e&lServer &f>&7 Hub enabled!"));

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
		Main.getPlugin().saveConfig();
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

	public static void toggleWings(Player player) {
		player.closeInventory();
		if (!player.hasPermission("core.wings")) {
			player.sendMessage(colorize("&e&lWings &f>&7 You don't have permission to use that."));
			return;
		}
		if (wings.contains(player.getUniqueId())) {
			wings.remove(player.getUniqueId());
			if (!player.hasPermission("core.fly")) {
				player.setAllowFlight(false);
			}
			player.setFlying(false);
			player.sendMessage(Utils.colorize("&e&lGadgets &f>&7 You have lost your wings."));
		} else {
			wings.add(player.getUniqueId());
			player.playSound(player.getLocation(), Sound.ENDERDRAGON_WINGS, 10, 10);
			player.setAllowFlight(true);
			player.setFlying(true);
			player.sendMessage(Utils.colorize("&e&lGadgets &f>&7 You have been granted the ability to &f&lfly&7!"));
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
		OrdinaryColor color = new OrdinaryColor(255, 255, 255);

		Location loc1R = loc.clone();
		loc1R.setYaw(loc1R.getYaw() + 110.0F);
		Location loc2R = loc1R.clone().add(loc1R.getDirection().multiply(1));
		ParticleEffect.REDSTONE.display(color, loc2R.add(0.0D, 0.8D, 0.0D), 30.0D);

		Location loc3R = loc1R.clone().add(loc1R.getDirection().multiply(0.8D));
		ParticleEffect.REDSTONE.display(color, loc3R.add(0.0D, 0.6D, 0.0D), 30.0D);
		Location loc4R = loc1R.clone().add(loc1R.getDirection().multiply(0.6D));
		ParticleEffect.REDSTONE.display(color, loc4R.add(0.0D, 0.4D, 0.0D), 30.0D);
		Location loc5R = loc1R.clone().add(loc1R.getDirection().multiply(0.4D));
		ParticleEffect.REDSTONE.display(color, loc5R.clone().add(0.0D, -0.2D, 0.0D), 30.0D);
		Location loc6R = loc1R.clone().add(loc1R.getDirection().multiply(0.2D));
		ParticleEffect.REDSTONE.display(color, loc6R.add(0.0D, -0.2D, 0.0D), 30.0D);

		int zu = 0;
		while (zu <= 3) {
			zu++;

			ParticleEffect.REDSTONE.display(color, loc2R.add(0.0D, -0.2D, 0.0D), 30.0D);
			ParticleEffect.REDSTONE.display(color, loc3R.add(0.0D, -0.2D, 0.0D), 30.0D);
			ParticleEffect.REDSTONE.display(color, loc4R.add(0.0D, -0.2D, 0.0D), 30.0D);
			ParticleEffect.REDSTONE.display(color, loc5R.add(0.0D, -0.2D, 0.0D), 30.0D);
			ParticleEffect.REDSTONE.display(color, loc6R.add(0.0D, -0.2D, 0.0D), 30.0D);
		}

		Location loc1L = loc.clone();
		loc1L.setYaw(loc1L.getYaw() - 110.0F);
		Location loc2L = loc1L.clone().add(loc1L.getDirection().multiply(1));
		ParticleEffect.REDSTONE.display(color, loc2L.add(0.0D, 0.8D, 0.0D), 30.0D);

		Location loc3L = loc1L.clone().add(loc1L.getDirection().multiply(0.8D));
		ParticleEffect.REDSTONE.display(color, loc3L.add(0.0D, 0.6D, 0.0D), 30.0D);
		Location loc4L = loc1L.clone().add(loc1L.getDirection().multiply(0.6D));
		ParticleEffect.REDSTONE.display(color, loc4L.add(0.0D, 0.4D, 0.0D), 30.0D);
		Location loc5L = loc1L.clone().add(loc1L.getDirection().multiply(0.4D));
		ParticleEffect.REDSTONE.display(color, loc5L.clone().add(0.0D, -0.2D, 0.0D), 30.0D);
		Location loc6L = loc1L.clone().add(loc1L.getDirection().multiply(0.2D));
		ParticleEffect.REDSTONE.display(color, loc6L.add(0.0D, -0.2D, 0.0D), 30.0D);

		zu = 0;

		while (zu <= 3) {
			zu++;

			ParticleEffect.REDSTONE.display(color, loc2L.add(0.0D, -0.2D, 0.0D), 30.0D);
			ParticleEffect.REDSTONE.display(color, loc3L.add(0.0D, -0.2D, 0.0D), 30.0D);
			ParticleEffect.REDSTONE.display(color, loc4L.add(0.0D, -0.2D, 0.0D), 30.0D);
			ParticleEffect.REDSTONE.display(color, loc5L.add(0.0D, -0.2D, 0.0D), 30.0D);
		}
	}

	public static Location getSpawn() {
		return spawn;
	}

	public static Inventory getParticleInventory(Player player) {
		InventoryCreator inv = new InventoryCreator("&6Particles &7(Page 1)", player, 45);
		inv.addItem(new ItemStack(Material.STAINED_GLASS_PANE), "&7Click an effect.", 'X', null, (short) 0);
		inv.addItem(new ItemStack(Material.CAULDRON_ITEM), "&5&lWitch Magic", 'A', null, (short) 0);
		inv.addItem(new ItemStack(Material.FIREBALL), "&6&lFlame", 'B', null, (short) 0);
		inv.addItem(new ItemStack(Material.NETHER_STAR), "&f&lSparks", 'C', null, (short) 0);
		inv.addItem(new ItemStack(Material.SNOW_BLOCK), "&f&lCloud", 'D', null, (short) 0);
		inv.addItem(new ItemStack(Material.REDSTONE), "&c&lRedstone Dust", 'E', null, (short) 0);
		inv.addItem(new ItemStack(Material.RECORD_9), "&3&lNotes", 'F', null, (short) 0);
		inv.addItem(new ItemStack(Material.WATER_BUCKET), "&1&lWater Drip", 'G', null, (short) 0);
		inv.addItem(new ItemStack(Material.EMERALD), "&a&lGreen Tinkle", 'H', null, (short) 0);
		inv.addItem(new ItemStack(Material.PAPER), "&7&lAncient Ruins", 'I', null, (short) 0);
		inv.addItem(new ItemStack(Material.NAME_TAG), "&c&lLove! <3", 'J', null, (short) 0);
		inv.addItem(new ItemStack(Material.COAL), "&8&lStorm Cloud", 'K', null, (short) 0);
		inv.addItem(new ItemStack(Material.LAVA_BUCKET), "&6&lLava Drip", 'L', null, (short) 0);
		inv.addItem(new ItemStack(Material.BARRIER), "&4Remove Trail", 'M', null, (short) 0);
		inv.addItem(new ItemStack(Material.REDSTONE_BLOCK), "&4Exit", 'N', null, (short) 0);
		inv.addItem(new ItemStack(Material.TNT), "&c&lExplosion", 'O', null, (short) 0);
		inv.addItem(new ItemStack(Material.GOLD_SWORD), "&e&lCrit", 'P', null, (short) 0);

		inv.addItem(new ItemStack(Material.ARROW), "&7Next Page ->", 'Y', null, (short) 0);

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

		inv.addItem(new ItemStack(Material.DIAMOND_SWORD), "&3&lMagic Crit", '1', null, (short) 0);
		inv.addItem(new ItemStack(Material.STAINED_GLASS_PANE), "&5&lPortal", '2', null, (short) 10);
		inv.addItem(new ItemStack(Material.FLINT_AND_STEEL), "&c&lHot Sparks", '3', null, (short) 0);
		inv.addItem(new ItemStack(Material.SLIME_BALL), "&a&lSlime Poof", '4', null, (short) 0);
		inv.addItem(new ItemStack(Material.SNOW_BALL), "&f&lSnow Poof", '5', null, (short) 0);
		inv.addItem(new ItemStack(Material.STONE), "&7&lFoot Print", '6', null, (short) 0);

		inv.setConfiguration(new char[] { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', '1', '2', '3', '4', '5',
				'6', 'Z', 'X', 'X', 'Z', 'Z', 'Z', 'Z', 'Z', 'Z', 'Z', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X',
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
		inv.addItem(new ItemStack(Material.SKULL_ITEM), "&a&lCircle Head", 'A', null, (short) 3);
		inv.addItem(new ItemStack(Material.LEATHER_BOOTS), "&c&lCircle Feet", 'B', null, (short) 0);
		inv.addItem(new ItemStack(Material.EMERALD), "&3&lHelix", 'C', null, (short) 0);
		inv.addItem(new ItemStack(Material.NETHER_STAR), "&4&lDouble Helix", 'D', null, (short) 0);
		inv.addItem(new ItemStack(Material.IRON_SWORD), "&6&lRandom", 'E', null, (short) 0);
		inv.addItem(new ItemStack(Material.BLAZE_POWDER), "&b&lCrossFire", 'F', null, (short) 0);
		inv.addItem(new ItemStack(Material.POTION), "&a&lSpiral", 'G', null, (short) 0);
		inv.addItem(new ItemStack(Material.REDSTONE), "&c&lBlood Helix", 'H', null, (short) 0);

		inv.setConfiguration(new char[] { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'A', 'B', 'C', 'D', 'E',
				'F', 'G', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'

		});
		return inv.getInventory();
	}

	public static void setParticleFormat(Player player, ParticleFormat format) {
		particleformats.put(player.getUniqueId(), format);
		particleTimer.put(player.getUniqueId(), 0);
		restartMainTimer();
	}

	public static ParticleFormat getParticleFormat(Player player) {
		return particleformats.get(player.getUniqueId());
	}

	@SuppressWarnings("deprecation")
	public static void startMainTimer() {
		task = Bukkit.getScheduler().runTaskTimer(Main.getPlugin(), new MainTimer(), 1, 1);
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
		for (Achievement achievement : Utils.getAchievements()) {
			String[] info = achievement.getItemInfo();
			if (Utils.getGamer(player).hasAchievement(achievement))
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
	public static char getAlphebet(int i){
		return alphebet[i-1];
	}

}
