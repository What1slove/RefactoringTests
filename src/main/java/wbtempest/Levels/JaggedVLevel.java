package wbtempest.Levels;

import wbtempest.Column;

public class JaggedVLevel extends Level {
    public JaggedVLevel(int levnum, int b_width, int b_height,int numscreens) {
        super(levnum, b_width, b_height,numscreens);
        zpull_x = b_width/2;
        zpull_y = b_height /5;
        generateColumns(b_width, b_height);
    }

    private void generateColumns(int b_width, int b_height) {
        int x, y, oldx=0, oldy=0;
        int cx = b_width/2;  // center for drawing; not same as where z-axis goes to.
        int cy = b_height * 31/60;
        boolean firsttime = true;
        int ycolwidth = 80;
        int xcolwidth = ycolwidth *4/5;
        int ystart;
        x = oldx = cx - (int)(xcolwidth*3.5);
        y = oldy = ystart = cy - ycolwidth;
        y+=ycolwidth;
        columns.add(new Column(oldx, oldy, x, y));
        oldy = y;
        while (y < ystart + ycolwidth*4){
            x+=xcolwidth;
            columns.add(new Column(oldx, oldy, x, y));
            oldx=x;
            y+=ycolwidth;
            columns.add(new Column(oldx, oldy, x, y));
            oldy=y;
        }
        while (y > ystart){
            x+=xcolwidth;
            columns.add(new Column(oldx, oldy, x, y));
            oldx=x;
            y-=ycolwidth;
            columns.add(new Column(oldx, oldy, x, y));
            oldy=y;
        }
    }
}
