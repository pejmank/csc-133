package com.mycompany.a3;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class Sounds {
	private Media m ;
	private GameWorld gw;
	public Sounds(String fileName, GameWorld g) {
		gw = g;
		try {
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/"+fileName);
			m = MediaManager.createMedia(is, "audi/wav") ;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void play() {
		if(gw.getSound())
		m.setTime(0);
		m.play();
	}
	
	public void pause() {
		m.pause();
	}
}
