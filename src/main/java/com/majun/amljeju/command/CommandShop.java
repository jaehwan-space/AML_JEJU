package com.majun.amljeju.command;

import com.majun.amljeju.gui.GuiShop;
import com.majun.amljeju.network.NetworkManager;
import com.majun.amljeju.network.PacketOpenShop;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

import java.util.Iterator;

public class CommandShop extends CommandBase {
    public String getName() {
        return "상점";
    }

    public String getUsage(ICommandSender sender) {
        return "command.shop.usage";
    }

    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws PlayerNotFoundException {
        if(args[0].equalsIgnoreCase("열기")){
            NBTTagCompound data;
            data = new NBTTagCompound();
            EntityPlayerMP player = getCommandSenderAsPlayer(sender);
//            NetworkManager.sendTo((IMessage)new PacketOpenShop(data), player);
            Minecraft.getMinecraft().displayGuiScreen((GuiScreen) new GuiShop(/*message.compound*/));
        }
//        try {
//            NBTTagCompound data;
//            Iterator<Integer> iterator1;
//            EntityPlayerMP player = getCommandSenderAsPlayer(sender);
//            switch (args[0]) {
//                case "열기":
//                    data = new NBTTagCompound();
//                    data.setString(String.valueOf(1), "df");
//                    NetworkManager.sendTo((IMessage)new PacketOpenShop(data), player);
//                    break;
//                case "도움말":
//                    player.sendMessage((ITextComponent)new TextComponentString(" "));
//                    player.sendMessage((ITextComponent)new TextComponentString(" "));
//                    break;
//            }
//        } catch (Exception e) {
//            sender.sendMessage((ITextComponent) new TextComponentString(Utils.convertColor("오류")));
//        }

    }

    public int getRequiredPermissionLevel() {
        return 0;
    }

    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }
}
