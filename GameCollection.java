package com.mycompany.a4;
import java.util.Iterator;
import java.util.Vector;

import com.codename1.charts.models.Point;



public class GameCollection implements Icollection{

	private Vector<GameObject> theGameCollection;
	
	GameCollection(){
		theGameCollection = new Vector<GameObject>();
	}
	
	

	public void addObject(GameObject g) {
		theGameCollection.add(g);
	}

	public void addPlayerCyborg(Point p, int s, int col, int spd, int angl, int enrgLevel, int dmgLvl, GameWorld g) {
		theGameCollection.add(PlayerCyborg.getPlayerCyborg(p, s, col, spd, angl, enrgLevel, dmgLvl, g));
	}
	public void addNonPlayerCyborg(Point p, int s, int col, int spd, int angl, int enrgLevel, int dmgLvl, int strat, GameWorld g) {
		theGameCollection.add(new NonPlayerCyborg(p, s, col, spd, angl, enrgLevel, dmgLvl, strat, g));
	}
	public void addBase(Point p, int s, int col, int seq) {
		theGameCollection.add(new Base(p, s, col, seq)); 

	}
	public void addDrone(Point p, int s, int col, int spd, int angl) {
		theGameCollection.add(new Drone(p, s, col, spd, angl)); 
	}
	public void addEnergyStation(Point p, int s, int col, int capac) {
		theGameCollection.add(new EnergyStation(p, s, col, capac)); 
	}
	
	
	
	@Override
	public void add(GameObject newObject) {

		theGameCollection.add(newObject);
		
	}


	@Override
	public IIterator getIterator() {
		// TODO Auto-generated method stub
		return new GameObjectIterator();
	}
	
	private class GameObjectIterator implements IIterator{
		private int curIndex ;
		/// Constructor/////
		 
		public GameObjectIterator() {
			curIndex=-1 ;
		}
		public boolean hasNext() {
			if (theGameCollection.size( ) <= 0)
				return false;
			if(curIndex==theGameCollection.size()-1)
				return false;
			return true;
		}
	
		public Object getNext() {
			curIndex++ ;
			return (theGameCollection.get(curIndex) );
		}
		public void remove() {
			
			theGameCollection.remove(this.curIndex);
			this.curIndex--;
		}
		public void remove(GameObject obj) {
			theGameCollection.remove(obj);
			this.curIndex--;
		}

	}
}
