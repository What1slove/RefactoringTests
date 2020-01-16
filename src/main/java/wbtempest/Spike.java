package wbtempest;

import wbtempest.Levels.Level;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a spike, which may appear in a column of the board, and may have a spinner
 * which can grow the spike and shott missiles at the player.
 * 
 * @author ugliest
 *
 */
public class Spike {
	private static int IMPACT_DAMAGE_LENGTH = 15;
	static int SPIKE_SCORE = 2;
	static double SPINNER_SPIN_SPEED = .3;
	static int SPINNER_SPEED = 3;
	static int SPINNER_SCORE = 50;
	private static Random r = new Random(new java.util.Date().getTime());
	private int length;
	private int colnum;
	private int spinnerz;
	private double spinnerangle;
	private int spinnerDir = 1;
	private boolean visible;
	private boolean spinnerVisible;

	public Spike(int colnum) {
		this.colnum = colnum;
		length = r.nextInt(Board.LEVEL_DEPTH*3/4)+ Board.LEVEL_DEPTH/10;
		spinnerz = Board.LEVEL_DEPTH - r.nextInt(length);
		spinnerangle = r.nextDouble();
		visible = true;
		spinnerVisible = true;
	}

	public int getColumn() {
		return colnum;
	}
	
	public int getLength() {
		return length;
	}

	public boolean isVisible() {
		return visible;
	}

	public boolean isSpinnerVisible() {
		return spinnerVisible;
	}
	
	public void setSpinnerVisible(boolean v) {
		spinnerVisible = v;
	}
	
	public int getSpinnerZ() {
		return spinnerz;
	}

	public void move() {
		spinnerz += spinnerDir * SPINNER_SPEED;
		spinnerangle += SPINNER_SPIN_SPEED;
		if (spinnerz > Board.LEVEL_DEPTH){
			spinnerz = Board.LEVEL_DEPTH;
			spinnerDir = -1; // go up
		}
		else if (spinnerz < Board.LEVEL_DEPTH - length) {
			// we're at top of spike; grow spike or flip dir
			if (length < Board.LEVEL_DEPTH - Crawler.CHEIGHT*2
					&& r.nextInt(2) > 0) {
				length+= IMPACT_DAMAGE_LENGTH;
			}
			else
				spinnerDir = 1; // go down
		}
	}
	
	/**
	 * a missile has hit this spike.  handle it.
	 */
	public void impact(){
		length -= IMPACT_DAMAGE_LENGTH;
		if (length < IMPACT_DAMAGE_LENGTH)
			visible = false;
	}
	
	public GameObjectCoordsMap getSpinnerCoords(Level lev){
		int nCoords = 16;
		GameObjectCoordsMap coordsMap = new GameObjectCoordsMap(nCoords);
		ArrayList<Coord> coords = coordsMap.coords;
		Column column = lev.getColumns().get(colnum);
		int p1x=column.getFirstPointX();
		int p1y=column.getFirstPointY();
		int p2x=column.getSecondPointX();
		int p2y=column.getSecondPointY();
		int[] mp = new int[2];
		mp[0] = p1x + (p2x-p1x)/2;
		mp[1] = p1y + (p2y-p1y)/2;
		int colWidth = (int)Math.sqrt(Math.pow((p2x-p1x),2) + Math.pow((p2y-p1y),2));
		int origRadius = colWidth/3;
		int radius = origRadius;
		float rad_dist = (float) (3.1415927 * 2)*3;
		float step = rad_dist/(nCoords);
		int ct = 0;
		for (double rads=spinnerangle; ct < nCoords; rads+=step, ct++)
		{
			coords.get(ct).setXYZ(mp[0] - (int)(Math.sin(rads) * radius * .85),mp[1] - (int)(Math.cos(rads) * radius),spinnerz);
			radius = origRadius *ct/nCoords; 
		}
		coordsMap.coords=coords;
		return coordsMap;
	}

	public GameObjectCoordsMap getCoords(Level lev){
		GameObjectCoordsMap coordsMap = new GameObjectCoordsMap(2);
		ArrayList<Coord> coords = coordsMap.coords;
		Column column = lev.getColumns().get(colnum);
		int p1x=column.getFirstPointX();
		int p1y=column.getFirstPointY();
		int p2x=column.getSecondPointX();
		int p2y=column.getSecondPointY();
		coords.get(0).setXYZ(p1x + (p2x - p1x)/2,p1y + (p2y - p1y)/2, Board.LEVEL_DEPTH);
		coords.get(1).setXYZ(coords.get(0));
		coords.get(1).setZ(Board.LEVEL_DEPTH - length);
		coordsMap.coords=coords;
		return coordsMap;
	}
	
	
  
}
