package com.mycompany.a4;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;

public class Sound extends Command{
	private GameWorld gw;
	public Sound(String command, GameWorld gw){
		super(command);
		this.gw = gw;
	}
	
	
	public void actionPerformed(ActionEvent e) {
	
		gw.changeSound();
		System.out.print("perfoming action");
	}
}
