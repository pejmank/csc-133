package com.mycompany.a4;
import com.codename1.charts.models.Point;

public abstract class Cyborg extends Movable implements Isteerable{

	private int maximumSpeed = 50;
	private int energyLevel;
	private int energyConsumptionRate = 1;
	private int damageLevel = 0;
	private int lastBaseReached = 1;
	private int damageLimit;
	private GameWorld gw;
	
	Cyborg(Point p, int s, int col, int spd, int head, int enrgLevel, int dmgLimit, GameWorld g){
		super(p, s, col, spd, head);
		gw = g; // to have a reference to GameWorld 
		energyLevel = enrgLevel;
		damageLimit = dmgLimit;
		
	}
	public void reset(Point p, int s, int col, int spd, int head, int enrgLevel){
		super.reset(p, s, col, spd, head);
		energyLevel = enrgLevel;
		damageLevel = 0;
		lastBaseReached = 1;
	}

	public void accelerate(int accelerate) {
		if((maximumSpeed - damageLevel) >= super.speedGetter() + accelerate) {
			super.increaseSpeed(accelerate);
		}
	}
	
	public void brake(int brake) {
		if(super.speedGetter() - brake >= 0)
			super.decreaseSpeed(brake);
		else 
			System.out.println("The cyborg is at rest and Brake cant be applied");
	}
	
	public boolean damageLimit() {
		if(damageLevel < damageLimit)
			return false;
		else
			return true;
	}
	
	public void damageSpeedCheck() {
		if(maximumSpeed - damageLevel < super.speedGetter())
			super.decreaseSpeed(super.speedGetter() - (maximumSpeed - damageLevel) );
	}
	public void ChangeEnergyLevel() {
		energyLevel -= energyConsumptionRate;
	}
	public void steerLeft() {
		if(angleGetter() >= -35) {
			changeAngle(-5);
			setAngle(-5);
		}
		else 
			System.out.println("cant turn left maximum has reached");			
	}
	public void steerRight() {
		if(angleGetter() <= 35) {
			changeAngle(5);
			setAngle(5);
		}
		else
			System.out.println("cant turn right maximum has reached");
	}
	public void colidedWithCyborg() {
		damageLevel += 5;
		damageSpeedCheck();
		colorFader(0, 20, 20);
		
	}
	public void colidedWithDrone() {
		damageLevel += 3;
		damageSpeedCheck();
		colorFader(0, 15, 15);
	}
	public void collisionWithNPC() {
		this.increaseDamage(4);
		colorFader(0, 18, 18);
	}
	public void baseCollision(int i) {
		if((lastBaseReached + 1) == i)
			lastBaseReached = i;
			
	}
	public int getEnergyConsumptionRate() {
		return energyConsumptionRate;
	}
	public int getEnergyLevel() {
		return energyLevel;
	}
	public void increaseEnergy(int energy) {
	energyLevel += energy;
	}
	public void setEnergyLevel(int energy) {
		energyLevel = energy;
	}
	public int getLastBaseReached() {
		return lastBaseReached;
	}
	public int getDamageLevel() {
		return damageLevel;
	}
	public int getMaximumSpeed() {
		return maximumSpeed;
	}
	public void increaseDamage(int i) {
		this.damageLevel += i;
	}
	public void energyStationCollision(EnergyStation e) {
		this.increaseEnergy(e.getCapacity());
		e.setCapacity(0);
		gw.newEnergyStation();
		gw.getChargeSound().play();
	}
	
	public String toString() {
		return "Cyborg| Loc: "+ Math.round(this.locationGetter().getX()*10.0)/10.0 +", "+ Math.round(this.locationGetter().getY()*10.0)/10.0 +" Color: "+ this.colorGetter() +" Heading: "+ this.headingGetter() +" Speed: "+ this.speedGetter() +" Size: "+ this.sizeGetter() +" Max Speed: "+ this.maximumSpeed +" Energy Consumption Rate: "+ this.energyConsumptionRate;
	}
}
