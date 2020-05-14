package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.mycompany.a3.Sound;
import com.codename1.ui.events.ActionEvent; 
import java.lang.String;
import java.util.Timer; 

public class Game extends Form implements Runnable{
	UITimer timer;
	private GameWorld gw;
	private MapView map;
	private ScoreView scoreView;
	int time = 100;
	public String s = "S";
	Button playPause;
	Button accelButton;
	Button left;
	Button changeStrategies;
	Button Brake;
	Button Right;
	SteerRight steerRight;
	SteerLeft steerLeft;
	Accelerate accelerate;
	Brake brake;
	Command sideMenu;
	Help help;

	Game(){
		gw = new GameWorld();
		gw.init();
		timer = new UITimer(this);
		
		timer.schedule(time, true, this);

		 map = new MapView(gw, this);
		 scoreView = new ScoreView();
		gw.addObserver(map);
		gw.addObserver(scoreView);

		Toolbar  myToolBar  = new Toolbar();
		
		help = new Help("Help", gw);
		steerRight = new SteerRight("turn right", gw);
		steerLeft = new SteerLeft("turn left", gw);
		accelerate = new Accelerate("accelerate", gw);
		brake = new Brake("brake", gw);
		ChangeStrategies change = new ChangeStrategies("change strategies", gw);
		Exit exit = new Exit("Exit", gw);
		About about = new About("About", gw);
		Sound sound = new Sound("sound", gw);


		
		this.setToolbar(myToolBar);
		myToolBar.setTitle("Sili-Challenge game");
		myToolBar.addCommandToRightBar(help);
		this.setLayout(new BorderLayout());
		sideMenu = new Command("Side Menu");
		myToolBar.addCommandToLeftSideMenu(sideMenu);

		Container leftContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		Container rightContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		Container bottomContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
		
		Container south =  new Container();
		south.getAllStyles().setBorder(Border.createLineBorder(3,  ColorUtil.BLACK));
		bottomContainer.getAllStyles().setMargin(0, 0, 200, 200);
		south.add(bottomContainer);

		this.add(BorderLayout.NORTH, this.scoreView);

		this.add(BorderLayout.SOUTH,south);
		this.add(BorderLayout.CENTER, this.map);


		myToolBar.addMaterialCommandToLeftSideMenu("Accelerate", FontImage.MATERIAL_ALBUM, accelerate);
		myToolBar.addMaterialCommandToLeftSideMenu("Exit", FontImage.MATERIAL_ALBUM, exit);
		myToolBar.addMaterialCommandToLeftSideMenu("About", FontImage.MATERIAL_ALBUM, about);
		myToolBar.addMaterialCommandToLeftSideMenu("sound", FontImage.MATERIAL_ALBUM, sound);

		
		accelButton = new Button("Acceleration");
		left = new Button("Left");
		changeStrategies = new Button("Change Strategies");
		Brake = new Button("brake");
		Right = new Button("Right");
		Button playPause = new Button("Pause");
		Button position = new Button("Position");
		accelButton.setCommand(accelerate);
		left.setCommand(steerLeft);
		Brake.setCommand(brake);
		Right.setCommand(steerRight);
		Position pos = new Position("Position", map);
		position.setCommand(pos);
		PlayP play = new PlayP("Pause", gw,this, playPause, map);
		playPause.setCommand(play);
		changeStrategies.setCommand(change);


		styleButton(Brake, 2,2,2,2);
		styleButton(Right, 2,2,2,2);
		styleButton(accelButton, 2,2,2,2);
		styleButton(left, 2,2,3,3);
		styleButton(changeStrategies, 2,2,3,3);
		styleButton(playPause, 2,2,3,3);
		styleButton(position, 2,2,3,3);

		
		scoreView.update(gw, 0);
		rightContainer.add(Brake);
		rightContainer.add(Right);

		this.add(BorderLayout.EAST, rightContainer);

		leftContainer.getAllStyles().setPadding(Component.TOP, 100);
		leftContainer.add(accelButton);
		leftContainer.add(left);
		leftContainer.add(changeStrategies);
		leftContainer.getAllStyles().setBgTransparency(40);
		leftContainer.getAllStyles().setBgColor(ColorUtil.GRAY);
		playPause.getAllStyles().setMargin(0, 0 , 200, 0);
		bottomContainer.add(playPause);
		bottomContainer.add(position);

	

		add(BorderLayout.WEST, leftContainer);
		this.addKeyListener('a', accelerate);
		this.addKeyListener('l', steerLeft);
		this.addKeyListener('r', steerRight);
		this.addKeyListener('b', brake);
		this.show();
		gw.createSound();
	}
	public Button getPlayButton() {
		return playPause;
	}
	public Button getaccelerate() {
		return this.accelButton;
	}
	public void styleButton(Button b, int top, int bottom, int left, int right) {

		b.getAllStyles().setBgTransparency(255);
		b.getAllStyles().setBgColor(ColorUtil.BLUE);
		b.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		b.getAllStyles().setPadding(top, bottom, left ,right);
	}
	public void run() {

		gw.clockTick(time);
		if(gw.bgsound != null)
		gw.playSound();
	}
}
		

