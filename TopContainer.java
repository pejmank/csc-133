package com.mycompany.a4;

import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;

public class TopContainer {

	Label livesLeft;
	Label time;
	Label plLastBase;
	Label plEnergyLevel;
	Label plDamageLevel;
	Label sound;
	Container topContainer = new Container(new FlowLayout());
	
	TopContainer(GameWorld gw){
		livesLeft = new Label("Lives: " + gw.livesGetter());
		time = new Label("time: " + gw.clockTickGetter());
		plLastBase = new Label("Player Last Base Reached: 0" );
		plEnergyLevel = new Label("Player Energy Level: 0");
		plDamageLevel= new Label("Player Damage Level: 0");
		sound = new Label("sound: OFF");
		time.getAllStyles().setMarginRight(100);

		topContainer.add(time);
		topContainer.add(livesLeft);
		topContainer.add(plLastBase);
		topContainer.add(plEnergyLevel);
		topContainer.add(plDamageLevel);
		topContainer.add(sound);
		
		
		
	}
	
	public Container containerGetter() {
		return topContainer;
		
	}


	
}
