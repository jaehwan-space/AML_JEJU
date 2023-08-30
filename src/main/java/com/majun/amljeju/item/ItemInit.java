package com.majun.amljeju.item;

import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemInit {
    public static List<Item> ITEMS = new ArrayList<>();

    public static Item SMELTED_IRON = (new ItemBase("smelted_iron")).setMaxStackSize(64);
    public static Item SMELTED_GOLD = (new ItemBase("smelted_gold")).setMaxStackSize(64);
    public static Item SMELTED_DIAMOND = (new ItemBase("smelted_diamond")).setMaxStackSize(64);

    public static Item LEVE1_MAP_0 = (new ItemBase("level1_map_0")).setMaxStackSize(1);
    public static Item LEVE1_MAP_1 = (new ItemBase("level1_map_1")).setMaxStackSize(1);
    public static Item LEVE1_MAP_2 = (new ItemBase("level1_map_2")).setMaxStackSize(1);
    public static Item LEVE1_MAP_3 = (new ItemBase("level1_map_3")).setMaxStackSize(1);
    public static Item LEVE1_MAP_4 = (new ItemBase("level1_map_4")).setMaxStackSize(1);
    public static Item LEVE2_MAP_0 = (new ItemBase("level2_map_0")).setMaxStackSize(1);
    public static Item LEVE2_MAP_1 = (new ItemBase("level2_map_1")).setMaxStackSize(1);
    public static Item LEVE2_MAP_2 = (new ItemBase("level2_map_2")).setMaxStackSize(1);
    public static Item LEVE2_MAP_3 = (new ItemBase("level2_map_3")).setMaxStackSize(1);
    public static Item LEVE2_MAP_4 = (new ItemBase("level2_map_4")).setMaxStackSize(1);
    public static Item LEVE3_MAP_0 = (new ItemBase("level3_map_0")).setMaxStackSize(1);
    public static Item LEVE3_MAP_1 = (new ItemBase("level3_map_1")).setMaxStackSize(1);
    public static Item LEVE3_MAP_2 = (new ItemBase("level3_map_2")).setMaxStackSize(1);
    public static Item LEVE3_MAP_3 = (new ItemBase("level3_map_3")).setMaxStackSize(1);
    public static Item LEVE3_MAP_4 = (new ItemBase("level3_map_4")).setMaxStackSize(1);


    public static Item STRAWBERRY_SYRUP = (new ItemBase("strawberry_syrup")).setMaxStackSize(64);
    public static Item LEMON_SYRUP = (new ItemBase("lemon_syrup")).setMaxStackSize(64);
    public static Item MANGO_SYRUP = (new ItemBase("mango_syrup")).setMaxStackSize(64);
    public static Item KIWI_SYRUP = (new ItemBase("kiwi_syrup")).setMaxStackSize(64);
    public static Item GRAPE_SYRUP = (new ItemBase("grape_syrup")).setMaxStackSize(64);

    public static Item MAKGEOLLI = (new ItemBase("makgeolli")).setMaxStackSize(64);
    public static Item MAKGEOLLI_CUP = (new ItemBase("makgeolli_cup")).setMaxStackSize(64);
    public static Item MAKGEOLLI_PACKAGE = (new ItemBase("makgeolli_package")).setMaxStackSize(64);

    public static Item CLEANING_BRUSH = (new CleaningToolBase("cleaning_brush"));
    public static Item DUSTING_BRUSH = (new CleaningToolBase("dusting_brush"));
    public static Item TONGS = (new CleaningToolBase("tongs"));
    public static Item FLOOR_BRUSH = (new CleaningToolBase("floor_brush"));
    public static Item FLY_SWATTER = (new CleaningToolBase("fly_swatter"));

    public static Item POOP = (new ItemBase("poop")).setMaxStackSize(64);

    public static Item GACHA_BOOK = (new ItemBase("gacha_book")).setMaxStackSize(1);
    public static Item AIRPLANE_TICKET = (new ItemBase("airplane_ticket")).setMaxStackSize(1);
}
