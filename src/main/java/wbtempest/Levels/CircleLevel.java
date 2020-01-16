package wbtempest.Levels;

import wbtempest.Column;

public class CircleLevel extends Level {
    public CircleLevel(int levnum, int b_width, int b_height,int numscreens) {
        super(levnum, b_width, b_height,numscreens);
        this.continuous = true;
        generateCollumns(b_width, b_height);
    }

    private void generateCollumns(int b_width, int b_height) {
        int ncols = 16;
        int x, y, oldx=0, oldy=0;
        int cx = b_width/2;  // center for drawing; not same as where z-axis goes to.
        int cy = b_height * 31/60;
        int radius = 250;
        boolean firsttime = true;
        float rad_dist = (float) (3.1415927 * 2);
        float step = rad_dist/(ncols);
        for (float rads=0; rads < rad_dist+step/2; rads+=step)
        {
            x = cx - (int)(Math.sin(rads) * radius * .95);
            y = cy - (int)(Math.cos(rads) * radius);
            if (firsttime){
                firsttime = false;
            }
            else {
                Column col = new Column(oldx, oldy, x, y);
                this.columns.add(col);
            }
            oldx = x;
            oldy = y;
        }
    }
}
