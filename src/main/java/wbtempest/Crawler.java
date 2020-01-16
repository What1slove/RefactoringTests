package wbtempest;

import wbtempest.Levels.Level;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Represents the player's crawler.
 * 
 * @author ugliest
 *
 */
public class Crawler {

	private static final int C_POSES = 6;
	private static final double SPEED = 2.2;
    static final int CHEIGHT = 10;
    private static final int CHEIGHT_H = CHEIGHT/2; // half height
    private static final int CHEIGHT_HP = (int) (CHEIGHT * 0.6);  // slightly more than half
    private static final int MAX_MISSILES = 7;
    private static final int INITIAL_ZAPS = 1;
    private double vpos;
    private double pos;
    private boolean visible;
    private Image image;
    private ArrayList<Missile> missiles;
    private Level lev;
    private int pos_max;
    private boolean fireFlag = false;
    private boolean szFlag = false;
    private int superzaps = 1;


    public Crawler(Level lev) {
        missiles = new ArrayList<Missile>();
        visible = true;
        this.lev = lev;
        this.pos_max = lev.getColumns().size() * C_POSES -1;
    }

    /**
     * handle crawler movement.  called per tick.
     * @param crawleroffset
     */
    public void move(int crawleroffset) {
    	int prevCol = getColumn();
        pos += vpos;
        if (prevCol != getColumn()) {
        	// we actually moved columns
        	SoundManager.get().play(Sound.CRAWLERMOVE);
        }

        if (lev.isContinuous()){
        	pos %= pos_max;
        	if (pos < 0)
        		pos = pos_max + pos;
        }
        else{
        	if (pos > pos_max)
        		pos = pos_max;
        	else if (pos < 0)
        		pos = 0;
        }
        
        if (fireFlag)
            fire(crawleroffset);
        
    }

    /** 
     * Returns the column number where the crawler currently is.
     * @return
     */
    public int getColumn(){
    	return (int)pos / C_POSES;
    }

    /**
     * Returns the coordinates to draw the crawler at its current position/pose.
     * 
     * Like everything in this game, the crawler is drawn based on a line
     * connecting a list of points. a few fixed positions are used.
     * 
     * @return
     */
    public GameObjectCoordsMap getCoords() {
        int colnum = getColumn();
        int pose = (int)pos % C_POSES / 2; // each pose here is doubled for more manageable movement

        GameObjectCoordsMap coordsMap = new GameObjectCoordsMap(9);
        ArrayList<Coord> coords = coordsMap.coords;
        Column column = lev.getColumns().get(colnum);
        int p1x=column.getFirstPointX();
        int p1y=column.getFirstPointY();
        int p2x=column.getSecondPointX();
        int p2y=column.getSecondPointY();
        switch (pose)
        {
        	case 0:{
                coords.get(0).setXYZ(p1x +(p2x - p1x)/3 ,p1y +(p2y - p1y)/3,CHEIGHT_H);
                coords.get(2).setXYZ(p1x +(p2x - p1x)/4 ,p1y +(p2y - p1y)/4,-CHEIGHT);
                coords.get(4).setXYZ(p2x -(p2x - p1x)/4 ,p2y -(p2y - p1y)/4,CHEIGHT_HP);
                coords.get(6).setXYZ(p1x +(p2x - p1x)/4 ,p1y +(p2y - p1y)/4,-CHEIGHT_H);
                break;
        	}
        	case 1: {
                coords.get(0).setXYZ(p1x +(p2x - p1x)/3 ,p1y +(p2y - p1y)/3,CHEIGHT_H);
                coords.get(2).setXYZ(p1x +(p2x - p1x)/2 ,p1y +(p2y - p1y)/2,-CHEIGHT);
                coords.get(4).setXYZ(p2x -(p2x - p1x)/3,p2y -(p2y - p1y)/3,CHEIGHT_H);
                coords.get(6).setXYZ(p1x +(p2x - p1x)/2 ,p1y +(p2y - p1y)/2,-CHEIGHT_H);
                break;
        	}
        	case 2: {
                coords.get(0).setXYZ(p1x +(p2x - p1x)/4 ,p1y +(p2y - p1y)/4,CHEIGHT_HP);
                coords.get(2).setXYZ(p1x +(p2x - p1x)*3/4 ,p1y +(p2y - p1y)*3/4,-CHEIGHT);
                coords.get(4).setXYZ(p2x -(p2x - p1x)/3,p2y -(p2y - p1y)/3,CHEIGHT_H);
                coords.get(6).setXYZ(p1x +(p2x - p1x)*3/4 ,p1y +(p2y - p1y)*2/3,-CHEIGHT_H);
                break;
        	}
        }
        coords.get(1).setXYZ(p1x ,p1y,0);
        coords.get(3).setXYZ(p2x ,p2y,0);
        coords.get(5).setXYZ(p2x -(p2x - p1x)/6 ,p2y -(p2y - p1y)/6,0);
        coords.get(7).setXYZ(p1x +(p2x - p1x)/6 ,p1y +(p2y - p1y)/6,0);
        coords.get(8).setXYZ(coords.get(0));
        coordsMap.coords=coords;
        return coordsMap;
    }

    public Image getImage() {
        return image;
    }

    public ArrayList<Missile> getMissiles() {
        return missiles;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    private void accelRight(){
    	vpos = SPEED;
    }

    private void accelLeft(){
    	vpos = -SPEED;
    }
    
    public boolean isSuperzapping(){
    	return szFlag;
    }
    
    public void resetZapper(){
    	superzaps = INITIAL_ZAPS;
    }

    public void keyPressed(KeyEvent e, int crawleroffset) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
        	fireFlag = true;
        	fire(crawleroffset);
        }
        else if (key == KeyEvent.VK_LEFT) {
        	accelLeft();
        }
        else if (key == KeyEvent.VK_RIGHT) {
        	accelRight();
        }
        else if (key == KeyEvent.VK_S && superzaps > 0) {
        	szFlag = true;
        	superzaps--;
        }
    }

    /**
     * fire a missile.
     * @param zoffset - z pos of crawler
     */
    public void fire(int zoffset) {
    	if (missiles.size() < MAX_MISSILES) {
    		if (missiles.size() == 0 
    				|| missiles.get(missiles.size()-1).getZPos() > Missile.HEIGHT*3) {
        		missiles.add(new Missile(this.getColumn(), Missile.HEIGHT/3+zoffset, true));
        		SoundManager.get().play(Sound.FIRE);
     		}
    	}
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            System.out.println("зашло 3");
            vpos = 0;
        }
        else if (key == KeyEvent.VK_RIGHT) {
            vpos = 0;
        }
        else if (key == KeyEvent.VK_SPACE) {
        	fireFlag = false;
        }
        else if (key == KeyEvent.VK_S) {
        	szFlag = false;
        }

    }
}

