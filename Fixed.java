package com.mycompany.a4;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

public class Fixed extends GameObject implements ISelectable{
	
	boolean selected = false;
	
	Fixed(Point p, int s, int col)
	{
		super(p, s, col);
	}
	public void reset(Point p, int s, int col) {
		super.reset(p,s, col);
	}

	public void locationSetter(Point p) {
		super.locationSetter(p);
	}
	@Override
	public void setSelected(boolean b) {
		// TODO Auto-generated method stub
		this.selected = b;
		
	}
	@Override
	public boolean isSelected() {

		if(selected)
			return true;
		return false;
	}
	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		int px = (int)pPtrRelPrnt.getX() + this.sizeGetter() / 2; // pointer location relative to
		int py = (int)pPtrRelPrnt.getY() + this.sizeGetter() / 2; // parent’s origin
		int xLoc = (int)(pCmpRelPrnt.getX() + this.locationGetter().getX());// shape location relative int yLoc = pCmpRelPrnt.getY()+ iShapeY;// to parent’s origin
		int yLoc = (int)(pCmpRelPrnt.getX() + this.locationGetter().getY());
		if ( (px >= xLoc) && (px <= xLoc+this.sizeGetter())
		        && (py >= yLoc) && (py <= yLoc+this.sizeGetter())) 
		        return true; 
		else return false;
	}
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		
	}


}
