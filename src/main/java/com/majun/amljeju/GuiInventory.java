package com.majun.amljeju;

import com.google.common.collect.Ordering;
import java.util.Collection;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiInventory extends GuiContainer {
    private float oldMouseX;

    private float oldMouseY;

    public GuiInventory(EntityPlayer player, InventoryPlayer inventoryPlayer) {
        super(new ContainerInventory(inventoryPlayer, player));
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        oldMouseX = mouseX;
        oldMouseY = mouseY;
        renderHoveredToolTip(mouseX, mouseY);
    }

    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        ScaledResolution res = new ScaledResolution(mc);
        float xx = (float)res.getScaledWidth_double() / 1280.0F;
        float yy = (float)res.getScaledHeight_double() / 720.0F;
        drawDefaultBackground();
        GL11.glPushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.translate((width / 2.0F) - 360.0D, (height / 2.0F) - 202.5D, 1.0D);  //그리기 시작하는 위치 맞추기(리소스 사이즈에서 4나누기 하면  350, 270 자리 값 나옴)
        GlStateManager.scale(0.375D, 0.3755, 0.375D);
        GL11.glBlendFunc(770, 771);
        mc.getTextureManager().bindTexture(new ResourceLocation("amljeju", "textures/gui/inventory.png"));
        drawModalRectWithCustomSizedTexture(0, 0, 0.0F, 0.0F, 1920, 1080, 1920.0F, 1080.0F);
        GlStateManager.disableBlend();
        GlStateManager.disableAlpha();
        GL11.glPopMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(width / 2.0F - 187.0F, height / 2.0F + -6.0F, 30.0F); // 플레이어 모델 그리는 위치 지정
        net.minecraft.client.gui.inventory.GuiInventory.drawEntityOnScreen(90, 0, 45, (guiLeft + 51) - oldMouseX, (guiTop + 75 - 50) - oldMouseY, (EntityLivingBase)mc.player);  //플레이어 모델 그리기
        GlStateManager.popMatrix();
        drawActivePotionEffects();
    }

    private void drawActivePotionEffects() {
        int i = guiLeft - 162;
        int j = guiTop;
        int k = 166;
        Collection<PotionEffect> collection = mc.player.getActivePotionEffects();
        if (!collection.isEmpty()) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.disableLighting();
            int l = 33;
            if (collection.size() > 5)
                l = 132 / (collection.size() - 1);
            for (PotionEffect potioneffect : Ordering.natural().sortedCopy(collection)) {
                Potion potion = potioneffect.getPotion();
                if (!potion.shouldRender(potioneffect))
                    continue;
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                mc.getTextureManager().bindTexture(INVENTORY_BACKGROUND);
                drawTexturedModalRect(i, j, 0, 166, 140, 32);
                if (potion.hasStatusIcon()) {
                    int i1 = potion.getStatusIconIndex();
                    drawTexturedModalRect(i + 6, j + 7, 0 + i1 % 8 * 18, 198 + i1 / 8 * 18, 18, 18);
                }
                potion.renderInventoryEffect(i, j, potioneffect, mc);
                if (!potion.shouldRenderInvText(potioneffect)) {
                    j += l;
                    continue;
                }
                String s1 = I18n.format(potion.getName(), new Object[0]);
                if (potioneffect.getAmplifier() == 1) {
                    s1 = s1 + " " + I18n.format("enchantment.level.2");
                } else if (potioneffect.getAmplifier() == 2) {
                    s1 = s1 + " " + I18n.format("enchantment.level.3");
                } else if (potioneffect.getAmplifier() == 3) {
                    s1 = s1 + " " + I18n.format("enchantment.level.4");
                }
                fontRenderer.drawStringWithShadow(s1, (i + 10 + 18), (j + 6), 16777215);
                String s = Potion.getPotionDurationString(potioneffect, 1.0F);
                fontRenderer.drawStringWithShadow(s, (i + 10 + 18), (j + 6 + 10), 8355711);
                j += l;
            }
        }
    }
}