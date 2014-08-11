package mctwitter.events;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import mctwitter.gui.GuiPin;
import mctwitter.helpers.FileHelper;
import mctwitter.helpers.WebHelper;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EventStartScreen {    
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void displayWarning(GuiOpenEvent e) throws TwitterException{
		if(e.gui instanceof GuiMainMenu && (!FileHelper.tokenFileExists() || !FileHelper.pinFileExists())){
			Twitter twitter = TwitterFactory.getSingleton();
		    
			String consumerKey = "mpqrPi60iFySt3zjST0mNImRZ";
			String consumerSecret = "lUWaHnhZZmrKK75wBBfsYhJKjMEFlqdnhvIT1dzzZ1MRjKzLO6";
			twitter.setOAuthConsumer(consumerKey, consumerSecret);
			  	
			RequestToken requestToken = twitter.getOAuthRequestToken();
			AccessToken accessToken = null;
			WebHelper.openURL(requestToken.getAuthorizationURL());
			
			
			e.gui = new GuiPin(e.gui, twitter, requestToken);
            MinecraftForge.EVENT_BUS.unregister(this);
		}
	}
}