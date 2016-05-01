package me.quickScythe.eridaunicore.core;

import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import me.quickScythe.eridaunicore.Main;
import me.quickScythe.eridaunicore.utils.packets.FakeAnvil;
import net.minecraft.server.v1_9_R1.ChatMessage;
import net.minecraft.server.v1_9_R1.EntityPlayer;
import net.minecraft.server.v1_9_R1.PacketPlayOutOpenWindow;
 
public class Anvil {
 
    public static void openAnvilInventory(final Player player, ItemStack item) {
   
        EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();
        FakeAnvil fakeAnvil = new FakeAnvil(entityPlayer);
        fakeAnvil.a("test");
        int containerId = entityPlayer.nextContainerCounter();
   
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutOpenWindow(containerId, "minecraft:anvil", new ChatMessage("Repairing", new Object[]{}), 0));
   
        entityPlayer.activeContainer = fakeAnvil;
        entityPlayer.activeContainer.windowId = containerId;
        entityPlayer.activeContainer.addSlotListener(entityPlayer);
        entityPlayer.activeContainer = fakeAnvil;
        entityPlayer.activeContainer.windowId = containerId;
   
        Inventory inv = fakeAnvil.getBukkitView().getTopInventory();
        inv.setItem(0, item);
   
        player.setMetadata("anvil", new FixedMetadataValue(Main.getPlugin(), "yes"));
        
        }
}