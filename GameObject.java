package com.mycompany.a3;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;


public abstract class GameObject implements ICollider {
	private Point location;
	private int size;
	private int color;
	
	GameObject(Point p, int s, int col)
	{
		location = p;
		size = s;
		color = col;
		
	}
	public void reset(Point p, int s, int col) {
		location = p;
		size = s; 
		color = col;
	}
	
	public void colorFader(int r, int g, int b) {
		color = ColorUtil.rgb(ColorUtil.red(color) + r, ColorUtil.green(color) + g, ColorUtil.blue(color) + b );
	}
	
	public void colorSetter(int col) {
		color = col;
	}
	public String colorGetter() {
		return "[" + ColorUtil.red(color) + "," + ColorUtil.green(color) + "," +
				ColorUtil.blue(color) + "]";
	}
	
    public int getColor() {
    	
        return color;
    }
	
	public int sizeGetter() {
		return size;
	}
	
	public Point locationGetter() {
		return location;
	}
	public void locationSetter(Point p) {
		location = p;
	}
	
	public boolean collidesWith (GameObject otherObject) {
		return false;
	}
	
	public void handleCollision (GameObject otherObject) {
		
	}
}
