package com.mycompany.a4;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;

public class Base extends Fixed implements IDrawble{
	private int sequenceNumber; 
	
	Base(Point p, int s, int col, int seqNum){
		super(p, s, col);
		
		sequenceNumber = seqNum;
	}
	public void reset(Point p, int s, int col, int seqNum){
		super.reset(p, s, col);
		
		sequenceNumber = seqNum;
	}
	
	public void colorSetter(int col)
	{
		
	}
	public void colorFader(int r, int g, int b) {
	}
	
	public String toString() {
		return "Base| Loc: "+ Math.round(this.locationGetter().getX()*10.0)/10.0 +", "+ Math.round(this.locationGetter().getY()*10.0)/10.0 +" Color: "+ this.colorGetter() + " Size: "+ this.sizeGetter() +" Sequence#: "+ this.sequenceNumber;
	
}
	public int getSequenceNumber() {
		return sequenceNumber;
	}
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {

		int xLoc = (int) (pCmpRelPrnt.getX() + this.locationGetter().getX());
		int yLoc = (int) (pCmpRelPrnt.getY() + this.locationGetter().getY());
		System.out.print("base  :" + this.locationGetter().getX() + "\n");
		float [] xPoints = {xLoc-this.sizeGetter() ,xLoc+this.sizeGetter() , xLoc} ;
		float [] yPoints = {yLoc - this.sizeGetter() /2 ,yLoc - this.sizeGetter() /2 , yLoc + this.sizeGetter() / 2} ;
		System.out.print(yPoints[0] + " \n");
		g.setColor(this.getColor());
		System.out.print(yPoints[0] + " \n");

		if(this.isSelected()) {
		int xp[] = {(int)xPoints[0], (int)xPoints[1], (int)xPoints[2]}; 	
		int yp[] = {(int)yPoints[0], (int)yPoints[1], (int)yPoints[2]}; 	

			g.drawPolygon(xp, yp, 3);
		}
		else
		g.fillTriangle((int)xPoints[0], (int)yPoints[0], (int)xPoints[1], (int)yPoints[1], (int)xPoints[2], (int)yPoints[2]);
		
		g.setColor(0);
		g.drawString(Integer.toString(sequenceNumber), xLoc, yLoc - this.sizeGetter() / 2 );
		
		
	}

}
