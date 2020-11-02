package P3;

public interface Action {
	/**
	 * 将该棋手的该颗棋子放置在棋盘上（考虑游戏的类型，不同的棋类游戏中的位置含义不同）
	 * 
	 * @param player         棋手
	 * @param piece          一颗棋子
	 * @param targetPosition 指定位置
	 * @return 该棋子并非属于该棋手、指定的位置超出棋盘的范围、指定位置已有棋子、所指定的棋子已经在棋盘上返回false，否则为true
	 */
	public boolean placePiece(Player player, Piece piece, Position targetPosition);

	/**
	 * 将处于初始位置的棋子移动到目的位置。
	 * 
	 * @param player         棋手
	 * @param nowPosition    当前位置坐标
	 * @param targetPosition 目标位置坐标
	 * @return 指定的位置超出棋盘的范围、目的地已有其他棋子、初始位置尚无可移动的棋子、两个位置相同、初始位置的棋子并非该棋手所有，返回false，否则返回true
	 */
	public boolean movePiece(Player player, Position nowPosition, Position targetPosition);

	/**
	 * 提子（针对围棋）将该位置上的对手棋子移除
	 * 
	 * @param player         棋手
	 * @param targetPosition 要移除位置的坐标
	 * @return 该位置超出棋盘的范围、该位置无棋子可提、所提棋子不是对方棋子则返回false
	 */
	public boolean removePiece(Player player, Position targetPosition);

	/**
	 * 吃子（针对国际象棋）将第一个位置上的棋子移动至第二个位置，第二个位置上原有的对手棋子从棋盘上移除
	 * 
	 * @param player         棋手 * @param nowPosition 当前位置坐标
	 * @param targetPosition 目标位置坐标
	 * @return指定的位置超出棋盘的范围、第一个位置上无棋子、第二个位置上无棋子、两个位置相同、第一个位置上的棋子不是自己的棋子、第二个位置上的棋子不是对方棋子 返回false。否则返回true
	 */
	public boolean eatPiece(Player player, Position nowPosition, Position targetPosition);
}
