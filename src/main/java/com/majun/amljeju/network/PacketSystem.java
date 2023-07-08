package com.majun.amljeju.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketSystem {
    public static SimpleNetworkWrapper networkWrapper;

    public static void init() {
        networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel("amljeju");
        networkWrapper.registerMessage(CPacketOpenInventory.class, CPacketOpenInventory.class, 0, Side.SERVER);
    }
}

