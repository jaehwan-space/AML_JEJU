package com.majun.amljeju.network;

import com.majun.amljeju.gui.Container_OreWork;
import com.majun.amljeju.gui.Gui_OreWork;
import com.majun.amljeju.proxy.ClientProxy;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

import java.nio.charset.StandardCharsets;

public class NetworkManager {
    public static SimpleNetworkWrapper networkWrapper = null;
    private static int packetId;

    public static void setup() {
        networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel("amljeju");  // 패킷 생성

        networkWrapper.registerMessage(PacketMessage.Handler.class, PacketMessage.class, packetId++, Side.CLIENT);
        networkWrapper.registerMessage(PacketOpenInv.class, PacketOpenInv.class, packetId++, Side.SERVER);
        networkWrapper.registerMessage(Container_OreWork.GUISlotChangedMessageHandler.class, Container_OreWork.GUISlotChangedMessage.class, packetId++, Side.SERVER);
        networkWrapper.registerMessage(Gui_OreWork.GUIButtonPressedMessageHandler.class, Gui_OreWork.GUIButtonPressedMessage.class, packetId++, Side.SERVER);
    }

    public static void sendToServer(IMessage message) {
        networkWrapper.sendToServer(message);
    }

    public static void sendTo(IMessage message, EntityPlayerMP player) {
        networkWrapper.sendTo(message, player);
    }

    public static void sendToAll(IMessage message) {
        networkWrapper.sendToAll(message);
    }

    public static void sendPacket(String message){
        ClientProxy.dataChannel.sendToServer(new FMLProxyPacket(new PacketBuffer(Unpooled.wrappedBuffer(message.getBytes(StandardCharsets.UTF_8))), "CH-AMLJEJU"));
    }
}
