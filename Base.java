package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

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

		g.setColor(this.getColor());
		
		int xLoc = (int) (pCmpRelPrnt.getX() + this.locationGetter().getX());
		int yLoc = (int) (pCmpRelPrnt.getY() + this.locationGetter().getY());
		
		int [] xPoints = {xLoc-this.sizeGetter() ,xLoc+this.sizeGetter() , xLoc} ;
		int [] yPoints = {yLoc - this.sizeGetter() /2 ,yLoc - this.sizeGetter() /2 , yLoc + this.sizeGetter() / 2} ;
		if(this.isSelected())
			g.drawPolygon(xPoints, yPoints, 3);
		else
		g.fillTriangle(xPoints[0], yPoints[0], xPoints[1], yPoints[1], xPoints[2], yPoints[2]);
		
		g.setColor(0);
		g.drawString(Integer.toString(sequenceNumber), xLoc, yLoc - this.sizeGetter() / 2 );
		
		
	}

}
