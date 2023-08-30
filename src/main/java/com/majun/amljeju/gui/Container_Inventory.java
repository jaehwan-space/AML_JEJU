package com.majun.amljeju.gui;

import com.majun.amljeju.AML_JEJU;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Container_Inventory extends Container {

    private static final EntityEquipmentSlot[] VALID_EQUIPMENT_SLOTS = new EntityEquipmentSlot[] { EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET };

    public InventoryCrafting craftMatrix = new InventoryCrafting(this, 2, 2);

    public InventoryCraftResult craftResult = new InventoryCraftResult();

    private World worldObj;

    private final EntityPlayer player;
    public Container_Inventory(InventoryPlayer inventoryPlayer, final EntityPlayer player) {
        this.player = player;
        craftMatrix = new InventoryCrafting(this, 2, 2);
        craftResult = new InventoryCraftResult();

        addSlotToContainer((Slot)new SlotCrafting(player, craftMatrix, (IInventory)craftResult, 0, 187, 26));  //제작된 아이템 위치 설정
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++)
                addSlotToContainer(new Slot((IInventory)craftMatrix, j + i * 2, 128 + j * 20, 16 + i * 19));  //2X2 조합 위치 선정
        }

        final EntityEquipmentSlot entityequipmentslot = VALID_EQUIPMENT_SLOTS[0];
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 39, 51, -7) {
            public int getSlotStackLimit() {
                return 1;
            }

            public boolean isItemValid(ItemStack stack) {
                return stack.getItem().isValidArmor(stack, entityequipmentslot, (Entity)player);
            }

            public boolean canTakeStack(EntityPlayer playerIn) {
                ItemStack itemstack = getStack();
                return ((itemstack.isEmpty() || playerIn.isCreative() || !EnchantmentHelper.hasBindingCurse(itemstack)) && super.canTakeStack(playerIn));
            }

            @SideOnly(Side.CLIENT)
            public String getSlotTexture() {
                return ItemArmor.EMPTY_SLOT_NAMES[entityequipmentslot.getIndex()];
            }
        });


        final EntityEquipmentSlot entityequipmentslot1 = VALID_EQUIPMENT_SLOTS[1];
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 38, 51, 15) {
            public int getSlotStackLimit() {
                return 1;
            }

            public boolean isItemValid(ItemStack stack) {
                return stack.getItem().isValidArmor(stack, entityequipmentslot1, (Entity)player);
            }

            public boolean canTakeStack(EntityPlayer playerIn) {
                ItemStack itemstack = getStack();
                return ((itemstack.isEmpty() || playerIn.isCreative() || !EnchantmentHelper.hasBindingCurse(itemstack)) && super.canTakeStack(playerIn));
            }

            @SideOnly(Side.CLIENT)
            public String getSlotTexture() {
                return ItemArmor.EMPTY_SLOT_NAMES[entityequipmentslot1.getIndex()];
            }
        });


        final EntityEquipmentSlot entityequipmentslot2 = VALID_EQUIPMENT_SLOTS[2];
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 37, 51, 38) {
            public int getSlotStackLimit() {
                return 1;
            }

            public boolean isItemValid(ItemStack stack) {
                return stack.getItem().isValidArmor(stack, entityequipmentslot2, (Entity)player);
            }

            public boolean canTakeStack(EntityPlayer playerIn) {
                ItemStack itemstack = getStack();
                return ((itemstack.isEmpty() || playerIn.isCreative() || !EnchantmentHelper.hasBindingCurse(itemstack)) && super.canTakeStack(playerIn));
            }

            @SideOnly(Side.CLIENT)
            public String getSlotTexture() {
                return ItemArmor.EMPTY_SLOT_NAMES[entityequipmentslot2.getIndex()];
            }
        });


        final EntityEquipmentSlot entityequipmentslot3 = VALID_EQUIPMENT_SLOTS[3];
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 36, 51, 61) {
            public int getSlotStackLimit() {
                return 1;
            }

            public boolean isItemValid(ItemStack stack) {
                return stack.getItem().isValidArmor(stack, entityequipmentslot3, (Entity)player);
            }

            public boolean canTakeStack(EntityPlayer playerIn) {
                ItemStack itemstack = getStack();
                return ((itemstack.isEmpty() || playerIn.isCreative() || !EnchantmentHelper.hasBindingCurse(itemstack)) && super.canTakeStack(playerIn));
            }

            @SideOnly(Side.CLIENT)
            public String getSlotTexture() {
                return ItemArmor.EMPTY_SLOT_NAMES[entityequipmentslot3.getIndex()];
            }
        });


        int row1_y = 172;
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 0, 115, row1_y));
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 1, 135, row1_y));
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 2, 156, row1_y));
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 3, 176, row1_y));
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 4, 196, row1_y));
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 5, 216, row1_y));
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 6, 237, row1_y));
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 7, 257, row1_y));
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 8, 277, row1_y));
        int row2_y = 107;
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 9, 115, row2_y));
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 10, 135, row2_y));
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 11, 156, row2_y));
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 12, 176, row2_y));
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 13, 196, row2_y));
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 14, 216, row2_y));
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 15, 237, row2_y));
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 16, 257, row2_y));
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 17, 277, row2_y));
        int row3_y = 127;
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 18, 115, row3_y));
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 19, 135, row3_y));
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 20, 156, row3_y));
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 21, 176, row3_y));
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 22, 196, row3_y));
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 23, 216, row3_y));
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 24, 237, row3_y));
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 25, 257, row3_y));
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 26, 277, row3_y));
        int row4_y = 147;
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 27, 115, row4_y));
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 28, 135, row4_y));
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 29, 156, row4_y));
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 30, 176, row4_y));
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 31, 196, row4_y));
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 32, 216, row4_y));
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 33, 237, row4_y));
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 34, 257, row4_y));
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 35, 277, row4_y));
        addSlotToContainer(new Slot((IInventory)inventoryPlayer, 40, 256, 71));  //왼손 인벤토리
        onCraftMatrixChanged((IInventory)craftMatrix);
    }

    public void onContainerClosed(EntityPlayer playerIn) {
        super.onContainerClosed(playerIn);
        craftResult.clear();
        if (!playerIn.world.isRemote)
            clearContainer(playerIn, playerIn.world, (IInventory)craftMatrix);
    }

    public void onCraftMatrixChanged(IInventory inventoryIn) {
        slotChangedCraftingGrid(player.world, player, craftMatrix, craftResult);
    }

    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            EntityEquipmentSlot entityequipmentslot = EntityLiving.getSlotForItemStack(itemstack);
            if (index == 0) {
                if (!mergeItemStack(itemstack1, 9, 45, true))
                    return ItemStack.EMPTY;
                slot.onSlotChange(itemstack1, itemstack);
            } else if (index >= 1 && index < 5) {
                if (!mergeItemStack(itemstack1, 9, 45, false))
                    return ItemStack.EMPTY;
            } else if (index >= 5 && index < 9) {
                if (!mergeItemStack(itemstack1, 9, 45, false))
                    return ItemStack.EMPTY;
            } else if (entityequipmentslot.getSlotType() == EntityEquipmentSlot.Type.ARMOR && !((Slot)inventorySlots.get(8 - entityequipmentslot.getIndex())).getHasStack()) {
                int i = 8 - entityequipmentslot.getIndex();
                if (!mergeItemStack(itemstack1, i, i + 1, false))
                    return ItemStack.EMPTY;
            } else if (entityequipmentslot == EntityEquipmentSlot.OFFHAND && !((Slot)inventorySlots.get(45)).getHasStack()) {
                if (!mergeItemStack(itemstack1, 45, 46, false))
                    return ItemStack.EMPTY;
            } else if (index >= 9 && index < 36) {
                if (!mergeItemStack(itemstack1, 36, 45, false))
                    return ItemStack.EMPTY;
            } else if (index >= 36 && index < 45) {
                if (!mergeItemStack(itemstack1, 9, 36, false))
                    return ItemStack.EMPTY;
            } else if (!mergeItemStack(itemstack1, 9, 45, false)) {
                return ItemStack.EMPTY;
            }
            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
            if (itemstack1.getCount() == itemstack.getCount())
                return ItemStack.EMPTY;
            ItemStack itemstack2 = slot.onTake(playerIn, itemstack1);
            if (index == 0)
                playerIn.dropItem(itemstack2, false);
        }
        return itemstack;
    }

    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }
}