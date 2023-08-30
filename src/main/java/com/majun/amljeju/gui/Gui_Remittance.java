package com.majun.amljeju.gui;

import com.majun.amljeju.Data;
import com.majun.amljeju.manager.DrawManager;
import com.majun.amljeju.manager.Utils;
import com.majun.amljeju.network.NetworkManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.text.TextComponentString;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Gui_Remittance extends GuiScreen {
    private static Minecraft minecraft = Minecraft.getMinecraft();
    private int textboxid = 0;
    private GuiTextField textbox;
    private String target_player;
    private static int page;

    public static void UpdateRemittance(String data){
        page = Integer.valueOf(data.split("#")[1]);
    }

    public Gui_Remittance(){}

    public void initGui() {
        textbox = new GuiTextField(textboxid, (Minecraft.getMinecraft()).fontRenderer, 78, 30, 100, 20);
        textbox.setFocused(true);
        textbox.setMaxStringLength(10);
        Keyboard.enableRepeatEvents(true);
        textbox.setEnableBackgroundDrawing(false);

        Data.guistat = 2;


        addButton(new GuiButtonImage(0, (int)(width * 0.4728F), (int)(height * 0.6629F), (int)(width * 0.0572F), (int)(height * 0.05F), "remittance/button"));
        addButton(new GuiCheckButtonImage(1, (int)(width * 0.3057F), (int)(height * 0.1870F), (int)(width * 0.0843F), (int)(height * 0.15F), "member/d7297"));
        addButton(new GuiCheckButtonImage(2, (int)(width * 0.4072F), (int)(height * 0.1870F), (int)(width * 0.0843F), (int)(height * 0.15F), "member/seoneng"));
        addButton(new GuiCheckButtonImage(3, (int)(width * 0.5088F), (int)(height * 0.1870F), (int)(width * 0.0843F), (int)(height * 0.15F), "member/huchu95"));
        addButton(new GuiCheckButtonImage(4, (int)(width * 0.6104F), (int)(height * 0.1870F), (int)(width * 0.0843F), (int)(height * 0.15F), "member/samsik23"));
        addButton(new GuiCheckButtonImage(5, (int)(width * 0.3057F), (int)(height * 0.3583F), (int)(width * 0.0843F), (int)(height * 0.15F), "member/noonkkob"));
        addButton(new GuiCheckButtonImage(6, (int)(width * 0.4072F), (int)(height * 0.3583F), (int)(width * 0.0843F), (int)(height * 0.15F), "member/kong7"));
        addButton(new GuiCheckButtonImage(7, (int)(width * 0.5088F), (int)(height * 0.3583F), (int)(width * 0.0843F), (int)(height * 0.15F), "member/daju"));
        addButton(new GuiCheckButtonImage(8, (int)(width * 0.6104F), (int)(height * 0.3583F), (int)(width * 0.0843F), (int)(height * 0.15F), "member/rutaey"));
        addButton(new GuiButtonImage(9, (int)(width * 0.4625F), (int)(height * 0.5296F), (int)(width * 0.0854F), (int)(height * 0.0861F), "remittance/check").setVisible(false));
    }

    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (Character.isDigit(typedChar) || keyCode == 14 || keyCode == 1)
            if (textbox.getText().length() == 0) {
                if (keyCode != 11 && keyCode != 82) {
                    textbox.textboxKeyTyped(typedChar, keyCode);
                    super.keyTyped(typedChar, keyCode);
                }
            } else {
                textbox.textboxKeyTyped(typedChar, keyCode);
                super.keyTyped(typedChar, keyCode);
            }
    }

    public void updateScreen() {
        textbox.updateCursorCounter();
        super.updateScreen();
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        EntityPlayerSP entityPlayerSP = mc.player;
        DrawManager.drawTexture("gui/remittance/background", width * 0.2411F, height * 0.0916F , 0.0D, 1.D, width * 0.5177F, height * 0.8166F);

        // 돈 출력
        String moneyString = Utils.convertMoneyToCommas(Data.Money);
        if(Minecraft.getMinecraft().displayWidth > 1600)
            DrawManager.aadrawRightString(moneyString + "원", width * 0.6975F, height * 0.8413F, width * 0.0018F, new Color(0, 0, 0), false);
        else
            DrawManager.drawRightString(moneyString + "원", width * 0.6935F, height * 0.8413F, width * 0.0018F, new Color(0, 0, 0), false);


        try {
            DrawManager.drawCenteredString(Utils.convertMoneyToCommas(Integer.parseInt(textbox.getText())) + "원", width * 0.5F, height * 0.5904F, width * 0.0023F, new Color(0, 0, 0), false);
        } catch (NumberFormatException exc) {
            DrawManager.drawCenteredString("0원", width * 0.5F, height * 0.5904F, width * 0.0023F, new Color(0, 0, 0), false);
        }

        if(target_player == "양띵")
            aaa(1).setChoosed(true);
        else
            aaa(1).setChoosed(false);
        if(target_player == "서넹")
            aaa(2).setChoosed(true);
        else
            aaa(2).setChoosed(false);
        if(target_player == "후추")
            aaa(3).setChoosed(true);
        else
            aaa(3).setChoosed(false);
        if(target_player == "삼식")
            aaa(4).setChoosed(true);
        else
            aaa(4).setChoosed(false);
        if(target_player == "눈꽃")
            aaa(5).setChoosed(true);
        else
            aaa(5).setChoosed(false);
        if(target_player == "콩콩")
            aaa(6).setChoosed(true);
        else
            aaa(6).setChoosed(false);
        if(target_player == "다주")
            aaa(7).setChoosed(true);
        else
            aaa(7).setChoosed(false);
        if(target_player == "루태")
            aaa(8).setChoosed(true);
        else
            aaa(8).setChoosed(false);

        if(page == 1){
            DrawManager.drawTexture("gui/remittance/notice", width * 0.2411F, height * 0.0916F , 1.0D, 1.D, width * 0.5177F, height * 0.8166F);

            // 버튼 숨기기
            List<Integer> visible_buttons = new ArrayList<>();
            List<Integer> enabled_buttons = new ArrayList<>();
            visible_buttons.add(9);
            for(int i=1; i<9; i++){
                enabled_buttons.add(i);
            }
            if(visible_buttons.size() != 0)
                setVisibleButtons(true, Utils.listToIntArray(visible_buttons));
            if(enabled_buttons.size() != 0)
                setVisibleButtons1(false, Utils.listToIntArray(enabled_buttons));
            bbb(0).setVisible(false);

        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        for (GuiButton button : buttonList) {
            boolean hovered = (mouseX >= button.x && mouseY >= button.y && mouseX < button.x + button.width && mouseY < button.y + button.height);
            if (button.visible && hovered) {
                if (mouseButton == 0) {
                    if (button.id == 0) {
                        if(target_player != null){
                            StringBuilder Data_Remittance = new StringBuilder();
                            if(textbox.getText() == "")
                                mc.player.sendMessage(new TextComponentString(Utils.convertColor("&6[한달살이] &f송금 금액을 입력해주세요.")));
                            else {
                                Data_Remittance.append("RemittancePlayer#" + target_player + "#" + textbox.getText());
                                NetworkManager.sendPacket(Data_Remittance.toString());
                            }
                        }else{
                            mc.player.sendMessage(new TextComponentString(Utils.convertColor("&6[한달살이] &f송금할 사람을 선택해주세요.")));
                        }
                    }
                    if (button.id == 1) {
                        target_player = "양띵";
                    }
                    if (button.id == 2) {
                        target_player = "서넹";
                    }
                    if (button.id == 3) {
                        target_player = "후추";
                    }
                    if (button.id == 4) {
                        target_player = "삼식";
                    }
                    if (button.id == 5) {
                        target_player = "눈꽃";
                    }
                    if (button.id == 6) {
                        target_player = "콩콩";
                    }
                    if (button.id == 7) {
                        target_player = "다주";
                    }
                    if (button.id == 8) {
                        target_player = "루태";
                    }

                    if (button.id == 9) {
                        mc.player.closeScreen();
                    }
                }
            }
        }
    }

    public void onGuiClosed() {
        Data.guistat = 0;
        target_player = null;
        page = 0;
    }

    public boolean doesGuiPauseGame() {
        return false;
    }

    public GuiCheckButtonImage aaa(int id) {
        if (buttonList.get(id) instanceof GuiCheckButtonImage)
            return (GuiCheckButtonImage) buttonList.get(id);
        return null;
    }

    public GuiButtonImage bbb(int id) {
        if (buttonList.get(id) instanceof GuiButtonImage)
            return (GuiButtonImage) buttonList.get(id);
        return null;
    }

    public void setVisibleButtons1(boolean visible, int... id) {
        for (int i : id)
            aaa(i).setVisible(visible);
    }

    public void setVisibleButtons(boolean visible, int... id) {
        for (int i : id)
            bbb(i).setVisible(visible);
    }

}
