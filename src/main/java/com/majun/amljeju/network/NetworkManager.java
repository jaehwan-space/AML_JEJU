package com.majun.amljeju.network;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkManager {
    public static SimpleNetworkWrapper INSTANCE = null;

    private static int packetId;

    public static void setup() {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("amljeju");
        INSTANCE.registerMessage(PacketOpenShop.Handler.class, PacketOpenShop.class, packetId++, Side.CLIENT);
    }

    public static void sendToServer(IMessage message) {
        INSTANCE.sendToServer(message);
    }

    public static void sendTo(IMessage message, EntityPlayerMP player) {
        INSTANCE.sendTo(message, player);
    }

    public static void sendToAll(IMessage message) {
        INSTANCE.sendToAll(message);
    }
}
