package com.mycompany.a4;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Display;
import com.codename1.charts.models.Point;

import java.util.Observable;
import java.util.Random;



public class GameWorld extends Observable {

	private GameCollection theGameCollection; 
	
	private int clockTick = 0; 
	private int livesRemaining = 3;
	private boolean sound = true;
	private int width = 775, height = 730; 
	private int numberOfBases = 5;
	public BGSound bgsound;
	public Sounds explosion;
	private Sounds chargeSound;
	private Sounds crash;

	Random r = new Random();
	GameWorld() {
		theGameCollection = new GameCollection();


}
	public int clockTickGetter() {
		return clockTick;
	}
	public void setHeight(int h) {
		height = h;
	}
	public void setWidth(int w) {
		width = w;
	}
	public int livesGetter() {
		return livesRemaining;
	}
	public void changeSound() {
		sound = !sound;
		this.changed();
	}
	private void changed() {
		this.setChanged();
		this.notifyObservers();
	}
	public GameCollection getGameCollection() {
		return theGameCollection;
	}
	public Sounds getChargeSound() {
		return chargeSound;
	}
	public Sounds getCrashSound() {
		return this.crash;
	}
	public void createSound() {
		bgsound = new BGSound("background.mp3");
		if(sound)
			bgsound.play();
		explosion = new Sounds("Explosion.wav", this);
		this.chargeSound = new Sounds("EnergyCharge.wav", this);
		this.crash = new Sounds("crash.wav", this);
	}
	public void playSound() {
		if(sound)
			bgsound.play();
		else
			bgsound.pause();
	}
	public void pauseSound() {
		bgsound.pause();
	}
	public void init() {
		this.theGameCollection.addBase(new Point(200,100), 40, ColorUtil.rgb(0, 0, 255), 1);
		this.theGameCollection.addBase(new Point(400,200), 40, ColorUtil.rgb(0, 0, 255), 2);
		this.theGameCollection.addBase(new Point(600,300), 40, ColorUtil.rgb(0, 0, 255), 3);
		this.theGameCollection.addBase(new Point(500,600), 40, ColorUtil.rgb(0, 0, 255), 4);
		this.theGameCollection.addBase(new Point(400,300), 40, ColorUtil.rgb(0, 0, 255), 5);
		
		this.theGameCollection.addPlayerCyborg(new Point(200,100), 30, ColorUtil.rgb(255, 0, 0), 5, 0, 1000 , 50, this);
		this.theGameCollection.addNonPlayerCyborg(new Point(300,100), 30, ColorUtil.rgb(255, 100, 0), 7, 0, 1300, 150, 1, this);
		this.theGameCollection.addNonPlayerCyborg(new Point(200,200), 30, ColorUtil.rgb(255, 100, 0), 7, 0, 1300, 150, 2, this );
		this.theGameCollection.addNonPlayerCyborg(new Point(250,300), 30, ColorUtil.rgb(255, 100, 0), 7, 0, 1300, 150, 2, this);

		
		this.theGameCollection.addDrone(new Point(r.nextInt(500) + 50, r.nextInt(500) + 50), 20 + r.nextInt(30),ColorUtil.rgb(255, 0, 255) , 5+ r.nextInt(5), r.nextInt(359));
		this.theGameCollection.addDrone(new Point(r.nextInt(500) + 50, r.nextInt(500) + 50), 20 + r.nextInt(30),ColorUtil.rgb(255, 0, 255) , 5+ r.nextInt(5), r.nextInt(359));

		this.theGameCollection.addEnergyStation(new Point(r.nextInt(500) + 50, r.nextInt(500) + 50), 40, ColorUtil.rgb(0, 255, 0), 30);
		this.theGameCollection.addEnergyStation(new Point(r.nextInt(500) + 50, r.nextInt(500) + 50), 40, ColorUtil.rgb(0, 255, 0), 30);
		this.changed();
		
		
		
		
		
	}
	public void createShockWave(Point p, int col, int spd) {
		this.theGameCollection.addShockWave(p, col, spd);
		this.changed();
	}
	public boolean getSound() {
		return sound;
	}
	public Cyborg getPlayerCyborg() {
		IIterator iter = theGameCollection.getIterator();
		while(iter.hasNext())
	{
		GameObject gameOb = (GameObject) iter.getNext();
			
			if (gameOb instanceof PlayerCyborg) {
				return (PlayerCyborg) gameOb;
			}
		}
		
		return null;
	}
	public void accelerate(int accel) {
		this.getPlayerCyborg().accelerate(accel);
		this.changed();
	}
	public void brake(int brake) {
		this.getPlayerCyborg().brake(brake);
		this.changed();
	}
	public void displayMap() {
		IIterator iter = theGameCollection.getIterator();
		while(iter.hasNext())
	{
		GameObject gameOb = (GameObject) iter.getNext();
			System.out.println(gameOb);
		}
	}
	public void steerLeft() {
		this.getPlayerCyborg().steerLeft();
		this.changed();

	}
	public void steerRight() {
		this.getPlayerCyborg().steerRight();
		this.changed();

	}
	public void colidedWithCyborg() {
		this.getPlayerCyborg().colidedWithCyborg();
		this.changed();

		
	}
	public void newEnergyStation() {
		this.theGameCollection.addEnergyStation(new Point(r.nextInt(500) + 50, r.nextInt(500) + 50), 40, ColorUtil.rgb(0, 255, 0), 30);					

	}
	public void baseCollision(int i) {
		this.getPlayerCyborg().baseCollision(i);
		this.changed();

	}
	
	public void collidedWithDrone() {
		
		this.getPlayerCyborg().colidedWithDrone();
		damageExceededCheck();
		this.changed(); 

		
	}
	
