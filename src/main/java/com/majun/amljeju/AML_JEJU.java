package com.majun.amljeju;

import com.majun.amljeju.command.CommandShop;
import com.majun.amljeju.manager.InvHandler;
import com.majun.amljeju.network.NetworkManager;
import com.majun.amljeju.proxy.ClientProxy;
import com.majun.amljeju.proxy.CommonProxy;
import net.minecraft.command.ICommand;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;

@Mod(modid = Data.MODID, name = Data.NAME, version = Data.VERSION, acceptedMinecraftVersions = "[1.12.2]")
public class AML_JEJU
{
    public static Logger logger;

    @SidedProxy(clientSide = "com.majun.amljeju.proxy.ClientProxy", serverSide = "com.majun.amljeju.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance("amljeju")
    public static AML_JEJU instance;

    public AML_JEJU() {
        instance = this;
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, (IGuiHandler) new InvHandler());
        NetworkManager.setup();  //마크 실행 시 패킷 실행
        if(FMLCommonHandler.instance().getSide().isClient())
            ClientProxy.updateIcon(); // 아이콘 변경
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        event.registerServerCommand((ICommand) new CommandShop());
    }

}
