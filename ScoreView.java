package com.mycompany.a4;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;

public class ScoreView extends Container implements Observer{

Label elapsedTime, livesRemaining, lastBaseReached, EnergyLevel, damageLevel, sound;

	public ScoreView() {
		this.setLayout(new FlowLayout(this.CENTER));
		
		this.elapsedTime = new Label();
		this.livesRemaining = new Label();
		this.lastBaseReached = new Label();
		this.EnergyLevel = new Label();
		this.damageLevel = new Label();
		this.sound = new Label();
		this.addAll(elapsedTime, livesRemaining, lastBaseReached, EnergyLevel, damageLevel, sound);


	}
	public void update(Observable observable, Object data) {
		
		GameWorld gw = (GameWorld) observable;
		Cyborg cyborg = gw.getPlayerCyborg();
		boolean s = gw.getSound();

		
		this.elapsedTime.setText("Elapsed Time: " + gw.clockTickGetter());
		this.livesRemaining.setText("Lives Left: " + gw.livesGetter());

		if(s)
		this.sound.setText("Sound: on");
		else 
			this.sound.setText("Sound: off");
		this.lastBaseReached.setText("Player Last base Reached: " + gw.getPlayerCyborg().getLastBaseReached());
		this.EnergyLevel.setText("Player Energy Level: " + gw.getPlayerCyborg().getEnergyLevel());
		this.damageLevel.setText("Player Damage Level: " + gw.getPlayerCyborg().getDamageLevel());
		//this.addAll(elapsedTime, livesRemaining, lastBaseReached, EnergyLevel, damageLevel, sound);
		this.repaint();
	}
	
}
