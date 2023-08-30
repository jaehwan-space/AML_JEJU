package com.majun.amljeju.gui;

import com.majun.amljeju.manager.DrawManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiCheckButtonImage extends GuiButton {
    private final String texture;
    private boolean choose;
    public GuiCheckButtonImage(int buttonId, int x, int y, int width, int height, String texture) {
        super(buttonId, x, y, width, height, "");
        this.texture = texture;
    }

    public void drawButton(Minecraft minecraft, int mouseX, int mouseY, float partialTicks) {
        if (visible) {
            hovered = (mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height);
            GlStateManager.disableDepth();
            if (hovered) {
                if(choose)
                    DrawManager.drawTexture("gui/" + texture + "_c", x, y, 0.0D, 1.0D, width, height);
                else
                    DrawManager.drawTexture("gui/" + texture + "_h", x, y, 0.0D, 1.0D, width, height);
            } else {
                if(choose)
                    DrawManager.drawTexture("gui/" + texture + "_c", x, y, 0.0D, 1.0D, width, height);
                else
                    DrawManager.drawTexture("gui/" + texture , x, y, 0.0D, 1.0D, width, height);
            }
            GlStateManager.enableDepth();
        }
    }


    public GuiCheckButtonImage setVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public GuiCheckButtonImage setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public GuiCheckButtonImage setChoosed(boolean choosed) {
        this.choose = choosed;
        return this;
    }
}
