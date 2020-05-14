package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class Brake extends Command{
	private GameWorld gw;
	public Brake(String command, GameWorld gw) {
		super(command);
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		this.gw.brake(1);
		System.out.print("brake");

	}

}