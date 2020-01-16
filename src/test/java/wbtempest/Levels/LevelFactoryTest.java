package wbtempest.Levels;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LevelFactoryTest {

    static int B_WIDTH = 800;
    static int B_HEIGHT = 680;

    @Test
    public void createLevel_1_shouldReturnCircleLevel() {
        LevelFactory levelFactory = new LevelFactory();
        Level result = levelFactory.createLevel(1,B_WIDTH,B_HEIGHT);
        assertEquals(result.getClass(),CircleLevel.class);
    }

    @Test
    public void createLevel_2_shouldReturnSquareLevel() {
        LevelFactory levelFactory = new LevelFactory();
        Level result = levelFactory.createLevel(2,B_WIDTH,B_HEIGHT);
        assertEquals(result.getClass(),SquareLevel.class);
    }

    @Test
    public void createLevel_3_shouldReturnTriangleLevel() {
        LevelFactory levelFactory = new LevelFactory();
        Level result = levelFactory.createLevel(3,B_WIDTH,B_HEIGHT);
        assertEquals(result.getClass(),TriangleLevel.class);
    }

    @Test
    public void createLevel_4_shouldReturnAngledVLevel() {
        LevelFactory levelFactory = new LevelFactory();
        Level result = levelFactory.createLevel(4,B_WIDTH,B_HEIGHT);
        assertEquals(result.getClass(),AngledVLevel.class);
    }

    @Test
    public void createLevel_5_shouldReturnStraightLineLevel() {
        LevelFactory levelFactory = new LevelFactory();
        Level result = levelFactory.createLevel(5,B_WIDTH,B_HEIGHT);
        assertEquals(result.getClass(),StraightLineLevel.class);
    }

    @Test
    public void createLevel_6_shouldReturnJaggedVLevel() {
        LevelFactory levelFactory = new LevelFactory();
        Level result = levelFactory.createLevel(6,B_WIDTH,B_HEIGHT);
        assertEquals(result.getClass(),JaggedVLevel.class);
    }

    @Test
    public void createLevel_7_shouldReturnCircleLevel() {
        LevelFactory levelFactory = new LevelFactory();
        Level result = levelFactory.createLevel(7,B_WIDTH,B_HEIGHT);
        assertEquals(result.getClass(),CircleLevel.class);
    }

    @Test
    public void createLevel_8_shouldReturnSquareLevel() {
        LevelFactory levelFactory = new LevelFactory();
        Level result = levelFactory.createLevel(8,B_WIDTH,B_HEIGHT);
        assertEquals(result.getClass(),SquareLevel.class);
    }

    @Test
    public void createLevel_9_shouldReturnTriangleLevel() {
        LevelFactory levelFactory = new LevelFactory();
        Level result = levelFactory.createLevel(9,B_WIDTH,B_HEIGHT);
        assertEquals(result.getClass(),TriangleLevel.class);
    }

    @Test
    public void createLevel_10_shouldReturnAngledVLevel() {
        LevelFactory levelFactory = new LevelFactory();
        Level result = levelFactory.createLevel(10,B_WIDTH,B_HEIGHT);
        assertEquals(result.getClass(),AngledVLevel.class);
    }

    @Test
    public void createLevel_11_shouldReturnStraightLineLevel() {
        LevelFactory levelFactory = new LevelFactory();
        Level result = levelFactory.createLevel(11,B_WIDTH,B_HEIGHT);
        assertEquals(result.getClass(),StraightLineLevel.class);
    }

    @Test
    public void createLevel_12_shouldReturnJaggedVLevel() {
        LevelFactory levelFactory = new LevelFactory();
        Level result = levelFactory.createLevel(12,B_WIDTH,B_HEIGHT);
        assertEquals(result.getClass(),JaggedVLevel.class);
    }
}