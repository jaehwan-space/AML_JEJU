package com.majun.amljeju.network;

import com.majun.amljeju.gui.GuiShop;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketOpenShop implements IMessage {
    public NBTTagCompound compound;

    public PacketOpenShop(NBTTagCompound compound) {
        this.compound = compound;
    }

    public void fromBytes(ByteBuf buf) {
        this.compound = ByteBufUtils.readTag(buf);
    }

    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, this.compound);
    }

    public PacketOpenShop() {}

    public static class Handler implements IMessageHandler<PacketOpenShop, IMessage> {
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(PacketOpenShop message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        @SideOnly(Side.CLIENT)
        private void handle(PacketOpenShop message, MessageContext ctx) {
            Minecraft.getMinecraft().displayGuiScreen((GuiScreen) new GuiShop(/*message.compound*/));
        }
    }
}
