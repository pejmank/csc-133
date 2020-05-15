package com.mycompany.a4;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
import com.codename1.ui.plaf.Border;
import com.mycompany.a4.GameObject;
import com.mycompany.a4.IIterator;
import com.mycompany.a4.IDrawble;

public class MapView extends Container implements Observer {
	private GameWorld gw;
	private Graphics myGraphics;
	Transform worldToND, ndToDisplay, theVTM ;
	private Game ga;
	private float winLeft, winRight, winTop, winBottom;
	private float height = 775, width = 730;
	private boolean position;
	MapView(GameWorld gw, Game g){
		this.gw = gw ;
		this.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.rgb(255, 0, 0)));
		ga = g;
		winLeft = winBottom = 0;
		
		winRight = 775;
		winTop = 670;

	}

	@Override
	public void update(Observable observable, Object data) {
		
	GameWorld gw = (GameWorld)observable;
	//gw.displayMap();
	this.repaint();

	
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Point pCmpRelPrnt = new Point(getX(), getY());
		myGraphics = g;
		GameObject cur;
		float winHeight = winTop - winBottom;
		float winWidth = winRight - winLeft;
		IIterator itr = gw.getGameCollection().getIterator();

		while(itr.hasNext()) {
			worldToND = buildWorldToNDXform(winWidth, winHeight, winLeft, winBottom);
			ndToDisplay = buildNDToDisplayXform(this.getWidth(), this.getHeight());
			Transform gXform = Transform.makeIdentity();
			g.getTransform(gXform);

			gXform.translate(getAbsoluteX(), getAbsoluteY());
			gXform.translate(0, winHeight);
			//apply scale associated with display mapping
			gXform.scale(1, -1);
			gXform.translate(-getAbsoluteX(), -getAbsoluteY());
			theVTM = ndToDisplay.copy();
			theVTM.concatenate(worldToND);
			gXform.translate(getAbsoluteX(),getAbsoluteY());
			gXform.concatenate(theVTM);
			//gXform.concatenate(theVTM);
			
			gXform.translate(-getAbsoluteX(), -getAbsoluteY());
			gXform.translate(getAbsoluteX(), getAbsoluteY());
			gXform.translate(0, winHeight);
			//apply scale associated with display mapping
			gXform.scale(1, -1);
			gXform.translate(-getAbsoluteX(), -getAbsoluteY());
			Point pScrnRelPrnt= new Point(gXform.getTranslateX(), gXform.getTranslateY());
			g.setTransform(gXform);
			cur= (GameObject)itr.getNext() ;
			if(cur instanceof IDrawble ) {
				((IDrawble) cur ).draw(g, pCmpRelPrnt);
			}
			
			g.resetAffine();
		}

		
	}
	public void pointerPressed(int x, int y) {
		IIterator iter = gw.getGameCollection().getIterator();
		if(position) {
			while(iter.hasNext()) {
				GameObject gameOb = (GameObject) iter.getNext();
				if(gameOb instanceof ISelectable) {
					if(((ISelectable)gameOb).isSelected()) {
						((Fixed)gameOb).locationSetter(new Point(x - this.getParent().getAbsoluteX() - this.getX(), y - this.getParent().getY() - this.getY()));
					}
						
				}
			
			}
			position = !position;
			this.repaint();
		}	
		else if(ga.s == "Play")
		{
		x = x + getParent().getAbsoluteX();
		y = y + getParent().getAbsoluteY(); 		
		Point pPtrRelPrnt = new Point(x, y);
		Point pCmpRelPrnt = new Point(getX(), getY());
		while(iter.hasNext()) {
			GameObject gameOb = (GameObject) iter.getNext();
			if(gameOb instanceof ISelectable) {
			if(((ISelectable)gameOb).contains(pPtrRelPrnt, pCmpRelPrnt))
			((ISelectable)gameOb).setSelected(true); 
			else
				((ISelectable)gameOb).setSelected(false); 
			}
		}
		this.repaint();
		}
			
	}
	public void reset() {
		
		IIterator iter = gw.getGameCollection().getIterator();
		while(iter.hasNext()) {
			GameObject gameOb = (GameObject) iter.getNext();
			if(gameOb instanceof ISelectable) {
				((ISelectable)gameOb).setSelected(false); 
			}
		}
		this.repaint();
		}
	public void setPosition() {
		position = !position;
	}
	private Transform buildWorldToNDXform(float winWidth, float winHeight, float winLeft, float winBottom){
		Transform tmpXfrom = Transform.makeIdentity(); 
		tmpXfrom.scale( (1/winWidth) , (1/winHeight) ); 
		tmpXfrom.translate(-winLeft,-winBottom);
		return tmpXfrom;
	}
	private Transform buildNDToDisplayXform (float displayWidth, float displayHeight){
		Transform tmpXfrom = Transform.makeIdentity(); 
		tmpXfrom.translate(0, displayHeight); 
		tmpXfrom.scale(displayWidth, -displayHeight); 

		return tmpXfrom;
	}
}
	




