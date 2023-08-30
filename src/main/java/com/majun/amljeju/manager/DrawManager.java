package com.majun.amljeju.manager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class DrawManager {

    private static Minecraft minecraft = Minecraft.getMinecraft();

    public static void drawTexture(String texture, double x, double y, double z, double scale, float tex_x, float tex_y) {
        GL11.glPushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.translate(x, y, z);
        GlStateManager.scale(scale, scale, scale);
        GL11.glBlendFunc(770, 771);
        ResourceLocation location = new ResourceLocation("amljeju", "textures/" + texture + ".png");
        minecraft.getTextureManager().bindTexture(location);
        Gui.drawModalRectWithCustomSizedTexture(0, 0, 0.0F, 0.0F, (int)tex_x, (int)tex_y, tex_x, tex_y);
        GlStateManager.disableBlend();
        GlStateManager.disableAlpha();
        GL11.glPopMatrix();
    }

    public static void drawString(String text, double x, double y, double scale, Color color, boolean shadow) {
        FontRenderer fontRendererIn = minecraft.fontRenderer;
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, 1.0D);
        GlStateManager.scale(scale, scale, scale);
        fontRendererIn.drawString(text, 0, 0, color.getRGB(), shadow);
        GlStateManager.popMatrix();
    }

    public static void drawCenteredString(String text, double x, double y, double scale, Color color, boolean shadow) {
        FontRenderer fontRendererIn = minecraft.fontRenderer;
        GlStateManager.pushMatrix();
        GlStateManager.translate((int)x, (int)y, 1.0F);
        GlStateManager.scale(scale, scale, scale);
        drawCenteredString(fontRendererIn, text, 0, 0, color.getRGB(), shadow);
        GlStateManager.popMatrix();
    }

    public static void drawRightString(String text, double x, double y, double scale, Color color, boolean shadow) {
        FontRenderer fontRendererIn = minecraft.fontRenderer;
        GlStateManager.pushMatrix();
        double moneyTextSize = minecraft.fontRenderer.getStringWidth(text);
        GlStateManager.translate(x - moneyTextSize * (minecraft.displayWidth * 0.0009F), y, 1.0F);
        GlStateManager.scale(scale, scale, scale);
        fontRendererIn.drawString(text, 0, 0, color.getRGB(), shadow);
        GlStateManager.popMatrix();
    }

    public static void aadrawRightString(String text, double x, double y, double scale, Color color, boolean shadow) {
        FontRenderer fontRendererIn = minecraft.fontRenderer;
        GlStateManager.pushMatrix();
        double moneyTextSize = minecraft.fontRenderer.getStringWidth(text);
        GlStateManager.translate(x - moneyTextSize * (minecraft.displayWidth * 0.0006F), y, 1.0F);
        GlStateManager.scale(scale, scale, scale);
        fontRendererIn.drawString(text, 0, 0, color.getRGB(), shadow);
        GlStateManager.popMatrix();
    }

    private static void drawCenteredString(FontRenderer fontRendererIn, String text, int x, int y, int color, boolean shadow) {
        fontRendererIn.drawString(text, (x - fontRendererIn.getStringWidth(text) / 2), y, color, shadow);
    }

    public static void drawItem(ItemStack stack, int x, int y, float scale) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, 5.0F);
        GlStateManager.scale(scale, scale, scale);
        RenderHelper.enableGUIStandardItemLighting();
        minecraft.getRenderItem().renderItemIntoGUI(stack, 0, 0);
        minecraft.getRenderItem().renderItemOverlays(minecraft.fontRenderer, stack, 0, 0);
        GlStateManager.popMatrix();
    }

}
