package wbtempest.Levels;

import wbtempest.Column;

public class AngledVLevel extends Level {
    public AngledVLevel(int levnum, int b_width, int b_height,int numscreens) {
        super(levnum, b_width, b_height,numscreens);
        zpull_x = b_width/2;
        zpull_y = b_height /4;
        generateColumns(b_width, b_height);
    }

    private void generateColumns(int b_width, int b_height) {
        int x, y, oldx=0, oldy=0;
        int cx = b_width/2;  // center for drawing; not same as where z-axis goes to.
        int cy = b_height * 31/60;
        boolean firsttime = true;
        int ncols = 16;

        for (x = cx/2, y=cy/3; x < cx; x+= cx/2/(ncols/2), y+=(cy*3/2)/(ncols/2)){
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
        for (; x <= cx*3/2; x+= cx/2/(ncols/2), y-=(cy*3/2)/(ncols/2)){
            Column col = new Column(oldx, oldy, x, y);
            columns.add(col);
            oldx = x;
            oldy = y;
        }
    }
}
