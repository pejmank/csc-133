package com.mycompany.a4;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class Position extends Command{
	private MapView map;
	public Position(String command, MapView m) {
		super(command);
		map = m;
	}
	public void actionPerformed(ActionEvent e) {
		map.setPosition();
	}
	
}
