package mctwitter.commands;

import java.util.ArrayList;

import mctwitter.twitter.TwitterHelper;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class CommandSend extends CommandBase{

	@Override
	public String getCommandName() {
		return "send";
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return "/send";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] array) {
		if(sender instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)sender;
			if(array.length > 0){
				if(array[0] != null)
					createMessage(player, array);
			}else{
				sendMessage(player, EnumChatFormatting.RED + "/send");
			}
		}
	}
	
	public void createMessage(EntityPlayer player, String[] args){
		String s = null;
		for(int i = 0; i < args.length; i++){
			String a = args[i];
			
			if(s == null)
				s = a;
			else
				s = s + " " + a;
		}
		placeTweet(player, s);
	}
	
	public void placeTweet(EntityPlayer player, String tweet){
		try{
			TwitterHelper.placeTweet(tweet);
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