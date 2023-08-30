package com.majun.amljeju.gui;

import com.majun.amljeju.Data;
import com.majun.amljeju.manager.DrawManager;
import com.majun.amljeju.manager.Utils;
import com.majun.amljeju.network.NetworkManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Gui_Shop extends GuiScreen {
    private int page;
    private static int max_page;
    private static String[] InfoData;
    private static Integer TotalItemAmount = null;
    private static String[] ItemsData = null;

    public static void UpdateShop(String Data){
        if (InfoData != null && InfoData.length > 1 && InfoData[1].equalsIgnoreCase(Data.split("\\^\\^\\^")[1])) { // 상점 이름 똑같을 경우 업데이트
            InfoData = Data.split("\\^\\^\\^");
            ItemsData = InfoData[3].split("///");

            max_page = (TotalItemAmount-1) / 6;
        }
    }


    public Gui_Shop(String Data) {
        InfoData = Data.split("\\^\\^\\^");

        TotalItemAmount = Integer.valueOf(InfoData[2]);
        ItemsData = InfoData[3].split("///");

        max_page = (TotalItemAmount-1) / 6;
    }

    public void initGui() {
        page = 0;
        Data.guistat = 1;
        addButton(new GuiButtonImage(0, (int)(width * 0.4458F), (int)(height * 0.8277F), (int)(width * 0.0187F), (int)(height * 0.0240F), "shop/left_arrow"));
        addButton(new GuiButtonImage(1, (int)(width * 0.5354F), (int)(height * 0.8277F), (int)(width * 0.0187F), (int)(height * 0.0240F), "shop/right_arrow"));

        for(int i=2; i<TotalItemAmount+2; i++){
            Boolean visible = true;
            if(i >= 8)  // 1페이지 넘어가는 버튼 숨기기
                visible = false;

            if((i-2)%6 == 0) {
                if(ItemsData[i-2].split("@@@")[1].contains("구매"))
                    addButton(new GuiButtonImage(i, (int)(width * 0.4192F), (int)(height * 0.4342F), (int)(width * 0.0427F), (int)(height * 0.0490F), "shop/purchase").setVisible(visible));
                else if(ItemsData[i-2].split("@@@")[1].contains("판매"))
                    addButton(new GuiButtonImage(i, (int)(width * 0.4192F), (int)(height * 0.4342F), (int)(width * 0.0427F), (int)(height * 0.0490F), "shop/sale").setVisible(visible));
            }else if((i-2)%6 == 1){
                if(ItemsData[i-2].split("@@@")[1].contains("구매"))
                    addButton(new GuiButtonImage(i, (int)(width * 0.7239F), (int)(height * 0.4342F), (int)(width * 0.0427F), (int)(height * 0.0490F), "shop/purchase").setVisible(visible));
                else if(ItemsData[i-2].split("@@@")[1].contains("판매"))
                    addButton(new GuiButtonImage(i, (int)(width * 0.7239F), (int)(height * 0.4342F), (int)(width * 0.0427F), (int)(height * 0.0490F), "shop/sale").setVisible(visible));
            }else if((i-2)%6 == 2){
                if(ItemsData[i-2].split("@@@")[1].contains("구매"))
                    addButton(new GuiButtonImage(i, (int)(width * 0.4192F), (int)(height * 0.5879F), (int)(width * 0.0427F), (int)(height * 0.0490F),"shop/purchase").setVisible(visible));
                else if(ItemsData[i-2].split("@@@")[1].contains("판매"))
                    addButton(new GuiButtonImage(i, (int)(width * 0.4192F), (int)(height * 0.5879F), (int)(width * 0.0427F), (int)(height * 0.0490F),"shop/sale").setVisible(visible));
            }else if((i-2)%6 == 3){
                if(ItemsData[i-2].split("@@@")[1].contains("구매"))
                    addButton((new GuiButtonImage(i, (int)(width * 0.7239F), (int)(height * 0.5879F), (int)(width * 0.0427F), (int)(height * 0.0490F),"shop/purchase")).setVisible(visible));
                else if(ItemsData[i-2].split("@@@")[1].contains("판매"))
                    addButton((new GuiButtonImage(i, (int)(width * 0.7239F), (int)(height * 0.5879F), (int)(width * 0.0427F), (int)(height * 0.0490F),"shop/sale")).setVisible(visible));
            }else if((i-2)%6 == 4){
                if(ItemsData[i-2].split("@@@")[1].contains("구매"))
                    addButton((new GuiButtonImage(i, (int)(width * 0.4192F), (int)(height * 0.7407F), (int)(width * 0.0427F), (int)(height * 0.0490F),"shop/purchase")).setVisible(visible));
                else if(ItemsData[i-2].split("@@@")[1].contains("판매"))
                    addButton((new GuiButtonImage(i, (int)(width * 0.4192F), (int)(height * 0.7407F), (int)(width * 0.0427F), (int)(height * 0.0490F),"shop/sale")).setVisible(visible));
            }else if((i-2)%6 == 5){
                if(ItemsData[i-2].split("@@@")[1].contains("구매"))
                    addButton((new GuiButtonImage(i, (int)(width * 0.7239F), (int)(height * 0.7407F), (int)(width * 0.0427F), (int)(height * 0.0490F),"shop/purchase")).setVisible(visible));
                else if(ItemsData[i-2].split("@@@")[1].contains("판매"))
                    addButton((new GuiButtonImage(i, (int)(width * 0.7239F), (int)(height * 0.7407F), (int)(width * 0.0427F), (int)(height * 0.0490F),"shop/sale")).setVisible(visible));
            }
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        EntityPlayerSP entityPlayerSP = mc.player;
        DrawManager.drawTexture("gui/shop/shop_background", width / 2 - width / 2.7, height / 13 , 0.0D, 1.0D, width * 0.7275F, height * 0.8343F);

        // 돈 출력
        String moneyString = Utils.convertMoneyToCommas(Data.Money);
        if(Minecraft.getMinecraft().displayWidth > 1600)
            DrawManager.drawRightString(moneyString + "원", width * 0.8355F, height * 0.1920F, width * 0.0025F, new Color(0, 0, 0), false);
        else
            DrawManager.drawRightString(moneyString + "원", width * 0.8055F, height * 0.1920F, width * 0.0025F, new Color(0, 0, 0), false);


        // 페이지 출력
        DrawManager.drawCenteredString((page+1) + " / " + (max_page+1), width * 0.498F, height * 0.8283F, width * 0.002F, new Color(96, 77, 23), false);

        // 버튼 숨기기
        List<Integer> visible_buttons = new ArrayList<>();
        List<Integer> hidden_buttons = new ArrayList<>();
        for(int i=0; i<TotalItemAmount; i++){
            if(i/6 == page)
                visible_buttons.add(i+2);
            else
                hidden_buttons.add(i+2);
        }
        if(visible_buttons.size() != 0)
            setVisibleButtons(true, Utils.listToIntArray(visible_buttons));
        if(hidden_buttons.size() != 0)
            setVisibleButtons(false, Utils.listToIntArray(hidden_buttons));

        // 아이템 박스 처리
        if(page == max_page){
            if((TotalItemAmount-1) % 6 >= 0)
                DrawManager.drawTexture("gui/shop/item_room", width * 0.2333F, height * 0.3722F, 0.5D, 1.0D, width * 0.2291F, height * 0.1129F);
            if((TotalItemAmount-1) % 6 >= 1)
                DrawManager.drawTexture("gui/shop/item_room", width * 0.5375F, height * 0.3722F, 0.5D, 1.0D, width * 0.2291F, height * 0.1129F);
            if((TotalItemAmount-1) % 6 >= 2)
                DrawManager.drawTexture("gui/shop/item_room", width * 0.2333F, height * 0.5268F, 0.5D, 1.0D, width * 0.2291F, height * 0.1129F);
            if((TotalItemAmount-1) % 6 >= 3)
                DrawManager.drawTexture("gui/shop/item_room", width * 0.5375F, height * 0.5268F, 0.5D, 1.0D, width * 0.2291F, height * 0.1129F);
            if((TotalItemAmount-1) % 6 >= 4)
                DrawManager.drawTexture("gui/shop/item_room", width * 0.2333F, height * 0.6787F, 0.5D, 1.0D, width * 0.2291F, height * 0.1129F);
            if((TotalItemAmount-1) % 6 >= 5)
                DrawManager.drawTexture("gui/shop/item_room", width * 0.5375F, height * 0.6787F, 0.5D, 1.0D, width * 0.2291F, height * 0.1129F);

        }else{
            DrawManager.drawTexture("gui/shop/item_room", width * 0.2333F, height * 0.3722F, 0.5D, 1.0D, width * 0.2291F, height * 0.1129F);
            DrawManager.drawTexture("gui/shop/item_room", width * 0.5375F, height * 0.3722F, 0.5D, 1.0D, width * 0.2291F, height * 0.1129F);
            DrawManager.drawTexture("gui/shop/item_room", width * 0.2333F, height * 0.5268F, 0.5D, 1.0D, width * 0.2291F, height * 0.1129F);
            DrawManager.drawTexture("gui/shop/item_room", width * 0.5375F, height * 0.5268F, 0.5D, 1.0D, width * 0.2291F, height * 0.1129F);
            DrawManager.drawTexture("gui/shop/item_room", width * 0.2333F, height * 0.6787F, 0.5D, 1.0D, width * 0.2291F, height * 0.1129F);
            DrawManager.drawTexture("gui/shop/item_room", width * 0.5375F, height * 0.6787F, 0.5D, 1.0D, width * 0.2291F, height * 0.1129F);
        }


        // 아이템 및 이름, 가격 표시
        for(int x=page*6; x<page*6+6; x++){
            if(x < TotalItemAmount){
                if(x % 6 == 0) {
                    DrawManager.drawItem(Utils.stringToStack(ItemsData[x].split("@@@")[0]), (int) (width * 0.2424F), (int) (height * 0.3868F), width * 0.003F);
                    DrawManager.drawString(Utils.truncateString(Utils.stringToStack(ItemsData[x].split("@@@")[0]).getDisplayName(),9), width * 0.3166F, height * 0.3865F, width * 0.0015F, new Color(0, 0, 0), false);
                    DrawManager.drawString(Utils.convertMoneyToCommas(Integer.valueOf(ItemsData[x].split("@@@")[3])) + "원", width * 0.3166F, height * 0.4473F, width * 0.0015F, new Color(0, 0, 0), false);
                    if(ItemsData[x].split("@@@")[2].contains("~"))
                        DrawManager.drawCenteredString("∞", width * 0.4405F, height * 0.3851F, width * 0.0022F, new Color(176, 132, 81), false);
                    else
                        DrawManager.drawCenteredString(ItemsData[x].split("@@@")[2], width * 0.4401F, height * 0.3861F, width * 0.0017F, new Color(176, 132, 81), false);
                }
                if(x % 6 == 1) {
                    DrawManager.drawItem(Utils.stringToStack(ItemsData[x].split("@@@")[0]), (int) (width * 0.5463F), (int) (height * 0.3868F), width * 0.003F);
                    DrawManager.drawString(Utils.truncateString(Utils.stringToStack(ItemsData[x].split("@@@")[0]).getDisplayName(),9), width * 0.6192F, height * 0.3865F, width * 0.0015F, new Color(0, 0, 0), false);
                    DrawManager.drawString(Utils.convertMoneyToCommas(Integer.valueOf(ItemsData[x].split("@@@")[3])) + "원", width * 0.6192F, height * 0.4473F, width * 0.0015F, new Color(0, 0, 0), false);
                    if(ItemsData[x].split("@@@")[2].contains("~"))
                        DrawManager.drawCenteredString("∞", width * 0.7451F, height * 0.3851F, width * 0.0022F, new Color(176, 132, 81), false);
                    else
                        DrawManager.drawCenteredString(ItemsData[x].split("@@@")[2], width * 0.7447F, height * 0.3861F, width * 0.0017F, new Color(176, 132, 81), false);
                }
                if(x % 6 == 2) {
                    DrawManager.drawItem(Utils.stringToStack(ItemsData[x].split("@@@")[0]), (int) (width * 0.2424F), (int) (height * 0.5398F), width * 0.003F);
                    DrawManager.drawString(Utils.truncateString(Utils.stringToStack(ItemsData[x].split("@@@")[0]).getDisplayName(),9), width * 0.3166F, height * 0.5411F, width * 0.0015F, new Color(0, 0, 0), false);
                    DrawManager.drawString(Utils.convertMoneyToCommas(Integer.valueOf(ItemsData[x].split("@@@")[3])) + "원", width * 0.3166F, height * 0.6028F, width * 0.0015F, new Color(0, 0, 0), false);
                    if(ItemsData[x].split("@@@")[2].contains("~"))
                        DrawManager.drawCenteredString("∞", width * 0.4405F, height * 0.5397F, width * 0.0022F, new Color(176, 132, 81), false);
                    else
                        DrawManager.drawCenteredString(ItemsData[x].split("@@@")[2], width * 0.4401F, height * 0.5407F, width * 0.0017F, new Color(176, 132, 81), false);
                }
                if(x % 6 == 3) {
                    DrawManager.drawItem(Utils.stringToStack(ItemsData[x].split("@@@")[0]), (int) (width * 0.5463F), (int) (height * 0.5398F), width * 0.003F);
                    DrawManager.drawString(Utils.truncateString(Utils.stringToStack(ItemsData[x].split("@@@")[0]).getDisplayName(),9), width * 0.6192F, height * 0.5411F, width * 0.0015F, new Color(0, 0, 0), false);
                    DrawManager.drawString(Utils.convertMoneyToCommas(Integer.valueOf(ItemsData[x].split("@@@")[3])) + "원", width * 0.6192F, height * 0.6028F, width * 0.0015F, new Color(0, 0, 0), false);
                    if(ItemsData[x].split("@@@")[2].contains("~"))
                        DrawManager.drawCenteredString("∞", width * 0.7451F, height * 0.5397F, width * 0.0022F, new Color(176, 132, 81), false);
                    else
                        DrawManager.drawCenteredString(ItemsData[x].split("@@@")[2], width * 0.7447F, height * 0.5407F, width * 0.0017F, new Color(176, 132, 81), false);
                }
                if(x % 6 == 4) {
                    DrawManager.drawItem(Utils.stringToStack(ItemsData[x].split("@@@")[0]), (int) (width * 0.2424F), (int) (height * 0.6925F), width * 0.003F);
                    DrawManager.drawString(Utils.truncateString(Utils.stringToStack(ItemsData[x].split("@@@")[0]).getDisplayName(),9), width * 0.3166F, height * 0.6929F, width * 0.0015F, new Color(0, 0, 0), false);
                    DrawManager.drawString(Utils.convertMoneyToCommas(Integer.valueOf(ItemsData[x].split("@@@")[3])) + "원", width * 0.3166F, height * 0.7547F, width * 0.0015F, new Color(0, 0, 0), false);
                    if(ItemsData[x].split("@@@")[2].contains("~"))
                        DrawManager.drawCenteredString("∞", width * 0.4405F, height * 0.6915F, width * 0.0022F, new Color(176, 132, 81), false);
                    else
                        DrawManager.drawCenteredString(ItemsData[x].split("@@@")[2], width * 0.4401F, height * 0.6925F, width * 0.0017F, new Color(176, 132, 81), false);
                }
                if(x % 6 == 5) {
                    DrawManager.drawItem(Utils.stringToStack(ItemsData[x].split("@@@")[0]), (int) (width * 0.5463F), (int) (height * 0.6925F), width * 0.003F);
                    DrawManager.drawString(Utils.truncateString(Utils.stringToStack(ItemsData[x].split("@@@")[0]).getDisplayName(),9), width * 0.6192F, height * 0.6929F, width * 0.0015F, new Color(0, 0, 0), false);
                    DrawManager.drawString(Utils.convertMoneyToCommas(Integer.valueOf(ItemsData[x].split("@@@")[3])) + "원", width * 0.6192F, height * 0.7547F, width * 0.0015F, new Color(0, 0, 0), false);
                    if(ItemsData[x].split("@@@")[2].contains("~"))
                        DrawManager.drawCenteredString("∞", width * 0.7451F, height * 0.6915F, width * 0.0022F, new Color(176, 132, 81), false);
                    else
                        DrawManager.drawCenteredString(ItemsData[x].split("@@@")[2], width * 0.7447F, height * 0.6925F, width * 0.0017F, new Color(176, 132, 81), false);
                }
            }
        }

        super.drawScreen(mouseX, mouseY, partialTicks);

        for (GuiButton button : buttonList) {
            boolean hovered = (mouseX >= button.x && mouseY >= button.y && mouseX < button.x + button.width && mouseY < button.y + button.height);
            if (button.visible && hovered && button.id != 0 && button.id != 1) {
                List<String> tooltips = new ArrayList<>();
                tooltips.add(Utils.convertColor("&f< " + Utils.stringToStack(ItemsData[button.id-2].split("@@@")[0]).getDisplayName() + " >"));
                tooltips.add("");
                tooltips.add(Utils.convertColor("&f가격 &7: &f" + Utils.convertMoneyToCommas(Integer.valueOf(ItemsData[button.id-2].split("@@@")[3])) + " &6원"));
                int hasCount = Utils.getAllItemCount(((EntityPlayer)entityPlayerSP).inventory, Utils.stringToStack(ItemsData[button.id-2].split("@@@")[0]));
                tooltips.add(Utils.convertColor("&f현재 아이템 갯수 &7: &f" + hasCount + "&6 개"));
                drawHoveringText(tooltips, mouseX, mouseY);
            }
        }
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        for (GuiButton button : buttonList) {
            boolean hovered = (mouseX >= button.x && mouseY >= button.y && mouseX < button.x + button.width && mouseY < button.y + button.height);
            if (button.visible && hovered) {
                if(mouseButton == 0){
                    if(button.id == 0){
                        if (page > 0)
                            page--;
                    }else if(button.id == 1){
                        if (page < max_page)
                            page++;
                    }else{
                        if(isShiftKeyDown()){
                            StringBuilder Data_ShopClicked = new StringBuilder();
                            Data_ShopClicked.append("ShopClicked#" + InfoData[1] + "#" + (button.id-2) + "#2");
                            NetworkManager.sendPacket(Data_ShopClicked.toString());
                        }else if(isAltKeyDown()){
                            StringBuilder Data_ShopClicked = new StringBuilder();
                            Data_ShopClicked.append("ShopClicked#" + InfoData[1] + "#" + (button.id-2) + "#3");
                            NetworkManager.sendPacket(Data_ShopClicked.toString());
                        }else{
                            StringBuilder Data_ShopClicked = new StringBuilder();
                            Data_ShopClicked.append("ShopClicked#" + InfoData[1] + "#" + (button.id-2) + "#1");
                            NetworkManager.sendPacket(Data_ShopClicked.toString());
                        }
                    }
                }else if(mouseButton == 1){
                    if (button.id != 0 && button.id != 1) {
                        if(isShiftKeyDown()){
                            StringBuilder Data_ShopClicked = new StringBuilder();
                            Data_ShopClicked.append("ShopClicked#" + InfoData[1] + "#" + (button.id-2) + "#2");
                            NetworkManager.sendPacket(Data_ShopClicked.toString());
                        }else if(isAltKeyDown()){
                            StringBuilder Data_ShopClicked = new StringBuilder();
                            Data_ShopClicked.append("ShopClicked#" + InfoData[1] + "#" + (button.id-2) + "#3");
                            NetworkManager.sendPacket(Data_ShopClicked.toString());
                        }else{
                            StringBuilder Data_ShopClicked = new StringBuilder();
                            Data_ShopClicked.append("ShopClicked#" + InfoData[1] + "#" + (button.id-2) + "#1");
                            NetworkManager.sendPacket(Data_ShopClicked.toString());
                        }
                    }
                }
            }
        }
    }

    public void onGuiClosed() {
        Data.guistat = 0;
        InfoData = null;
        TotalItemAmount = null;
        ItemsData = null;
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
