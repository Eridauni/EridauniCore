package me.quickScythe.eridaunicore.core;

import net.minecraft.server.v1_9_R1.BlockPosition;
import net.minecraft.server.v1_9_R1.ContainerAnvil;
import net.minecraft.server.v1_9_R1.EntityHuman;
 
public final class FakeAnvil extends ContainerAnvil {
 
public FakeAnvil(EntityHuman entityHuman) {    
    super(entityHuman.inventory, entityHuman.world, new BlockPosition(0,0,0), entityHuman);
}
 
 
@Override
public boolean a(EntityHuman entityHuman) {    
return true;
    }
}