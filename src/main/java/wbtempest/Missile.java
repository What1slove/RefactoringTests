package wbtempest;

import wbtempest.Levels.Level;

import java.util.ArrayList;

/**
 * A missile.  If being fired down into the level, it represents a player missile;
 * if fired up (at the player), it represents an enemy missile.
 * 
 * @author ugliest
 *
 */
public class Missile {
	private static int BASE_SPEED = 8;
	static int HEIGHT = 8;
	private static int HEIGHT_H = HEIGHT/2;
	private int colnum;
	private int zpos;
	private boolean visible = true;
	private int speed;
	
	public Missile(int colnum, int zpos, boolean down){
		this.colnum = colnum;
		this.zpos = zpos;
    	if (down)
    		speed = BASE_SPEED;
    	else
    		speed = -BASE_SPEED/2;
	}
	
	public int getZPos(){
		return zpos;
	}
	
	public int getColumn(){
		return colnum;
	}
	
	public void move(int maxz){
		zpos+=speed;
		if ((zpos > maxz) || (zpos < 0))
			visible = false;
	}
	
	public boolean isVisible(){
		return visible;
	}
	
	/**
	 * return the points that make up the onscreen missile.
	 * 
	 * @param lev
	 * @return
	 */
	public GameObjectCoordsMap getCoords(Level lev){
		GameObjectCoordsMap coordsMap = new GameObjectCoordsMap(5);
		ArrayList<Coord> coords = coordsMap.coords;
		Column column = lev.getColumns().get(colnum);
		int p1x=column.getFirstPointX();
		int p1y=column.getFirstPointY();
		int p2x=column.getSecondPointX();
		int p2y=column.getSecondPointY();
		coords.get(0).setXYZ(p1x+(p2x - p1x)*2/5,p1y+(p2y - p1y)*2/5,zpos-HEIGHT_H);
		coords.get(1).setXYZ(p1x+(p2x - p1x)/2,p1y+(p2y - p1y)/2,zpos-HEIGHT);
		coords.get(2).setXYZ(p1x+(p2x - p1x)*3/5,p1y+(p2y - p1y)*3/5,zpos-HEIGHT_H);
		coords.get(3).setXYZ(p1x+(p2x - p1x)/2,p1y+(p2y - p1y)/2,zpos);
		coords.get(4).setXYZ(coords.get(0));
		coordsMap.coords=coords;
		return coordsMap;
	}

	/**
	 * coordinates for the second layer to be drawn; idea is to allow board to draw in 
	 * a different color.
	 * @param lev
	 * @return
	 */
	public GameObjectCoordsMap getLayerCoords(Level lev){
		GameObjectCoordsMap coordsMap = new GameObjectCoordsMap(5);
		ArrayList<Coord> coords = coordsMap.coords;
		Column column = lev.getColumns().get(colnum);
		int p1x=column.getFirstPointX();
		int p1y=column.getFirstPointY();
		int p2x=column.getSecondPointX();
		int p2y=column.getSecondPointY();
		coords.get(0).setXYZ(p1x+(p2x - p1x)*9/20,p1y+(p2y - p1y)*9/20,zpos-HEIGHT_H);
		coords.get(1).setXYZ(p1x+(p2x - p1x)/2,p1y+(p2y - p1y)/2,zpos-HEIGHT*3/5);
		coords.get(2).setXYZ(p1x+(p2x - p1x)*11/20,p1y+(p2y - p1y)*11/20,zpos-HEIGHT_H);
		coords.get(3).setXYZ(p1x+(p2x - p1x)/2,p1y+(p2y - p1y)/2,zpos-HEIGHT*2/5);
		coords.get(4).setXYZ(coords.get(0));
		coordsMap.coords=coords;
		return coordsMap;
	}

	public void setVisible(boolean b) {
		this.visible = b;
	}
}
