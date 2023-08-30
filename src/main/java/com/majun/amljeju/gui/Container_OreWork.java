package com.majun.amljeju.gui;

import com.majun.amljeju.item.ItemInit;
import com.majun.amljeju.network.NetworkManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;


public class Container_OreWork extends Container implements Supplier<Map<Integer, Slot>> {
    public static IInventory internal;
    private EntityPlayer player;
    private World world;
    private int x, y, z;
    private Map<Integer, Slot> customSlots = new HashMap<>();


    public static ItemStack GetItemStack(){
        return internal.getStackInSlot(0);
    }
    public static void ClearItem(){
        internal.removeStackFromSlot(0);
    }
    public static void SetItem(int value){
        if(value == 0)
            internal.setInventorySlotContents(1, new ItemStack(ItemInit.SMELTED_IRON, 1));
        else if(value == 1)
            internal.setInventorySlotContents(1, new ItemStack(ItemInit.SMELTED_GOLD, 1));
        else if(value == 2)
            internal.setInventorySlotContents(1, new ItemStack(ItemInit.SMELTED_DIAMOND, 1));
    }

    private void addPlayerInventory(InventoryPlayer playerInventory, int xPos, int yPos) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                if(j > 4)
                    addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, xPos + j * 24 - 1, yPos + i * 24 - 19));
                else
                    addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, xPos + j * 24, yPos + i * 24 - 19));
            }
        }
        for (int i = 0; i < 9; i++) {
            if(i > 4)
                addSlotToContainer(new Slot(playerInventory, i, xPos + i * 24 - 1, yPos + 58));
            else
                addSlotToContainer(new Slot(playerInventory, i, xPos + i * 24, yPos + 58));
        }
    }

    private void addCustomSlots() {
        customSlots.put(0, addSlotToContainer(new Slot(internal, 0, 44, 2) {
            public int getSlotStackLimit() {
                return 1;
            }

            public boolean isItemValid(ItemStack stack) {
                return stack.getItem() == Items.IRON_INGOT || stack.getItem() == Items.GOLD_INGOT || stack.getItem() == Items.DIAMOND;
            }
        }));
        customSlots.put(1, addSlotToContainer(new Slot(internal, 1, 117, 2) {
            public boolean isItemValid(ItemStack stack) {
                return stack.getItem().equals(ItemInit.SMELTED_IRON)  || stack.getItem().equals(ItemInit.SMELTED_GOLD) || stack.getItem().equals(ItemInit.SMELTED_DIAMOND);
            }
        }));
    }

    public Container_OreWork(World world, int x, int y, int z, EntityPlayer player) {
        this.player = player;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        internal = new InventoryBasic("Chest", true, 2);

        TileEntity ent = world.getTileEntity(new BlockPos(x, y, z));
        if (ent instanceof IInventory)
            this.internal = (IInventory) ent;

        addCustomSlots();
        addPlayerInventory(player.inventory, -15, 99);
    }

    public Map<Integer, Slot> get() {
        return customSlots;
    }


    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = (Slot) inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index < 2) {
                if (!this.mergeItemStack(itemstack1, 2, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(itemstack1, itemstack);
            } else if (!this.mergeItemStack(itemstack1, 0, 2, false)) {
                if (index < 2 + 27) {
                    if (!this.mergeItemStack(itemstack1, 2 + 27, this.inventorySlots.size(), true)) {
                        return ItemStack.EMPTY;
                    }
                } else {
                    if (!this.mergeItemStack(itemstack1, 2, 2 + 27, false)) {
                        return ItemStack.EMPTY;
                    }
                }
                return ItemStack.EMPTY;
            }
            if (itemstack1.getCount() == 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(playerIn, itemstack1);
        }
        return itemstack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return internal.isUsableByPlayer(player);
    }

    @Override
    protected boolean mergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean reverseDirection) {
        boolean flag = false;
        int i = startIndex;
        if (reverseDirection) {
            i = endIndex - 1;
        }
        if (stack.isStackable()) {
            while (!stack.isEmpty()) {
                if (reverseDirection) {
                    if (i < startIndex) {
                        break;
                    }
                } else if (i >= endIndex) {
                    break;
                }
                Slot slot = this.inventorySlots.get(i);
                ItemStack itemstack = slot.getStack();
                if (slot.isItemValid(itemstack) && !itemstack.isEmpty() && itemstack.getItem() == stack.getItem()
                        && (!stack.getHasSubtypes() || stack.getMetadata() == itemstack.getMetadata())
                        && ItemStack.areItemStackTagsEqual(stack, itemstack)) {
                    int j = itemstack.getCount() + stack.getCount();
                    int maxSize = Math.min(slot.getSlotStackLimit(), stack.getMaxStackSize());
                    if (j <= maxSize) {
                        stack.setCount(0);
                        itemstack.setCount(j);
                        slot.putStack(itemstack);
                        flag = true;
                    } else if (itemstack.getCount() < maxSize) {
                        stack.shrink(maxSize - itemstack.getCount());
                        itemstack.setCount(maxSize);
                        slot.putStack(itemstack);
                        flag = true;
                    }
                }
                if (reverseDirection) {
                    --i;
                } else {
                    ++i;
                }
            }
        }
        if (!stack.isEmpty()) {
            if (reverseDirection) {
                i = endIndex - 1;
            } else {
                i = startIndex;
            }
            while (true) {
                if (reverseDirection) {
                    if (i < startIndex) {
                        break;
                    }
                } else if (i >= endIndex) {
                    break;
                }
                Slot slot1 = this.inventorySlots.get(i);
                ItemStack itemstack1 = slot1.getStack();
                if (itemstack1.isEmpty() && slot1.isItemValid(stack)) {
                    if (stack.getCount() > slot1.getSlotStackLimit()) {
                        slot1.putStack(stack.splitStack(slot1.getSlotStackLimit()));
                    } else {
                        slot1.putStack(stack.splitStack(stack.getCount()));
                    }
                    slot1.onSlotChanged();
                    flag = true;
                    break;
                }
                if (reverseDirection) {
                    --i;
                } else {
                    ++i;
                }
            }
        }
        return flag;
    }


    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        super.onContainerClosed(playerIn);
        if ((internal instanceof InventoryBasic) && (playerIn instanceof IInventory)) {
            clearContainer(playerIn, playerIn.world, internal);
        }
    }


    private void slotChanged(int slotid, int ctype, int meta) {
        if (this.world != null && this.world.isRemote) {
            NetworkManager.sendToServer(new GUISlotChangedMessage(slotid, x, y, z, ctype, meta));
            handleSlotAction(player, slotid, ctype, meta, x, y, z);
        }
    }

    private static void handleSlotAction(EntityPlayer entity, int slotID, int changeType, int meta, int x, int y, int z) {
        World world = entity.world;
        // security measure to prevent arbitrary chunk generation
        if (!world.isBlockLoaded(new BlockPos(x, y, z)))
            return;
    }


    public static class GUISlotChangedMessage implements IMessage {
        int slotID, x, y, z, changeType, meta;
        public GUISlotChangedMessage() {
        }

        public GUISlotChangedMessage(int slotID, int x, int y, int z, int changeType, int meta) {
            this.slotID = slotID;
            this.x = x;
            this.y = y;
            this.z = z;
            this.changeType = changeType;
            this.meta = meta;
        }

        @Override
        public void toBytes(io.netty.buffer.ByteBuf buf) {
            buf.writeInt(slotID);
            buf.writeInt(x);
            buf.writeInt(y);
            buf.writeInt(z);
            buf.writeInt(changeType);
            buf.writeInt(meta);
        }

        @Override
        public void fromBytes(io.netty.buffer.ByteBuf buf) {
            slotID = buf.readInt();
            x = buf.readInt();
            y = buf.readInt();
            z = buf.readInt();
            changeType = buf.readInt();
            meta = buf.readInt();
        }
    }

    public static class GUISlotChangedMessageHandler implements IMessageHandler<GUISlotChangedMessage, IMessage> {
        @Override
        public IMessage onMessage(GUISlotChangedMessage message, MessageContext context) {
            EntityPlayerMP entity = context.getServerHandler().player;
            entity.getServerWorld().addScheduledTask(() -> {
                int slotID = message.slotID;
                int changeType = message.changeType;
                int meta = message.meta;
                int x = message.x;
                int y = message.y;
                int z = message.z;
                handleSlotAction(entity, slotID, changeType, meta, x, y, z);
            });
            return null;
        }
    }

}