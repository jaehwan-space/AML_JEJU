package com.majun.amljeju.network;

import com.majun.amljeju.AML_JEJU;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CPacketOpenInventory implements IMessage, IMessageHandler<CPacketOpenInventory, CPacketOpenInventory> {
    public void fromBytes(ByteBuf buf) {}
    
    public void toBytes(ByteBuf buf) {}
    
    public CPacketOpenInventory onMessage(CPacketOpenInventory message, MessageContext ctx) {
    EntityPlayerMP player = (ctx.getServerHandler()).player;
    player.openGui(AML_JEJU.instance, 0, player.getEntityWorld(), (int)player.posX, (int)player.posY, (int)player.posZ);
    return null;
    }
}
