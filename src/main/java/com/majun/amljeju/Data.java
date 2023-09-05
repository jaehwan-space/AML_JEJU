package com.majun.amljeju;

import com.majun.amljeju.block.BlockInit;
import com.majun.amljeju.item.ItemInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import java.util.Random;

public class Data {
    public static final String MODID = "amljeju";
    public static final String NAME = "AML_JEJU";
    public static final String VERSION = "1.4";
    public static int guistat = 0;  // 0:기본 HUD가 표시되는 상태, 1:HUD가 표시되지 않고 다른 GUI가 표시됨(상점)
    public static int call_stat = 0; //0: 기본 상태, 1:통화 요청이 들어온 상태
    public static int Money = 0;
    public static String Job = "";
    public static String Player_Name = "";
    public static String CallRequest_Name = "";
    static Random random = new Random();
    // 0부터 2까지의 랜덤한 정수 생성
    public static int randomNum = random.nextInt(8);

    public static final CreativeTabs tabamljeju = new CreativeTabs("tabamljeju") {
        public ItemStack getTabIconItem() {
            return new ItemStack(BlockInit.POOP_BLOCK);
        }
    };
}
