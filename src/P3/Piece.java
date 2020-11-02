package P3;

public class Piece {
	//Abstraction function:
	//Piece代表棋盘上的棋子，type代表棋子的类型，是白棋，黑棋还是兵，车等等。position是棋子的位置。
    // Representation invariant:
	// piece不能映射为空
	// Safety from rep exposure:
	//所有的变量都是private类型，type是final类型，采用了防御式编程。
	final private String type;// 棋子的类型
	private Position position;// 棋子的位置

	//constructor
	public Piece(String Type, Position position) {
		// TODO Auto-generated constructor stub
		this.type = Type;
		this.position = position;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Piece other = (Piece) obj;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	public String getType() {
		String typeCloneString=new String(type);
		return typeCloneString;
	}

	public Position getPiecePosition() {
		Position positionCopy=new Position(position.getX(),position.getY());
		return positionCopy;
	}

	public void setPiecePosition(Position piecePosition) {
		this.position = piecePosition;
	}
}
