package com.majun.amljeju.gui;

import com.majun.amljeju.AML_JEJU;
import com.majun.amljeju.manager.DrawManager;
import com.majun.amljeju.manager.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;
import java.text.DecimalFormat;

public class HUD extends Gui {
    private static Minecraft minecraft = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            double width = event.getResolution().getScaledWidth_double();
            double height = event.getResolution().getScaledHeight_double();
            if (AML_JEJU.guistat == 0) {
                DrawManager.drawTexture("stat_hud", (width - 540.0F * AML_JEJU.guiScale), (height / 2 + 50.0F * AML_JEJU.guiScale), 1.0D, (540.0F * AML_JEJU.guiScale), (280.0F * AML_JEJU.guiScale));
                String moneyString = Utils.convertMoneyToCommas(103030);
                DrawManager.drawCenteredString(moneyString, (width - 245.0F * AML_JEJU.guiScale), (height / 2 + 100.0F * AML_JEJU.guiScale), (10.0F * AML_JEJU.guiScale), new Color(96, 77, 23), false);
            }
        }
        if (minecraft.displayWidth >= 1600 && minecraft.displayHeight >= 860) {
            AML_JEJU.guiScale = 0.25F;
        } else if (minecraft.displayWidth <= 854 && minecraft.displayHeight <= 480) {
            AML_JEJU.guiScale = 0.125F;
        } else {
            AML_JEJU.guiScale = 0.1875F;
        }
    }


}
