package com.majun.amljeju.gui;

import com.majun.amljeju.Data;
import com.majun.amljeju.manager.DrawManager;
import com.majun.amljeju.manager.Utils;
import com.majun.amljeju.proxy.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;
import java.util.Random;

public class HUD extends Gui {
    private static Minecraft minecraft = Minecraft.getMinecraft();

    private static int[] numberArr = new int[6];
    public static void Timerupdate(int time) {
        int hour = time / 3600;
        int min = time % 3600 / 60;
        int sec = time % 3600 % 60;
        numberArr[0] = hour;
        numberArr[1] = min / 10;
        numberArr[2] = min % 10;
        numberArr[3] = sec / 10;
        numberArr[4] = sec % 10;
        numberArr[5] = time;
    }

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            double width = event.getResolution().getScaledWidth_double();
            double height = event.getResolution().getScaledHeight_double();
            
            if (Data.guistat == 0) {
                if(Data.Player_Name.contains("양띵"))
                    DrawManager.drawTexture("gui/hud/stat_d7297", width * 0.8451F, height * 0.3085F, 1.0D, 1.0D, (float) (width * 0.1473F), (float) (height * 0.3F));
                else if(Data.Player_Name.contains("서넹"))
                    DrawManager.drawTexture("gui/hud/stat_seoneng", width * 0.8451F, height * 0.3085F, 1.0D, 1.0D, (float) (width * 0.1473F), (float) (height * 0.3F));
                else if(Data.Player_Name.contains("후추"))
                    DrawManager.drawTexture("gui/hud/stat_huchu95", width * 0.8451F, height * 0.3085F, 1.0D, 1.0D, (float) (width * 0.1473F), (float) (height * 0.3F));
                else if(Data.Player_Name.contains("삼식"))
                    DrawManager.drawTexture("gui/hud/stat_samsik23", width * 0.8451F, height * 0.3085F, 1.0D, 1.0D, (float) (width * 0.1473F), (float) (height * 0.3F));
                else if(Data.Player_Name.contains("눈꽃"))
                    DrawManager.drawTexture("gui/hud/stat_noonkkob", width * 0.8451F, height * 0.3085F, 1.0D, 1.0D, (float) (width * 0.1473F), (float) (height * 0.3F));
                else if(Data.Player_Name.contains("콩콩"))
                    DrawManager.drawTexture("gui/hud/stat_kong7", width * 0.8451F, height * 0.3085F, 1.0D, 1.0D, (float) (width * 0.1473F), (float) (height * 0.3F));
                else if(Data.Player_Name.contains("다주"))
                    DrawManager.drawTexture("gui/hud/stat_daju", width * 0.8451F, height * 0.3085F, 1.0D, 1.0D, (float) (width * 0.1473F), (float) (height * 0.3F));
                else if(Data.Player_Name.contains("루태"))
                    DrawManager.drawTexture("gui/hud/stat_rutaey", width * 0.8451F, height * 0.3085F, 1.0D, 1.0D, (float) (width * 0.1473F), (float) (height * 0.3F));
                else{
                    switch (Data.randomNum) {
                        case 0:
                            DrawManager.drawTexture("gui/hud/stat_d7297", width * 0.8451F, height * 0.3085F, 1.0D, 1.0D, (float) (width * 0.1473F), (float) (height * 0.3F));
                            break;
                        case 1:
                            DrawManager.drawTexture("gui/hud/stat_seoneng", width * 0.8451F, height * 0.3085F, 1.0D, 1.0D, (float) (width * 0.1473F), (float) (height * 0.3F));
                            break;
                        case 2:
                            DrawManager.drawTexture("gui/hud/stat_huchu95", width * 0.8451F, height * 0.3085F, 1.0D, 1.0D, (float) (width * 0.1473F), (float) (height * 0.3F));
                            break;
                        case 3:
                            DrawManager.drawTexture("gui/hud/stat_samsik23", width * 0.8451F, height * 0.3085F, 1.0D, 1.0D, (float) (width * 0.1473F), (float) (height * 0.3F));
                            break;
                        case 4:
                            DrawManager.drawTexture("gui/hud/stat_noonkkob", width * 0.8451F, height * 0.3085F, 1.0D, 1.0D, (float) (width * 0.1473F), (float) (height * 0.3F));
                            break;
                        case 5:
                            DrawManager.drawTexture("gui/hud/stat_kong7", width * 0.8451F, height * 0.3085F, 1.0D, 1.0D, (float) (width * 0.1473F), (float) (height * 0.3F));
                            break;
                        case 6:
                            DrawManager.drawTexture("gui/hud/stat_daju", width * 0.8451F, height * 0.3085F, 1.0D, 1.0D, (float) (width * 0.1473F), (float) (height * 0.3F));
                            break;
                        case 7:
                            DrawManager.drawTexture("gui/hud/stat_rutaey", width * 0.8451F, height * 0.3085F, 1.0D, 1.0D, (float) (width * 0.1473F), (float) (height * 0.3F));
                            break;
                    }
                }
                String moneyString = Utils.convertMoneyToCommas(Data.Money);
                if(Minecraft.getMinecraft().displayWidth > 1600)
                    DrawManager.aadrawRightString(moneyString + "원", width * 0.9772F, height * 0.5203F, width * 0.0018F, new Color(96, 48, 23), false);
                else
                    DrawManager.drawRightString(moneyString + "원", width * 0.9802F, height * 0.5203F, width * 0.0018F, new Color(96, 48, 23), false);

                DrawManager.drawCenteredString(Data.Job,width * 0.9499F, height * 0.5740F, width * 0.0016F, new Color(96, 48, 23), false);
            }

