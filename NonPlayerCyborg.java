package com.mycompany.a4;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class NonPlayerCyborg extends Cyborg implements IDrawble{
	
	private Istrategy S;
	 NonPlayerCyborg(Point p, int s, int col, int spd, int head, int enrgLevel, int dmgLimit, int strategy, GameWorld g){
		super(p, s, col, spd, head, enrgLevel, dmgLimit, g);
		strategyType = strategy;
	}
	 
	private Istrategy Strategy = new Strategy1();
	private int strategyType; 

	 public String toString() {
			return "NonPlayerCyborg| Loc: "+ Math.round(this.locationGetter().getX()*10.0)/10.0 +", "+ Math.round(this.locationGetter().getY()*10.0)/10.0 +" Color: "+ this.colorGetter() +" Heading: "+ this.headingGetter() +" Speed: "+ this.speedGetter() +" Size: "+ this.sizeGetter() +" Max Speed: "+ this.getMaximumSpeed() +" Energy Consumption Rate: "+ this.getEnergyConsumptionRate();
		}

	public void setStrategy( PlayerCyborg plcyborg, Base base) {
		if(strategyType == 1) {
			this.setAngle(Strategy.theStrategy1(plcyborg, this));
			strategyType = 2;
		} else {
			this.setAngle(Strategy.theStrategy2(base, this));
		}
		
		
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {

		g.setColor(this.getColor());
		
		 int xLoc = (int) (pCmpRelPrnt.getX()+this.locationGetter().getX());
		 int yLoc =  (int) (pCmpRelPrnt.getY()+this.locationGetter().getY());
		 
		 g.drawRect(xLoc - this.sizeGetter() / 2, yLoc - this.sizeGetter() / 2, this.sizeGetter(), this.sizeGetter());
		 
		 
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
		}else if(otherObject instanceof NonPlayerCyborg  || otherObject instanceof PlayerCyborg) {
			System.out.print(otherObject);
			this.collisionWithNPC();
		}
	}

	
}
