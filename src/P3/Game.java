package P3;

public class Game implements Action {
	private static final int Chess = 8;// 象棋的棋盘长度
	private static final int Go = 18;// 围棋的棋盘长度
	private static final int piecesizeChess = 16;// 象棋的最多棋子数目
	private static final int piecesizeGo = 181;// 围棋最多棋子数目

	// Abstraction function:
	// Game代表了一局游戏，board映射为game中的棋盘，游戏的玩家一二是player1和player2.
	// Representation invariant:
	// Game不能映射为空，board不能映射为空，player1,player2不能映射为空
	// Safety from rep exposure:
	// 所有fields都是 private
	private Player player1;// 玩家1
	private Player player2;// 玩家2
	private Board board;// 棋盘

	//constructor
	public Game(String player1, String player2, String gameType) {
		// TODO Auto-generated constructor stub
		if (gameType.equals("chess")) {
			Board board = new Board("chess", Game.Chess - 1);
			this.board = board;// 初始化象棋棋盘
			this.player1 = new Player(player1);// 初始化玩家一
			this.player2 = new Player(player2);// 初始化玩家二
			playerChessInit(player1);
			playerChessInit(player2);
			this.board = boardChessInit(board);
		} else if (gameType.equals("go")) {
			Board board = new Board("go", Game.Go);
			this.board = this.boardGoInit(board);// 初始化围棋棋盘
			this.player1 = new Player(player1);// 初始化玩家一
			this.player2 = new Player(player2);// 初始化玩家二
		}
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public Board getBoard() {
		return board;
	}

	/**
	 * 生成一个围棋棋盘，分配好最初棋盘的占用情况
	 * 
	 * @return 返回一个分配好的Board类
	 */
	private Board boardGoInit(Board board) {// 初始化围棋棋盘
		// TODO Auto-generated method stub

		for (int i = 0; i <= Game.Go; i++) {
			for (int j = 0; j <= Game.Go; j++) {
				board.setPlacedFalse(j, Game.Go - i);// 将棋盘上两行状态设置为占用
			}
		}
		return board;
	}

	/**
	 * 生成一个象棋棋盘，分配好最初棋盘的占用情况
	 * 
	 * @return 返回一个分配好的Board类
	 */
	private Board boardChessInit(Board board) {// 初始化象棋棋盘
		// TODO Auto-generated method stub
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < Game.Chess; j++) {
				board.setPlacedTrue(j, i);// 将棋盘下两行状态设置为占用
				board.setPlacedTrue(j, Game.Chess - 1 - i);// 将棋盘上两行状态设置为占用
			}
		}
		return board;
	}

	/**
	 * 初始化Player类，包括给玩家命名，分配所属的棋子；给定两个名字，初始化两个Player对象；
	 * 将各Piece对象的所有权分配给两个Player对象。在围象棋中，双方的棋子种类和数量都是完全一样的，只有颜色不同）；
	 * 如果是国际象棋，需要将所有Piece对象放置到棋盘上的初始位置（各棋子的初始位置必须要符合国际象棋规则）
	 * 
	 * @param playerName   玩家自定义的名字
	 * @param playerNumber 玩家序号
	 * @return 返回一个初始化完成的Player类
	 */
	private void playerChessInit(String playerName) {// 初始化象棋玩家
		// TODO Auto-generated method stub
		int x0 = 0;
		int x1 = 0;
		Player player = null;
		if (playerName.equals(player1.getName())) {
			x0 = 0;
			x1 = 1;
			player = this.player1;
		} else if (playerName.equals(player2.getName())) {
			x0 = 7;
			x1 = 6;
			player = this.player2;
		}

		// 初始化pawn 兵
		for (int i = 0; i < Chess; i++) {
			Position position = new Position(i, x1);
			Piece pawnPiece = new Piece("pawn", position);
			this.placePiece(player, pawnPiece, position);
		}
		// 初始化rook车
		for (int i = 0; i < Chess; i += 7) {
			Position position = new Position(i, x0);
			Piece rookPiece = new Piece("rook", position);
			this.placePiece(player, rookPiece, position);
		}
		// 初始化knight马
		for (int i = 1; i < Chess; i += 5) {
			Position position = new Position(i, x0);
			Piece knightPiece = new Piece("knight", position);
			this.placePiece(player, knightPiece, position);
		}
		// 初始化bishop象
		for (int i = 2; i < Chess; i += 3) {
			Position position = new Position(i, x0);
			Piece bishoptPiece = new Piece("bishop", position);
			this.placePiece(player, bishoptPiece, position);
		}
		// 初始化queen王后
		Position positionQueen = new Position(3, x0);
		Piece queenPiece = new Piece("queen", positionQueen);
		this.placePiece(player, queenPiece, positionQueen);
		// 初始化king王
		Position positionKing = new Position(4, x0);
		Piece kingPiece = new Piece("king", positionKing);
		this.placePiece(player, kingPiece, positionKing);
	}

	@Override
	public boolean placePiece(Player player, Piece piece, Position targetPosition) {
		// TODO Auto-generated method stub
		int size = 0;
		switch (board.getType()) {
		case "chess":
			size = piecesizeChess;
			break;
		case "go":
			size = piecesizeGo;
			if (player.getName().equals(player1.getName())) {
				if (piece.getType().equals("black")) {
					System.out.println("error:该棋子不属于该玩家");
					return false;
				}
				break;
			} else if (player.getName().equals(player2.getName())) {
				if (piece.getType().equals("white")) {
					System.out.println("error:该棋子不属于该玩家");
					return false;
				}
				break;
			}
		}
		if (player.getName().equals(this.player1.getName())) {
			if (player1.getNumberOfPieces() >= size) {
				System.out.println("error:棋子数目已满");
				return false;
			} else if (targetPosition.getX() > board.getSize() || targetPosition.getY() > board.getSize()) {
				System.out.println("error:位置超出棋盘大小");
				return false;
			} else if (board.getPlaced(targetPosition.getX(), targetPosition.getY())) {
				System.out.println("error:该位置有棋子");
				return false;
			} else if (this.player1.contains(piece)) {
				System.out.println("error:所指定的棋子已在棋盘上");
				return false;
			} else {
				board.setPlacedTrue(targetPosition.getX(), targetPosition.getY());
				this.player1.addPiece(piece);
				this.player1.addStep(String.format("%s put a piece %s in (%d,%d).", player1.getName(), piece.getType(),
						targetPosition.getX(), targetPosition.getY()));
				return true;
			}
		} else if (player.getName().equals(this.player2.getName())) {
			if (player2.getNumberOfPieces() >= size) {
				System.out.println("error:棋子数目已满");
				return false;
			} else if (targetPosition.getX() > board.getSize() || targetPosition.getY() > board.getSize()) {
				System.out.println("error:位置超出棋盘大小");
				return false;
			} else if (board.getPlaced(targetPosition.getX(), targetPosition.getY())) {
				System.out.println("error:该位置有棋子");
				return false;
			} else if (this.player2.contains(piece)) {
				System.out.println("error:所指定的棋子已在棋盘上");
				return false;
			} else {
				board.setPlacedTrue(targetPosition.getX(), targetPosition.getY());
				this.player2.addPiece(piece);
				this.player2.addStep(String.format("%s put a piece %s in (%d,%d).", player2.getName(), piece.getType(),
						targetPosition.getX(), targetPosition.getY()));
				return true;
			}
		} else {
			System.out.println("error:玩家不属于这个游戏的两个玩家");
			return false;
		}
	}

	@Override
	public boolean movePiece(Player player, Position nowPosition, Position targetPosition) {
		// TODO Auto-generated method stub
		if (board.getType().equals("go")) {
			if (player1.getName().equals(player.getName())) {
				if (player1.pieceOfPosition(nowPosition) != null) {
					if ((player1.pieceOfPosition(nowPosition).getType().equals("black"))) {
						System.out.println("error:该棋子不属于该玩家");
						return false;
					}
				}
			} else if (player2.getName().equals(player.getName())) {
				if (player2.pieceOfPosition(nowPosition) != null) {
					if (player2.pieceOfPosition(nowPosition).getType().equals("white")) {
						System.out.println("error:该棋子不属于该玩家");
						return false;
					}
				}
			}
		}
		if (player.getName().equals(this.player1.getName())) {
			Piece piece = player1.pieceOfPosition(nowPosition);
			if (nowPosition.getX() > board.getSize() || nowPosition.getY() > board.getSize()) {
				System.out.println("error:指定位置超出棋盘大小");
				return false;
			}
			if (targetPosition.getX() > board.getSize() || targetPosition.getY() > board.getSize()) {
				System.out.println("error:目标位置超出棋盘大小");
				return false;
			} else if (nowPosition.equals(targetPosition)) {
				System.out.println("error:两个位置相同");
				return false;
			} else if (board.getPlaced(targetPosition.getX(), targetPosition.getY())) {
				System.out.println("error:目标位置有棋子");
				return false;
			} else if (piece == null) {
				System.out.println("error:该棋手初始位置无棋子");
				return false;
			} else {
				player1.removePiece(piece);
				board.setPlacedFalse(nowPosition.getX(), nowPosition.getY());
				piece.setPiecePosition(targetPosition);
				player1.addPiece(piece);
				board.setPlacedTrue(targetPosition.getX(), targetPosition.getY());
				this.player1.addStep(String.format("%s move the piece %s from (%d,%d) to (%d,%d).", player1.getName(),
						piece.getType(), nowPosition.getX(), nowPosition.getY(), targetPosition.getX(),
						targetPosition.getY()));
				return true;
			}
		} else if (player.getName().equals(this.player2.getName())) {
			Piece piece = player2.pieceOfPosition(nowPosition);
			if (nowPosition.getX() > board.getSize() || nowPosition.getY() > board.getSize()) {
				System.out.println("error:指定位置超出棋盘大小");
				return false;
			} else if (targetPosition.getX() > board.getSize() || targetPosition.getY() > board.getSize()) {
				System.out.println("error:目标位置超出棋盘大小");
				return false;
			} else if (nowPosition.equals(targetPosition)) {
				System.out.println("error:两个位置相同");
				return false;
			} else if (board.getPlaced(targetPosition.getX(), targetPosition.getY())) {
				System.out.println("error:目标位置有棋子");
				return false;
			} else if (piece == null) {
				System.out.println("error:该棋手初始位置无棋子");
				return false;
			} else {
				player2.removePiece(piece);
				board.setPlacedFalse(nowPosition.getX(), nowPosition.getY());
				piece.setPiecePosition(targetPosition);
				player2.addPiece(piece);
				board.setPlacedTrue(targetPosition.getX(), targetPosition.getY());
				this.player2.addStep(String.format("%s move the piece %s from (%d,%d) to (%d,%d).", player2.getName(),
						piece.getType(), nowPosition.getX(), nowPosition.getY(), targetPosition.getX(),
						targetPosition.getY()));
				return true;
			}
		} else

		{
			System.out.println("error:玩家不属于这个游戏的两个玩家");
			return false;
		}

	}

	@Override
	public boolean removePiece(Player player, Position targetPosition) {
		// TODO Auto-generated method stub
		if (board.getType().equals("chess")) {
			System.out.println("error:象棋无提子操作");
			return false;
		} else if (player.getName().equals(this.player1.getName())) {
			Piece piece = player2.pieceOfPosition(targetPosition);
			if (targetPosition.getX() > board.getSize() || targetPosition.getY() > board.getSize()) {
				System.out.println("error:位置超出棋盘大小");
				return false;
			} else if (!board.getPlaced(targetPosition.getX(), targetPosition.getY()) && piece == null) {
				System.out.println("error:目标位置无棋子");
				return false;
			} else if (piece == null) {
				System.out.println("error:所提棋子不是对方棋子");
				return false;
			} else {
				player2.removePiece(piece);
				board.setPlacedFalse(targetPosition.getX(), targetPosition.getY());
				this.player1.addStep(String.format("%s remove the piece %s in (%d,%d).", player1.getName(),
						piece.getType(), targetPosition.getX(), targetPosition.getY()));
				return true;
			}
		} else if (player.getName().equals(this.player2.getName())) {
			Piece piece = player1.pieceOfPosition(targetPosition);
			if (targetPosition.getX() > board.getSize() || targetPosition.getY() > board.getSize()) {
				System.out.println("error:位置超出棋盘大小");
				return false;
			} else if (!board.getPlaced(targetPosition.getX(), targetPosition.getY()) && piece == null) {
				System.out.println("error:目标位置无棋子");
				return false;
			} else if (piece == null) {
				System.out.println("error:所提棋子不是对方棋子");
				return false;
			} else {
				player1.removePiece(piece);
				board.setPlacedFalse(targetPosition.getX(), targetPosition.getY());
				this.player2.addStep(String.format("%s remove the piece %s in (%d,%d).", player2.getName(),
						piece.getType(), targetPosition.getX(), targetPosition.getY()));
				return true;
			}
		} else {
			System.out.println("error:玩家不属于这个游戏的两个玩家");
			return false;
		}
	}

	@Override
	public boolean eatPiece(Player player, Position nowPosition, Position targetPosition) {
		// TODO Auto-generated method stub
		if (board.getType().equals("go")) {
			System.out.println("error:围棋无吃子操作");
			return false;
		} else if (player.getName().equals(this.player1.getName())) {
			Piece piece1 = player1.pieceOfPosition(nowPosition);
			Piece piece2 = player2.pieceOfPosition(targetPosition);
			if (targetPosition.getX() > board.getSize() || targetPosition.getY() > board.getSize()) {
				System.out.println("error:位置超出棋盘大小");
				return false;
			} else if (!board.getPlaced(nowPosition.getX(), nowPosition.getY())) {
				System.out.println("error:初始位置无棋子");
				return false;
			} else if (nowPosition.equals(targetPosition)) {
				System.out.println("error:两个位置相同");
				return false;
			} else if (!board.getPlaced(targetPosition.getX(), targetPosition.getY())) {
				System.out.println("error:目标位置无棋子");
				return false;
			} else if (piece1 == null) {
				System.out.println("error:初始位置不是该棋手棋子");
				return false;
			} else if (piece2 == null) {
				System.out.println("error:目标位置不是对手棋子");
				return false;
			} else {
				player1.removePiece(piece1);
				player2.removePiece(piece2);
				board.setPlacedFalse(nowPosition.getX(), nowPosition.getY());
				piece1.setPiecePosition(targetPosition);
				player1.addPiece(piece1);
				this.player1.addStep(String.format("%s eat the piece %s in (%d,%d) by the piece %s in (%d,%d).",
						player1.getName(), piece2.getType(), targetPosition.getX(), targetPosition.getY(),
						piece1.getType(), nowPosition.getX(), nowPosition.getY()));
				return true;
			}
		} else if (player.getName().equals(this.player2.getName())) {
			Piece piece1 = player1.pieceOfPosition(targetPosition);
			Piece piece2 = player2.pieceOfPosition(nowPosition);
			if (targetPosition.getX() > board.getSize() || targetPosition.getY() > board.getSize()) {
				System.out.println("error:位置超出棋盘大小");
				return false;
			} else if (!board.getPlaced(nowPosition.getX(), nowPosition.getY())) {
				System.out.println("error:初始位置无棋子");
				return false;
			} else if (nowPosition.equals(targetPosition)) {
				System.out.println("error:两个位置相同");
				return false;
			} else if (!board.getPlaced(targetPosition.getX(), targetPosition.getY())) {
				System.out.println("error:目标位置无棋子");
				return false;
			} else if (piece2 == null) {
				System.out.println("error:初始位置不是该棋手棋子");
				return false;
			} else if (piece1 == null) {
				System.out.println("error:目标位置不是对手棋子");
				return false;
			} else {
				player1.removePiece(piece1);
				player2.removePiece(piece2);
				board.setPlacedFalse(nowPosition.getX(), nowPosition.getY());
				piece2.setPiecePosition(targetPosition);
				player2.addPiece(piece2);
				this.player2.addStep(String.format("%s eat the piece %s in (%d,%d) by the piece %s in (%d,%d).",
						player2.getName(), piece1.getType(), targetPosition.getX(), targetPosition.getY(),
						piece2.getType(), nowPosition.getX(), nowPosition.getY()));
				return true;
			}
		} else {
			System.out.println("error:玩家不属于这个游戏的两个玩家");
			return false;
		}
	}

	// 查询某个位置占用情况
	public String OccupancyOfThePosition(Position position) {
		if (board.getPlaced(position.getX(), position.getY())) {
			for (Piece key : this.player1.getPieces()) {
				if (position.equals(key.getPiecePosition())) {
					return String.format("(%d,%d)位置被%s的%s棋子占用", position.getX(), position.getY(), player1.getName(),
							key.getType());
				}
			}
			for (Piece key : this.player2.getPieces()) {
				if (position.equals(key.getPiecePosition())) {
					return String.format("(%d,%d)位置被%s的%s棋子占用", position.getX(), position.getY(), player2.getName(),
							key.getType());
				}
			}
		} else {
			return "未被占用";
		}
		return null;
	}
}
