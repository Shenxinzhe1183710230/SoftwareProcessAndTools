package P3;

public class Game implements Action {
	private static final int Chess = 8;// ��������̳���
	private static final int Go = 18;// Χ������̳���
	private static final int piecesizeChess = 16;// ��������������Ŀ
	private static final int piecesizeGo = 181;// Χ�����������Ŀ

	// Abstraction function:
	// Game������һ����Ϸ��boardӳ��Ϊgame�е����̣���Ϸ�����һ����player1��player2.
	// Representation invariant:
	// Game����ӳ��Ϊ�գ�board����ӳ��Ϊ�գ�player1,player2����ӳ��Ϊ��
	// Safety from rep exposure:
	// ����fields���� private
	private Player player1;// ���1
	private Player player2;// ���2
	private Board board;// ����

	//constructor
	public Game(String player1, String player2, String gameType) {
		// TODO Auto-generated constructor stub
		if (gameType.equals("chess")) {
			Board board = new Board("chess", Game.Chess - 1);
			this.board = board;// ��ʼ����������
			this.player1 = new Player(player1);// ��ʼ�����һ
			this.player2 = new Player(player2);// ��ʼ����Ҷ�
			playerChessInit(player1);
			playerChessInit(player2);
			this.board = boardChessInit(board);
		} else if (gameType.equals("go")) {
			Board board = new Board("go", Game.Go);
			this.board = this.boardGoInit(board);// ��ʼ��Χ������
			this.player1 = new Player(player1);// ��ʼ�����һ
			this.player2 = new Player(player2);// ��ʼ����Ҷ�
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
	 * ����һ��Χ�����̣������������̵�ռ�����
	 * 
	 * @return ����һ������õ�Board��
	 */
	private Board boardGoInit(Board board) {// ��ʼ��Χ������
		// TODO Auto-generated method stub

		for (int i = 0; i <= Game.Go; i++) {
			for (int j = 0; j <= Game.Go; j++) {
				board.setPlacedFalse(j, Game.Go - i);// ������������״̬����Ϊռ��
			}
		}
		return board;
	}

	/**
	 * ����һ���������̣������������̵�ռ�����
	 * 
	 * @return ����һ������õ�Board��
	 */
	private Board boardChessInit(Board board) {// ��ʼ����������
		// TODO Auto-generated method stub
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < Game.Chess; j++) {
				board.setPlacedTrue(j, i);// ������������״̬����Ϊռ��
				board.setPlacedTrue(j, Game.Chess - 1 - i);// ������������״̬����Ϊռ��
			}
		}
		return board;
	}

