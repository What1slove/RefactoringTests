package wbtempest;

import org.junit.Test;
import wbtempest.Levels.Level;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MissileTest {


    /**
     * Проверею соответствие результатов старых и новых(отрефакторинных) методов
     * т.к это единственный критерий "правильности" возвращаемых данных
     */
    @Test
    public void getCoords() {
        Level level = mock(Level.class);
        Column testColumn = new Column(1,20,300,4000);
        Column[] collumnsArray = new Column[10];
        collumnsArray[0] = testColumn;
        when(level.getColumns()).thenReturn(Arrays.asList(collumnsArray));
        int zpos = 10;
        Missile missile = new Missile(0,zpos,false);
        GameObjectCoordsMap result = missile.getCoords(level);

        int HEIGHT = 8;
        int HEIGHT_H = HEIGHT/2;
        int[][] coords=new int[5][3];
        int[] p1 = testColumn.getFirstPoint();
        int[] p2 = testColumn.getSecondPoint();
        coords[0][0] = p1[0]+(p2[0] - p1[0])*2/5;
        coords[0][1] = p1[1]+(p2[1] - p1[1])*2/5;
        coords[0][2] = zpos-HEIGHT_H;
        coords[1][0] = p1[0]+(p2[0] - p1[0])/2;
        coords[1][1] = p1[1]+(p2[1] - p1[1])/2;
        coords[1][2] = zpos-HEIGHT;
        coords[2][0] = p1[0]+(p2[0] - p1[0])*3/5;
        coords[2][1] = p1[1]+(p2[1] - p1[1])*3/5;
        coords[2][2] = zpos-HEIGHT_H;
        coords[3][0] = p1[0]+(p2[0] - p1[0])/2;
        coords[3][1] = p1[1]+(p2[1] - p1[1])/2;
        coords[3][2] = zpos;
        coords[4] = coords[0];


        for (int i=0;i<5;i++){
            assertEquals(coords[i][0],result.coords.get(i).getX());
            assertEquals(coords[i][1],result.coords.get(i).getY());
            assertEquals(coords[i][2],result.coords.get(i).getZ());
        }
    }

    @Test
    public void getLayerCoords() {
        Level level = mock(Level.class);
        Column testColumn = new Column(1,20,300,4000);
        Column[] collumnsArray = new Column[10];
        collumnsArray[0] = testColumn;
        when(level.getColumns()).thenReturn(Arrays.asList(collumnsArray));
        int zpos = 10;
        Missile missile = new Missile(0,zpos,false);
        GameObjectCoordsMap result = missile.getLayerCoords(level);

        int HEIGHT = 8;
        int HEIGHT_H = HEIGHT/2;
        int[][] coords=new int[5][3];
        int[] p1 = testColumn.getFirstPoint();
        int[] p2 = testColumn.getSecondPoint();
        coords[0][0] = p1[0]+(p2[0] - p1[0])*9/20;
        coords[0][1] = p1[1]+(p2[1] - p1[1])*9/20;
        coords[0][2] = zpos-HEIGHT_H;
        coords[1][0] = p1[0]+(p2[0] - p1[0])/2;
        coords[1][1] = p1[1]+(p2[1] - p1[1])/2;
        coords[1][2] = zpos-HEIGHT*3/5;
        coords[2][0] = p1[0]+(p2[0] - p1[0])*11/20;
        coords[2][1] = p1[1]+(p2[1] - p1[1])*11/20;
        coords[2][2] = zpos-HEIGHT_H;
        coords[3][0] = p1[0]+(p2[0] - p1[0])/2;
        coords[3][1] = p1[1]+(p2[1] - p1[1])/2;
        coords[3][2] = zpos-HEIGHT*2/5;
        coords[4] = coords[0];


        for (int i=0;i<5;i++){
            assertEquals(coords[i][0],result.coords.get(i).getX());
            assertEquals(coords[i][1],result.coords.get(i).getY());
            assertEquals(coords[i][2],result.coords.get(i).getZ());
        }
    }
}