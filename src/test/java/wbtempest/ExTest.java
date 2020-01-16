package wbtempest;

import org.junit.Test;
import wbtempest.Levels.Level;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExTest {


    /**
     * Проверею соответствие результатов старых и новых(отрефакторинных) методов
     * т.к это единственный критерий "правильности" возвращаемых данных
     */
    @Test
    public void getCoords_JumpRight1() {
        Level level = mock(Level.class);
        Column testColumn = new Column(1,20,300,4000);
        Column[] collumnsArray = new Column[10];
        collumnsArray[0] = testColumn;
        when(level.getColumns()).thenReturn(Arrays.asList(collumnsArray));
        Ex ex = new Ex(0,false,10,true,false);
        int z = 10;
        ex.resetZ(z);
        GameObjectCoordsMap result = ex.getCoords(level);

        int HEIGHT = 20;
        int EXHEIGHT_H = HEIGHT/2;
        int[][] coords=new int[7][3];
        int[] p1 = testColumn.getFirstPoint();
        int[] p2 = testColumn.getSecondPoint();
        coords[0][0] = p1[0] + (p2[0]-p1[0])/4;
        coords[0][1] = p1[1] + (p2[1]-p1[1])/4;
        coords[0][2] = (int) (z+EXHEIGHT_H*2);
        coords[1][0] = p2[0] + (p2[0]-p1[0])/4;
        coords[1][1] = p2[1] + (p2[1]-p1[1])/4;
        coords[1][2] = (int) (z+EXHEIGHT_H*2);
        coords[2][0] = p2[0] - (p2[0]-p1[0])/11;
        coords[2][1] = p2[1] - (p2[1]-p1[1])/11;
        coords[2][2] = (int) (z+EXHEIGHT_H*1.8);
        coords[3][0] = p2[0];
        coords[3][1] = p2[1];
        coords[3][2] = (int) z;
        coords[4][0] = (int) (p1[0] + (p2[0]-p1[0])/2);
        coords[4][1] = (int) (p1[1] + (p2[1]-p1[1])/2);
        coords[4][2] = (int) (z+ EXHEIGHT_H*5);
        coords[5][0] = (int) (p2[0] - (p2[0]-p1[0])/2.5);
        coords[5][1] = (int) (p2[1] - (p2[1]-p1[1])/2.5);
        coords[5][2] = (int) (z+EXHEIGHT_H *2.6);
        coords[6] = coords[0];


        for (int i=0;i<7;i++){
            assertEquals(coords[i][0],result.coords.get(i).getX());
            assertEquals(coords[i][1],result.coords.get(i).getY());
            assertEquals(coords[i][2],result.coords.get(i).getZ());
        }
    }

    @Test
    public void getCoords_JumpRight2() {
        Level level = mock(Level.class);
        Column testColumn = new Column(1,20,300,4000);
        Column[] collumnsArray = new Column[10];
        collumnsArray[0] = testColumn;
        when(level.getColumns()).thenReturn(Arrays.asList(collumnsArray));
        Ex ex = new Ex(0,false,10,true,false);
        ex.move(0,0);
        int z = 10;
        ex.resetZ(z);
        GameObjectCoordsMap result = ex.getCoords(level);

        int HEIGHT = 20;
        int EXHEIGHT_H = HEIGHT/2;
        int[][] coords=new int[7][3];
        int[] p1 = testColumn.getFirstPoint();
        int[] p2 = testColumn.getSecondPoint();
        coords[0][0] = p2[0] - (p2[0]-p1[0])/2;
        coords[0][1] = p2[1] - (p2[1]-p1[1])/2;
        coords[0][2] = (int) (z+EXHEIGHT_H*4);
        coords[1][0] = (int) (p2[0] + (p2[0]-p1[0])/3.5);
        coords[1][1] = (int) (p2[1] + (p2[1]-p1[1])/3.5);
        coords[1][2] = (int) (z+EXHEIGHT_H*1.8);
        coords[2][0] = p2[0];// - (p2[0]-p1[0])/15;
        coords[2][1] = p2[1];// - (p2[1]-p1[1])/15;
        coords[2][2] = (int) (z+EXHEIGHT_H*1.8);
        coords[3][0] = p2[0];
        coords[3][1] = p2[1];
        coords[3][2] = (int) z;
        coords[4][0] = (int) (p2[0] - (p2[0]-p1[0])/4.5);
        coords[4][1] = (int) (p2[1] - (p2[1]-p1[1])/4.5);
        coords[4][2] = (int) (z+ EXHEIGHT_H*8);
        coords[5][0] = (int) (p2[0] - (p2[0]-p1[0])/4.5);
        coords[5][1] = (int) (p2[1] - (p2[1]-p1[1])/4.5);
        coords[5][2] = (int) (z+EXHEIGHT_H *4);
        coords[6] = coords[0];


        for (int i=0;i<7;i++){
            assertEquals(coords[i][0],result.coords.get(i).getX());
            assertEquals(coords[i][1],result.coords.get(i).getY());
            assertEquals(coords[i][2],result.coords.get(i).getZ());
        }

    }

    @Test
    public void getCoords_Landleft1() {
        Level level = mock(Level.class);
        Column testColumn = new Column(1,20,300,4000);
        Column[] collumnsArray = new Column[10];
        collumnsArray[1] = testColumn;
        when(level.getColumns()).thenReturn(Arrays.asList(collumnsArray));
        Ex ex = new Ex(0,false,10,true,false);
        ex.move(0,0);
        ex.move(0,0);
        int z = 10;
        ex.resetZ(z);
        GameObjectCoordsMap result = ex.getCoords(level);

        int HEIGHT = 20;
        int EXHEIGHT_H = HEIGHT/2;
        int[][] coords=new int[7][3];
        int[] p1 = testColumn.getFirstPoint();
        int[] p2 = testColumn.getSecondPoint();
        coords[0][0] = p1[0];
        coords[0][1] = p1[1];
        coords[0][2] = (int) z;
        coords[1][0] = (int) (p1[0] + (p2[0]-p1[0])/4.5);
        coords[1][1] = (int) (p1[1] + (p2[1]-p1[1])/4.5);
        coords[1][2] = (int) (z+ EXHEIGHT_H*8);
        coords[2][0] = (int) (p1[0] + (p2[0]-p1[0])/4.5);
        coords[2][1] = (int) (p1[1] + (p2[1]-p1[1])/4.5);
        coords[2][2] = (int) (z+EXHEIGHT_H *4);
        coords[3][0] = p2[0] - (p2[0]-p1[0])/2;
        coords[3][1] = p2[1] - (p2[1]-p1[1])/2;
        coords[3][2] = (int) (z+EXHEIGHT_H*4);
        coords[4][0] = (int) (p1[0] - (p2[0]-p1[0])/3.5);
        coords[4][1] = (int) (p1[1] - (p2[1]-p1[1])/3.5);
        coords[4][2] = (int) (z+EXHEIGHT_H*1.8);
        coords[5][0] = p1[0];// - (p2[0]-p1[0])/15;
        coords[5][1] = p1[1];// - (p2[1]-p1[1])/15;
        coords[5][2] = (int) (z+EXHEIGHT_H*1.8);
        coords[6] = coords[0];


        for (int i=0;i<7;i++){
            assertEquals(coords[i][0],result.coords.get(i).getX());
            assertEquals(coords[i][1],result.coords.get(i).getY());
            assertEquals(coords[i][2],result.coords.get(i).getZ());
        }

    }

    @Test
    public void getCoords_Landleft2() {
        Level level = mock(Level.class);
        Column testColumn = new Column(1,20,300,4000);
        Column[] collumnsArray = new Column[10];
        collumnsArray[1] = testColumn;
        when(level.getColumns()).thenReturn(Arrays.asList(collumnsArray));
        Ex ex = new Ex(0,false,10,true,false);
        ex.move(0,0);
        ex.move(0,0);
        ex.move(0,0);
        int z = 10;
        ex.resetZ(z);
        GameObjectCoordsMap result = ex.getCoords(level);

        int HEIGHT = 20;
        int EXHEIGHT_H = HEIGHT/2;
        int[][] coords=new int[7][3];
        int[] p1 = testColumn.getFirstPoint();
        int[] p2 = testColumn.getSecondPoint();
        coords[0][0] = p1[0];
        coords[0][1] = p1[1];
        coords[0][2] = (int) z;
        coords[1][0] = (int) (p1[0] + (p2[0]-p1[0])/2);
        coords[1][1] = (int) (p1[1] + (p2[1]-p1[1])/2);
        coords[1][2] = (int) (z+ EXHEIGHT_H*5);
        coords[2][0] = (int) (p1[0] + (p2[0]-p1[0])/2.5);
        coords[2][1] = (int) (p1[1] + (p2[1]-p1[1])/2.5);
        coords[2][2] = (int) (z+EXHEIGHT_H *2.6);
        coords[3][0] = p2[0] - (p2[0]-p1[0])/4;
        coords[3][1] = p2[1] - (p2[1]-p1[1])/4;
        coords[3][2] = (int) (z+EXHEIGHT_H*2);
        coords[4][0] = p1[0] - (p2[0]-p1[0])/4;
        coords[4][1] = p1[1] - (p2[1]-p1[1])/4;
        coords[4][2] = (int) (z+EXHEIGHT_H*2);
        coords[5][0] = p1[0] + (p2[0]-p1[0])/11;
        coords[5][1] = p1[1] + (p2[1]-p1[1])/11;
        coords[5][2] = (int) (z+EXHEIGHT_H*1.8);
        coords[6] = coords[0];


        for (int i=0;i<7;i++){
            assertEquals(coords[i][0],result.coords.get(i).getX());
            assertEquals(coords[i][1],result.coords.get(i).getY());
            assertEquals(coords[i][2],result.coords.get(i).getZ());
        }

    }

    @Test
    public void getCoords_Straight() {
        Level level = mock(Level.class);
        Column testColumn = new Column(1,20,300,4000);
        Column[] collumnsArray = new Column[10];
        collumnsArray[0] = testColumn;
        when(level.getColumns()).thenReturn(Arrays.asList(collumnsArray));
        Ex ex = new Ex(0,false,10,true,false);
        ex.resetState();
        int z = 10;
        ex.resetZ(z);
        GameObjectCoordsMap result = ex.getCoords(level);

        int HEIGHT = 20;
        int EXHEIGHT_H = HEIGHT/2;
        int[][] coords=new int[7][3];
        int[] p1 = testColumn.getFirstPoint();
        int[] p2 = testColumn.getSecondPoint();
        coords[0][0] = p1[0];
        coords[0][1] = p1[1];
        coords[0][2] = (int) (z-EXHEIGHT_H);
        coords[1][0] = p2[0];
        coords[1][1] = p2[1];
        coords[1][2] = (int) (z+EXHEIGHT_H);
        coords[2][0] = p2[0] - (p2[0]-p1[0])/3;
        coords[2][1] = p2[1] - (p2[1]-p1[1])/3;
        coords[2][2] = (int) z;
        coords[3][0] = p2[0];
        coords[3][1] = p2[1];
        coords[3][2] = (int) (z-EXHEIGHT_H);
        coords[4][0] = p1[0];
        coords[4][1] = p1[1];
        coords[4][2] = (int) (z+EXHEIGHT_H);
        coords[5][0] = p1[0] + (p2[0]-p1[0])/3;
        coords[5][1] = p1[1] + (p2[1]-p1[1])/3;
        coords[5][2] = (int) z;
        coords[6][0] = p1[0];
        coords[6][1] = p1[1];
        coords[6][2] = (int) (z-EXHEIGHT_H);


        for (int i=0;i<7;i++){
            assertEquals(coords[i][0],result.coords.get(i).getX());
            assertEquals(coords[i][1],result.coords.get(i).getY());
            assertEquals(coords[i][2],result.coords.get(i).getZ());
        }

    }

    @Test
    public void getCoords_Pod() {
        Level level = mock(Level.class);
        Column testColumn = new Column(1,20,300,4000);
        Column[] collumnsArray = new Column[10];
        collumnsArray[0] = testColumn;
        when(level.getColumns()).thenReturn(Arrays.asList(collumnsArray));
        Ex ex = new Ex(0,true,10,true,false);
        int z = 10;
        ex.resetZ(z);
        GameObjectCoordsMap result = ex.getCoords(level);

        int PODSIZE = 35;
        int[][] coords=new int[17][3];
        int[] p1 = testColumn.getFirstPoint();
        int[] p2 = testColumn.getSecondPoint();
        int cx = p1[0] + (p2[0]-p1[0])/2;
        int cy = p1[1] + (p2[1]-p1[1])/2;

        // define outer and inner diamonds
        int[][] outer = new int[4][3];
        int[][] inner = new int[4][3];
        outer[0][0] = cx;
        outer[0][1] = cy - PODSIZE;
        outer[0][2] = (int)z;
        outer[1][0] = cx + PODSIZE;
        outer[1][1] = cy;
        outer[1][2] = (int) z;
        outer[2][0] = cx;
        outer[2][1] = cy + PODSIZE;
        outer[2][2] = (int) z;
        outer[3][0] = cx - PODSIZE;
        outer[3][1] = cy;
        outer[3][2] = (int) z;
        inner[0][0] = cx;
        inner[0][1] = cy - PODSIZE/3;
        inner[0][2] = (int) z;
        inner[1][0] = cx + PODSIZE/3;
        inner[1][1] = cy;
        inner[1][2] = (int) z;
        inner[2][0] = cx;
        inner[2][1] = cy + PODSIZE/3;
        inner[2][2] = (int) z;
        inner[3][0] = cx - PODSIZE/3;
        inner[3][1] = cy;
        inner[3][2] = (int) z;

        // define line path through those diamonds:
        coords[0] = outer[0];
        coords[1] = outer[1];
        coords[2] = inner[1];
        coords[3] = inner [0];
        coords[4] = outer[1];
        coords[5] = outer[2];
        coords[6] = inner[2];
        coords[7] = inner[1];
        coords[8] = outer[2];
        coords[9] = outer[3];
        coords[10]= inner[3];
        coords[11]= inner[2];
        coords[12]= outer[3];
        coords[13]= outer[0];
        coords[14]= inner[0];
        coords[15]= inner[3];
        coords[16]= outer[0];


        for (int i=0;i<17;i++){
            assertEquals(coords[i][0],result.coords.get(i).getX());
            assertEquals(coords[i][1],result.coords.get(i).getY());
            assertEquals(coords[i][2],result.coords.get(i).getZ());
        }

    }

    @Test
    public void getDeathCoords() {
        Level level = mock(Level.class);
        Column testColumn = new Column(1,2,3,4);
        Column[] collumnsArray = new Column[10];
        collumnsArray[0] = testColumn;
        when(level.getColumns()).thenReturn(Arrays.asList(collumnsArray));
        Ex ex = new Ex(0,false,10,true,false);
        int z = 10;
        ex.resetZ(z);
        GameObjectCoordsMap result = ex.getDeathCoords(level);

        int HEIGHT = 20;
        int EXHEIGHT_H = HEIGHT/2;
        int[][] coords=new int[15][3];
        int[] p1 = testColumn.getFirstPoint();
        int[] p2 = testColumn.getSecondPoint();
        coords[0][0] = p1[0] + (p2[0]-p1[0])/5;
        coords[0][1] = p1[1] + (p2[0]-p1[0])/5;
        coords[0][2] = (int) z;
        coords[1][0] = p2[0] - (p2[0]-p1[0])/2;  // center point
        coords[1][1] = p2[1] - (p2[1]-p1[1])/2;
        coords[1][2] = (int) z;
        coords[2][0] = p1[0] + (p2[0]-p1[0])/3;
        coords[2][1] = p1[1] + (p2[1]-p1[1])/3;
        coords[2][2] = (int) (z + EXHEIGHT_H*2);
        coords[3] = coords[1];
        coords[4][0] = p2[0] - (p2[0]-p1[0])/2;
        coords[4][1] = p2[1] - (p2[1]-p1[1])/2;
        coords[4][2] = (int) (z + EXHEIGHT_H*4);
        coords[5] = coords[1];
        coords[6][0] = p2[0] - (p2[0]-p1[0])/3;
        coords[6][1] = p2[1] - (p2[1]-p1[1])/3;
        coords[6][2] = (int) (z+EXHEIGHT_H*2);
        coords[7] = coords[1];
        coords[8][0] = p2[0] - (p2[0]-p1[0])/5;
        coords[8][1] = p2[1] - (p2[1]-p1[1])/5;
        coords[8][2] = (int) z;
        coords[9] = coords[1];
        coords[10][0] = p2[0] - (p2[0]-p1[0])/3;
        coords[10][1] = p2[1] - (p2[1]-p1[1])/3;
        coords[10][2] = (int) (z-EXHEIGHT_H*2);
        coords[11] = coords[1];
        coords[12][0] = p2[0] - (p2[0]-p1[0])/2;
        coords[12][1] = p2[1] - (p2[1]-p1[1])/2;
        coords[12][2] = (int) (z - EXHEIGHT_H*4);
        coords[13] = coords[1];
        coords[14][0] = p1[0] + (p2[0]-p1[0])/3;
        coords[14][1] = p1[1] + (p2[1]-p1[1])/3;
        coords[14][2] = (int) (z - EXHEIGHT_H*2);


        for (int i=0;i<15;i++){
            assertEquals(coords[i][0],result.coords.get(i).getX());
            assertEquals(coords[i][1],result.coords.get(i).getY());
            assertEquals(coords[i][2],result.coords.get(i).getZ());
        }

    }
}