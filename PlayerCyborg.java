package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class PlayerCyborg extends Cyborg implements IDrawble{
	private GameWorld gw; 
	private static PlayerCyborg plCyborg;
	private PlayerCyborg(Point p, int s, int col, int spd, int head, int enrgLevel, int dmgLimit, GameWorld g){
		super(p, s, col, spd, head, enrgLevel, dmgLimit, g);
		gw = g;
	}

	public static PlayerCyborg getPlayerCyborg(Point p, int s, int col, int spd, int head, int enrgLevel, int dmgLimit, GameWorld g) { 
		if (plCyborg == null)
			plCyborg =  new PlayerCyborg(p, s, col, spd, head, enrgLevel, dmgLimit, g);
		return plCyborg;
	}
	public String toString() {
		return "PlayerCyborg| Loc: "+ Math.round(this.locationGetter().getX()*10.0)/10.0 +", "+ Math.round(this.locationGetter().getY()*10.0)/10.0 +" Color: "+ this.colorGetter() +" Heading: "+ this.headingGetter() +" Speed: "+ this.speedGetter() +" Size: "+ this.sizeGetter() +" Max Speed: "+ this.getMaximumSpeed() +" Energy Consumption Rate: "+ this.getEnergyConsumptionRate();
	}

	public void draw(Graphics g, Point pComRelPrnt) {
		// TODO Auto-generated method stub
		 g.setColor(this.getColor());
		 int xLoc = (int) (pComRelPrnt.getX()+this.locationGetter().getX());
		 int yLoc =  (int) (pComRelPrnt.getY()+this.locationGetter().getY());
		 
		 g.fillRect( xLoc - this.sizeGetter() /2,yLoc - this.sizeGetter() / 2, this.sizeGetter(), this.sizeGetter());

	}
	public void colidedWithDrone() {
		this.increaseDamage(3);
		damageSpeedCheck();
		gw.getCrashSound().play();
		colorFader(0, 15, 15);
	}
	public void collisionWithNPC() {
		this.increaseDamage(4);
		gw.getCrashSound().play();
		colorFader(0, 18, 18);
	}
	
	public boolean collidesWith(GameObject otherObject) {
		// TODO Auto-generated method stub
		boolean result = false;
		int rect1x = (int)this.locationGetter().getX();
		int rect1y = (int)this.locationGetter().getY();
		int rect2x =  (int)otherObject.locationGetter().getX();
		int rect2y = (int)otherObject.locationGetter().getY();
		int rect1width = this.sizeGetter();
		int rect1height = this.sizeGetter();
		int rect2width = otherObject.sizeGetter();
		int rect2height = otherObject.sizeGetter();


			if (rect1x < rect2x + rect2width &&
					   rect1x + rect1width > rect2x &&
					   rect1y < rect2y + rect2height &&
					   rect1y + rect1height > rect2y){ result = true ; }
		
		return result;
	}

	public void handleCollision(GameObject otherObject) {
		// TODO Auto-generated method stub

		if(otherObject instanceof Base) {
			Base b = (Base)otherObject;
			this.baseCollision(b.getSequenceNumber());
		
		}else if (otherObject instanceof Drone) {
			this.colidedWithDrone();
		}else if(otherObject instanceof EnergyStation) {
			EnergyStation e = (EnergyStation)otherObject;
			if(0 < e.getCapacity())
			this.energyStationCollision(e);
		}else if(otherObject instanceof NonPlayerCyborg) {
			System.out.print(otherObject);
			this.collisionWithNPC();
		}
		
	}
}
