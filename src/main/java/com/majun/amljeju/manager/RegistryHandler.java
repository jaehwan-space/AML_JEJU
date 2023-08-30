package com.majun.amljeju.manager;

import com.majun.amljeju.Data;
import com.majun.amljeju.block.BlockInit;
import com.majun.amljeju.item.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = Data.MODID)
public class RegistryHandler {
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for (Item item : ItemInit.ITEMS)
            event.getRegistry().register(item);
        for (Block block : BlockInit.BLOCKS)
            event.getRegistry().register((new ItemBlock(block)).setRegistryName(block.getRegistryName()));
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        for (Block block : BlockInit.BLOCKS)
            event.getRegistry().register(block);
    }

    @SubscribeEvent
    public static void registerModelRenders(ModelRegistryEvent event) {
        for (Item item : ItemInit.ITEMS) {
            if (item instanceof IRegistryModel)
                ((IRegistryModel)item).registerModel();
        }
        for (Block block : BlockInit.BLOCKS) {
            if (block instanceof IRegistryModel)
                ((IRegistryModel)block).registerModel();
        }
    }
}
