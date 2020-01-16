package wbtempest;

import java.util.ArrayList;

public class GameObjectCoordsMap {
    public ArrayList<Coord> coords;

    public GameObjectCoordsMap() {
        ArrayList<Coord> coords = new ArrayList<Coord>();
        this.coords = coords;
    }

    public GameObjectCoordsMap(ArrayList<Coord> coords) {
        this.coords = coords;
    }

    public GameObjectCoordsMap(int size) {
        ArrayList<Coord> coords = new ArrayList<Coord>();
        if (size>0){
            for (int i=0;i<size;i++){
                Coord coord = new Coord(Integer.MAX_VALUE-1,Integer.MAX_VALUE-1,Integer.MAX_VALUE-1);
                coords.add(coord);
            }
        }
        this.coords = coords;
    }

    public void addCoord(int x, int y, int z){
        Coord coord = new Coord(x,y,z);
        coords.add(coord);
    }
}
