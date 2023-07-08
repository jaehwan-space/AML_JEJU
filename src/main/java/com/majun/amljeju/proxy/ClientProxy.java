package com.majun.amljeju.proxy;

import com.majun.amljeju.HUD;
import com.majun.amljeju.network.CPacketOpenInventory;
import com.majun.amljeju.network.PacketSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class ClientProxy extends CommonProxy {
    private Minecraft minecraft = Minecraft.getMinecraft();

    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new HUD());
        super.init(event);
    }

    @SubscribeEvent
    public void guiOpenEvent(GuiOpenEvent event) {
        if (event.getGui() != null && event.getGui().getClass() == GuiInventory.class && !(Minecraft.getMinecraft()).player.isCreative()) {
            event.setCanceled(true);
            PacketSystem.networkWrapper.sendToServer((IMessage)new CPacketOpenInventory());
        }
    }
}

