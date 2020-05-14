package com.mycompany.a4;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;

public class Legs {
	private Point bottom; 
	private int color;
	int height = 27;
	int width = 12;
	int ange = 40;
	
	private Transform myTranslation ;
	private Transform myRotation ;
	private Transform myScale ;
	  public Legs (int x, int y){
		  bottom = new Point(x, y);
		myTranslation = Transform.makeIdentity(); 
		myRotation = Transform.makeIdentity(); 
		myScale = Transform.makeIdentity();
	  }
	  public void setColor(int col) {
		  color = col;
	  }
	  public void rotate (double degrees) {
		  myRotation.rotate( (float)Math.toRadians(degrees), 0, 0);
		  }
	  public void scale (float sx, float sy) { 
		  myScale.scale (sx, sy);
	  }
	  public void translate (float tx, float ty) { 
		  myTranslation.translate (tx, ty);
	  }
	  public void incA() {
		  ange = ange + 40;
	  }
	  public void draw (Graphics g, Point pCmpRelPrnt, Point pCmpRelScrn, int s) {
		Transform gXform = Transform.makeIdentity();
		g.getTransform(gXform);
		Transform gOrigXform = gXform.copy(); //save the original xform
		gXform.translate(pCmpRelScrn.getX(),pCmpRelScrn.getY());
		gXform.translate(- s / 2, myTranslation.getTranslateY());
		gXform.concatenate(myRotation);
		System.out.print("angele: " + ange);
		gXform.scale(myScale.getScaleX(), myScale.getScaleY());
		gXform.translate(-pCmpRelScrn.getX(),-pCmpRelScrn.getY());

		g.setTransform(gXform);
		System.out.print("get x: " +(int)bottom.getX() + "translate c:" + pCmpRelPrnt.getX() ); 
		g.drawRect((int)bottom.getX() + (int)pCmpRelPrnt.getX(), (int)bottom.getY(), width, height);
		g.setTransform(gOrigXform); //restore the original xform (remove LTs) //do not use resetAffine() in draw()! Instead use getTransform()/setTransform(gOrigForm)
		}
		
	

}
