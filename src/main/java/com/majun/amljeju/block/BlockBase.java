package com.majun.amljeju.block;

import com.majun.amljeju.AML_JEJU;
import com.majun.amljeju.Data;
import com.majun.amljeju.item.ItemInit;
import com.majun.amljeju.manager.IRegistryModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBase extends Block implements IRegistryModel {
    public BlockBase(String name, Material mt) {
        super(mt);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Data.tabamljeju);
        BlockInit.BLOCKS.add(this);
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te, ItemStack stack) {
        if(state.getBlock().getUnlocalizedName().contains("tile.poop_block")){
            if (!world.isRemote) {
                spawnAsEntity(world, pos, new ItemStack(ItemInit.POOP, 1)); // stack 부분 바꾸면 다른 아이템으로 드롭 가능
                world.destroyBlock(pos, false);
            }
        }
        if(state.getBlock().getUnlocalizedName().contains("tile.dust")){
            if (!world.isRemote && stack.getItem().getUnlocalizedName().contains("item.dusting_brush")) {
                spawnAsEntity(world, pos, new ItemStack(this)); // stack 부분 바꾸면 다른 아이템으로 드롭 가능
                world.destroyBlock(pos, false);
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer(){  //이미지의 투명 부분 표현(해당 구문 없으면 투명이 하얀색으로 표시됨)
        return BlockRenderLayer.TRANSLUCENT;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }


    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public void registerModel() {
        AML_JEJU.instance.proxy.registerModel(Item.getItemFromBlock(this));
    }
}