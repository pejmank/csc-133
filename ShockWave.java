package com.mycompany.a4;

import java.util.Random;
import java.util.Vector;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;
import com.codename1.util.MathUtil;

public class ShockWave extends Movable implements IDrawble{

	// the lifespan of a shock wave, in ms
	private int lifeLength = 16;
	
	// the speed of a shock wave
	private int speed = 200;
	private int MAX_LEVEL = 8;
	private float epsilon = (float)0.001;
	Random r = new Random();
	int hold = 20;
	float arr[] = new float[8];
	
	// this shock waves current lifespan
	private int lifeSpan;
	
	// holds the 4 control points
	Vector controlPointVector = new Vector();
	
	
	ShockWave(Point p, int color, int speed, int heading){
		super(p, color, speed, heading);
		lifeSpan = lifeLength;
		initPoints();

	}
	
	
	// initialize the shock wave with 4 control points
	// if called a second time, will not create new points
	private void initPoints(){
		// set up random points, don't do this for now
		if(controlPointVector.isEmpty()){
			// try to make semi-consistent, but still random and unique, shockwaves
			controlPointVector.add(new ControlPoint( arr[0] = this.locationGetter().getX(), arr[1] =this.locationGetter().getY()));
			controlPointVector.add(new ControlPoint(arr[2] =r.nextInt(500) + 100,arr[3] = r.nextInt(500) +100));
			controlPointVector.add(new ControlPoint(arr[4] =r.nextInt(500) + 5, arr[5] =r.nextInt(500)+ 5));
			controlPointVector.add(new ControlPoint(arr[6] =this.locationGetter().getX(), arr[7] =this.locationGetter().getY() + 50) );	
		}
	}
	public void updateP(){
		controlPointVector.removeAllElements();
		controlPointVector.add(new ControlPoint(arr[0]  + hold, arr[1]));
		controlPointVector.add(new ControlPoint(arr[2], arr[3]));
		controlPointVector.add(new ControlPoint(arr[4], arr[5]));
		controlPointVector.add(new ControlPoint(arr[6]+ hold, arr[7]) );
		hold += 25;
	}
	
	public void draw(Graphics  g,  Point  pCmpRelPrnt) {
		updateP();
		if(lifeLength > 0)
		this.drawBezierCurve(g, this.controlPointVector, 1);
		lifeLength--;
		 		
	}
	private void drawBezierCurve(Graphics g2d, Vector controlPoints, int level){
		if( straightEnough(controlPoints) || level > MAX_LEVEL){
			g2d.setColor(getColor());
			g2d.drawLine( (int)((ControlPoint)controlPoints.elementAt(0)).getX(), (int)((ControlPoint)controlPoints.elementAt(0)).getY(),
						  (int)((ControlPoint)controlPoints.elementAt(3)).getX(), (int)((ControlPoint)controlPoints.elementAt(3)).getY());

		} else{
			Vector leftPoints = new Vector(), rightPoints = new Vector();
			subdivideCurve(controlPoints, leftPoints, rightPoints);
			drawBezierCurve(g2d, leftPoints, level + 1);
			drawBezierCurve(g2d, rightPoints, level + 1);
		}
	}
	
	
	private void subdivideCurve(Vector controlPoints, Vector leftSubVector, Vector rightSubVector){
		ControlPoint l0, l1, l2, l3, r0, r1, r2, r3;
		l0 = new ControlPoint( ((ControlPoint)controlPoints.elementAt(0)) );
		
		l1 = new ControlPoint( ((((ControlPoint)controlPoints.elementAt(0)).getX() + ((ControlPoint)controlPoints.elementAt(1)).getX()) / 2), 
							   ((((ControlPoint)controlPoints.elementAt(0)).getY() + ((ControlPoint)controlPoints.elementAt(1)).getY()) / 2));
		
		l2 = new ControlPoint( (l1.getX()/2) + ((((ControlPoint)controlPoints.elementAt(1)).getX() + ((ControlPoint)controlPoints.elementAt(2)).getX())) / 4, 
							   (l1.getY()/2) + ((((ControlPoint)controlPoints.elementAt(1)).getY() + ((ControlPoint)controlPoints.elementAt(2)).getY())) / 4);
		
		r3 = new ControlPoint( ((ControlPoint)controlPoints.elementAt(3)) );
		
		r2 = new ControlPoint( ((((ControlPoint)controlPoints.elementAt(2)).getX() + ((ControlPoint)controlPoints.elementAt(3)).getX())) / 2, 
				   			   ((((ControlPoint)controlPoints.elementAt(2)).getY() + ((ControlPoint)controlPoints.elementAt(3)).getY())) / 2);
		
		r1 = new ControlPoint( (r2.getX()/2) + ((((ControlPoint)controlPoints.elementAt(1)).getX() + ((ControlPoint)controlPoints.elementAt(2)).getX())) / 4, 
				   			   (r2.getY()/2) + ((((ControlPoint)controlPoints.elementAt(1)).getY() + ((ControlPoint)controlPoints.elementAt(2)).getY())) / 4);
		
		l3 = new ControlPoint( ( (l2.getX() + r1.getX()) / 2), ( (l2.getY() + r1.getY()) / 2) );
		
		r0 = new ControlPoint( l3 );
		
		leftSubVector.add(l0);
		leftSubVector.add(l1);
		leftSubVector.add(l2);
		leftSubVector.add(l3);
		
		rightSubVector.add(r0);
		rightSubVector.add(r1);
		rightSubVector.add(r2);
		rightSubVector.add(r3);
	}
	
