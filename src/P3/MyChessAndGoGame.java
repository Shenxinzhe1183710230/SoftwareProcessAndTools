package P3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyChessAndGoGame {
	public static void main(String[] args) {

		Print print = new Print(); // 新建窗口
		JFrame jFrameOutAndIn = new JFrame("输入和显示");
		jFrameOutAndIn = print.InAndOut(jFrameOutAndIn);

	}

}

class Print extends JFrame {
	private Game game;
	private JFrame jFrame1 = new JFrame("象棋");
	private JFrame jFrame2 = new JFrame("围棋");
	private PrintGo printGo = new PrintGo();
	private int countPlace = 0;
	private int countRemove = 0;
	private int countEat = 0;
	private int countMove = 0;

	public JFrame InAndOut(JFrame jFrame) {
		History history = new History();
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		JPanel panel6 = new JPanel();
		JPanel panel7 = new JPanel();
		JPanel panel8 = new JPanel();
		JPanel panel9 = new JPanel();
		jFrame.setSize(600, 500);
		jFrame.setLocation(1300, 100);
		Box vBox = Box.createVerticalBox();
		Box xBox1 = Box.createHorizontalBox();
		Box xBox2 = Box.createHorizontalBox();
		// 创建一个 5 行 40 列的文本区域
		final JTextArea textAreaInput = new JTextArea(5, 40);
		final JTextArea textAreaOut = new JTextArea(10, 45);
		// 设置编辑权限
		textAreaOut.setEditable(false);
		// 设置自动换行
		textAreaInput.setLineWrap(true);
		textAreaOut.setLineWrap(true);
		// 添加到内容面板
		panel1.add(textAreaInput);
		panel2.add(textAreaOut);

		// 设置选中部分颜色
		textAreaInput.setSelectedTextColor(Color.BLUE);
		// 创建开始游戏按钮
		JButton beginButton = new JButton("开始游戏");
		beginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				textAreaInput.setText("playgame ");
				textAreaOut.setText("玩家一 姓名，玩家二的姓名，想要玩的游戏类型，中间用空格隔开之后点提交按钮");
			}
		});
		panel3.add(beginButton);
		// 创建place放置棋子按钮
		JButton placeButton = new JButton("place a piece");
		placeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				countPlace++;
				if (countPlace % 2 != 0) {
					textAreaInput.setText("place 1 ");
					textAreaOut.setText(String.format("轮到%s，请输入想要放置的棋子类型(象棋的时候输入)，目标位置横坐标，目标位置纵坐标，中间用空格隔开之后点提交按钮",
							game.getPlayer1().getName()));
				} else {
					textAreaInput.setText("place 2 ");
					textAreaOut.setText(String.format("轮到%s，请输入想要放置棋子类型(象棋的时候输入)，请输入目标位置横坐标，目标位置纵坐标，中间用空格隔开之后点提交按钮",
							game.getPlayer2().getName()));
				}
			}
		});
		panel4.add(placeButton);
		// 创建一个move按钮
		JButton moveButton = new JButton("move a piece");
		moveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				countMove++;
				if (countMove % 2 != 0) {
					textAreaInput.setText("move 1 ");
					textAreaOut.setText(String.format("轮到%s，要移动棋子的位置横坐标，要移动棋子的位置纵坐标，目标位置横坐标，目标位置纵坐标，中间用空格隔开之后点提交按钮",
							game.getPlayer1().getName()));
				} else {
					textAreaInput.setText("move 2 ");
					textAreaOut.setText(String.format("轮到%s，要移动棋子的位置横坐标，要移动棋子的位置纵坐标，目标位置横坐标，目标位置纵坐标，中间用空格隔开之后点提交按钮",
							game.getPlayer2().getName()));
				}
			}
		});
		panel5.add(moveButton);
		// 创建remove提子按钮
		JButton removeButton = new JButton("remove a piece");
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				countRemove++;
				if (countRemove % 2 != 0) {
					textAreaInput.setText("remove 1 ");
					textAreaOut.setText(String.format("轮到%s，要提子的位置横坐标，要移动棋子的位置纵坐标，中间用空格隔开之后点提交按钮(所提棋子必须为对方棋子)",
							game.getPlayer1().getName()));
				} else {
					textAreaInput.setText("remove 2 ");
					textAreaOut.setText(String.format("轮到%s，要提子的位置横坐标，要移动棋子的位置纵坐标，中间用空格隔开之后点提交按钮(所提棋子必须为对方棋子)",
							game.getPlayer2().getName()));
				}
			}
		});
		panel6.add(removeButton);
		// 创建eat吃子按钮
		JButton eatButton = new JButton("eat a piece");
		eatButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				countEat++;
				if (countEat % 2 != 0) {
					textAreaInput.setText("eat 1 ");
					textAreaOut.setText(
							String.format("轮到%s，要使用棋子的位置横坐标，要使用棋子的位置纵坐标，想要吃掉的棋子位置横坐标，想要吃掉的棋子位置纵坐标，中间用空格隔开之后点提交按钮)",
									game.getPlayer1().getName()));
				} else {
					textAreaInput.setText("eat 2 ");
					textAreaOut.setText(
							String.format("轮到%s，要使用棋子的位置横坐标，要使用棋子的位置纵坐标，想要吃掉的棋子位置横坐标，想要吃掉的棋子位置纵坐标，中间用空格隔开之后点提交按钮)",
									game.getPlayer2().getName()));
				}
			}
		});
		panel7.add(eatButton);
		// 创建inquiry查询某个位置占用情况按钮
		JButton inquiryButton = new JButton("Query occupancy of the position");
		inquiryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textAreaInput.setText("inquiry ");
				textAreaOut.setText("想要查询位置横坐标，想要查询棋子位置纵坐标，中间用空格隔开之后点提交按钮");
			}
		});
		panel8.add(inquiryButton);
		// 创建numberQuerry查询棋子数目
		JButton numberButton = new JButton("Query the total number of pieces on the board");
		numberButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textAreaOut.setText(String.format(
						"%s的棋子数目" + game.getPlayer1().getNumberOfPieces() + "\n%s的棋子数目"
								+ game.getPlayer2().getNumberOfPieces(),
						game.getPlayer1().getName(), game.getPlayer2().getName()));
			}
		});
		panel9.add(numberButton);
		// 创建一个提交按钮，点击按钮获取输入文本
		JButton pushButton = new JButton("提交");
		pushButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				history.setHistoryString(textAreaInput.getText());
				textAreaInput.setText("");
				if (history.getHistoryString().equals("end")) {
					jFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					jFrame1.setVisible(false);
					String resultString = game.getPlayer1().getName() + "的历史\n";
					for (String key : game.getPlayer1().getHistoryList()) {
						resultString = resultString + key + "\n";
					}
					resultString = resultString + game.getPlayer2().getName() + "的历史\n";
					for (String key : game.getPlayer2().getHistoryList()) {
						resultString = resultString + key + "\n";
					}
					textAreaOut.setText(resultString);
				} else {
					String[] s = history.getHistoryString().split(" ");
					if (s[0].equals("playgame")) {
						if (s.length == 4) {
							game = new Game(s[1], s[2], s[3]);
							if (game.getBoard().getType().equals("chess")) {
								jFrame1 = piecePrint(game);
							} else if (game.getBoard().getType().equals("go")) {
								jFrame2 = printGo.piecePrintGo(game, jFrame2);
							}
						} else {
							textAreaOut.setText("输入有误，请重新输入");
						}
					}
					switch (s[0]) {
					case "place":
						if (game.getBoard().getType().equals("chess")) {
							if (s.length == 5) {
								String s3 = s[3];
								String s4 = s[4];
								if (s3.matches("[0-9]+") && s4.matches("[0-9]+")) {
									Position position = new Position(Integer.parseInt(s3), Integer.parseInt(s4));
									if (s[1].equals("1")) {
										game.placePiece(game.getPlayer1(), new Piece(s[2], position), position);
									} else if (s[1].equals("2")) {
										game.placePiece(game.getPlayer2(), new Piece(s[2], position), position);
									}
									jFrame1 = piecePrint(game);
								} else {
									textAreaOut.setText("输入有误，请重新输入");
									break;
								}
							} else {
								textAreaOut.setText("输入有误，请重新输入");
								break;
							}
						} else if (game.getBoard().getType().equals("go")) {
							if (s.length == 4) {
								String s2 = s[2];
								String s3 = s[3];
								if (s2.matches("[0-9]+") && s3.matches("[0-9]+")) {
									Position position = new Position(Integer.parseInt(s2), Integer.parseInt(s3));
									if (s[1].equals("1")) {
										game.placePiece(game.getPlayer1(), new Piece("white", position), position);
									} else if (s[1].equals("2")) {
										game.placePiece(game.getPlayer2(), new Piece("black", position), position);
									}
									jFrame2 = printGo.piecePrintGo(game, jFrame2);
									break;
								}else {
									textAreaOut.setText("输入有误，请重新输入");
									break;
								}
							}else {
								textAreaOut.setText("输入有误，请重新输入");
								break;
							}
						}
					case "move":
						if (s.length == 6) {
							String s2 = s[2];
							String s3 = s[3];
							String s4 = s[4];
							String s5 = s[5];
							if (s3.matches("[0-9]+") && s4.matches("[0-9]+") && s4.matches("[0-9]+")
									&& s5.matches("[0-9]+")) {
								Position nowPosition = new Position(Integer.parseInt(s2), Integer.parseInt(s3));
								Position targetPosition = new Position(Integer.parseInt(s4), Integer.parseInt(s5));
								if (s[1].equals("1")) {
									game.movePiece(game.getPlayer1(), nowPosition, targetPosition);
								} else if (s[1].equals("2")) {
									game.movePiece(game.getPlayer2(), nowPosition, targetPosition);
								}
								if (game.getBoard().getType().equals("chess")) {
									jFrame1 = piecePrint(game);
								} else if (game.getBoard().getType().equals("go")) {
									jFrame2 = printGo.piecePrintGo(game, jFrame2);
								}
								// jFrame1 = piecePrint(game);
								break;
							} else {
								textAreaOut.setText("输入有误，请重新输入");
								break;
							}
						} else {
							textAreaOut.setText("输入有误，请重新输入");
							break;
						}
					case "remove":
						if (s.length == 4) {
							String s2 = s[2];
							String s3 = s[3];
							if (s2.matches("[0-9]+") && s3.matches("[0-9]+")) {
								Position targetPosition1 = new Position(Integer.parseInt(s2), Integer.parseInt(s3));
								if (s[1].equals("1")) {
									game.removePiece(game.getPlayer1(), targetPosition1);
								} else if (s[1].equals("2")) {
									game.removePiece(game.getPlayer2(), targetPosition1);
								}
								if (game.getBoard().getType().equals("chess")) {
									jFrame1 = piecePrint(game);
								} else if (game.getBoard().getType().equals("go")) {
									jFrame2 = printGo.piecePrintGo(game, jFrame2);
								}
								break;
							} else {
								textAreaOut.setText("输入有误，请重新输入");
								break;
							}
						} else {
							textAreaOut.setText("输入有误，请重新输入");
							break;
						}
					case "eat":
						if (s.length == 6) {
							String s2 = s[2];
							String s3 = s[3];
							String s4 = s[4];
							String s5 = s[5];
							if (s2.matches("[0-9]+") && s3.matches("[0-9]+") && s4.matches("[0-9]+")
									&& s5.matches("[0-9]+")) {
								Position nowPosition2 = new Position(Integer.parseInt(s2), Integer.parseInt(s3));
								Position targetPosition2 = new Position(Integer.parseInt(s4), Integer.parseInt(s5));
								if (s[1].equals("1")) {
									game.eatPiece(game.getPlayer1(), nowPosition2, targetPosition2);
								} else if (s[1].equals("2")) {
									game.eatPiece(game.getPlayer2(), nowPosition2, targetPosition2);
								}
								if (game.getBoard().getType().equals("chess")) {
									jFrame1 = piecePrint(game);
								} else if (game.getBoard().getType().equals("go")) {
									jFrame2 = printGo.piecePrintGo(game, jFrame2);
								}
								break;
							} else {
								textAreaOut.setText("输入有误，请重新输入");
								break;
							}
						} else {
							textAreaOut.setText("输入有误，请重新输入");
							break;
						}
					case "inquiry":
						if (s.length == 3) {
							String s1 = s[1];
							String s2 = s[2];
							if (s1.matches("[0-9]+") && s2.matches("[0-9]+")) {
								Position position3 = new Position(Integer.parseInt(s1), Integer.parseInt(s2));
								textAreaOut.setText(game.OccupancyOfThePosition(position3));
								break;
							} else {
								textAreaOut.setText("输入有误，请重新输入");
								break;
							}
						} else {
							textAreaOut.setText("输入有误，请重新输入");
							break;
						}
					default:
						break;
					}
				}
			}

		});
		panel1.add(pushButton);
		// 创建一个垂直盒子容器, 把上面 2个 JPanel 串起来作为内容面板添加到窗口
		vBox.add(panel1);
		vBox.add(panel2);
		xBox1.add(panel3);
		xBox1.add(panel4);
		xBox1.add(panel5);
		xBox1.add(panel6);
		xBox2.add(panel7);
		xBox2.add(panel8);
		xBox2.add(panel9);
		vBox.add(xBox1);
		vBox.add(xBox2);
		jFrame.setContentPane(vBox);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		return jFrame;
	}

	public JFrame piecePrint(Game game) {
		String bishopString = "src/P3/picture/bishop.jpg";
		String kingString = "src/P3/picture/king.jpg";
		String knightString = "src/P3/picture/knight.jpg";
		String pawnString = "src/P3/picture/pawn.jpg";
		String queenString = "src/P3/picture/queen.jpg";
		String rookString = "src/P3/picture/rook.jpg";
		String bishopString2 = "src/P3/picture/bishop2.jpg";
		String kingString2 = "src/P3/picture/king2.jpg";
		String knightString2 = "src/P3/picture/knight2.jpg";
		String pawnString2 = "src/P3/picture/pawn2.jpg";
		String queenString2 = "src/P3/picture/queen2.jpg";
		String rookString2 = "src/P3/picture/rook2.jpg";
		JPanel panel1 = new JPanel();
		// 设置窗口的大小（八个格子，每个格子宽，高为20个像素）
		jFrame1.setSize(810, 850);
		// 窗口的位置
		jFrame1.setLocation(500, 50);
		// 格子的行数，列数
		int m = 8;
		// 格子的像素
		int n = 100;
		for (Piece piece : game.getPlayer1().getPieces()) {
			JLabel jLabel = new JLabel();
			panel1.setLayout(null);
			jLabel.setBounds(piece.getPiecePosition().getX() * 100, (7 - piece.getPiecePosition().getY()) * 100, n, n);
			ImageIcon image = null;
			switch (piece.getType()) {
			case "bishop":
				image = new ImageIcon(bishopString);
				break;
			case "king":
				image = new ImageIcon(kingString);
				break;
			case "knight":
				image = new ImageIcon(knightString);
				break;
			case "pawn":
				image = new ImageIcon(pawnString);
				break;
			case "queen":
				image = new ImageIcon(queenString);
				break;
			case "rook":
				image = new ImageIcon(rookString);
				break;
			default:
				break;
			}
			Image img = image.getImage();
			img = img.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
			image.setImage(img);
			jLabel.setIcon(image);
			jLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			panel1.add(jLabel);
		}
		for (Piece piece : game.getPlayer2().getPieces()) {
			JLabel jLabel = new JLabel();
			panel1.setLayout(null);
			jLabel.setBounds(piece.getPiecePosition().getX() * 100, (7 - piece.getPiecePosition().getY()) * 100, n, n);
			ImageIcon image = null;
			switch (piece.getType()) {
			case "bishop":
				image = new ImageIcon(bishopString2);
				break;
			case "king":
				image = new ImageIcon(kingString2);
				break;
			case "knight":
				image = new ImageIcon(knightString2);
				break;
			case "pawn":
				image = new ImageIcon(pawnString2);
				break;
			case "queen":
				image = new ImageIcon(queenString2);
				break;
			case "rook":
				image = new ImageIcon(rookString2);
				break;
			default:
				break;
			}
			Image img = image.getImage();
			img = img.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
			image.setImage(img);
			jLabel.setIcon(image);
			jLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			panel1.add(jLabel);
		}
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				// 在每个位置添加标签
				JLabel jLabel = new JLabel();
				// 标签的大小为格子的大小
				jLabel.setSize(n, n);
				// 标签的位置为索引乘以格子的高度/宽度
				jLabel.setLocation(i * n, (7 - j) * n);
				// 放置黑色方块
				if (game.getBoard().getPlaced(i, 7 - j) == false) {
					if ((i + 7 - j) % 2 == 0) {
						jLabel.setBackground(Color.BLACK);
						// 设置不透明度为不透明
						jLabel.setOpaque(true);
					} else {
						// 放置白色方块
						jLabel.setBackground(Color.WHITE);
						jLabel.setOpaque(true);
					}
					// 设置边界颜色
					jLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					panel1.add(jLabel);
				}
			}
		}
		jFrame1.setContentPane(panel1);
		jFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame1.setVisible(true);
		return jFrame1;
	}
}

