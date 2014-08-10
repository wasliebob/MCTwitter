package mctwitter.commands;

import java.util.ArrayList;

import mctwitter.twitter.TwitterHelper;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class CommandTimeline extends CommandBase{

	@Override
	public String getCommandName() {
		return "timeline";
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return "/timeline";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] array) {
		if(sender instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)sender;
			if(array.length > 0){
				if(array[0] != null)
					view(player, array);
			}else{
				sendMessage(player, EnumChatFormatting.RED + "/timeline <amount of tweets>");
			}
		}
	}
	
	public void view(EntityPlayer player, String[] array){
		try{
			ArrayList<String> tweets = TwitterHelper.getTimeLine(Integer.parseInt(array[0]));
			
			for(String tweet : tweets)
				sendMessage(player, tweet);
		}catch (NumberFormatException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public int compareTo(Object arg0) {
		return 0;
	}
	
	public static void sendMessage(EntityPlayer player, String text){
		player.addChatComponentMessage(new ChatComponentText(text));
	}
}