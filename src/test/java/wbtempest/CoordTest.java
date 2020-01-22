package wbtempest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoordTest {

    Coord coord;

    @Before
    public void setUp() throws Exception {
        coord = new Coord(1,20,300);
    }

    @Test
    public void getX() {
        int result = coord.getX();
        assertEquals(result,1);
    }

    @Test
    public void getY() {
        int result = coord.getY();
        assertEquals(result,20);
    }

    @Test
    public void getZ() {
        int result = coord.getZ();
        assertEquals(result,300);
    }

    @Test
    public void setX() {
        coord.setX(4000);
        int result = coord.getX();
        assertEquals(result,4000);
    }

    @Test
    public void setY() {
        coord.setY(4000);
        int result = coord.getY();
        assertEquals(result,4000);
    }

    @Test
    public void setZ() {
        coord.setZ(4000);
        int result = coord.getZ();
        assertEquals(result,4000);
    }

    @Test
    public void setXYZ_byInts_checkX() {
        coord.setXYZ(4,50,600);
        int result = coord.getX();
        assertEquals(result,4);
    }

    @Test
    public void setXYZ_byInts_checkY() {
        coord.setXYZ(4,50,600);
        int result = coord.getY();
        assertEquals(result,50);
    }

    @Test
    public void setXYZ_byInts_checkZ() {
        coord.setXYZ(4,50,600);
        int result = coord.getZ();
        assertEquals(result,600);
    }

    @Test
    public void SetXYZ_byAnotherCoord_checkX() {
        Coord newCoord = new Coord(4,50,600);
        coord.setXYZ(newCoord);
        int result = coord.getX();
        assertEquals(result,4);
    }
    @Test
    public void SetXYZ_byAnotherCoord_checkY() {
        Coord newCoord = new Coord(4,50,600);
        coord.setXYZ(newCoord);
        int result = coord.getY();
        assertEquals(result,50);
    }
    @Test
    public void SetXYZ_byAnotherCoord_checkZ() {
        Coord newCoord = new Coord(4,50,600);
        coord.setXYZ(newCoord);
        int result = coord.getZ();
        assertEquals(result,600);
    }
}