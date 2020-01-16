package wbtempest;

/**
 * Represents a column of the game board.  Convenience class.
 * @author ugliest
 *
 */
public class Column {
	private int[] firstPoint;
	private int[] secondPoint;

	public Column(int firstPointX, int firstPointY, int secondPointX, int secondPointY) {
		this.firstPoint = new int[]{firstPointX,firstPointY};
		this.secondPoint = new int[]{secondPointX,secondPointY};
	}

	public int getFirstPointX() {
		return firstPoint[0];
	}

	public int getFirstPointY() {
		return firstPoint[1];
	}

	public int getSecondPointX() {
		return secondPoint[0];
	}

	public int getSecondPointY() {
		return secondPoint[1];
	}

	public int[] getFirstPoint() {
		return firstPoint;
	}
	public int[] getSecondPoint() {
		return secondPoint;
	}
}
