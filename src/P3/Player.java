package P3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Player {
	// Abstraction function:
	// Player映射操作pieces的玩家，name为玩家的名称，pieces代表玩家在棋盘上的棋子，historyList的字符串list为玩家历史
	// Representation invariant:
	// Player不能映射为空
	// Safety from rep exposure:
	// 所有fields都是 private,name为final,采用了防御式编程
	final private String name;// 玩家的名字，不可更改
	private Set<Piece> pieces = new HashSet<Piece>();// 该玩家当前拥有的棋子集合
	private List<String> historyList = new ArrayList<String>();// 棋手的操作历史

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

	public int getNumberOfPieces() {// 获取棋盘上属于自己的棋子数
		return pieces.size();
	}

	public void addStep(String step) {// 向historyList域中添加一个走棋步骤
		historyList.add(step);
	}

	/**
	 * 判断所有棋子中是否包含piece
	 * 
	 * @param piece 想要查找的棋子
	 * @return 如果有该棋子，返回true，没有返回false；
	 */
	public boolean contains(Piece piece) {// 判断所有棋子中是否包含piece
		for (Piece key : this.pieces) {
			if (key.equals(piece)) {
				return true;
			}
		}
		return false;
	}

	public Piece pieceOfPosition(Position position) {// 获取某个位置的棋子
		Iterator<Piece> it = pieces.iterator();
		while (it.hasNext()) {
			Piece piece = it.next();
			if (piece.getPiecePosition().equals(position)) {
				return piece;
			}
		}
		return null;

	}

	public boolean addPiece(Piece newPiece) { // 添加一枚棋子，已经拥有返回false，否则添加并返回true
		if (pieces.contains(newPiece)) {
			return false;
		} else {
			pieces.add(newPiece);

			return true;
		}
	}

	public boolean removePiece(Piece removePiece) {// 移除一枚棋子，不存在返回false，否则移除并返回true
		for (Piece piece : pieces) {
			if (piece.equals(removePiece)) {
				pieces.remove(piece);
				return true;
			}
		}
		return false;
	}
}
