package wbtempest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ColumnTest {

    Column col;

    @Before
    public void setUp() throws Exception {
        col = new Column(1,20,300,4000);
    }

    @Test
    public void getFirstPointX() {
        int result =col.getFirstPointX();
        assertEquals(result,1);
    }


    @Test
    public void getFirstPointY() {
        int result =col.getFirstPointY();
        assertEquals(result,20);
    }

    @Test
    public void getSecondPointX() {
        int result =col.getSecondPointX();
        assertEquals(result,300);
    }

    @Test
    public void getSecondPointY() {
        int result =col.getSecondPointY();
        assertEquals(result,4000);
    }

    @Test
    public void getFirstPoint() {
        int[] result =col.getFirstPoint();
        int[] expectedResult = new int[]{1,20};
        assertArrayEquals(result,expectedResult);
    }

    @Test
    public void getSecondPoint() {
        int[] result =col.getSecondPoint();
        int[] expectedResult = new int[]{300,4000};
        assertArrayEquals(result,expectedResult);
    }
}