            // 통화 요청
            if(Data.call_stat == 1){
                DrawManager.drawTexture("gui/call_request/frame", width * 0.8516F, height * 0.6990F, 1.0D, 1.0D, (float) (width * 0.1411F), (float) (height * 0.2333F));
                DrawManager.drawCenteredString(Data.CallRequest_Name,width * 0.9411F, height * 0.7877F, width * 0.0026F, new Color(0, 0, 0), false);

                if(Data.CallRequest_Name.contains("양띵"))
                    DrawManager.drawTexture("gui/call_request/d7297", width * 0.8631F, height * 0.7416F, 1.0D, 1.0D, (float) (width * 0.0458F), (float) (height * 0.0814F));
                else if(Data.CallRequest_Name.contains("서넹"))
                    DrawManager.drawTexture("gui/call_request/seoneng", width * 0.8631F, height * 0.7416F, 1.0D, 1.0D, (float) (width * 0.0458F), (float) (height * 0.0814F));
                else if(Data.CallRequest_Name.contains("후추"))
                    DrawManager.drawTexture("gui/call_request/huchu95", width * 0.8631F, height * 0.7416F, 1.0D, 1.0D, (float) (width * 0.0458F), (float) (height * 0.0814F));
                else if(Data.CallRequest_Name.contains("삼식"))
                    DrawManager.drawTexture("gui/call_request/samsik23", width * 0.8631F, height * 0.7416F, 1.0D, 1.0D, (float) (width * 0.0458F), (float) (height * 0.0814F));
                else if(Data.CallRequest_Name.contains("눈꽃"))
                    DrawManager.drawTexture("gui/call_request/noonkkob", width * 0.8631F, height * 0.7416F, 1.0D, 1.0D, (float) (width * 0.0458F), (float) (height * 0.0814F));
                else if(Data.CallRequest_Name.contains("콩콩"))
                    DrawManager.drawTexture("gui/call_request/kong7", width * 0.8631F, height * 0.7416F, 1.0D, 1.0D, (float) (width * 0.0458F), (float) (height * 0.0814F));
                else if(Data.CallRequest_Name.contains("다주"))
                    DrawManager.drawTexture("gui/call_request/daju", width * 0.8631F, height * 0.7416F, 1.0D, 1.0D, (float) (width * 0.0458F), (float) (height * 0.0814F));
                else if(Data.CallRequest_Name.contains("루태"))
                    DrawManager.drawTexture("gui/call_request/rutaey", width * 0.8631F, height * 0.7416F, 1.0D, 1.0D, (float) (width * 0.0458F), (float) (height * 0.0814F));
                else
                    DrawManager.drawTexture("gui/call_request/who", width * 0.8631F, height * 0.7416F, 1.0D, 1.0D, (float) (width * 0.0458F), (float) (height * 0.0814F));
            }

            // 타이머
            if(numberArr[5] > -1){
                DrawManager.drawTexture("gui/timer/frame", width * 0.8593F, height * 0.0231F, 1.0D, 1.0D, (float) (width * 0.1314F), (float) (height * 0.0891F));

                DrawManager.drawTexture("gui/timer/" + numberArr[0], width * 0.8702F, height * 0.0462F, 1.0D, 1.0D, (float) (width * 0.0172F), (float) (height * 0.0428F));
                DrawManager.drawTexture("gui/timer/" + numberArr[1], width * 0.8985F, height * 0.0462F, 1.0D, 1.0D, (float) (width * 0.0172F), (float) (height * 0.0428F));
                DrawManager.drawTexture("gui/timer/" + numberArr[2], width * 0.9163F, height * 0.0462F, 1.0D, 1.0D, (float) (width * 0.0172F), (float) (height * 0.0428F));
                DrawManager.drawTexture("gui/timer/" + numberArr[3], width * 0.9426F, height * 0.0462F, 1.0D, 1.0D, (float) (width * 0.0172F), (float) (height * 0.0428F));
                DrawManager.drawTexture("gui/timer/" + numberArr[4], width * 0.9602F, height * 0.0462F, 1.0D, 1.0D, (float) (width * 0.0172F), (float) (height * 0.0428F));
            }

        }
        if (event.getType().equals(RenderGameOverlayEvent.ElementType.DEBUG)) {
            event.setCanceled(true);
            EntityPlayerSP entityPlayerSP = (Minecraft.getMinecraft()).player;
            GlStateManager.pushMatrix();
            GlStateManager.translate(5.0F, 15.0F, 1.0F);
            DrawManager.drawString("" + (int)((EntityPlayer)entityPlayerSP).posX + " / " + (int)((EntityPlayer)entityPlayerSP).posY + " / " + (int)((EntityPlayer)entityPlayerSP).posZ, 0.0D, 30.0D, 1.0D, Color.WHITE, false);
            DrawManager.drawString("" + ((EntityPlayer)entityPlayerSP).world.getBiome(entityPlayerSP.getPosition()).getBiomeName(), 0.0D, 41.0D, 1.0D, Color.WHITE, false);
            DrawManager.drawString("" + ClientProxy.getCardinalDirection((EntityPlayer)entityPlayerSP), 0.0D, 51.0D, 1.0D, Color.WHITE, false);
            GlStateManager.popMatrix();
        }
    }


}
