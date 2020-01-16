package wbtempest.Levels;

public class LevelFactory {
    private int numscreens=6;
    public Level createLevel (int levnum, int b_width, int b_height){
        int screennum = (levnum-1) % numscreens;
        switch (screennum) {
            case 0:
                return new CircleLevel(levnum,b_width,b_height,numscreens);
            case 1:
                return new SquareLevel(levnum,b_width,b_height,numscreens);
            case 2:
                return new TriangleLevel(levnum,b_width,b_height,numscreens);
            case 3:
                return new AngledVLevel(levnum,b_width,b_height,numscreens);
            case 4:
                return new StraightLineLevel(levnum,b_width,b_height,numscreens);
            case 5:
                return new JaggedVLevel(levnum,b_width,b_height,numscreens);
        }
        return new CircleLevel(levnum,b_width,b_height,numscreens);
    }
}
