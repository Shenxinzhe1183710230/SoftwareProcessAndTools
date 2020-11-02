package P3;

public class Position {
	private final int x;
	private final int y;

	// Abstraction function:
	// Position代表棋子在棋盘上的位置，x代表位置的横坐标，y代表位置的纵坐标
	// Representation invariant:
	// position不能映射为空
	// Safety from rep exposure:
	// 所有fields都是 private and final
	// 使用immutable数据类型

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Position [x=" + x + ", y=" + y + "]";
	}

	//Constructor
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
