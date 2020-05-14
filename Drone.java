package com.mycompany.a3;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

import java.util.Random;


public class Drone extends Movable implements IDrawble{
	private Random r = new Random();
	
	Drone(Point p, int s, int col, int spd, int head){
		super(p, s, col, spd, head);
	}
	public void reset(Point p, int s, int col, int spd, int head){
		super.reset(p, s, col, spd, head);
	}
	public void randomSteer() {
		super.setAngle(r.nextInt(5));
	}
	public void hitWall() {
		super.setAngle(180);
	}

	public void colorFader() {
	}
	
	public String toString() {
		return "Drone| Loc: "+ Math.round(this.locationGetter().getX()*10.0)/10.0 +", "+ Math.round(this.locationGetter().getY()*10.0)/10.0 +" Color: "+ this.colorGetter() +" Heading: "+ this.headingGetter() +" Speed: "+ this.speedGetter() +" Size: "+ this.sizeGetter();
	}
	
	public void draw(Graphics  g,  Point  pCmpRelPrnt) {
		 g.setColor(this.getColor());
		 int xLoc = (int) (pCmpRelPrnt.getX()+this.locationGetter().getX());
		 int yLoc =  (int) (pCmpRelPrnt.getY()+this.locationGetter().getY());
		 int [] xPoints = {xLoc-this.sizeGetter()/2 ,xLoc+this.sizeGetter()/2 , xLoc} ;
		 int [] yPoints = {yLoc-this.sizeGetter() ,yLoc-this.sizeGetter() , yLoc} ;
		 
		 
		 g.drawPolygon(xPoints, yPoints, 3);
		 		
	}
	public boolean collidesWith(GameObject otherObject) {
			return false;
	}
	public void handleCollision(GameObject otherObject) {
			
	}

}
