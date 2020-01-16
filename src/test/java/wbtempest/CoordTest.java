package wbtempest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoordTest {


    @Test
    public void getX() {
        Coord coord = new Coord(1,2,3);
        int result = coord.getX();
        assertEquals(result,1);
    }

    @Test
    public void getY() {
        Coord coord = new Coord(1,2,3);
        int result = coord.getY();
        assertEquals(result,2);
    }

    @Test
    public void getZ() {
        Coord coord = new Coord(1,2,3);
        int result = coord.getZ();
        assertEquals(result,3);
    }

    @Test
    public void setX() {
        Coord coord = new Coord(1,2,3);
        coord.setX(4);
        int result = coord.getX();
        assertEquals(result,4);
    }

    @Test
    public void setY() {
        Coord coord = new Coord(1,2,3);
        coord.setY(4);
        int result = coord.getY();
        assertEquals(result,4);
    }

    @Test
    public void setZ() {
        Coord coord = new Coord(1,2,3);
        coord.setZ(4);
        int result = coord.getZ();
        assertEquals(result,4);
    }

    @Test
    public void setXYZ_byInts_checkX() {
        Coord coord = new Coord(1,2,3);
        coord.setXYZ(4,5,6);
        int result = coord.getX();
        assertEquals(result,4);
    }

    @Test
    public void setXYZ_byInts_checkY() {
        Coord coord = new Coord(1,2,3);
        coord.setXYZ(4,5,6);
        int result = coord.getY();
        assertEquals(result,5);
    }

    @Test
    public void setXYZ_byInts_checkZ() {
        Coord coord = new Coord(1,2,3);
        coord.setXYZ(4,5,6);
        int result = coord.getZ();
        assertEquals(result,6);
    }

    @Test
    public void SetXYZ_byAnotherCoord_checkX() {
        Coord coord = new Coord(1,2,3);
        Coord newCoord = new Coord(4,5,6);
        coord.setXYZ(newCoord);
        int result = coord.getX();
        assertEquals(result,4);
    }
    @Test
    public void SetXYZ_byAnotherCoord_checkY() {
        Coord coord = new Coord(1,2,3);
        Coord newCoord = new Coord(4,5,6);
        coord.setXYZ(newCoord);
        int result = coord.getY();
        assertEquals(result,5);
    }
    @Test
    public void SetXYZ_byAnotherCoord_checkZ() {
        Coord coord = new Coord(1,2,3);
        Coord newCoord = new Coord(4,5,6);
        coord.setXYZ(newCoord);
        int result = coord.getZ();
        assertEquals(result,6);
    }
}