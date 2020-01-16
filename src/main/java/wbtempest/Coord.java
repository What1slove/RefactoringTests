package wbtempest;

public class Coord {
    private int x;
    private int y;
    private int z;

    public Coord(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Coord() {
        this.x = -1;
        this.y = -1;
        this.z = -1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void setXYZ(int x, int y,int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public void setXYZ(Coord coord) {
        this.x = coord.getX();
        this.y = coord.getY();
        this.z = coord.getZ();
    }


}
