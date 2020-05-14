package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class SteerLeft extends Command{

	private GameWorld gw;
	public SteerLeft(String command, GameWorld gw) {
		super(command);
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.print("left");
		gw.steerLeft();
	}
}
