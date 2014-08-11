package mctwitter.twitter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import mctwitter.helpers.FileHelper;
import net.minecraft.util.EnumChatFormatting;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TwitterHelper {
	public static ArrayList<String> getTimeLine(int number) throws Exception{
		TwitterFactory factory = new TwitterFactory();
		AccessToken accessToken = Auth.loadAccessToken(Integer.parseInt(FileHelper.getPin()));
	    Twitter twitter = factory.getInstance();
	    
	    String consumerKey = "mpqrPi60iFySt3zjST0mNImRZ";
		String consumerSecret = "lUWaHnhZZmrKK75wBBfsYhJKjMEFlqdnhvIT1dzzZ1MRjKzLO6";
		twitter.setOAuthConsumer(consumerKey, consumerSecret);	    
	    twitter.setOAuthAccessToken(accessToken);
		
	    ArrayList<String> tw = new ArrayList<String>();

		List<Status> tweets = twitter.getHomeTimeline();
		
		for(int i = 0; i < number; i++)
			tw.add(EnumChatFormatting.AQUA + tweets.get(i).getUser().getName() + ": " + EnumChatFormatting.WHITE + tweets.get(i).getText());
		return tw;
	}
	
	public static void placeTweet(String tweet) throws Exception{
		TwitterFactory factory = new TwitterFactory();
		AccessToken accessToken = Auth.loadAccessToken(Integer.parseInt(FileHelper.getPin()));
	    Twitter twitter = factory.getInstance();
	    
	    String consumerKey = "mpqrPi60iFySt3zjST0mNImRZ";
		String consumerSecret = "lUWaHnhZZmrKK75wBBfsYhJKjMEFlqdnhvIT1dzzZ1MRjKzLO6";
		twitter.setOAuthConsumer(consumerKey, consumerSecret);	    
	    twitter.setOAuthAccessToken(accessToken);
		
	    twitter.updateStatus(tweet);
	}
	
	public static void placeScreen(File file) throws Exception{
		TwitterFactory factory = new TwitterFactory();
		AccessToken accessToken = Auth.loadAccessToken(Integer.parseInt(FileHelper.getPin()));
	    Twitter twitter = factory.getInstance();
	    
	    String consumerKey = "mpqrPi60iFySt3zjST0mNImRZ";
		String consumerSecret = "lUWaHnhZZmrKK75wBBfsYhJKjMEFlqdnhvIT1dzzZ1MRjKzLO6";
		twitter.setOAuthConsumer(consumerKey, consumerSecret);	    
	    twitter.setOAuthAccessToken(accessToken);
	    
	    StatusUpdate stat = new StatusUpdate(null);
	    stat.setMedia(new File(FileHelper.getScreenshotLocation() + file.getName()));
	    twitter.updateStatus(stat);
	}
	
	public static ArrayList<String> getNotifications(int number) throws Exception{
		TwitterFactory factory = new TwitterFactory();
		AccessToken accessToken = Auth.loadAccessToken(Integer.parseInt(FileHelper.getPin()));
	    Twitter twitter = factory.getInstance();
	    
	    String consumerKey = "mpqrPi60iFySt3zjST0mNImRZ";
		String consumerSecret = "lUWaHnhZZmrKK75wBBfsYhJKjMEFlqdnhvIT1dzzZ1MRjKzLO6";
		twitter.setOAuthConsumer(consumerKey, consumerSecret);	    
	    twitter.setOAuthAccessToken(accessToken);
		
	    ArrayList<String> tw = new ArrayList<String>();

		List<Status> tweets = twitter.getMentionsTimeline();
		
		for(int i = 0; i < number; i++)
			tw.add(EnumChatFormatting.AQUA + tweets.get(i).getUser().getScreenName() + ": " + EnumChatFormatting.WHITE + tweets.get(i).getText());
		return tw;
	}
}