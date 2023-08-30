package com.majun.amljeju.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class BlockInit {
    public static List<Block> BLOCKS = new ArrayList<>();

    public static Block HAIR = new CarpetBase("hair", Material.CARPET).setHardness(5.0F);
    public static Block MOLD = new CarpetBase("mold", Material.CARPET).setHardness(5.0F);
    public static Block STAIN = new CarpetBase("stain", Material.CARPET).setHardness(5.0F);
    public static Block POOP_BLOCK = new BlockBase("poop_block", Material.GROUND).setHardness(4F);
    public static Block DUST = new BlockBase("dust", Material.GROUND).setHardness(4.0F);
    public static Block SMELTINGMACHINE = new BlockSMELTINGMACHINE().setHardness(4.0F);
}
