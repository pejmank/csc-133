package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class SteerRight extends Command {
	

	private GameWorld gw;
	public SteerRight(String command, GameWorld gw) {
		super(command);
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		this.gw.steerRight();
	}
	


}
