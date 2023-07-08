package com.majun.amljeju.network;


import com.majun.amljeju.ContainerInventory;
import com.majun.amljeju.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GUIHandler implements IGuiHandler {
    public static final int INVENTORY = 0;

    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == 0)
            return new ContainerInventory(player.inventory, player);
        return null;
    }

    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == 0)
            return new GuiInventory(player, player.inventory);
        return null;
    }
}
