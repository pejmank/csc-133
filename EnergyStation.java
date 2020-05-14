package com.mycompany.a3;
import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

public class EnergyStation extends Fixed implements IDrawble{

	private int capacity; 
	EnergyStation(Point p, int s, int col, int capac){
		super(p, s, col);
		capacity = capac;
	}
	public void reset(Point p, int s, int col, int capac){
		super.reset(p, s, col);
		capacity = capac;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int i) {
		capacity = i;
		colorFader(150, 0 , 150);
	}
	public String toString() {
		return "EnergyStation| Loc: "+ Math.round(this.locationGetter().getX()*10.0)/10.0 +", "+ Math.round(this.locationGetter().getY()*10.0)/10.0 +" Color: "+ this.colorGetter() + " Size: "+ this.sizeGetter() +" capacity: "+ this.capacity;
	
}
	public void draw(Graphics  g,  Point  pCmpRelPrnt) {
		g.setColor(this.getColor());
		
		int xLoc = (int) (pCmpRelPrnt.getX()+this.locationGetter().getX());
		 int yLoc =  (int) (pCmpRelPrnt.getY()+this.locationGetter().getY());
		 if(this.isSelected())
			 g.drawArc(xLoc - this.sizeGetter() / 2, yLoc - this.sizeGetter() / 2, this.sizeGetter(), this.sizeGetter(), 0, 360);
		 else
		g.fillArc(xLoc - this.sizeGetter() / 2, yLoc - this.sizeGetter() / 2, this.sizeGetter(), this.sizeGetter(), 0, 360);
		
		g.setColor(0);
		g.drawString(Integer.toString(capacity), xLoc - this.sizeGetter() / 4, yLoc - this.sizeGetter()/4 );
	}
	
}
