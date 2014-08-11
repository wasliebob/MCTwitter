package mctwitter.gui;

import java.awt.Color;

import mctwitter.twitter.Auth;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.StatCollector;

import org.lwjgl.input.Keyboard;

import twitter4j.Twitter;
import twitter4j.auth.RequestToken;

public class GuiPin extends GuiScreen{
	public GuiPin(GuiScreen parent, Twitter twitter, RequestToken token){
		this.parent = parent;
		this.twitter = twitter;
		this.token = token;
	}
    private GuiScreen parent;
    private Twitter twitter;
    private RequestToken token;
    
    private GuiTextField pin;
    
    @SuppressWarnings("unchecked")
	@Override
    public void initGui(){
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(1, (this.width / 2) - 175, (this.height - 44), 350, 20, "Save"));

        /** Exit Button */
        this.buttonList.add(new GuiButton(0, (this.width / 2) - 175, (this.height - 24), 350, 20, "Back"));
        
        pin = new GuiTextField(Minecraft.getMinecraft().fontRenderer, (this.width / 2) - 175, (this.height - 184), 350, 20);
        pin.setFocused(true);
        pin.setMaxStringLength(1000);
    }
    
    @Override
    public void keyTyped(char c, int i){
    	super.keyTyped(c, i);
    	
    	if(pin.isFocused())
    		pin.textboxKeyTyped(c, i);
    }

    @Override
    public void actionPerformed(GuiButton button){
    	if(button.id == 0)
    		this.mc.displayGuiScreen(this.parent);
    	if(button.id == 1){
    		try {
				Auth.setup(pin.getText(), twitter, token);
	    		this.mc.displayGuiScreen(this.parent);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    }
    
    @Override
    public void drawScreen(int x, int y, float renderPartialTicks){
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, StatCollector.translateToLocal("Twitter Verification"), this.width / 2, 82, Color.red.getRGB());        
        pin.drawTextBox();
        
        super.drawScreen(x, y, renderPartialTicks);
    }
    
    public int translateToOne(int value){
    	return value == 0 ? 1 : value;
    }
    
    @Override
    public void onGuiClosed(){
        Keyboard.enableRepeatEvents(false);
    }
}