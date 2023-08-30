package com.majun.amljeju.manager;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.text.DecimalFormat;
import java.util.List;

public class Utils {

    public static int[] listToIntArray(List<Integer> list) {
        int[] intArray = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            intArray[i] = list.get(i);
        }
        return intArray;
    }

    public static String convertMoneyToCommas(int amount) {
        DecimalFormat commas = new DecimalFormat("#,###");
        return commas.format(amount);
    }

    public static String convertColor(String message) {
        return message.replace("&", "§");
    }

    public static int getAllItemCount(InventoryPlayer player, ItemStack item) {
        int count = 0;
        for (int slot = 0; slot < player.getSizeInventory(); slot++) {
            ItemStack stack = player.getStackInSlot(slot);
            if (stack.isItemEqual(item))
                count += stack.getCount();
        }
        return count;
    }

    public static void sendMessage(EntityPlayer player, String message, boolean actionbar) {
        player.sendStatusMessage((ITextComponent)new TextComponentString(convertColor(message)), actionbar);
    }

    public static void sendMessage(String player, String message, boolean actionbar) {
        EntityPlayerMP playerMP = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(player);
        if (playerMP != null)
            sendMessage((EntityPlayer)playerMP, message, actionbar);
    }

    public static ItemStack stringToStack(String data) {
        int itemID = Integer.parseInt(data.split("#")[0]);
        int amount = Integer.parseInt(data.split("#")[1]);
        int meta = Integer.parseInt(data.split("#")[2]);
        ItemStack stack = new ItemStack(Item.getItemById(itemID), amount, meta);
        String compound = data.split("#")[3];
        if (!compound.equals("null"))
            try {
                stack.getItem().readNBTShareTag(stack, JsonToNBT.getTagFromJson(compound));
            } catch (NBTException e) {
                throw new RuntimeException(e);
            }
        return stack;
    }

    public static String truncateString(String input, int maxLength) {
        if (input == null || input.length() <= maxLength) {
            return input;
        } else {
            return input.substring(0, maxLength - 2) + "..";
        }
    }
}
