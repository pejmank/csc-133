package com.mycompany.a3;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class PlayP extends Command{
	private GameWorld gw;
	private Game ga;
	private Button b;
	private MapView map;
	public PlayP(String command, GameWorld gw, Game g, Button a, MapView m) {
		super(command);
		this.gw = gw;
		this.ga = g;
		b = a;
		map = m;
	}
	
	public void actionPerformed(ActionEvent e) {
			if(b.getText() == "Pause") {
				ga.timer.cancel();
				gw.pauseSound();
				b.setText("Play");
				this.disableButton();
			}
			else {
				map.reset();
				b.setText("Pause");
				gw.playSound();
				ga.timer.schedule(ga.time, true, ga);
				this.EnableButton();
			}
			ga.s = b.getText();
			
			
	
	
	}
	public void disableButton() {
		ga.accelButton.setEnabled(false);
		ga.Brake.setEnabled(false);
		ga.changeStrategies.setEnabled(false);
		ga.left.setEnabled(false);
		ga.Right.setEnabled(false);
		ga.removeKeyListener('a', ga.accelerate);
		ga.removeKeyListener('l', ga.steerLeft);
		ga.removeKeyListener('r', ga.steerRight);
		ga.removeKeyListener('b', ga.brake);
	}
	public void EnableButton() {
		ga.accelButton.setEnabled(true);
		ga.Brake.setEnabled(true);
		ga.changeStrategies.setEnabled(true);
		ga.left.setEnabled(true);
		ga.Right.setEnabled(true);
		ga.addKeyListener('a', ga.accelerate);
		ga.addKeyListener('l', ga.steerLeft);
		ga.addKeyListener('r', ga.steerRight);
		ga.addKeyListener('b', ga.brake);
		
		
	}
	
}
