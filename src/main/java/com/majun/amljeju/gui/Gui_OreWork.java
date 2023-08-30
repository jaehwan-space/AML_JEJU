package com.majun.amljeju.gui;

import com.majun.amljeju.network.NetworkManager;
import com.majun.amljeju.network.SmeltButton;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

import static com.majun.amljeju.gui.Gui_OreWork.GUIButtonPressedMessageHandler.handleButtonAction;

public class Gui_OreWork extends GuiContainer {
    private EntityPlayer player;
    private int x, y, z;

    public Gui_OreWork(World world, int x, int y, int z, EntityPlayer player){
        super(new Container_OreWork(world, x, y, z, player));
        this.player = player;
        this.x = x;
        this.y = y;
        this.z = z;

    }

    public void initGui(){
        super.initGui();
        buttonList.clear();
        addButton(new GuiButtonImage(0, (int)(width * 0.4729F), (int)(height * 0.3675F), (int)(width * 0.0552F), (int)(height * 0.0425F), "orework/button"));
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        drawDefaultBackground();
        renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        drawDefaultBackground();
        GL11.glPushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.translate((width / 2.0F) - 360.0D, (height / 2.0F) - 202.5D, 1.0D);  //그리기 시작하는 위치 맞추기(리소스 사이즈에서 4나누기 하면  350, 270 자리 값 나옴)
        GlStateManager.scale(0.375D, 0.3755, 0.375D);
        GL11.glBlendFunc(770, 771);
        mc.getTextureManager().bindTexture(new ResourceLocation("amljeju", "textures/gui/orework/background.png"));
        drawModalRectWithCustomSizedTexture(0, 0, 0.0F, 0.0F, 1920, 1080, 1920.0F, 1080.0F);
        GlStateManager.disableBlend();
        GlStateManager.disableAlpha();
        GL11.glPopMatrix();
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
    }
    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        for (GuiButton button : buttonList) {
            boolean hovered = (mouseX >= button.x && mouseY >= button.y && mouseX < button.x + button.width && mouseY < button.y + button.height);
            if (button.visible && hovered) {
                if(button.id == 0){
                    NetworkManager.sendToServer(new GUIButtonPressedMessage(button.id, x, y, z));
                    handleButtonAction(player, button.id, x, y, z);
                }
            }
        }
    }

    public void onGuiClosed() {super.onGuiClosed();}
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

    public static class GUIButtonPressedMessageHandler implements IMessageHandler<GUIButtonPressedMessage, IMessage> {
        @Override
        public IMessage onMessage(GUIButtonPressedMessage message, MessageContext context) {
            EntityPlayerMP entity = context.getServerHandler().player;
            entity.getServerWorld().addScheduledTask(() -> {
                int buttonID = message.buttonID;
                int x = message.x;
                int y = message.y;
                int z = message.z;
                handleButtonAction(entity, buttonID, x, y, z);
            });
            return null;
        }

        static void handleButtonAction(EntityPlayer entity, int buttonID, int x, int y, int z) {
            World world = entity.world;
            // security measure to prevent arbitrary chunk generation
            if (!world.isBlockLoaded(new BlockPos(x, y, z)))
                return;
            if (buttonID == 0) {
                {
                    java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
                    $_dependencies.put("entity", entity);
                    SmeltButton.executeProcedure($_dependencies);
                }
            }
        }
    }
    public static class GUIButtonPressedMessage implements IMessage {
        int buttonID, x, y, z;
        public GUIButtonPressedMessage() {
        }

        public GUIButtonPressedMessage(int buttonID, int x, int y, int z) {
            this.buttonID = buttonID;
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public void toBytes(io.netty.buffer.ByteBuf buf) {
            buf.writeInt(buttonID);
            buf.writeInt(x);
            buf.writeInt(y);
            buf.writeInt(z);
        }

        @Override
        public void fromBytes(io.netty.buffer.ByteBuf buf) {
            buttonID = buf.readInt();
            x = buf.readInt();
            y = buf.readInt();
            z = buf.readInt();
        }
    }



}
