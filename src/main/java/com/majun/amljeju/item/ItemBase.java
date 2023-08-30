package com.majun.amljeju.item;

import com.majun.amljeju.AML_JEJU;
import com.majun.amljeju.Data;
import com.majun.amljeju.manager.IRegistryModel;
import com.majun.amljeju.manager.Utils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBase extends Item implements IRegistryModel {
    public ItemBase(String name) {
//        setHasSubtypes(true); // 메타 데이터를 사용하도록 설정
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Data.tabamljeju);
        ItemInit.ITEMS.add(this);
    }

    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (stack.getUnlocalizedName().contains("map")) {

            NBTTagCompound nbt = stack.getTagCompound(); // NBT 태그 가져오기 또는 생성하기

            if (nbt == null) {
                nbt = new NBTTagCompound();
                stack.setTagCompound(nbt);
            }
            if (!nbt.hasKey("uses")) {
                if(getUnlocalizedName().contains("level1_map")){
                    nbt.setInteger("uses", 1);
                }
                if(getUnlocalizedName().contains("level2_map")){
                    nbt.setInteger("uses", 3);
                }
                if(getUnlocalizedName().contains("level3_map")){
                    nbt.setInteger("uses", 9999);
                }
            }

            stack.setTagCompound(nbt);
            tooltip.add(" ");
            if (Keyboard.isKeyDown(42)) {
                int remain_number = stack.getTagCompound().getInteger("uses");
                if(remain_number == 9999)
                    tooltip.add(Utils.convertColor("&7남은 이동 가능한 횟수 : &e무제한"));
                else
                    tooltip.add(Utils.convertColor("&7남은 이동 가능한 횟수 : &e" + remain_number + "&7개"));
            } else {
                tooltip.add(Utils.convertColor("&7남은 횟수를 확인할려면 &e<SHIFT>&7키를 눌러주세요."));
            }
        }

        if (stack.getUnlocalizedName().contains("gacha_book")) {
            tooltip.add(Utils.convertColor("&7우클릭 시 인챈트북을 지급합니다."));
        }
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!worldIn.isRemote && handIn == EnumHand.MAIN_HAND && playerIn instanceof EntityPlayerMP && getUnlocalizedName().contains("map")) {
            ItemStack itemStack = playerIn.getHeldItem(handIn);

            NBTTagCompound nbt = itemStack.getTagCompound(); // NBT 태그 가져오기 또는 생성하기

            int uses = nbt.getInteger("uses");

            if(uses == 9999){
                worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 1.0f, 1.0f);
            }else if (uses > 0) {
                worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 1.0f, 1.0f);

                uses--;
                nbt.setInteger("uses", uses);
                itemStack.setTagCompound(nbt);

                if (uses == 0) {
                    playerIn.setHeldItem(handIn, ItemStack.EMPTY); // 아이템 제거
                }
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

/*
    @Override
    public String getUnlocalizedName(ItemStack stack) {
        if(this.getUnlocalizedName().contains("map")) {
            return super.getUnlocalizedName() + "_" + stack.getItemDamage();
        }else{
            return super.getUnlocalizedName();
        }
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if(this.getUnlocalizedName().contains("map")){
            if (this.isInCreativeTab(tab)) {
                for (int metadata = 0; metadata < 5; metadata++) {
                    items.add(new ItemStack(this, 1, metadata)); // 각 메타 데이터의 아이템을 추가
                }
            }
        }else{
            items.add(new ItemStack(this));
        }
    }
*/

    public void registerModel() {
        AML_JEJU.instance.proxy.registerModel(this);
    }
}
