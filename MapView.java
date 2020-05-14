package com.mycompany.a3;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer {
	private GameWorld gw;
	private Graphics myGraphics;
	private Game ga;
	private boolean position;
	MapView(GameWorld gw, Game g){
		this.gw = gw ;
		this.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.rgb(255, 0, 0)));
		ga = g;


	}

	@Override
	public void update(Observable observable, Object data) {
		
	GameWorld gw = (GameWorld)observable;
	gw.displayMap();
	this.repaint();

	
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Point pCmpRelPrnt = new Point(getX(), getY());
		myGraphics = g;
		GameObject cur ;


		IIterator itr = gw.getGameCollection().getIterator();

		while(itr.hasNext()) {
			cur= (GameObject)itr.getNext() ;
			if(cur instanceof IDrawble ) {
				((IDrawble) cur ).draw(g, pCmpRelPrnt);
			}
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
}
	




