package com.majun.amljeju.gui;

import com.majun.amljeju.AML_JEJU;
import com.majun.amljeju.manager.DrawManager;
import com.majun.amljeju.manager.Utils;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuiShop extends GuiScreen {
    private final Map<Integer, Integer> SHOP_DATA = new HashMap<>();

    public int page;

    public GuiShop(/*NBTTagCompound compound*/) {
//        for (String key : compound.getKeySet()) {
//            int number = Integer.parseInt(key);
//            SHOP_DATA.put(Integer.valueOf(number), Integer.valueOf(compound.getInteger(key)));
//        }
//        if (!SHOP_DATA.isEmpty()) {
//            System.out.println("데이터 로드 성공");
//        } else {
//            System.out.println("데이터 로드 실패");
//        }
    }

    public void initGui() {
        page = 0;
        AML_JEJU.guistat = 1;
//        addButton(new GuiButtonImage(0, (int)(76.0F * AML_JEJU.guiScale), height / 2, (int)(76.0F * AML_JEJU.guiScale), (int)(76.0F * AML_JEJU.guiScale), "left_arrow"));
//        addButton(new GuiButtonImage(1, (int)(width - 76.0F * AML_JEJU.guiScale * 2.0F), height / 2, (int)(76.0F * AML_JEJU.guiScale), (int)(76.0F * AML_JEJU.guiScale), "right_arrow"));
        addButton(new GuiButtonImage(2, width / 2 - (int)(699.0F * AML_JEJU.guiScale), height / 2 - (int)(302.0F * AML_JEJU.guiScale), (int)(164.0F * AML_JEJU.guiScale), (int)(103.0F * AML_JEJU.guiScale), "shop/purchase"));
        addButton(new GuiButtonImage(3, this.width / 2 - (int)(212.0F * AML_JEJU.guiScale), this.height / 2 - (int)(302.0F * AML_JEJU.guiScale), (int)(164.0F * AML_JEJU.guiScale), (int)(103.0F * AML_JEJU.guiScale), "shop/purchase"));
        addButton(new GuiButtonImage(4, this.width / 2 + (int)(274.0F * AML_JEJU.guiScale), this.height / 2 - (int)(302.0F * AML_JEJU.guiScale), (int)(164.0F * AML_JEJU.guiScale), (int)(103.0F * AML_JEJU.guiScale), "shop/sale"));
        addButton((new GuiButtonImage(5, this.width / 2 - (int)(699.0F * AML_JEJU.guiScale), this.height / 2 - (int)(302.0F * AML_JEJU.guiScale), (int)(164.0F * AML_JEJU.guiScale), (int)(103.0F * AML_JEJU.guiScale), "shop/sale")).setVisible(false));
        addButton((new GuiButtonImage(6, this.width / 2 - (int)(212.0F * AML_JEJU.guiScale), this.height / 2 - (int)(302.0F * AML_JEJU.guiScale), (int)(164.0F * AML_JEJU.guiScale), (int)(103.0F * AML_JEJU.guiScale), "shop/purchase")).setVisible(false));
        addButton((new GuiButtonImage(7, this.width / 2 + (int)(274.0F * AML_JEJU.guiScale), this.height / 2 - (int)(302.0F * AML_JEJU.guiScale), (int)(164.0F * AML_JEJU.guiScale), (int)(103.0F * AML_JEJU.guiScale), "shop/sale")).setVisible(false));
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        EntityPlayerSP entityPlayerSP = mc.player;
        DrawManager.drawTexture("shop/shop_background", width / 2 - width / 2.7, height / 13 , 1.0D, width * 0.7265F, height * 0.8333F);
        String moneyString = Utils.convertMoneyToCommas(213000);
        DrawManager.drawRightCenteredString(moneyString + "원", width * 0.8229F, height * 0.1898F, (12.0F * AML_JEJU.guiScale), new Color(0, 0, 0), false);
//        switch (this.page) {
//            case 0:
//                setVisibleButtons(true, new int[] { 2, 3, 4 });
//                setVisibleButtons(false, new int[] { 5, 6, 7, 8 });
//                break;
//            case 1:
//                setVisibleButtons(true, new int[] { 5, 6, 7 });
//                setVisibleButtons(false, new int[] { 2, 3, 4, 8 });
//                break;
//            case 2:
//                setVisibleButtons(true, new int[] { 8 });
//                setVisibleButtons(false, new int[] { 2, 3, 4, 5, 6, 7 });
//                DrawManager.drawTexture("button/shop_nothing.png", (this.width / 2 - (int)(212.0F * AML_JEJU.guiScale)), (this.height / 2 - (int)(302.0F * AML_JEJU.guiScale)), (int)(448.0F * AML_JEJU.guiScale), (int)(704.0F * AML_JEJU.guiScale), 1.0D);
//                DrawManager.drawTexture("button/shop_nothing.png", (this.width / 2 + (int)(274.0F * AML_JEJU.guiScale)), (this.height / 2 - (int)(302.0F * AML_JEJU.guiScale)), (int)(448.0F * AML_JEJU.guiScale), (int)(704.0F * AML_JEJU.guiScale), 1.0D);
//                break;
//        }
        super.drawScreen(mouseX, mouseY, partialTicks);
        for (GuiButton button : buttonList) {
            boolean hovered = (mouseX >= button.x && mouseY >= button.y && mouseX < button.x + button.width && mouseY < button.y + button.height);
            if (button.visible && hovered && button.id != 0 && button.id != 1) {
                List<String> tooltips = new ArrayList<>();
                tooltips.add(Utils.convertColor("&l< 테스트 >"));
                int hasCount = Utils.getAllItemCount(((EntityPlayer)entityPlayerSP).inventory, Item.getItemById(1));
                tooltips.add(Utils.convertColor("현재 아이템 갯수 : &a" + hasCount + "개"));
                drawHoveringText(tooltips, mouseX, mouseY);
            }
        }
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        for (GuiButton button : buttonList) {
            boolean hovered = (mouseX >= button.x && mouseY >= button.y && mouseX < button.x + button.width && mouseY < button.y + button.height);
            if (button.visible && hovered) {
                int amount;
                switch (mouseButton) {
//                    case 0:
//                        switch (button.id) {
//                            case 0:
//                                if (page > 0)
//                                    page--;
//                                continue;
//                            case 1:
//                                if (page < 2)
//                                    page++;
//                                continue;
//                        }
//                        amount = ((Integer)SHOP_DATA.get(Integer.valueOf(button.id - 2))).intValue();
//                        if (amount != 0 && Data.money - amount >= 0) {
//                            Data.money -= amount;
//                            NetworkManager.sendToServer((IMessage)new PacketBuyStock(button.id - 2, amount));
//                        }
//                    case 1:
//                        if (button.id != 0 && button.id != 1) {
//                            amount = ((Integer)this.SHOP_DATA.get(Integer.valueOf(button.id - 2))).intValue();
//                            int hasCount = Utils.getAllItemCount(this.field_146297_k.player.inventory, Utils.getStockItem(button.id - 2));
//                            if (amount != 0 && hasCount != 0) {
//                                Data.money += amount;
//                                NetworkManager.sendToServer((IMessage)new PacketSellStock(button.id - 2, amount));
//                            }
//                        }
                }
            }
        }
    }

    public void onGuiClosed() {
        AML_JEJU.guistat = 0;
        SHOP_DATA.clear();
    }

    public boolean doesGuiPauseGame() {
        return false;
    }

    public GuiButtonImage b(int id) {
        if (buttonList.get(id) instanceof GuiButtonImage)
            return (GuiButtonImage) buttonList.get(id);
        return null;
    }

    public void setVisibleButtons(boolean visible, int... id) {
        for (int i : id)
            b(i).setVisible(visible);
    }
}
