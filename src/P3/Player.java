package P3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Player {
	// Abstraction function:
	// Playerӳ�����pieces����ң�nameΪ��ҵ����ƣ�pieces��������������ϵ����ӣ�historyList���ַ���listΪ�����ʷ
	// Representation invariant:
	// Player����ӳ��Ϊ��
	// Safety from rep exposure:
	// ����fields���� private,nameΪfinal,�����˷���ʽ���
	final private String name;// ��ҵ����֣����ɸ���
	private Set<Piece> pieces = new HashSet<Piece>();// ����ҵ�ǰӵ�е����Ӽ���
	private List<String> historyList = new ArrayList<String>();// ���ֵĲ�����ʷ

	// constructor
	public Player(String playerName) {
		this.name = playerName;
	}

	public Set<Piece> getPieces() {
		return pieces;
	}

	public List<String> getHistoryList() {
		List<String> xList = new ArrayList<String>(historyList);
		return xList;
	}

	public String getName() {
		String copyString = new String(name);
		return copyString;
	}

	public int getNumberOfPieces() {// ��ȡ�����������Լ���������
		return pieces.size();
	}

	public void addStep(String step) {// ��historyList�������һ�����岽��
		historyList.add(step);
	}

	/**
	 * �ж������������Ƿ����piece
	 * 
	 * @param piece ��Ҫ���ҵ�����
	 * @return ����и����ӣ�����true��û�з���false��
	 */
	public boolean contains(Piece piece) {// �ж������������Ƿ����piece
		for (Piece key : this.pieces) {
			if (key.equals(piece)) {
				return true;
			}
		}
		return false;
	}

	public Piece pieceOfPosition(Position position) {// ��ȡĳ��λ�õ�����
		Iterator<Piece> it = pieces.iterator();
		while (it.hasNext()) {
			Piece piece = it.next();
			if (piece.getPiecePosition().equals(position)) {
				return piece;
			}
		}
		return null;

	}

	public boolean addPiece(Piece newPiece) { // ���һö���ӣ��Ѿ�ӵ�з���false��������Ӳ�����true
		if (pieces.contains(newPiece)) {
			return false;
		} else {
			pieces.add(newPiece);

			return true;
		}
	}

	public boolean removePiece(Piece removePiece) {// �Ƴ�һö���ӣ������ڷ���false�������Ƴ�������true
		for (Piece piece : pieces) {
			if (piece.equals(removePiece)) {
				pieces.remove(piece);
				return true;
			}
		}
		return false;
	}
}
