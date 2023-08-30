package com.majun.amljeju.network;

import com.majun.amljeju.AML_JEJU;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.nio.charset.StandardCharsets;

public class PacketMessage implements IMessage{
    public String messages;
    public PacketMessage() {}

    public PacketMessage(String messages) {
        this.messages = messages;
    }

    public void fromBytes(ByteBuf buf) {  //들어올때 한번 거침
        int len = buf.readInt();
        messages = buf.toString(buf.readerIndex(), len, StandardCharsets.UTF_8);
        buf.readerIndex(buf.readerIndex() + len);
    }

    public void toBytes(ByteBuf buf) {  //보낼때 한번 거침
        byte[] bytes = messages.getBytes(StandardCharsets.UTF_8);
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);
    }
    public static class Handler implements IMessageHandler<PacketMessage, IMessage> {
        public IMessage onMessage(PacketMessage message, MessageContext ctx) {
            handleMessage(message);
            return null;
        }
    }

    public static void handleMessage(PacketMessage message) {
        String code = message.messages.toString();
        if (code.contains("SHOP")) {
            try {
                Minecraft.getMinecraft().player.sendMessage(new TextComponentString(code));
            } catch (Exception e) {
                AML_JEJU.logger.error(e.getMessage());
            }
        }
    }
}
