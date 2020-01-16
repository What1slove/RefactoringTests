package wbtempest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ColumnTest {

    Column col;

    @Before
    public void setUp() throws Exception {
        col = new Column(1,2,3,4);
    }

    @Test
    public void getFirstPointX() {
        int result =col.getFirstPointX();
        assertEquals(result,1);
    }


    @Test
    public void getFirstPointY() {
        int result =col.getFirstPointY();
        assertEquals(result,2);
    }

    @Test
    public void getSecondPointX() {
        int result =col.getSecondPointX();
        assertEquals(result,3);
    }

    @Test
    public void getSecondPointY() {
        int result =col.getSecondPointY();
        assertEquals(result,4);
    }

    @Test
    public void getFirstPoint() {
        int[] result =col.getFirstPoint();
        int[] expectedResult = new int[]{1,2};
        assertArrayEquals(result,expectedResult);
    }

    @Test
    public void getSecondPoint() {
        int[] result =col.getSecondPoint();
        int[] expectedResult = new int[]{3,4};
        assertArrayEquals(result,expectedResult);
    }
}