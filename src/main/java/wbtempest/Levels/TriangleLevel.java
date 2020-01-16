package wbtempest.Levels;

import wbtempest.Column;

public class TriangleLevel extends Level {
    public TriangleLevel(int levnum, int b_width, int b_height,int numscreens) {
        super(levnum, b_width, b_height,numscreens);
        this.continuous = true;
        generateColumns(b_width, b_height);
    }

    private void generateColumns(int b_width, int b_height) {
        int x, y, oldx=0, oldy=0;
        int cx = b_width/2;  // center for drawing; not same as where z-axis goes to.
        int cy = b_height*3/5;

        boolean firsttime = true;
        int radius = 320;
        int ncols = 15;
        int colsPerSide = ncols/3;
        // left
        for (x = cx, y = cy-radius; y < cy+radius*3/4; y+=(radius*3/2/colsPerSide),x-=radius*2/3/colsPerSide){
            if (firsttime){
                firsttime = false;
            }
            else {
                Column col = new Column(oldx, oldy, x, y);
                columns.add(col);
            }
            oldx = x;
            oldy = y;
        }
        // bottom
        firsttime = true;
        int targx = cx + (cx-oldx);
        for (x = oldx, y = oldy; x < targx; x+=(radius*4/3/colsPerSide)){
            if (firsttime){
                firsttime = false;
            }
            else {
                Column col = new Column(oldx, oldy, x, y);
                columns.add(col);
            }
            oldx = x;
            oldy = y;
        }
        // right
        for (; y >= cy-radius; y-=(radius*3/2/colsPerSide),x-=radius*2/3/colsPerSide+1){
            Column col = new Column(oldx, oldy, x, y);
            columns.add(col);
            oldx = x;
            oldy = y;
        }
    }
}
