package com.majun.amljeju.proxy;

import com.majun.amljeju.Data;
import com.majun.amljeju.gui.*;
import com.majun.amljeju.network.PacketOpenInv;
import com.majun.amljeju.network.NetworkManager;
import io.netty.buffer.ByteBufUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.io.IOUtils;
import org.lwjgl.opengl.Display;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Objects;


public class ClientProxy extends CommonProxy {
    public static KeyBinding keyCall;
    public static KeyBinding keyRemittance;
    public static KeyBinding call_accept;
    public static KeyBinding call_refuse;
    private Minecraft minecraft = Minecraft.getMinecraft();
    public static final FMLEventChannel dataChannel = NetworkRegistry.INSTANCE.newEventDrivenChannel("CH-AMLJEJU");

    @SideOnly(Side.CLIENT)
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new HUD());  //HUD 이벤트 실행
        dataChannel.register(this);
        OBJLoader.INSTANCE.addDomain("amljeju");

        keyCall = new KeyBinding("key.call", 35, "key.categories.amljeju");
        ClientRegistry.registerKeyBinding(keyCall);
        keyRemittance = new KeyBinding("key.remittance", 36, "key.categories.amljeju");
        ClientRegistry.registerKeyBinding(keyRemittance);
        call_accept = new KeyBinding("key.call_accept", 26 /* [ */, "key.categories.amljeju");
        ClientRegistry.registerKeyBinding(call_accept);
        call_refuse = new KeyBinding("key.call_refuse", 27 /* ] */, "key.categories.amljeju");
        ClientRegistry.registerKeyBinding(call_refuse);

        super.init(event);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        if (keyCall.isPressed() && mc.currentScreen == null)
            minecraft.addScheduledTask(() -> minecraft.displayGuiScreen((GuiScreen) new Gui_Call()));
        if (keyRemittance.isPressed() && mc.currentScreen == null)
            minecraft.addScheduledTask(() -> minecraft.displayGuiScreen((GuiScreen) new Gui_Remittance()));
        if (call_accept.isPressed() && mc.currentScreen == null){
            StringBuilder Data_CallRequset = new StringBuilder();
            Data_CallRequset.append("CallJudgment#0");
            NetworkManager.sendPacket(Data_CallRequset.toString());

            Data.call_stat = 0;
            Data.CallRequest_Name = "";
        }
        if (call_refuse.isPressed() && mc.currentScreen == null){
            StringBuilder Data_CallRequset = new StringBuilder();
            Data_CallRequset.append("CallJudgment#1");
            NetworkManager.sendPacket(Data_CallRequset.toString());

            Data.call_stat = 0;
            Data.CallRequest_Name = "";
        }

    }

    public void registerModel(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Objects.<ResourceLocation>requireNonNull(item.getRegistryName()), "inventory"));
    }

    @SubscribeEvent
    public void guiOpenEvent(GuiOpenEvent event) {
        if (event.getGui() != null && event.getGui().getClass() == GuiInventory.class && !(Minecraft.getMinecraft()).player.isCreative()) {
            event.setCanceled(true);
            NetworkManager.sendToServer((IMessage) new PacketOpenInv());
        }
    }

    @SubscribeEvent
    public void onGuiInit(GuiScreenEvent.InitGuiEvent.Pre event) {
        if (event.getGui() instanceof net.minecraft.client.gui.GuiIngameMenu) {
            event.setCanceled(true);
            minecraft.addScheduledTask(() -> minecraft.displayGuiScreen((GuiScreen)new Gui_ESC()));
        }
    }

    public static void updateIcon() {
        Util.EnumOS enumos = Util.getOSType();
        if (enumos != Util.EnumOS.OSX) {
            InputStream stream16 = null;
            InputStream stream32 = null;
            try {
                Minecraft minecraft = Minecraft.getMinecraft();
                stream16 = minecraft.getResourceManager().getResource( new ResourceLocation("amljeju", "textures/etc/icon16.png")).getInputStream();
                stream32 = minecraft.getResourceManager().getResource( new ResourceLocation("amljeju", "textures/etc/icon32.png")).getInputStream();
                Display.setTitle(I18n.format("한달살이 in Jeju", new Object[0]));
                Display.setIcon(new ByteBuffer[] { readImageToBuffer(stream16), readImageToBuffer(stream32) });
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                IOUtils.closeQuietly(stream16);
                IOUtils.closeQuietly(stream32);
            }
        }
    }

    private static ByteBuffer readImageToBuffer(InputStream imageStream) throws IOException {
        BufferedImage bufferedimage = ImageIO.read(imageStream);
        int[] aint = bufferedimage.getRGB(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(), (int[])null, 0, bufferedimage.getWidth());
        ByteBuffer bytebuffer = ByteBuffer.allocate(4 * aint.length);
        for (int i : aint)
            bytebuffer.putInt(i << 8 | i >> 24 & 0xFF);
        bytebuffer.flip();
        return bytebuffer;
    }

    public static String getCardinalDirection(EntityPlayer player) {
        double rotation = ((player.rotationYawHead - 90.0F) % 360.0F);
        if (rotation < 0.0D)
            rotation += 360.0D;
        if (0.0D <= rotation && rotation < 22.5D)
            return "N / 북쪽";
        if (22.5D <= rotation && rotation < 67.5D)
            return "NE / 북동";
        if (67.5D <= rotation && rotation < 112.5D)
            return "E / 동쪽";
        if (112.5D <= rotation && rotation < 157.5D)
            return "SE / 남동";
        if (157.5D <= rotation && rotation < 202.5D)
            return "S / 남쪽";
        if (202.5D <= rotation && rotation < 247.5D)
            return "SW / 남서";
        if (247.5D <= rotation && rotation < 292.5D)
            return "W / 서쪽";
        if (292.5D <= rotation && rotation < 337.5D)
            return "NW / 북서";
        if (337.5D <= rotation && rotation < 360.0D)
            return "N / 북쪽";
        return null;
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onClientCustomPacket(FMLNetworkEvent.ClientCustomPacketEvent event) {
        if (event.getPacket().channel().equals("CH-AMLJEJU")) {
            String packetData = new String(ByteBufUtil.getBytes(event.getPacket().payload()), Charset.forName("UTF-8"));

            String[] InfoData = packetData.split("\\^\\^\\^");
            if(InfoData[0].contains("MoneyStat"))
                Data.Money = Integer.valueOf(InfoData[1]);
            else if(InfoData[0].contains("Timer"))
                HUD.Timerupdate(Integer.valueOf(InfoData[1]));
            else if(InfoData[0].contains("ShopUpdate"))
               Gui_Shop.UpdateShop(packetData);
            else if(InfoData[0].contains("ShopOpen"))
                minecraft.addScheduledTask(() -> minecraft.displayGuiScreen((GuiScreen) new Gui_Shop(packetData)));
            else if(InfoData[0].contains("RemittanceOpen"))
                minecraft.addScheduledTask(() -> minecraft.displayGuiScreen((GuiScreen) new Gui_Remittance()));
            else if(InfoData[0].contains("RemittanceUpdate"))
                Gui_Remittance.UpdateRemittance(packetData);
            else if(InfoData[0].contains("PlayerInfo")) {
                Data.Player_Name = InfoData[1];
                Data.Job = InfoData[2];
            }
            else if(InfoData[0].contains("Request_send")) {
                Data.call_stat = Integer.valueOf(InfoData[1]);
                Data.CallRequest_Name = InfoData[2];
            }
            else if(InfoData[0].contains("Request_Cancel")) {
                Data.call_stat = Integer.valueOf(InfoData[1]);
            }
        }
    }

}