class PrintGo {
	JFrame frame;;

	public JFrame piecePrintGo(Game game, JFrame jFrame) {
		frame = jFrame;

		frame.setSize(700, 700); // 设置窗口大小
		frame.setLocation(200, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置画图结束后的操作：退出画图程序
		frame.setVisible(true); // 显示窗口, 不然啥都不显示

		JPanel panel = new JPanel() { // 初始化一个新画布
			private static final long serialVersionUID = 1L; // 不用管，可加可不加

			@Override
			public void paint(Graphics g) { // 重写 pait 方法
				super.paint(g); // 这个要加上，但不加也能正常显示
				for (int colum = 60; colum <= 600; colum = colum + 30) {// 行
					g.drawLine(60, colum, 600, colum);
				}
				for (int rand = 60; rand <= 600; rand = rand + 30) {// 列
					g.drawLine(rand, 60, rand, 600);
				}
				for (Piece piece : game.getPlayer1().getPieces()) {

					switch (piece.getType()) {
					case "white":
						g.setColor(Color.white);
						g.fillOval(piece.getPiecePosition().getX() * 30 + 50,
								(18 - piece.getPiecePosition().getY()) * 30 + 50, 20, 20);
						break;
					case "black":
						g.setColor(Color.black);
						g.fillOval(piece.getPiecePosition().getX() * 30 + 50,
								(18 - piece.getPiecePosition().getY()) * 30 + 50, 20, 20);
						break;
					default:
						break;
					}
				}
				for (Piece piece : game.getPlayer2().getPieces()) {

					switch (piece.getType()) {
					case "white":
						g.setColor(Color.white);
						g.fillOval(piece.getPiecePosition().getX() * 30 + 50,
								(18 - piece.getPiecePosition().getY()) * 30 + 50, 20, 20);
						break;
					case "black":
						g.setColor(Color.black);
						g.fillOval(piece.getPiecePosition().getX() * 30 + 50,
								(18 - piece.getPiecePosition().getY()) * 30 + 50, 20, 20);
						break;
					default:
						break;
					}
				}
			}
		};
		frame.setContentPane(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		return frame;
	}
}
