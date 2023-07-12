package com.majun.amljeju.proxy;

import com.majun.amljeju.gui.HUD;
import com.majun.amljeju.network.CPacketOpenInventory;
import com.majun.amljeju.network.PacketSystem;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

import java.nio.charset.Charset;

public class ClientProxy extends CommonProxy {
    private Minecraft minecraft = Minecraft.getMinecraft();
    public static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel("CH-AML_JEJU");  //플러그인 네트워크 생성

    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new HUD());  //HUD 이벤트 실행
        super.init(event);
    }

    @SubscribeEvent
    public void guiOpenEvent(GuiOpenEvent event) {
        if (event.getGui() != null && event.getGui().getClass() == GuiInventory.class && !(Minecraft.getMinecraft()).player.isCreative()) {
            event.setCanceled(true);
            PacketSystem.networkWrapper.sendToServer((IMessage)new CPacketOpenInventory());
        }
    }

    @SubscribeEvent
    public static void onClientPacketReceived(FMLNetworkEvent.ClientCustomPacketEvent event) {
        if (event.getPacket().channel().equals("CH-AML_JEJU")) {
            String message = new String(ByteBufUtil.getBytes(event.getPacket().payload()), Charset.forName("UTF-8"));

            // 메시지를 출력하는 코드를 작성합니다.
            Minecraft.getMinecraft().player.sendMessage(new TextComponentString(message));
        }
    }
}

