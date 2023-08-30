package com.majun.amljeju.network;

import com.majun.amljeju.item.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import java.util.Map;
import java.util.function.Supplier;

public class SmeltButton {
    public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
        if (dependencies.get("entity") == null) {
            System.err.println("Failed to load dependency entity for procedure Sss!");
            return;
        }
        Entity entity = (Entity) dependencies.get("entity");
        if (entity instanceof EntityPlayerMP) {
            Container _current = ((EntityPlayerMP) entity).openContainer;
            if (_current instanceof Supplier) {
                Object invobj = ((Supplier) _current).get();
                if (invobj instanceof Map) {
                    if(Math.random() < 0.5){
                        if(((Slot) ((Map) invobj).get(0)).getStack().getItem().equals(Items.IRON_INGOT)){
                            ((Slot) ((Map) invobj).get(1)).putStack(new ItemStack(ItemInit.SMELTED_IRON, 1));
                        }else if(((Slot) ((Map) invobj).get(0)).getStack().getItem().equals(Items.GOLD_INGOT)){
                            ((Slot) ((Map) invobj).get(1)).putStack(new ItemStack(ItemInit.SMELTED_GOLD, 1));
                        }else if(((Slot) ((Map) invobj).get(0)).getStack().getItem().equals(Items.DIAMOND)){
                            ((Slot) ((Map) invobj).get(1)).putStack(new ItemStack(ItemInit.SMELTED_DIAMOND, 1));
                        }
                    }
                    ((Slot) ((Map) invobj).get(0)).putStack(ItemStack.EMPTY);
                    _current.detectAndSendChanges();
                }
            }
        }
    }

}