	/**
	 * ��ʼ��Player�࣬����������������������������ӣ������������֣���ʼ������Player����
	 * ����Piece���������Ȩ���������Player������Χ�����У�˫�����������������������ȫһ���ģ�ֻ����ɫ��ͬ����
	 * ����ǹ������壬��Ҫ������Piece������õ������ϵĳ�ʼλ�ã������ӵĳ�ʼλ�ñ���Ҫ���Ϲ����������
	 * 
	 * @param playerName   ����Զ��������
	 * @param playerNumber ������
	 * @return ����һ����ʼ����ɵ�Player��
	 */
	private void playerChessInit(String playerName) {// ��ʼ���������
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

		// ��ʼ��pawn ��
		for (int i = 0; i < Chess; i++) {
			Position position = new Position(i, x1);
			Piece pawnPiece = new Piece("pawn", position);
			this.placePiece(player, pawnPiece, position);
		}
		// ��ʼ��rook��
		for (int i = 0; i < Chess; i += 7) {
			Position position = new Position(i, x0);
			Piece rookPiece = new Piece("rook", position);
			this.placePiece(player, rookPiece, position);
		}
		// ��ʼ��knight��
		for (int i = 1; i < Chess; i += 5) {
			Position position = new Position(i, x0);
			Piece knightPiece = new Piece("knight", position);
			this.placePiece(player, knightPiece, position);
		}
		// ��ʼ��bishop��
		for (int i = 2; i < Chess; i += 3) {
			Position position = new Position(i, x0);
			Piece bishoptPiece = new Piece("bishop", position);
			this.placePiece(player, bishoptPiece, position);
		}
		// ��ʼ��queen����
		Position positionQueen = new Position(3, x0);
		Piece queenPiece = new Piece("queen", positionQueen);
		this.placePiece(player, queenPiece, positionQueen);
		// ��ʼ��king��
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
					System.out.println("error:�����Ӳ����ڸ����");
					return false;
				}
				break;
			} else if (player.getName().equals(player2.getName())) {
				if (piece.getType().equals("white")) {
					System.out.println("error:�����Ӳ����ڸ����");
					return false;
				}
				break;
			}
		}
		if (player.getName().equals(this.player1.getName())) {
			if (player1.getNumberOfPieces() >= size) {
				System.out.println("error:������Ŀ����");
				return false;
			} else if (targetPosition.getX() > board.getSize() || targetPosition.getY() > board.getSize()) {
				System.out.println("error:λ�ó������̴�С");
				return false;
			} else if (board.getPlaced(targetPosition.getX(), targetPosition.getY())) {
				System.out.println("error:��λ��������");
				return false;
			} else if (this.player1.contains(piece)) {
				System.out.println("error:��ָ������������������");
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
				System.out.println("error:������Ŀ����");
				return false;
			} else if (targetPosition.getX() > board.getSize() || targetPosition.getY() > board.getSize()) {
				System.out.println("error:λ�ó������̴�С");
				return false;
			} else if (board.getPlaced(targetPosition.getX(), targetPosition.getY())) {
				System.out.println("error:��λ��������");
				return false;
			} else if (this.player2.contains(piece)) {
				System.out.println("error:��ָ������������������");
				return false;
			} else {
				board.setPlacedTrue(targetPosition.getX(), targetPosition.getY());
				this.player2.addPiece(piece);
				this.player2.addStep(String.format("%s put a piece %s in (%d,%d).", player2.getName(), piece.getType(),
						targetPosition.getX(), targetPosition.getY()));
				return true;
			}
		} else {
			System.out.println("error:��Ҳ����������Ϸ���������");
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
						System.out.println("error:�����Ӳ����ڸ����");
						return false;
					}
				}
			} else if (player2.getName().equals(player.getName())) {
				if (player2.pieceOfPosition(nowPosition) != null) {
					if (player2.pieceOfPosition(nowPosition).getType().equals("white")) {
						System.out.println("error:�����Ӳ����ڸ����");
						return false;
					}
				}
			}
		}
		if (player.getName().equals(this.player1.getName())) {
			Piece piece = player1.pieceOfPosition(nowPosition);
			if (nowPosition.getX() > board.getSize() || nowPosition.getY() > board.getSize()) {
				System.out.println("error:ָ��λ�ó������̴�С");
				return false;
			}
			if (targetPosition.getX() > board.getSize() || targetPosition.getY() > board.getSize()) {
				System.out.println("error:Ŀ��λ�ó������̴�С");
				return false;
			} else if (nowPosition.equals(targetPosition)) {
				System.out.println("error:����λ����ͬ");
				return false;
			} else if (board.getPlaced(targetPosition.getX(), targetPosition.getY())) {
				System.out.println("error:Ŀ��λ��������");
				return false;
			} else if (piece == null) {
				System.out.println("error:�����ֳ�ʼλ��������");
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
				System.out.println("error:ָ��λ�ó������̴�С");
				return false;
			} else if (targetPosition.getX() > board.getSize() || targetPosition.getY() > board.getSize()) {
				System.out.println("error:Ŀ��λ�ó������̴�С");
				return false;
			} else if (nowPosition.equals(targetPosition)) {
				System.out.println("error:����λ����ͬ");
				return false;
			} else if (board.getPlaced(targetPosition.getX(), targetPosition.getY())) {
				System.out.println("error:Ŀ��λ��������");
				return false;
			} else if (piece == null) {
				System.out.println("error:�����ֳ�ʼλ��������");
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
			System.out.println("error:��Ҳ����������Ϸ���������");
			return false;
		}

	}

	@Override
	public boolean removePiece(Player player, Position targetPosition) {
		// TODO Auto-generated method stub
		if (board.getType().equals("chess")) {
			System.out.println("error:���������Ӳ���");
			return false;
		} else if (player.getName().equals(this.player1.getName())) {
			Piece piece = player2.pieceOfPosition(targetPosition);
			if (targetPosition.getX() > board.getSize() || targetPosition.getY() > board.getSize()) {
				System.out.println("error:λ�ó������̴�С");
				return false;
			} else if (!board.getPlaced(targetPosition.getX(), targetPosition.getY()) && piece == null) {
				System.out.println("error:Ŀ��λ��������");
				return false;
			} else if (piece == null) {
				System.out.println("error:�������Ӳ��ǶԷ�����");
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
				System.out.println("error:λ�ó������̴�С");
				return false;
			} else if (!board.getPlaced(targetPosition.getX(), targetPosition.getY()) && piece == null) {
				System.out.println("error:Ŀ��λ��������");
				return false;
			} else if (piece == null) {
				System.out.println("error:�������Ӳ��ǶԷ�����");
				return false;
			} else {
				player1.removePiece(piece);
				board.setPlacedFalse(targetPosition.getX(), targetPosition.getY());
				this.player2.addStep(String.format("%s remove the piece %s in (%d,%d).", player2.getName(),
						piece.getType(), targetPosition.getX(), targetPosition.getY()));
				return true;
			}
		} else {
			System.out.println("error:��Ҳ����������Ϸ���������");
			return false;
		}
	}

	@Override
	public boolean eatPiece(Player player, Position nowPosition, Position targetPosition) {
		// TODO Auto-generated method stub
		if (board.getType().equals("go")) {
			System.out.println("error:Χ���޳��Ӳ���");
			return false;
		} else if (player.getName().equals(this.player1.getName())) {
			Piece piece1 = player1.pieceOfPosition(nowPosition);
			Piece piece2 = player2.pieceOfPosition(targetPosition);
			if (targetPosition.getX() > board.getSize() || targetPosition.getY() > board.getSize()) {
				System.out.println("error:λ�ó������̴�С");
				return false;
			} else if (!board.getPlaced(nowPosition.getX(), nowPosition.getY())) {
				System.out.println("error:��ʼλ��������");
				return false;
			} else if (nowPosition.equals(targetPosition)) {
				System.out.println("error:����λ����ͬ");
				return false;
			} else if (!board.getPlaced(targetPosition.getX(), targetPosition.getY())) {
				System.out.println("error:Ŀ��λ��������");
				return false;
			} else if (piece1 == null) {
				System.out.println("error:��ʼλ�ò��Ǹ���������");
				return false;
			} else if (piece2 == null) {
				System.out.println("error:Ŀ��λ�ò��Ƕ�������");
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
				System.out.println("error:λ�ó������̴�С");
				return false;
			} else if (!board.getPlaced(nowPosition.getX(), nowPosition.getY())) {
				System.out.println("error:��ʼλ��������");
				return false;
			} else if (nowPosition.equals(targetPosition)) {
				System.out.println("error:����λ����ͬ");
				return false;
			} else if (!board.getPlaced(targetPosition.getX(), targetPosition.getY())) {
				System.out.println("error:Ŀ��λ��������");
				return false;
			} else if (piece2 == null) {
				System.out.println("error:��ʼλ�ò��Ǹ���������");
				return false;
			} else if (piece1 == null) {
				System.out.println("error:Ŀ��λ�ò��Ƕ�������");
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
			System.out.println("error:��Ҳ����������Ϸ���������");
			return false;
		}
	}

	// ��ѯĳ��λ��ռ�����
	public String OccupancyOfThePosition(Position position) {
		if (board.getPlaced(position.getX(), position.getY())) {
			for (Piece key : this.player1.getPieces()) {
				if (position.equals(key.getPiecePosition())) {
					return String.format("(%d,%d)λ�ñ�%s��%s����ռ��", position.getX(), position.getY(), player1.getName(),
							key.getType());
				}
			}
			for (Piece key : this.player2.getPieces()) {
				if (position.equals(key.getPiecePosition())) {
					return String.format("(%d,%d)λ�ñ�%s��%s����ռ��", position.getX(), position.getY(), player2.getName(),
							key.getType());
				}
			}
		} else {
			return "δ��ռ��";
		}
		return null;
	}
}
