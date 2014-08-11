package mctwitter.proxies;

import mctwitter.events.MCTKeyHandler;
import cpw.mods.fml.common.FMLCommonHandler;

public class ClientProxy extends CommonProxy{
	
	@Override
	public void bindKey(){
		FMLCommonHandler.instance().bus().register(new MCTKeyHandler());
	}
}