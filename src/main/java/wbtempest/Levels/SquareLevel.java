package wbtempest.Levels;

import wbtempest.Column;

public class SquareLevel extends Level {
    public SquareLevel(int levnum, int b_width, int b_height,int numscreens) {
        super(levnum, b_width, b_height,numscreens);
        this.continuous = true;
        generateColumns(b_width, b_height);
    }

    private void generateColumns(int b_width, int b_height) {
        int ncols = 16;
        int x, y, oldx=0, oldy=0;
        int cx = b_width/2;  // center for drawing; not same as where z-axis goes to.
        int cy = b_height * 31/60;
        int radius = 250;
        boolean firsttime = true;
        int colsPerSide = ncols/4;
        // left
        for (x = cx - radius, y = cy-radius; y < cy+radius; y+=(radius*2/colsPerSide)){
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
        for (x = cx - radius, y = cy+radius; x < cx+radius; x+=(radius*2/colsPerSide)){
            Column col = new Column(oldx, oldy, x, y);
            columns.add(col);
            oldx = x;
            oldy = y;
        }
        // right
        for (x = cx + radius, y = cy+radius; y > cy-radius; y-=(radius*2/colsPerSide)){
            Column col = new Column(oldx, oldy, x, y);
            columns.add(col);
            oldx = x;
            oldy = y;
        }
        // top
        for (x = cx + radius, y = cy-radius; x >= cx-radius; x-=(radius*2/colsPerSide)){
            Column col = new Column(oldx, oldy, x, y);
            columns.add(col);
            oldx = x;
            oldy = y;
        }
    }
}
