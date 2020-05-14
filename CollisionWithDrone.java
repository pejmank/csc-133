package com.mycompany.a4;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CollisionWithDrone extends Command{

	private GameWorld gw;
	public CollisionWithDrone(String command, GameWorld gw) {
		super(command);
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		this.gw.collidedWithDrone();
	}
}
