package wbtempest;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameObjectCoordsMapTest {

    @Test
    public void addCoord_addToEmptyObject_checkX() {
        GameObjectCoordsMap coordsMap = new GameObjectCoordsMap();
        coordsMap.addCoord(1,20,300);
        Coord expectedResult = new Coord(1,20,300);
        Coord result = coordsMap.coords.get(0);
        assertEquals(result.getX(),expectedResult.getX());
    }
    @Test
    public void addCoord_addToEmptyObject_checkY() {
        GameObjectCoordsMap coordsMap = new GameObjectCoordsMap();
        coordsMap.addCoord(1,20,300);
        Coord expectedResult = new Coord(1,20,300);
        Coord result = coordsMap.coords.get(0);
        assertEquals(result.getY(),expectedResult.getY());
    }
    @Test
    public void addCoord_addToEmptyObject_checkZ() {
        GameObjectCoordsMap coordsMap = new GameObjectCoordsMap();
        coordsMap.addCoord(1,20,300);
        Coord expectedResult = new Coord(1,20,300);
        Coord result = coordsMap.coords.get(0);
        assertEquals(result.getZ(),expectedResult.getZ());
    }
    @Test
    public void addCoord_addToNoEmptyObject_checkX() {
        GameObjectCoordsMap coordsMap = new GameObjectCoordsMap(1);
        coordsMap.addCoord(1,20,300);
        Coord expectedResult = new Coord(1,20,300);
        Coord result = coordsMap.coords.get(1);
        assertEquals(result.getX(),expectedResult.getX());
    }
    @Test
    public void addCoord_addToNoEmptyObject_checkY() {
        GameObjectCoordsMap coordsMap = new GameObjectCoordsMap(1);
        coordsMap.addCoord(1,20,300);
        Coord expectedResult = new Coord(1,20,300);
        Coord result = coordsMap.coords.get(1);
        assertEquals(result.getY(),expectedResult.getY());
    }
    @Test
    public void addCoord_addToNoEmptyObject_checkZ() {
        GameObjectCoordsMap coordsMap = new GameObjectCoordsMap(1);
        coordsMap.addCoord(1,20,300);
        Coord expectedResult = new Coord(1,20,300);
        Coord result = coordsMap.coords.get(1);
        assertEquals(result.getZ(),expectedResult.getZ());
    }
}