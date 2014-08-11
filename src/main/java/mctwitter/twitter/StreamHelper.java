package mctwitter.twitter;

import mctwitter.helpers.FileHelper;
import mctwitter.main.MCTwitter;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.auth.AccessToken;

public class StreamHelper {
    public static StreamListener listener = new StreamListener();

	public static void addListener() throws IllegalStateException, TwitterException{
		TwitterStream twitter = new TwitterStreamFactory().getInstance();
		
		AccessToken accessToken = Auth.loadAccessToken(Integer.parseInt(FileHelper.getPin()));
	    String consumerKey = "mpqrPi60iFySt3zjST0mNImRZ";
		String consumerSecret = "lUWaHnhZZmrKK75wBBfsYhJKjMEFlqdnhvIT1dzzZ1MRjKzLO6";
		
		twitter.setOAuthConsumer(consumerKey, consumerSecret);	    
	    twitter.setOAuthAccessToken(accessToken);
	    	    
	    twitter.addListener(listener);
	    twitter.user();
	}
	
	public static void event(){
		
	}
}