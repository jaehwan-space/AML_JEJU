package com.majun.amljeju.manager;


import com.majun.amljeju.gui.Container_Inventory;
import com.majun.amljeju.gui.Container_OreWork;
import com.majun.amljeju.gui.Gui_Inventory;
import com.majun.amljeju.gui.Gui_OreWork;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class InvHandler implements IGuiHandler {
    public static final int INVENTORY = 0;
    public static final int OreWork = 1;

    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == 0)
            return new Container_Inventory(player.inventory, player);
        if (ID == 1)
            return new Container_OreWork(world, x, y, z, player);
        return null;
    }

    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == 0)
            return new Gui_Inventory(player, player.inventory);
        if (ID == 1)
            return new Gui_OreWork(world, x, y, z, player);
        return null;
    }
}
