package com.mycompany.a4;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;

public class About extends Command{
	
	private GameWorld gw;
	private ShowAbout about;
	public About(String command, GameWorld gw){
		super(command);
		this.gw = gw;
	}
	
	private class ShowAbout extends Form{
		ShowAbout(){
		this.setTitle("About");
		Dialog.show("About", "Pezhman Kaviani \n CSC 133 \n Sili-Challenge Game", "Ok", null);

		}
	}
	
	public void actionPerformed(ActionEvent e) {
	
		about = new ShowAbout();
		
		
	}


}
