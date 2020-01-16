package wbtempest.Levels;

import wbtempest.Column;

public class StraightLineLevel extends Level {
    public StraightLineLevel(int levnum, int b_width, int b_height,int numscreens) {
        super(levnum, b_width, b_height,numscreens);
        zpull_x = b_width/2;
        zpull_y = b_height /4;
        generateColumns(b_width, b_height);
    }

    private void generateColumns(int b_width, int b_height) {
        int x, y, oldx=0, oldy=0;
        boolean firsttime = true;
        int ncols = 14;
        y = b_height * 5/7;
        for (x = b_width *1/(ncols+2); x < b_width * (1+ncols)/(ncols+2); x+= b_width/(ncols+2)){
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
    }
}
