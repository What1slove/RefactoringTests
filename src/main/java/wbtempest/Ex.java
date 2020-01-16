package wbtempest;

import wbtempest.Levels.Level;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents an Ex, the principal enemy in the game, shaped like an "x".
 * May also be a pod, which when destroyed or when reaches the front of the playing board,
 * produces two exes.
 * 
 * @author ugliest
 *
 */
public class Ex {

    static int HEIGHT = 20;
    static int SCOREVAL=150;
    static int PODSCOREVAL=50;
    static int PODSIZE = 35;
    private static int EXHEIGHT_H = HEIGHT/2; // half the height of an Ex
    private static Random rnd = new Random(new java.util.Date().getTime());
    
    // possible states of an ex
	private static enum State {STRAIGHT, JUMPRIGHT1, JUMPLEFT1, JUMPRIGHT2, JUMPLEFT2, LANDRIGHT1, LANDRIGHT2, LANDLEFT1, LANDLEFT2};

	private static int JUMPINTERVAL = 30;  // ticks
	private static int JUMPINTERVAL_INITIAL = 2;  // ticks
	private static double SPEED = 1.4;
    private int col;
    private int ncols;
    private boolean isPod = false;
    private double z = Board.LEVEL_DEPTH;
    private boolean visible;
    private int jumptimer = 0;
    private State prefdir = State.JUMPRIGHT1;  // initial predisp to jump right
    private State s = prefdir;
    private boolean canMove=false;
    private boolean levContinuous;
    private boolean spawning = true;
 
    /**
     * Initialize an Ex
     * @param col - initial column of ex
     * @param ncols - nulber of cols the current level contains
     * @param canMove - can the ex move col to col?
     * @param levelContinuous - is the level continuous?
     */
    public Ex(int col, boolean isPod, int ncols, boolean canMove, boolean levelContinuous) {
        this.col = col;
        this.isPod = isPod;
        this.ncols = ncols;
        this.canMove = canMove;
        this.levContinuous = levelContinuous;
        visible = true;
    }
    
    public void resetZ(int zdepth) {
    	z = zdepth;
    }
    
    public boolean isPod() {
    	return isPod;
    }
    
    public void setPod(boolean isPod) {
    	this.isPod = isPod;
    }

    /**
     * Spawns an additional ex, traveling in the opposite direction.
     */
    public Ex spawn() {
    	Ex spawn = new Ex(col, false, ncols, canMove, levContinuous);
    	spawn.z = z;
    	if (prefdir == State.JUMPLEFT1)
    		spawn.prefdir = State.JUMPRIGHT1;
    	else
    		spawn.prefdir = State.JUMPLEFT1;
    	return spawn;
    }

