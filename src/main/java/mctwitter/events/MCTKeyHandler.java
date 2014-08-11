package mctwitter.events;

import java.io.File;

import mctwitter.helpers.FileHelper;
import mctwitter.twitter.TwitterHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ScreenShotHelper;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;

public class MCTKeyHandler{
  public KeyBinding a1 = new KeyBinding("Screenshot Tweet", 71, "key.categories.misc");
  
  public MCTKeyHandler(){
    ClientRegistry.registerKeyBinding(a1);
  }
 
  @SubscribeEvent
  public void onKeyInput(KeyInputEvent e){
	  if(a1.getIsKeyPressed()){
		  if(FMLClientHandler.instance().getClient().inGameHasFocus){
			  EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			  Minecraft mc = Minecraft.getMinecraft();
			  
			  try {
				  File file = FileHelper.getFileLocation();
				  String fileName = "tweetScreen.png";
				  mc.ingameGUI.getChatGUI().printChatMessage(ScreenShotHelper.saveScreenshot(file, fileName, mc.displayWidth, mc.displayHeight, mc.getFramebuffer()));
				  System.out.println(FileHelper.getScreenshotLocation() + fileName);
				  TwitterHelper.placeScreen(new File(FileHelper.getScreenshotLocation() + fileName));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		  }
	  }
  }
}