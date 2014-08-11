package mctwitter.main;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class Config {

	public static void loadConfig(FMLPreInitializationEvent e){
		Configuration config = new Configuration(e.getSuggestedConfigurationFile());
		
		realTime = config.get(Configuration.CATEGORY_GENERAL, "realTime", true, "Set this to true to enable real time tweets, following, unfollowing").getBoolean(realTime);		
		
		config.save();
	}
	public static boolean realTime;
}