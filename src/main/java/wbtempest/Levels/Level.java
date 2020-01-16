package wbtempest.Levels;

import wbtempest.Column;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the details of the level being played.
 * 
 * @author ugliest
 *
 */
public abstract class Level {
	private static int BASE_EX_FIRE_BPS = 35;  // bps chance per tick that we fire an ex at player
	
	private int levnum;
	private int exesct;
	private float spikespct;
	protected List<Column> columns;
	protected boolean continuous = false;
	private boolean exesCanMove = false;
	protected int zpull_x;  // z pull is the point that the z-axis leads to for this level
	protected int zpull_y;
	private int numscreens;
	
	public Level(int levnum, int b_width, int b_height,int numscreens){
		this.levnum = levnum;
		this.numscreens=numscreens;
		zpull_x = b_width/2;  // where z-axis goes; default z pull to be just low of center.
		zpull_y = b_height *35/60;
		exesct = 5 + levnum*2; 
		exesCanMove = (levnum != 1);
		if (levnum < 4)
			spikespct = (float)0;
		else if (levnum < 6)
			spikespct = (float) 0.5;
		else if (levnum < 9)
			spikespct = (float) 0.75;
		else spikespct = (float) 1;
		columns = new ArrayList<Column>();
	}

	public List<Column> getColumns(){
		return columns;
	}
	
	public Color getLevelColor(){
		if (levnum > numscreens*2)
			return Color.RED;
		return Color.BLUE;
	}
	
	public int getZPull_X() {
		return zpull_x;
	}

	public int getZPull_Y() {
		return zpull_y;
	}
	
	public boolean isContinuous(){
		return continuous;
	}
	
	public boolean exesCanMove(){
		return exesCanMove;
	}
	
	public int getNumExes(){
		return this.exesct;
	}
	
	public int getExFireBPS(){
		return BASE_EX_FIRE_BPS + levnum/2;
	}
	
	public int getNumSpikes(){
		return (int) (spikespct * columns.size());
	}
	
	public List<int[]> getBoardFrontCoords(){
		List<int[]> coordList = new ArrayList<int[]>();
		for (int i=0; i<columns.size(); i++){
			Column col = columns.get(i);
			if (i==0)
				coordList.add(col.getFirstPoint());
			coordList.add(col.getSecondPoint());
		}
		return coordList;
	}

}
