package com.mycompany.a4;

import com.codename1.charts.models.Point;

import java.lang.Math;

public class Movable extends GameObject{
	
	private int angle;
	private int heading; 
	private int speed; 
	Movable(Point p, int s, int col, int spd, int head){
		super(p, s, col);
		speed = spd;
		heading = head;
	}
	Movable(Point p, int s, int col, int head){
		super(p, s, col);
		heading = head;
		

	}
	
	public void reset(Point p, int s, int col, int spd, int head) {
		super.reset(p, s, col);
		speed = spd;
		heading = head;
		angle = 0;
	}
	
	public void setAngle(int degree) {
		heading += degree;
		convertHeading();
	}


	public int speedGetter() {
		return speed;
	}
	public void increaseSpeed(int acc) {
		speed += acc;
	}
	public void decreaseSpeed(int reduceSpeed) {
		speed -= reduceSpeed;
	}
	public void colorSetter(int col) {
		super.colorSetter(col);
	}

	public int headingGetter() {
		return heading;
	}
	public int sizeGetter() {
		return super.sizeGetter();
	}
	public int angleGetter(){
		return angle;
	}
	public void changeAngle(int angl) {
		angle += angl;
	}
	private void convertHeading() {
		if(heading > 360)
			heading -= 360;
		if(heading < 0 )
			heading += 360;
		
	}
	
	double dirX = 1, dirY = 1;
	
	public void move(int time, int height, int width) {
		setAngle(angle);
		Point oldLocation = this.locationGetter();
   	 int currentX = (int) this.locationGetter().getX();
   	 int currentY = (int) this.locationGetter().getY();
		
   	 double dist = ( speed * (time/time) ) * .75;

		Double deltaXDouble = (Math.cos(Math.toRadians(90 - this.heading)) * dist);
		Double deltaYDouble = (Math.sin(Math.toRadians(90 - this.heading)) * dist);
		Integer deltaX = deltaXDouble.intValue();
		Integer deltaY = deltaYDouble.intValue();
		
		if ( (currentX+this.sizeGetter() >= width) || (currentX < 0) ) 
			dirX = -dirX ;
		if ( (currentY+this.sizeGetter() >= height) || (currentY < 0) )
			dirY = -dirY ;
		currentX += deltaXDouble * dirX;
		currentY += deltaYDouble * dirY;
		
		Point newLocation = new Point((currentX), (currentY));
		this.locationSetter(newLocation);
				
	}

	public boolean collidesWith (GameObject otherObject) {
		return false;
	}
	
	public void handleCollision (GameObject otherObject) {
		
	}
	
}
