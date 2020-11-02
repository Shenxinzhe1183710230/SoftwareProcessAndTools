package P3;

import java.util.Arrays;

public class Board {
	// Abstraction function:
	// Board 代表棋子所处的棋盘，type代表棋盘的类型，象棋还是围棋，size代表了棋盘的边长，
	//placed表示棋盘上的位置占用情况
	// Representation invariant:
	// Board不能映射为空
	// Safety from rep exposure:
	// 所有fields都是 private and final
	// 使用immutable数据类型
	final private String type;// 棋盘类型
	final private int size;// 棋盘的长度
	private boolean[][] placed;// 是否被放置

	//constructor
	public Board(String typeString, int sizeInt) {
		// TODO Auto-generated constructor stub
		this.type = typeString;
		this.size = sizeInt;
		sizeInt++;
		this.placed = new boolean[sizeInt][sizeInt];
		for (int i = 0; i < sizeInt; i++) {
			Arrays.fill(placed[i], Boolean.FALSE);
		}
	}

	public int getSize() {
		int sizecopy = size;
		return sizecopy;
	}

	public boolean getPlaced(int x, int y) {// 查询某个位置的占用情况
		boolean copy = placed[x][y];
		return copy;
	}

	public void setPlacedTrue(int x, int y) {// 改变某位置的状态（有棋子）
		this.placed[x][y] = true;
	}

	public void setPlacedFalse(int x, int y) {// 改变某位置的状态（无棋子）
		this.placed[x][y] = false;
	}

	public String getType() {
		String copyString = new String(type);
		return copyString;
	}

}
