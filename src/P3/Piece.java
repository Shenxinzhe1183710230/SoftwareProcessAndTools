package P3;

public class Piece {
	//Abstraction function:
	//Piece���������ϵ����ӣ�type�������ӵ����ͣ��ǰ��壬���廹�Ǳ������ȵȡ�position�����ӵ�λ�á�
    // Representation invariant:
	// piece����ӳ��Ϊ��
	// Safety from rep exposure:
	//���еı�������private���ͣ�type��final���ͣ������˷���ʽ��̡�
	final private String type;// ���ӵ�����
	private Position position;// ���ӵ�λ��

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