	private void damageExceededCheck(){ 
		if(this.getPlayerCyborg().damageLimit() || this.getPlayerCyborg().getEnergyLevel() < 1) {
			System.out.println("player loses a life, damage limit exceeded.");
			this.explosion.play();
			if(livesRemaining > 1)
			System.out.println("game is restarted, Lives Remining:" + (livesRemaining - 1));
			livesRemaining--;
			int checkBase = 0;
				IIterator iter = theGameCollection.getIterator();
				while(iter.hasNext())
			{
				GameObject gameOb = (GameObject) iter.getNext();
				
				if (gameOb instanceof PlayerCyborg) {
					((PlayerCyborg) gameOb).reset(new Point(200,100), 30, ColorUtil.rgb(255, 0, 0), 5, 0, 1000 );
				}
				if (gameOb instanceof NonPlayerCyborg) {
					((NonPlayerCyborg) gameOb).reset(new Point(r.nextInt(500) + 50, r.nextInt(500) + 50), 30, ColorUtil.rgb(255, 0, 0), 15, 0, 20 );
				}
				if(gameOb instanceof Drone) {
					
					((Drone) gameOb).reset(new Point(r.nextInt(500) + 50, r.nextInt(500) + 50), 20 + r.nextInt(30),ColorUtil.rgb(255, 0, 255) , 5+ r.nextInt(5), r.nextInt(359));		
					
				}	
				if(gameOb instanceof Base) {
					if(checkBase < 1) {
						((Base) gameOb).reset(new Point(200, 100), 40 ,ColorUtil.rgb(0, 0, 255) , checkBase + 1);
						checkBase++; // to set the first base as the same location as Cyborg
					}else {
					((Base) gameOb).reset(new Point(r.nextInt(500) + 50,r.nextInt(500) + 50), 40, ColorUtil.rgb(0, 0, 255), checkBase + 1);
						checkBase++;
					}
					
				}
				if(gameOb instanceof EnergyStation)
					((EnergyStation) gameOb).reset(new Point(r.nextInt(500) + 50, r.nextInt(500) + 50), 30, ColorUtil.rgb(0, 255, 0), 30);
			
			
			
			}
			this.clockTick = 0;
			this.changed();
		}
		if(livesRemaining < 1) {
			System.out.println("Game  over,  you  failed!");
			this.explosion.play();
			exit();
		}

	}

	public void clockTick(int time) {
		IIterator iter = theGameCollection.getIterator();
		while(iter.hasNext())
	{
		GameObject gameOb = (GameObject) iter.getNext();
			
			if(gameOb instanceof Drone) 
				((Drone) gameOb).randomSteer();
			
			if (gameOb instanceof Movable) {
				((Movable) gameOb).move(time, this.height, this.width);
			}
			
			if (gameOb instanceof Cyborg) {
				int ecRate = ((Cyborg) gameOb).getEnergyConsumptionRate();
				int energL = ((Cyborg) gameOb).getEnergyLevel();
				((Cyborg) gameOb).setEnergyLevel(energL - ecRate);
				damageExceededCheck();
			}
		}
		
		this.clockTick++;
		checkCollision();
		wonGame();
		this.changed();


	}
	
	public void displayState() {
		String elapsedTime = "Elapsed Time: " + this.clockTick;
		String livesLeft = "Lives Left: " + this.livesRemaining;
		
		PlayerCyborg plcyborg = (PlayerCyborg) this.getPlayerCyborg();
		String lastFlagReached = "Last Base Reached: " + plcyborg.getLastBaseReached();
		String foodLevel = "Energy Level: " + plcyborg.getEnergyLevel();
		String damageLevel = "Damage Level: " + plcyborg.getDamageLevel();
		
		System.out.println(elapsedTime +"\n"+ livesLeft +"\n"+ lastFlagReached +"\n"+ foodLevel +"\n"+ damageLevel);
		
	}
	public void wonGame() {
		if(this.getPlayerCyborg().getLastBaseReached() == numberOfBases) {
			System.out.println("Game  over,  you  win!  Total  time: " + clockTick);
			this.changed();
			exit();
		}
	}
	public void prompt() {
		System.out.println("are you sure you wCyborg to exit? press 'y' to exit or 'n' to continue");
	}
	public void exit() {
		Display.getInstance().exitApplication();
	}
	public void changeStrategies() {
		IIterator iter = theGameCollection.getIterator();
		PlayerCyborg pl = null;
		Base base = null;
		NonPlayerCyborg npc;
		while(iter.hasNext())
	{
		GameObject gameOb = (GameObject) iter.getNext();
		if(gameOb instanceof PlayerCyborg)
			pl = (PlayerCyborg)gameOb;
		if(gameOb instanceof Base)
			base = (Base)gameOb;
		if(gameOb instanceof NonPlayerCyborg) {
			npc = (NonPlayerCyborg)gameOb;
			npc.setStrategy(pl, base);
		}
			
			
	}
	this.changed();
	
	}
	public void checkCollision() {

		IIterator iter = theGameCollection.getIterator();
			  // check if moving caused any collisions
			while(iter.hasNext()){
				ICollider curObj = (ICollider)iter.getNext(); 
			// get a collidable object // check if this object collides with any OTHER object
			IIterator iter2 = theGameCollection.getIterator();
			while(iter2.hasNext()){
			ICollider otherObj = (ICollider) iter2.getNext(); // get a collidable object // check for collision
			if(otherObj!=curObj){// make sure it's not the SAME object 
			if(curObj.collidesWith((GameObject)otherObj)){
			curObj.handleCollision((GameObject)otherObj); }
			} }
		}
		this.changed();
	}

}
