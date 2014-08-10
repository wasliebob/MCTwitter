package mctwitter.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

import mctwitter.twitter.Auth;

public class GuiPin extends JFrame{
	public GuiPin(String string){		
		createGUI();
	}
	private static final long serialVersionUID = 1L;
	public JTextField bar;
	public String pin;
	
	public void createGUI() {
		Container window = this.getContentPane();
		bar = new JTextField(50);
		bar.addActionListener(new Action(this, bar));
		
		window.setLayout(new FlowLayout());
		window.add(bar);
		window.setBackground(new Color(255, 255, 255));
	}
	
	public class Action implements ActionListener{
		public Action(GuiPin gui, JTextField field){
			this.field = field;
			this.gui = gui;
		}
		JTextField field;
		GuiPin gui;
		
		@Override
		public void actionPerformed(ActionEvent e){
			this.gui.pin = field.getText();
			this.gui.dispose();
		}
	}
}