package com.majun.amljeju.gui;

import com.majun.amljeju.manager.DrawManager;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.realms.RealmsBridge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;

@SideOnly(Side.CLIENT)
public class Gui_ESC extends GuiScreen {
    private int saveStep;

    private int visibleTime;

    public void initGui() {
        saveStep = 0;
        buttonList.clear();
        int i = -16;
        int j = 98;
        buttonList.add(new GuiButton(1, width / 2 - 100, height / 2 + 30 + -16, I18n.format("menu.returnToMenu", new Object[0])));
        if (!mc.isIntegratedServerRunning())
            ((GuiButton)buttonList.get(0)).displayString = I18n.format("menu.disconnect", new Object[0]);
        buttonList.add(new GuiButton(4, width / 2 - 100, height / 2 - 20 + -16, I18n.format("menu.returnToGame", new Object[0])));
        buttonList.add(new GuiButton(0, width / 2 - 100, height / 2 + 5 + -16, I18n.format("menu.options", new Object[0])));
    }

    protected void actionPerformed(GuiButton button) throws IOException {
        boolean flag, flag1;
        if(button.id == 0){
            mc.displayGuiScreen((GuiScreen) new GuiOptions(this, mc.gameSettings));
        }else if(button.id == 1){
            flag = mc.isIntegratedServerRunning();
            flag1 = mc.isConnectedToRealms();
            button.enabled = false;
            mc.world.sendQuittingDisconnectingPacket();
            mc.loadWorld((WorldClient) null);
            if (flag) {
                mc.displayGuiScreen((GuiScreen)new GuiMainMenu());
            } else if (flag1) {
                RealmsBridge realmsbridge = new RealmsBridge();
                realmsbridge.switchToRealms((GuiScreen)new GuiMainMenu());
            } else {
                mc.displayGuiScreen((GuiScreen)new GuiMainMenu());
            }
        }else{
            mc.displayGuiScreen((GuiScreen)null);
            mc.setIngameFocus();
        }
    }

    public void updateScreen() {
        super.updateScreen();
        visibleTime++;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        DrawManager.drawTexture("etc/logo", width * 0.3285F, height * 0.1002, 0.0D, 1.0D, width * 0.3224F, height * 0.2629F);
        DrawManager.drawTexture("etc/team_logo", width * 0.4355F, height * 0.6288F, 0.0D, 1.0D, width * 0.1494F, height * 0.0842F);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
