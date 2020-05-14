package com.mycompany.a4;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;

public class Help extends Command{

	private GameWorld gw;
	private ShowHelp help;
	public Help(String command, GameWorld gw){
		super(command);
		this.gw = gw;
	}
	
	private class ShowHelp extends Form{
		ShowHelp(){
		this.setTitle("Help");
		Dialog.show("Help", "you can use the following keyboad keys: \n 'a’ (accelerate) \n 'b' (brake)  \n "
				+ " 'r' (right turn)\n" + " 'l' (left turn)\n ", "Ok", null);

		}
	}
	
	public void actionPerformed(ActionEvent e) {
	
		help = new ShowHelp();
		
		
	}
}
