package com.majun.amljeju.item;

import com.majun.amljeju.AML_JEJU;
import com.majun.amljeju.Data;
import com.majun.amljeju.manager.IRegistryModel;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

import java.util.UUID;

public class CleaningToolBase extends Item implements IRegistryModel {

    public CleaningToolBase(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setMaxStackSize(1);
        setMaxDamage(40);
        setCreativeTab(Data.tabamljeju);
        ItemInit.ITEMS.add(this);
    }


    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player) {
        // 블록을 캘 때 호출되는 메서드입니다.
        // 여기서 ItemDamage를 1 줄입니다.
        if (!player.world.isRemote && player.canHarvestBlock(player.world.getBlockState(pos))) {
            itemstack.damageItem(1, player);
        }

        return super.onBlockStartBreak(itemstack, pos, player);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {

        if(stack.getItem().getUnlocalizedName().contains("fly_swatter")){
            stack.damageItem(1, attacker);
            if(!stack.hasTagCompound()){
                AttributeModifier modifierDamage = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 5.0D, 0);
                AttributeModifier modifierSpeed = new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", 1.6D, 0);
                stack.addAttributeModifier(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), modifierDamage, EntityEquipmentSlot.MAINHAND);
                stack.addAttributeModifier(SharedMonsterAttributes.ATTACK_SPEED.getName(), modifierSpeed, EntityEquipmentSlot.MAINHAND);
            }
        }

        return super.hitEntity(stack, target, attacker);
    }

    public void registerModel() {
        AML_JEJU.instance.proxy.registerModel(this);
    }

}
