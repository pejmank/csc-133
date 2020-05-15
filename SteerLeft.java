package com.mycompany.a4;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class SteerLeft extends Command{

	private GameWorld gw;
	public SteerLeft(String command, GameWorld gw) {
		super(command);
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.steerLeft();
	}
}