    public void move(int xbound, int crawlerCol) {
        if (z > 0) 
            z -= SPEED;
        if (z > Board.LEVEL_DEPTH)
        	z -= SPEED/2; // go faster when they're just spinning aimlessly before hitting the board.
        
        switch (s) {
        case STRAIGHT:
        	if ((canMove && !isPod) || z <= 0 || z > Board.LEVEL_DEPTH){
        		// if exes are allowed to move column to column, or if we're already at 
        		// the front of the board, move every now and then.
        		jumptimer--;
        		if (jumptimer <= 0) {
        				if (z < Board.LEVEL_DEPTH && spawning)
        				{ // haven't chosen a direction yet
        					spawning = false;
        					prefdir = State.values()[rnd.nextInt(2)+1]; // right or left at random, note depends on order of values in state enum, which is tacky
        				}
        				if (z <= 0 && (rnd.nextInt(4) == 1)) {
        					// at top, randomly reorient and make sure we're evilly headed toward the player
        					if (levContinuous) {
        						int diff = crawlerCol - getColumn();
        						boolean wrap = Math.abs(diff) > ncols/2;
        						if ((diff > 0 && wrap) // ex is low and we need to wrap
        								|| (diff < 0 && !wrap)) // ex is high and we don't
        							prefdir = State.JUMPLEFT1;
        						else
        							prefdir = State.JUMPRIGHT1;
        					}
        					else{
        						if (crawlerCol > getColumn())
        							prefdir = State.JUMPRIGHT1;
        						else
        							prefdir = State.JUMPLEFT1;
        					}
        				}
    					s = prefdir;
        		}
        	}
        	break;
        case JUMPRIGHT2:
        	s = State.LANDLEFT1;
        	col++;
        	if (col >= ncols){
        		if (levContinuous){
                  	col %= ncols;
        		}
        		else {
        			col--;
        			prefdir = State.JUMPLEFT1;
        		}
        	}
        		
        	break;

        case JUMPLEFT2:
        	s = State.LANDRIGHT1;
        	col--;
           	if (col < 0){
        		if (levContinuous)
            		col = ncols + col;
        		else {
        			col++;
        			prefdir = State.JUMPRIGHT1;
        		}
           	}	
        	break;
        	
        case JUMPRIGHT1:
        	s = State.JUMPRIGHT2;
        	break;

        case JUMPLEFT1:
        	s = State.JUMPLEFT2;
        	break;

        case LANDRIGHT1:
        	s = State.LANDRIGHT2;
        	break;

        case LANDLEFT1:
        	s = State.LANDLEFT2;
        	break;

        case LANDRIGHT2:
        case LANDLEFT2:
        	if (z < Board.LEVEL_DEPTH) {
            	jumptimer = JUMPINTERVAL;
        	}
        	else
        	{
        		jumptimer = JUMPINTERVAL_INITIAL;
        	}
        	s = State.STRAIGHT;
        }
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public int getColumn() {
//    	System.out.println("Ex.GetColumn returning "+col);
        return col;
    }
    
    public int getZ(){
//    	System.out.println("Ex.GetZ "+z);
    	return (int)z;
    }
    
    public GameObjectCoordsMap getDeathCoords(Level lev){
    	// return death explosion image coords
		GameObjectCoordsMap coordsMap = new GameObjectCoordsMap(15);
		ArrayList<Coord> coords = coordsMap.coords;
    	Column column = lev.getColumns().get(col);
		int p1x=column.getFirstPointX();
		int p1y=column.getFirstPointY();
		int p2x=column.getSecondPointX();
		int p2y=column.getSecondPointY();
		coords.get(0).setXYZ(p1x + (p2x-p1x)/5,p1y + (p2x-p1x)/5,(int)z);
		coords.get(2).setXYZ(p1x + (p2x-p1x)/3,p1y + (p2y-p1y)/3,(int)(z + EXHEIGHT_H*2));
		coords.get(4).setXYZ(p2x - (p2x-p1x)/2,p2y - (p2y-p1y)/2,(int)(z + EXHEIGHT_H*4));
		coords.get(6).setXYZ(p2x - (p2x-p1x)/3,p2y - (p2y-p1y)/3,(int)(z+EXHEIGHT_H*2));
		coords.get(8).setXYZ(p2x - (p2x-p1x)/5,p2y - (p2y-p1y)/5,(int)z);
		coords.get(10).setXYZ(p2x - (p2x-p1x)/3,p2y - (p2y-p1y)/3,(int)(z-EXHEIGHT_H*2));
		coords.get(12).setXYZ(p2x - (p2x-p1x)/2,p2y - (p2y-p1y)/2,(int)(z - EXHEIGHT_H*4));
		coords.get(14).setXYZ(p1x + (p2x-p1x)/3,p1y + (p2y-p1y)/3,(int)(z - EXHEIGHT_H*2));
		for (int i=1;i<=13;i+=2){
			coords.get(i).setXYZ(p2x - (p2x-p1x)/2,p2y - (p2y-p1y)/2,(int)z);
		}
		coordsMap.coords=coords;
    	return  coordsMap;
    }

    /**
     * If the screen needs to freeze (eg player death) the ex can ve reset to 
     * its normal state via this method.
     */
    public void resetState() {
    	s = State.STRAIGHT;
    }
    
    /**
     * return coords for drawing this ex, based on its state.
     * 
     * @param lev the current level; column information is needed.
     * @return
     */
    public GameObjectCoordsMap getCoords(Level lev){

		GameObjectCoordsMap coordsMap;
    	Column column = lev.getColumns().get(col);
		int p1x=column.getFirstPointX();
		int p1y=column.getFirstPointY();
		int p2x=column.getSecondPointX();
		int p2y=column.getSecondPointY();

    	if (isPod && z < Board.LEVEL_DEPTH) {
			coordsMap = new GameObjectCoordsMap(17);
			ArrayList<Coord> coords = coordsMap.coords;
    		int cx = p1x + (p2x-p1x)/2;
    		int cy = p1y + (p2y-p1y)/2;
    		coords.get(0).setXYZ(cx,cy - PODSIZE,(int)z);
			coords.get(1).setXYZ(cx + PODSIZE, cy, (int) z);
			coords.get(2).setXYZ(cx + PODSIZE/3, cy, (int) z);
			coords.get(3).setXYZ(cx , cy - PODSIZE/3, (int) z);
			coords.get(4).setXYZ(coords.get(1));
			coords.get(5).setXYZ(cx , cy + PODSIZE, (int) z);
			coords.get(6).setXYZ(cx , cy + PODSIZE/3, (int) z);
			coords.get(7).setXYZ(coords.get(2));
			coords.get(8).setXYZ(coords.get(5));
			coords.get(9).setXYZ(cx - PODSIZE , cy, (int) z);
			coords.get(10).setXYZ(cx - PODSIZE/3 , cy, (int) z);
			coords.get(11).setXYZ(coords.get(6));
			coords.get(12).setXYZ(coords.get(9));
			coords.get(13).setXYZ(coords.get(0));
			coords.get(14).setXYZ(coords.get(3));
			coords.get(15).setXYZ(coords.get(10));
			coords.get(16).setXYZ(coords.get(0));
			coordsMap.coords=coords;
    	}
    	else {
			coordsMap = new GameObjectCoordsMap(7);
			ArrayList<Coord> coords = coordsMap.coords;
    		switch (s) {
    		case STRAIGHT:
				coords.get(0).setXYZ(p1x,p1y,(int) (z-EXHEIGHT_H));
				coords.get(1).setXYZ(p2x,p2y,(int) (z+EXHEIGHT_H));
				coords.get(2).setXYZ(p2x - (p2x-p1x)/3,p2y - (p2y-p1y)/3,(int) z);
				coords.get(3).setXYZ(p2x,p2y,(int) (z-EXHEIGHT_H));
				coords.get(4).setXYZ(p1x,p1y,(int) (z+EXHEIGHT_H));
				coords.get(5).setXYZ(p1x + (p2x-p1x)/3,p1y + (p2y-p1y)/3,(int) z);
				coords.get(6).setXYZ(coords.get(0));
    			break;

    		case JUMPRIGHT1:
    		case LANDRIGHT2:
				coords.get(0).setXYZ(p1x + (p2x-p1x)/4 ,p1y + (p2y-p1y)/4,(int) (z+EXHEIGHT_H*2));
				coords.get(1).setXYZ(p2x + (p2x-p1x)/4 ,p2y + (p2y-p1y)/4,(int) (z+EXHEIGHT_H*2));
				coords.get(2).setXYZ(p2x - (p2x-p1x)/11 ,p2y - (p2y-p1y)/11,(int) (z+EXHEIGHT_H*1.8));
				coords.get(3).setXYZ(p2x ,p2y,(int) z);
				coords.get(4).setXYZ(p1x + (p2x-p1x)/2 ,p1y + (p2y-p1y)/2,(int)(z+ EXHEIGHT_H*5));
				coords.get(5).setXYZ((int) (p2x - (p2x-p1x)/2.5) ,(int) (p2y - (p2y-p1y)/2.5),(int) (z+EXHEIGHT_H *2.6));
				coords.get(6).setXYZ(coords.get(0));
    			break;

    		case JUMPLEFT1:
    		case LANDLEFT2:
				coords.get(0).setXYZ(p1x ,p1y,(int) z);
				coords.get(1).setXYZ(p1x + (p2x-p1x)/2 ,p1y + (p2y-p1y)/2,(int)(z+ EXHEIGHT_H*5));
				coords.get(2).setXYZ((int) (p1x + (p2x-p1x)/2.5) ,(int) (p1y + (p2y-p1y)/2.5),(int)(z+EXHEIGHT_H *2.6));
				coords.get(3).setXYZ(p2x - (p2x-p1x)/4 ,p2y - (p2y-p1y)/4,(int)(z+EXHEIGHT_H*2));
				coords.get(4).setXYZ(p1x - (p2x-p1x)/4 ,p1y - (p2y-p1y)/4,(int)(z+EXHEIGHT_H*2));
				coords.get(5).setXYZ(p1x + (p2x-p1x)/11 ,p1y + (p2y-p1y)/11,(int)(z+EXHEIGHT_H*1.8));
				coords.get(6).setXYZ(coords.get(0));
    			break;

    		case JUMPLEFT2:
    		case LANDLEFT1:
				coords.get(0).setXYZ(p1x ,p1y,(int) z);
				coords.get(1).setXYZ((int) (p1x + (p2x-p1x)/4.5) ,(int) (p1y + (p2y-p1y)/4.5),(int) (z+ EXHEIGHT_H*8));
				coords.get(2).setXYZ((int) (p1x + (p2x-p1x)/4.5) ,(int) (p1y + (p2y-p1y)/4.5),(int) (z+EXHEIGHT_H *4));
				coords.get(3).setXYZ(p2x - (p2x-p1x)/2 ,p2y - (p2y-p1y)/2,(int) (z+EXHEIGHT_H *4));
				coords.get(4).setXYZ((int) (p1x - (p2x-p1x)/3.5) ,(int) (p1y - (p2y-p1y)/3.5),(int) (z+EXHEIGHT_H*1.8));
				coords.get(5).setXYZ(p1x ,p1y,(int) (z+EXHEIGHT_H*1.8));
				coords.get(6).setXYZ(coords.get(0));
    			break;

    		case JUMPRIGHT2:
    		case LANDRIGHT1:
				coords.get(0).setXYZ(p2x - (p2x-p1x)/2 , p2y - (p2y-p1y)/2,(int) (z+EXHEIGHT_H*4));
				coords.get(1).setXYZ((int) (p2x + (p2x-p1x)/3.5) ,(int) (p2y + (p2y-p1y)/3.5),(int) (z+EXHEIGHT_H*1.8));
				coords.get(2).setXYZ(p2x ,p2y,(int) (z+EXHEIGHT_H*1.8));
				coords.get(3).setXYZ(p2x ,p2y,(int) z);
				coords.get(4).setXYZ((int) (p2x - (p2x-p1x)/4.5) ,(int) (p2y - (p2y-p1y)/4.5),(int) (z+ EXHEIGHT_H*8));
				coords.get(5).setXYZ((int) (p2x - (p2x-p1x)/4.5) ,(int) (p2y - (p2y-p1y)/4.5),(int) (z+EXHEIGHT_H *4));
				coords.get(6).setXYZ(coords.get(0));
    			break;
    		}
			coordsMap.coords=coords;
    	}

    	return coordsMap;
    }
}

