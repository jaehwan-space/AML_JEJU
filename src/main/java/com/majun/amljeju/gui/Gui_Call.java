package com.majun.amljeju.gui;

import com.majun.amljeju.Data;
import com.majun.amljeju.manager.DrawManager;
import com.majun.amljeju.network.NetworkManager;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;

public class Gui_Call extends GuiScreen {

    public Gui_Call(){}

    public void initGui() {
        addButton(new GuiButtonImage(0, (int)(width * 0.4473F), (int)(height * 0.6814F), (int)(width * 0.0369F), (int)(height * 0.0657F), "call/entire"));
        addButton(new GuiButtonImage(1, (int)(width * 0.3057F), (int)(height * 0.3472F), (int)(width * 0.0843F), (int)(height * 0.15F), "member/d7297"));
        addButton(new GuiButtonImage(2, (int)(width * 0.4072F), (int)(height * 0.3472F), (int)(width * 0.0843F), (int)(height * 0.15F), "member/seoneng"));
        addButton(new GuiButtonImage(3, (int)(width * 0.5088F), (int)(height * 0.3472F), (int)(width * 0.0843F), (int)(height * 0.15F), "member/huchu95"));
        addButton(new GuiButtonImage(4, (int)(width * 0.6104F), (int)(height * 0.3472F), (int)(width * 0.0843F), (int)(height * 0.15F), "member/samsik23"));
        addButton(new GuiButtonImage(5, (int)(width * 0.3057F), (int)(height * 0.5185F), (int)(width * 0.0843F), (int)(height * 0.15F), "member/noonkkob"));
        addButton(new GuiButtonImage(6, (int)(width * 0.4072F), (int)(height * 0.5185F), (int)(width * 0.0843F), (int)(height * 0.15F), "member/kong7"));
        addButton(new GuiButtonImage(7, (int)(width * 0.5088F), (int)(height * 0.5185F), (int)(width * 0.0843F), (int)(height * 0.15F), "member/daju"));
        addButton(new GuiButtonImage(8, (int)(width * 0.6104F), (int)(height * 0.5185F), (int)(width * 0.0843F), (int)(height * 0.15F), "member/rutaey"));
        addButton(new GuiButtonImage(9, (int)(width * 0.5156F), (int)(height * 0.6814F), (int)(width * 0.0369F), (int)(height * 0.0657F), "call/village"));
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        EntityPlayerSP entityPlayerSP = mc.player;
        DrawManager.drawTexture("gui/call/background", width * 0.2447F, height * 0.2196F , 0.0D, 1.0D, width * 0.5114F, height * 0.6379F);
        DrawManager.drawTexture("gui/call/etc", width * 0.5557F, height * 0.1018F , 0.0D, 1.0D, width * 0.2007F, height * 0.0925F);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        for (GuiButton button : buttonList) {
            boolean hovered = (mouseX >= button.x && mouseY >= button.y && mouseX < button.x + button.width && mouseY < button.y + button.height);
            if (button.visible && hovered) {
                if(mouseButton == 0){
                    if (button.id == 0) {
                        StringBuilder Data_ShopClicked = new StringBuilder();
                        Data_ShopClicked.append("CallMove#" + "단체");
                        NetworkManager.sendPacket(Data_ShopClicked.toString());
                        mc.displayGuiScreen(null);
                    }
                    if (button.id == 9) {
                        StringBuilder Data_ShopClicked = new StringBuilder();
                        Data_ShopClicked.append("CallMove#" + "마을회관");
                        NetworkManager.sendPacket(Data_ShopClicked.toString());
                        mc.displayGuiScreen(null);
                    }


                    if (button.id == 1) {
                        StringBuilder Data_ShopClicked = new StringBuilder();
                        Data_ShopClicked.append("CallRequest#양띵");
                        NetworkManager.sendPacket(Data_ShopClicked.toString());
                        mc.displayGuiScreen(null);
                    }
                    if (button.id == 2) {
                        StringBuilder Data_ShopClicked = new StringBuilder();
                        Data_ShopClicked.append("CallRequest#서넹");
                        NetworkManager.sendPacket(Data_ShopClicked.toString());
                        mc.displayGuiScreen(null);
                    }
                    if (button.id == 3) {
                        StringBuilder Data_ShopClicked = new StringBuilder();
                        Data_ShopClicked.append("CallRequest#후추");
                        NetworkManager.sendPacket(Data_ShopClicked.toString());
                        mc.displayGuiScreen(null);
                    }
                    if (button.id == 4) {
                        StringBuilder Data_ShopClicked = new StringBuilder();
                        Data_ShopClicked.append("CallRequest#삼식");
                        NetworkManager.sendPacket(Data_ShopClicked.toString());
                        mc.displayGuiScreen(null);
                    }
                    if (button.id == 5) {
                        StringBuilder Data_ShopClicked = new StringBuilder();
                        Data_ShopClicked.append("CallRequest#눈꽃");
                        NetworkManager.sendPacket(Data_ShopClicked.toString());
                        mc.displayGuiScreen(null);
                    }if (button.id == 6) {
                        StringBuilder Data_ShopClicked = new StringBuilder();
                        Data_ShopClicked.append("CallRequest#콩콩");
                        NetworkManager.sendPacket(Data_ShopClicked.toString());
                        mc.displayGuiScreen(null);
                    }if (button.id == 7) {
                        StringBuilder Data_ShopClicked = new StringBuilder();
                        Data_ShopClicked.append("CallRequest#다주");
                        NetworkManager.sendPacket(Data_ShopClicked.toString());
                        mc.displayGuiScreen(null);
                    }if (button.id == 8) {
                        StringBuilder Data_ShopClicked = new StringBuilder();
                        Data_ShopClicked.append("CallRequest#루태");
                        NetworkManager.sendPacket(Data_ShopClicked.toString());
                        mc.displayGuiScreen(null);
                    }

                }
                if (mouseButton == 1) {
                    if(isShiftKeyDown()){
                        if (button.id == 0) {
                            StringBuilder Data_ShopClicked = new StringBuilder();
                            Data_ShopClicked.append("CallMove#" + "단체");
                            NetworkManager.sendPacket(Data_ShopClicked.toString());
                            mc.displayGuiScreen(null);
                        }
                        if (button.id == 9) {
                            StringBuilder Data_ShopClicked = new StringBuilder();
                            Data_ShopClicked.append("CallMove#" + "마을회관");
                            NetworkManager.sendPacket(Data_ShopClicked.toString());
                            mc.displayGuiScreen(null);
                        }
                        if (button.id == 1) {
                            StringBuilder Data_ShopClicked = new StringBuilder();
                            Data_ShopClicked.append("CallMove#" + "양띵");
                            NetworkManager.sendPacket(Data_ShopClicked.toString());
                            mc.displayGuiScreen(null);
                        }
                        if (button.id == 2) {
                            StringBuilder Data_ShopClicked = new StringBuilder();
                            Data_ShopClicked.append("CallMove#" + "서넹");
                            NetworkManager.sendPacket(Data_ShopClicked.toString());
                            mc.displayGuiScreen(null);
                        }
                        if (button.id == 3) {
                            StringBuilder Data_ShopClicked = new StringBuilder();
                            Data_ShopClicked.append("CallMove#" + "후추");
                            NetworkManager.sendPacket(Data_ShopClicked.toString());
                            mc.displayGuiScreen(null);
                        }
                        if (button.id == 4) {
                            StringBuilder Data_ShopClicked = new StringBuilder();
                            Data_ShopClicked.append("CallMove#" + "삼식");
                            NetworkManager.sendPacket(Data_ShopClicked.toString());
                            mc.displayGuiScreen(null);
                        }
                        if (button.id == 5) {
                            StringBuilder Data_ShopClicked = new StringBuilder();
                            Data_ShopClicked.append("CallMove#" + "눈꽃");
                            NetworkManager.sendPacket(Data_ShopClicked.toString());
                            mc.displayGuiScreen(null);
                        }if (button.id == 6) {
                            StringBuilder Data_ShopClicked = new StringBuilder();
                            Data_ShopClicked.append("CallMove#" + "콩콩");
                            NetworkManager.sendPacket(Data_ShopClicked.toString());
                            mc.displayGuiScreen(null);
                        }if (button.id == 7) {
                            StringBuilder Data_ShopClicked = new StringBuilder();
                            Data_ShopClicked.append("CallMove#" + "다주");
                            NetworkManager.sendPacket(Data_ShopClicked.toString());
                            mc.displayGuiScreen(null);
                        }if (button.id == 8) {
                            StringBuilder Data_ShopClicked = new StringBuilder();
                            Data_ShopClicked.append("CallMove#" + "루태");
                            NetworkManager.sendPacket(Data_ShopClicked.toString());
                            mc.displayGuiScreen(null);
                        }
                    }
                }
            }
        }
    }

    public void onGuiClosed() {
        Data.guistat = 0;
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
