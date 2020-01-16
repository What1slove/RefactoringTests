package wbtempest;

import org.junit.Test;
import wbtempest.Levels.Level;
import wbtempest.Levels.LevelFactory;
import wbtempest.Levels.SquareLevel;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CrawlerTest {


    /**
     * Проверею соответствие результатов старых и новых(отрефакторинных) методов
     * т.к это единственный критерий "правильности" возвращаемых данных
     */
    @Test
    public void getCoords_pose0() {
        Level level = mock(Level.class);
        Column testColumn = new Column(1,20,300,4000);
        Column[] collumnsArray = new Column[10];
        collumnsArray[0] = testColumn;
        when(level.getColumns()).thenReturn(Arrays.asList(collumnsArray));
        Crawler crawler = new Crawler(level);
        GameObjectCoordsMap result = crawler.getCoords();


        int[][] coords=new int[9][3];
        int[] pt1 = testColumn.getFirstPoint();
        int[] pt2 = testColumn.getSecondPoint();
        int CHEIGHT = 10;
        int CHEIGHT_H = CHEIGHT/2;
        int CHEIGHT_HP = (int) (CHEIGHT * 0.6);
        coords[0][0] = pt1[0] +(pt2[0] - pt1[0])/3;
        coords[0][1] = pt1[1] +(pt2[1] - pt1[1])/3;
        coords[0][2] = CHEIGHT_H;
        coords[2][0] = pt1[0] +(pt2[0] - pt1[0])/4;
        coords[2][1] = pt1[1] +(pt2[1] - pt1[1])/4;
        coords[2][2] = -CHEIGHT;
        coords[4][0] = pt2[0] -(pt2[0] - pt1[0])/4;
        coords[4][1] = pt2[1] -(pt2[1] - pt1[1])/4;
        coords[4][2] = CHEIGHT_HP;
        coords[6][0] = pt1[0] +(pt2[0] - pt1[0])/4;
        coords[6][1] = pt1[1] +(pt2[1] - pt1[1])/4;
        coords[6][2] = -CHEIGHT_H;
        coords[1][0] = pt1[0];
        coords[1][1] = pt1[1];
        coords[1][2]=0;
        coords[3][0] = pt2[0];
        coords[3][1] = pt2[1];
        coords[3][2] = 0;
        coords[5][0] = pt2[0] -(pt2[0] - pt1[0])/6;
        coords[5][1] = pt2[1] -(pt2[1] - pt1[1])/6;
        coords[5][2] = 0;
        coords[7][0] = pt1[0] +(pt2[0] - pt1[0])/6;
        coords[7][1] = pt1[1] +(pt2[1] - pt1[1])/6;
        coords[7][2] = 0;
        coords[8] = coords[0];


        for (int i=0;i<9;i++){
            assertEquals(coords[i][0],result.coords.get(i).getX());
            assertEquals(coords[i][1],result.coords.get(i).getY());
            assertEquals(coords[i][2],result.coords.get(i).getZ());
        }
    }

    @Test
    public void getCoords_pose1() {
        Level level = mock(Level.class);
        Column testColumn = new Column(1,20,300,4000);
        Column[] collumnsArray = new Column[10];
        collumnsArray[0] = testColumn;
        when(level.getColumns()).thenReturn(Arrays.asList(collumnsArray));
        Crawler crawler = new Crawler(level);
        //иммитируем keyEvent для смены значения pose у объекта
        KeyEvent event = mock(KeyEvent.class);
        when(event.getKeyCode()).thenReturn(39);
        crawler.keyPressed(event,0);
        crawler.move(0);
        GameObjectCoordsMap result = crawler.getCoords();


        int[][] coords=new int[9][3];
        int[] pt1 = testColumn.getFirstPoint();
        int[] pt2 = testColumn.getSecondPoint();
        int CHEIGHT = 10;
        int CHEIGHT_H = CHEIGHT/2;
        int CHEIGHT_HP = (int) (CHEIGHT * 0.6);
        coords[0][0] = pt1[0] +(pt2[0] - pt1[0])/3;
        coords[0][1] = pt1[1] +(pt2[1] - pt1[1])/3;
        coords[0][2] = CHEIGHT_H;
        coords[2][0] = pt1[0] +(pt2[0] - pt1[0])/2;
        coords[2][1] = pt1[1] +(pt2[1] - pt1[1])/2;
        coords[2][2] = -CHEIGHT;
        coords[4][0] = pt2[0] -(pt2[0] - pt1[0])/3;
        coords[4][1] = pt2[1] -(pt2[1] - pt1[1])/3;
        coords[4][2] = CHEIGHT_H;
        coords[6][0] = pt1[0] +(pt2[0] - pt1[0])/2;
        coords[6][1] = pt1[1] +(pt2[1] - pt1[1])/2;
        coords[6][2] = -CHEIGHT_H;
        coords[1][0] = pt1[0];
        coords[1][1] = pt1[1];
        coords[1][2]=0;
        coords[3][0] = pt2[0];
        coords[3][1] = pt2[1];
        coords[3][2] = 0;
        coords[5][0] = pt2[0] -(pt2[0] - pt1[0])/6;
        coords[5][1] = pt2[1] -(pt2[1] - pt1[1])/6;
        coords[5][2] = 0;
        coords[7][0] = pt1[0] +(pt2[0] - pt1[0])/6;
        coords[7][1] = pt1[1] +(pt2[1] - pt1[1])/6;
        coords[7][2] = 0;
        coords[8] = coords[0];


        for (int i=0;i<9;i++){
            assertEquals(coords[i][0],result.coords.get(i).getX());
            assertEquals(coords[i][1],result.coords.get(i).getY());
            assertEquals(coords[i][2],result.coords.get(i).getZ());
        }
    }

    @Test
    public void getCoords_pose2() {
        Level level = mock(Level.class);
        Column testColumn = new Column(1,20,300,4000);
        Column[] collumnsArray = new Column[10];
        collumnsArray[0] = testColumn;
        when(level.getColumns()).thenReturn(Arrays.asList(collumnsArray));
        Crawler crawler = new Crawler(level);
        //иммитируем keyEvent для смены значения pose у объекта
        KeyEvent event = mock(KeyEvent.class);
        when(event.getKeyCode()).thenReturn(39);
        crawler.keyPressed(event,0);
        crawler.move(0);
        crawler.move(0);
        GameObjectCoordsMap result = crawler.getCoords();


        int[][] coords=new int[9][3];
        int[] pt1 = testColumn.getFirstPoint();
        int[] pt2 = testColumn.getSecondPoint();
        int CHEIGHT = 10;
        int CHEIGHT_H = CHEIGHT/2;
        int CHEIGHT_HP = (int) (CHEIGHT * 0.6);
        coords[0][0] = pt1[0] +(pt2[0] - pt1[0])/4;
        coords[0][1] = pt1[1] +(pt2[1] - pt1[1])/4;
        coords[0][2] = CHEIGHT_HP;
        coords[2][0] = pt1[0] +(pt2[0] - pt1[0])*3/4;
        coords[2][1] = pt1[1] +(pt2[1] - pt1[1])*3/4;
        coords[2][2] = -CHEIGHT;
        coords[4][0] = pt2[0] -(pt2[0] - pt1[0])/3;
        coords[4][1] = pt2[1] -(pt2[1] - pt1[1])/3;
        coords[4][2] = CHEIGHT_H;
        coords[6][0] = pt1[0] +(pt2[0] - pt1[0])*3/4;
        coords[6][1] = pt1[1] +(pt2[1] - pt1[1])*2/3;
        coords[6][2] = -CHEIGHT_H;
        coords[1][0] = pt1[0];
        coords[1][1] = pt1[1];
        coords[1][2]=0;
        coords[3][0] = pt2[0];
        coords[3][1] = pt2[1];
        coords[3][2] = 0;
        coords[5][0] = pt2[0] -(pt2[0] - pt1[0])/6;
        coords[5][1] = pt2[1] -(pt2[1] - pt1[1])/6;
        coords[5][2] = 0;
        coords[7][0] = pt1[0] +(pt2[0] - pt1[0])/6;
        coords[7][1] = pt1[1] +(pt2[1] - pt1[1])/6;
        coords[7][2] = 0;
        coords[8] = coords[0];


        for (int i=0;i<9;i++){
            assertEquals(coords[i][0],result.coords.get(i).getX());
            assertEquals(coords[i][1],result.coords.get(i).getY());
            assertEquals(coords[i][2],result.coords.get(i).getZ());
        }
    }

}