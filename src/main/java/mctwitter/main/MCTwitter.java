package mctwitter.main;

import mctwitter.commands.CommandSend;
import mctwitter.commands.CommandTimeline;
import mctwitter.helpers.FileHelper;
import mctwitter.libs.LibMod;
import mctwitter.proxies.CommonProxy;
import mctwitter.twitter.Auth;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

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

    	if(!FileHelper.tokenFileExists() || !FileHelper.pinFileExists())
    		Auth.setup();
    	else if(FileHelper.tokenFileExists() && FileHelper.pinFileExists()){
    		Auth.auth();
    	}
    }

    @EventHandler
    public void init(FMLInitializationEvent event){}
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) throws Exception{}

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event){
    	event.registerServerCommand(new CommandTimeline());
    	event.registerServerCommand(new CommandSend());
    }
}