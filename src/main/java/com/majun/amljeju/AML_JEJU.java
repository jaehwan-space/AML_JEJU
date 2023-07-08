package com.majun.amljeju;

import com.majun.amljeju.network.GUIHandler;
import com.majun.amljeju.network.PacketSystem;
import com.majun.amljeju.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.apache.logging.log4j.Logger;

@Mod(modid = AML_JEJU.MODID, name = AML_JEJU.NAME, version = AML_JEJU.VERSION, acceptedMinecraftVersions = "[1.12.2]")
public class AML_JEJU
{
    public static final String MODID = "amljeju";
    public static final String NAME = "AML_JEJU";
    public static final String VERSION = "1.0";
    public static float guiScale = 0.5F;

    private static Logger logger;

    @SidedProxy(clientSide = "com.majun.amljeju.proxy.ClientProxy", serverSide = "com.majun.amljeju.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance("amljeju")
    public static AML_JEJU instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, (IGuiHandler) new GUIHandler());
        PacketSystem.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

}