	private boolean straightEnough(Vector controlPoints){
		float d1, d2;
		// d1^2 
		d1 = (length( ((ControlPoint)controlPointVector.elementAt(0)), (((ControlPoint)controlPointVector.elementAt(1))) )
		   + length( ((ControlPoint)controlPointVector.elementAt(1)), (((ControlPoint)controlPointVector.elementAt(2))) )
		   + length( ((ControlPoint)controlPointVector.elementAt(2)), (((ControlPoint)controlPointVector.elementAt(3))) ));

		// d2^2
		d2 = length( ((ControlPoint)controlPointVector.elementAt(0)), (((ControlPoint)controlPointVector.elementAt(3))) );



		if( Math.abs(d1-d2) < epsilon ) {
			return true;
		}
		else
			return false;
	}
	
	private float length(ControlPoint p1, ControlPoint p2){
		float length, xDis, yDis;
		xDis = p2.getX() - p1.getX();
		yDis = p2.getY() - p1.getY();

		// length = sqrt( xDis^2 + yDis^2 )
		length =  (float)Math.sqrt( (MathUtil.pow((float)xDis, 2) + MathUtil.pow((float)yDis, 2)) );
		return length;
	}

	/*@Override
	public void draw(Graphics2D g2d) {
		
		// save the AT for restoration later
		AffineTransform saveAT = g2d.getTransform();
		
		// add current objects transformations
		g2d.transform(getTranslate());
		g2d.transform(getRotation());
		g2d.transform(getScale());
	
	/*	
		// draw the control point lines first, so curve is on top
		g2d.setColor(Color.RED);
		g2d.drawLine( (int)((ControlPoint)controlPointVector.elementAt(0)).getX(), (int)((ControlPoint)controlPointVector.elementAt(0)).getY(), (int)((ControlPoint)controlPointVector.elementAt(1)).getX(), (int)((ControlPoint)controlPointVector.elementAt(1)).getY() );
		g2d.setColor(Color.GREEN);
		g2d.drawLine( (int)((ControlPoint)controlPointVector.elementAt(1)).getX(), (int)((ControlPoint)controlPointVector.elementAt(1)).getY(), (int)((ControlPoint)controlPointVector.elementAt(2)).getX(), (int)((ControlPoint)controlPointVector.elementAt(2)).getY() );
		g2d.setColor(Color.BLUE);
		g2d.drawLine( (int)((ControlPoint)controlPointVector.elementAt(2)).getX(), (int)((ControlPoint)controlPointVector.elementAt(2)).getY(), (int)((ControlPoint)controlPointVector.elementAt(3)).getX(), (int)((ControlPoint)controlPointVector.elementAt(3)).getY() );
		g2d.setColor(Color.WHITE);
		g2d.drawLine( (int)((ControlPoint)controlPointVector.elementAt(3)).getX(), (int)((ControlPoint)controlPointVector.elementAt(3)).getY(), (int)((ControlPoint)controlPointVector.elementAt(0)).getX(), (int)((ControlPoint)controlPointVector.elementAt(0)).getY() );
	*/
		
	/*	// set color and draw the bezier curve 
	g2d.setColor(getColor());
		drawBezierCurve(g2d, controlPointVector, 1);
		
		// restore the g2d object for the next object
		g2d.setTransform(saveAT);
	}*/


	
	
	// NOTE the remaining methods have not been fully implemented
	// since we are not handling collisions with shock waves
	// there is nothing to be done when a collision occurs,
	// so there is no need to implement detection w/it
	

	
	private class ControlPoint {
		
		// x and y coords
		private float x, y;
		
		// constructor, expects nums from ShockWave.r.nextInt()
		ControlPoint(float xIn, float yIn){
			x = xIn;
			y = yIn;
		}
		
		ControlPoint(ControlPoint p){
			x = p.getX();
			y = p.getY();
		}
		
		public float getX(){
			return x;
		}
		
		public float getY(){
			return y;
		}
		
	}
}
