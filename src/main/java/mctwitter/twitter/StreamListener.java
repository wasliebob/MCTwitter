package mctwitter.twitter;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import twitter4j.DirectMessage;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.User;
import twitter4j.UserList;
import twitter4j.UserStreamListener;

public class StreamListener implements UserStreamListener{	
	@Override
	public void onStatus(Status status){
		Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.AQUA + status.getUser().getName() + ": " + EnumChatFormatting.WHITE + status.getText()));
	}

	@Override
	public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
		
	}

	@Override
	public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
		
	}

	@Override
	public void onScrubGeo(long userId, long upToStatusId) {
		
	}

	@Override
	public void onStallWarning(StallWarning warning) {
		
	}

	@Override
	public void onException(Exception ex) {
		
	}

	@Override
	public void onDeletionNotice(long directMessageId, long userId) {
		
	}

	@Override
	public void onFriendList(long[] friendIds) {
		
	}

	@Override
	public void onFavorite(User source, User target, Status favoritedStatus) {
	}

	@Override
	public void onUnfavorite(User source, User target, Status unfavoritedStatus) {
		
	}

	@Override
	public void onFollow(User source, User followedUser){
		Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GREEN + source.getName() + " Started following " + followedUser.getScreenName()));
	}

	@Override
	public void onUnfollow(User source, User unfollowedUser) {
		Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + source.getName() + " unfollowed " + unfollowedUser.getScreenName()));
	}

	@Override
	public void onDirectMessage(DirectMessage directMessage) {
		
	}

	@Override
	public void onUserListMemberAddition(User addedMember, User listOwner,
			UserList list) {
		
	}

	@Override
	public void onUserListMemberDeletion(User deletedMember, User listOwner,
			UserList list) {
		
	}

	@Override
	public void onUserListSubscription(User subscriber, User listOwner,
			UserList list) {
		
	}

	@Override
	public void onUserListUnsubscription(User subscriber, User listOwner,
			UserList list) {
		
	}

	@Override
	public void onUserListCreation(User listOwner, UserList list) {		
	}

	@Override
	public void onUserListUpdate(User listOwner, UserList list) {
		
	}

	@Override
	public void onUserListDeletion(User listOwner, UserList list) {		
	}

	@Override
	public void onUserProfileUpdate(User updatedUser) {		
	}

	@Override
	public void onBlock(User source, User blockedUser) {		
	}

	@Override
	public void onUnblock(User source, User unblockedUser) {		
	}
}