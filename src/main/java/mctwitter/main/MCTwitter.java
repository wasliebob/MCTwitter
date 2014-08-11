package mctwitter.main;

import mctwitter.commands.CommandNotifications;
import mctwitter.commands.CommandSend;
import mctwitter.commands.CommandTimeline;
import mctwitter.events.EventStartScreen;
import mctwitter.helpers.FileHelper;
import mctwitter.libs.LibMod;
import mctwitter.proxies.CommonProxy;
import mctwitter.twitter.Auth;
import mctwitter.twitter.StreamHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.relauncher.Side;

@Mod(name=LibMod.modName, modid=LibMod.modId, version=LibMod.version)
public class MCTwitter {
    @SidedProxy(clientSide = "mctwitter.proxies.ClientProxy", serverSide = "mctwitter.proxies.CommonProxy")
    public static CommonProxy proxy;
    
    @Instance
    public static MCTwitter instance;
    public static String configLocation;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) throws Exception{
    	proxy.load();
    	configLocation = event.getModConfigurationDirectory().toString();
    	
    	Config.loadConfig(event);
    	FileHelper.createMainFolder();
    	
    	if(FileHelper.tokenFileExists() && FileHelper.pinFileExists()){
    		Auth.auth();
    	}
    }

    @EventHandler
    public void init(FMLInitializationEvent event){}
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) throws Exception{
    	if(event.getSide() == Side.CLIENT)
    		MinecraftForge.EVENT_BUS.register(new EventStartScreen());
    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event) throws Exception{
    	event.registerServerCommand(new CommandTimeline());
    	event.registerServerCommand(new CommandSend());
    	event.registerServerCommand(new CommandNotifications());
    	
    	if(Config.realTime)
    		StreamHelper.addListener();
    }
}