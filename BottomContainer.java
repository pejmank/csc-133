package com.mycompany.a4;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;

public class BottomContainer {
	
	Button collideNPC;
	Button collideBase;
	Button collideEnergy;
	Button collideDrone;
	Button tick;
	Container BottomContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));

	
	BottomContainer(String  NPC, String Base, String Energy,  String Drone, String t){
		
		Button collideNPC = new Button(NPC);
		Button collideBase = new Button(Base);
		Button collideEnergy = new Button(Energy);
		Button collideDrone = new Button(Drone);
		Button tick = new Button(t);
		BottomContainer.add(collideNPC);
		BottomContainer.add(collideBase);
		BottomContainer.add(collideEnergy);
		BottomContainer.add(collideDrone);
		BottomContainer.add(tick);
		//collideNPC.getAllStyles().setMargin(0, 0, 300, 0);
		this.Style(collideNPC);
		this.Style(collideBase);
		this.Style(collideEnergy);
		this.Style(collideDrone);
		this.Style(tick);
		//tick.getAllStyles().setMargin(0, 0, 0, 0);
		//tick.getAllStyles().setPadding(4,4,0,0);

		

		
	}
	
	private void Style(Button button) {
		button.getAllStyles().setBgTransparency(255);
		button.getAllStyles().setBgColor(ColorUtil.BLUE);
		button.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		button.getAllStyles().setPadding(3, 3, 0, 0);
		//button.getAllStyles().setPaddingTop(3);
		//button.getAllStyles().setPaddingBottom(3);
		//button.getAllStyles().setPaddingLeft(0);

		
	}
	public Container containerGetter() {
		return  BottomContainer;
	}

}
