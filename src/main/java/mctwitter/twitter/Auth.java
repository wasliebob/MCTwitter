package mctwitter.twitter;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import mctwitter.gui.GuiPin;
import mctwitter.helpers.FileHelper;
import mctwitter.helpers.WebHelper;
import net.minecraft.client.gui.Gui;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class Auth {
	public static void setup() throws Exception{
	    Twitter twitter = TwitterFactory.getSingleton();
	    
	    String consumerKey = "mpqrPi60iFySt3zjST0mNImRZ";
	  	String consumerSecret = "lUWaHnhZZmrKK75wBBfsYhJKjMEFlqdnhvIT1dzzZ1MRjKzLO6";
	  	twitter.setOAuthConsumer(consumerKey, consumerSecret);
	  	
	    RequestToken requestToken = twitter.getOAuthRequestToken();
	    AccessToken accessToken = null;
    	GuiPin gui = new GuiPin(requestToken.getAuthorizationURL());
    	gui.setSize(575, 70);
    	gui.setVisible(true);
	    while (null == accessToken) {
	    	WebHelper.openURL(requestToken.getAuthorizationURL());
	    	
	    	String pin = gui.pin;
	    	while(pin == null){
	    		pin = gui.pin;
	    	}
	    	try{
	    		if(pin.length() > 0){
	    			accessToken = twitter.getOAuthAccessToken(requestToken, pin);
	    		}else{
	    			accessToken = twitter.getOAuthAccessToken();
	    		}
	    	}catch (TwitterException te) {
	    		if(401 == te.getStatusCode()){
	    			System.out.println("Unable to get the access token.");
	    		}else{
	    			te.printStackTrace();
	    		}
	    	}
	    }
	    FileHelper.createPinFile(gui.pin);
	    FileHelper.createTokenFile(accessToken.getToken(), accessToken.getTokenSecret());
	    
		Auth.auth();
	}
	
	public static void auth() throws Exception{
	    TwitterFactory factory = new TwitterFactory();
	    AccessToken accessToken = loadAccessToken(Integer.parseInt(FileHelper.getPin()));
	    Twitter twitter = factory.getInstance();
	    
	    String consumerKey = "mpqrPi60iFySt3zjST0mNImRZ";
		String consumerSecret = "lUWaHnhZZmrKK75wBBfsYhJKjMEFlqdnhvIT1dzzZ1MRjKzLO6";
		twitter.setOAuthConsumer(consumerKey, consumerSecret);	    
	    twitter.setOAuthAccessToken(accessToken);
	}
	
	public static AccessToken loadAccessToken(int useId){
		String token = FileHelper.getToken();
		String tokenSecret = FileHelper.getTokenSecret();
		return new AccessToken(token, tokenSecret);
	}
}