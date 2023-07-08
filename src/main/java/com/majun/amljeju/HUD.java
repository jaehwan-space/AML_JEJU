package com.majun.amljeju;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.text.DecimalFormat;

public class HUD extends Gui {
    private static Minecraft minecraft = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            double width = event.getResolution().getScaledWidth_double();
            double height = event.getResolution().getScaledHeight_double();
            //if (ClientProxy.hud_time == null) {
            drawTexture("stat_hud", (width - 540.0F * AML_JEJU.guiScale), (height/2 + 50.0F * AML_JEJU.guiScale), 1.0D, (540.0F * AML_JEJU.guiScale), (280.0F * AML_JEJU.guiScale));
            String moneyString = convertMoneyToCommas(103030);
            drawCenteredString(moneyString, (width - 245.0F * AML_JEJU.guiScale), (height/2 + 100.0F * AML_JEJU.guiScale), (10.0F * AML_JEJU.guiScale), new Color(96, 77, 23), false);
        }
        if (minecraft.displayWidth >= 1400 && minecraft.displayHeight >= 820) {
            AML_JEJU.guiScale = 0.25F;
        } else if (minecraft.displayWidth <= 854 && minecraft.displayHeight <= 480) {
            AML_JEJU.guiScale = 0.125F;
        } else {
            AML_JEJU.guiScale = 0.1875F;
        }
    }

    public static String convertMoneyToCommas(int amount) {
        DecimalFormat commas = new DecimalFormat("#,###");
        return commas.format(amount);
    }

    private void drawTexture(String texture, double x, double y, double scale, float tex_x, float tex_y) {
        GL11.glPushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.translate(x, y, 1.0D);
        GlStateManager.scale(scale, scale, scale);
        GL11.glBlendFunc(770, 771);
        ResourceLocation location = new ResourceLocation("amljeju", "textures/gui/" + texture + ".png");
        minecraft.getTextureManager().bindTexture(location);
        drawModalRectWithCustomSizedTexture(0, 0, 0.0F, 0.0F, (int)tex_x, (int)tex_y, tex_x, tex_y);
        GlStateManager.disableBlend();
        GlStateManager.disableAlpha();
        GL11.glPopMatrix();
    }

    private void drawMoneyText(String moneyText) {
        GL11.glPushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GlStateManager.translate(1.0D, 1.0D, 1.0D);
        GL11.glBlendFunc(770, 771);
        GL11.glScalef(1.2F, 1.2F, 1.2F);
        double moneyTextSize = minecraft.fontRenderer.getStringWidth(moneyText);
        double moneyStartX = 103.0D - moneyTextSize;
        double moneyStartY = 6.0D;
        drawString(minecraft.fontRenderer, moneyText, (int)moneyStartX, (int)moneyStartY, -1);
        GlStateManager.disableBlend();
        GlStateManager.disableAlpha();
        GL11.glPopMatrix();
    }

    public static void drawString(String text, double x, double y, double scale, Color color) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, 1.0D);
        GlStateManager.scale(scale, scale, scale);
        minecraft.fontRenderer.drawStringWithShadow(text, 0.0F, 0.0F, color.getRGB());
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

    private static void drawCenteredString(FontRenderer fontRendererIn, String text, int x, int y, int color, boolean shadow) {
        fontRendererIn.drawString(text, (x - fontRendererIn.getStringWidth(text) / 2), y, color, shadow);
    }

}
