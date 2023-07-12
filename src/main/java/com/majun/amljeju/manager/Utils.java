package com.majun.amljeju.manager;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.text.DecimalFormat;

public class Utils {

    public static String convertMoneyToCommas(int amount) {
        DecimalFormat commas = new DecimalFormat("#,###");
        return commas.format(amount);
    }

    public static String convertColor(String message) {
        return message.replace("&", "§");
    }

    public static int getAllItemCount(InventoryPlayer player, Item item) {
        int count = 0;
        for (int slot = 0; slot < player.getSizeInventory(); slot++) {
            ItemStack stack = player.getStackInSlot(slot);
            if (stack.getItem().equals(item))
                count += stack.getCount();
        }
        return count;
    }
}
