package com.mycompany.a4;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;

public class PlayerCyborg extends Cyborg implements IDrawble{
	private GameWorld gw; 
	private static PlayerCyborg plCyborg;
	private Transform myTranslation ;
	private Transform myRotation ;
	private Transform myScale ;
	Point hold = new Point(); 
	int e = 1;
	private PlayerCyborg(Point p, int s, int col, int spd, int head, int enrgLevel, int dmgLimit, GameWorld g){
		super(p, s, col, spd, head, enrgLevel, dmgLimit, g);
		gw = g;
		myTranslation = Transform.makeIdentity(); 
		myRotation = Transform.makeIdentity(); 
		myScale = Transform.makeIdentity();
		
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
		
		hold.setX((pComRelPrnt.getX()+this.locationGetter().getX()));
		hold.setY((pComRelPrnt.getY()+this.locationGetter().getY()));
			
		Transform gXform = Transform.makeIdentity();
		g.getTransform(gXform);
		Transform gOrigXform = gXform.copy(); //save the original xform

		gXform.translate(pComRelPrnt.getX(),pComRelPrnt.getY());
		gXform.translate(myTranslation.getTranslateX(), myTranslation.getTranslateY());
		gXform.concatenate(myRotation); 
		gXform.scale(myScale.getScaleX(), myScale.getScaleY());
		gXform.translate(-pComRelPrnt.getX(),-pComRelPrnt.getY());
		g.setTransform(gXform);
		 g.setColor(this.getColor());
		 Legs [] legs= new Legs[2];
		 legs[0] = new Legs((int)this.locationGetter().getX(), (int)this.locationGetter().getY());
		 legs[0].translate(0, e * 5);
		 legs[0].draw(g, pComRelPrnt, this.locationGetter(), 0);
		 legs[1] = new Legs((int)this.locationGetter().getX(), (int)this.locationGetter().getY());
		 legs[1].translate(0, e * 5);
		 legs[0].draw(g, pComRelPrnt, this.locationGetter(), this.sizeGetter());

		 int xLoc = (int) (pComRelPrnt.getX()+this.locationGetter().getX());
		 int yLoc =  (int) (pComRelPrnt.getY()+this.locationGetter().getY());
		 
		 g.fillRect( xLoc - this.sizeGetter() /2,yLoc - this.sizeGetter() / 2, this.sizeGetter(), this.sizeGetter());
		 g.setTransform(gOrigXform);
		 e = -e;
	}
	public void colidedWithDrone() {
		this.increaseDamage(3);
		damageSpeedCheck();
		gw.getCrashSound().play();
		colorFader(0, 15, 15);
		gw.createShockWave(hold, ColorUtil.BLACK, 5);
		//v.draw(g, pCmpRelPrnt);
	}
	public void collisionWithNPC() {
		this.increaseDamage(4);
		gw.getCrashSound().play();
		colorFader(0, 18, 18);
		gw.createShockWave(hold, ColorUtil.BLACK, 5);
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
	public void move(int time, int height, int width) {
		setAngle(this.angleGetter());
		Point oldLocation = this.locationGetter();
   	 int currentX = (int) this.locationGetter().getX();
   	 int currentY = (int) this.locationGetter().getY();
		
   	 double dist = ( this.speedGetter() * (time/time) ) * .75;

		Double deltaXDouble = (Math.cos(Math.toRadians(90 - this.headingGetter())) * dist);
		Double deltaYDouble = (Math.sin(Math.toRadians(90 - this.headingGetter())) * dist);
		Integer deltaX = deltaXDouble.intValue();
		Integer deltaY = deltaYDouble.intValue();
		
		currentX += deltaXDouble * dirX;
		currentY += deltaYDouble * dirY;
		
		Point newLocation = new Point((currentX), (currentY));
		this.locationSetter(newLocation);
				
	}
}
