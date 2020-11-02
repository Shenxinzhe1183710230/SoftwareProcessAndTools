package P3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyChessAndGoGame {
	public static void main(String[] args) {

		Print print = new Print(); // �½�����
		JFrame jFrameOutAndIn = new JFrame("�������ʾ");
		jFrameOutAndIn = print.InAndOut(jFrameOutAndIn);

	}

}

class Print extends JFrame {
	private Game game;
	private JFrame jFrame1 = new JFrame("����");
	private JFrame jFrame2 = new JFrame("Χ��");
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
		// ����һ�� 5 �� 40 �е��ı�����
		final JTextArea textAreaInput = new JTextArea(5, 40);
		final JTextArea textAreaOut = new JTextArea(10, 45);
		// ���ñ༭Ȩ��
		textAreaOut.setEditable(false);
		// �����Զ�����
		textAreaInput.setLineWrap(true);
		textAreaOut.setLineWrap(true);
		// ��ӵ��������
		panel1.add(textAreaInput);
		panel2.add(textAreaOut);

		// ����ѡ�в�����ɫ
		textAreaInput.setSelectedTextColor(Color.BLUE);
		// ������ʼ��Ϸ��ť
		JButton beginButton = new JButton("��ʼ��Ϸ");
		beginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				textAreaInput.setText("playgame ");
				textAreaOut.setText("���һ ��������Ҷ�����������Ҫ�����Ϸ���ͣ��м��ÿո����֮����ύ��ť");
			}
		});
		panel3.add(beginButton);
		// ����place�������Ӱ�ť
		JButton placeButton = new JButton("place a piece");
		placeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				countPlace++;
				if (countPlace % 2 != 0) {
					textAreaInput.setText("place 1 ");
					textAreaOut.setText(String.format("�ֵ�%s����������Ҫ���õ���������(�����ʱ������)��Ŀ��λ�ú����꣬Ŀ��λ�������꣬�м��ÿո����֮����ύ��ť",
							game.getPlayer1().getName()));
				} else {
					textAreaInput.setText("place 2 ");
					textAreaOut.setText(String.format("�ֵ�%s����������Ҫ������������(�����ʱ������)��������Ŀ��λ�ú����꣬Ŀ��λ�������꣬�м��ÿո����֮����ύ��ť",
							game.getPlayer2().getName()));
				}
			}
		});
		panel4.add(placeButton);
		// ����һ��move��ť
		JButton moveButton = new JButton("move a piece");
		moveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				countMove++;
				if (countMove % 2 != 0) {
					textAreaInput.setText("move 1 ");
					textAreaOut.setText(String.format("�ֵ�%s��Ҫ�ƶ����ӵ�λ�ú����꣬Ҫ�ƶ����ӵ�λ�������꣬Ŀ��λ�ú����꣬Ŀ��λ�������꣬�м��ÿո����֮����ύ��ť",
							game.getPlayer1().getName()));
				} else {
					textAreaInput.setText("move 2 ");
					textAreaOut.setText(String.format("�ֵ�%s��Ҫ�ƶ����ӵ�λ�ú����꣬Ҫ�ƶ����ӵ�λ�������꣬Ŀ��λ�ú����꣬Ŀ��λ�������꣬�м��ÿո����֮����ύ��ť",
							game.getPlayer2().getName()));
				}
			}
		});
		panel5.add(moveButton);
		// ����remove���Ӱ�ť
		JButton removeButton = new JButton("remove a piece");
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				countRemove++;
				if (countRemove % 2 != 0) {
					textAreaInput.setText("remove 1 ");
					textAreaOut.setText(String.format("�ֵ�%s��Ҫ���ӵ�λ�ú����꣬Ҫ�ƶ����ӵ�λ�������꣬�м��ÿո����֮����ύ��ť(�������ӱ���Ϊ�Է�����)",
							game.getPlayer1().getName()));
				} else {
					textAreaInput.setText("remove 2 ");
					textAreaOut.setText(String.format("�ֵ�%s��Ҫ���ӵ�λ�ú����꣬Ҫ�ƶ����ӵ�λ�������꣬�м��ÿո����֮����ύ��ť(�������ӱ���Ϊ�Է�����)",
							game.getPlayer2().getName()));
				}
			}
		});
		panel6.add(removeButton);
		// ����eat���Ӱ�ť
		JButton eatButton = new JButton("eat a piece");
		eatButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				countEat++;
				if (countEat % 2 != 0) {
					textAreaInput.setText("eat 1 ");
					textAreaOut.setText(
							String.format("�ֵ�%s��Ҫʹ�����ӵ�λ�ú����꣬Ҫʹ�����ӵ�λ�������꣬��Ҫ�Ե�������λ�ú����꣬��Ҫ�Ե�������λ�������꣬�м��ÿո����֮����ύ��ť)",
									game.getPlayer1().getName()));
				} else {
					textAreaInput.setText("eat 2 ");
					textAreaOut.setText(
							String.format("�ֵ�%s��Ҫʹ�����ӵ�λ�ú����꣬Ҫʹ�����ӵ�λ�������꣬��Ҫ�Ե�������λ�ú����꣬��Ҫ�Ե�������λ�������꣬�м��ÿո����֮����ύ��ť)",
									game.getPlayer2().getName()));
				}
			}
		});
		panel7.add(eatButton);
		// ����inquiry��ѯĳ��λ��ռ�������ť
		JButton inquiryButton = new JButton("Query occupancy of the position");
		inquiryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textAreaInput.setText("inquiry ");
				textAreaOut.setText("��Ҫ��ѯλ�ú����꣬��Ҫ��ѯ����λ�������꣬�м��ÿո����֮����ύ��ť");
			}
		});
		panel8.add(inquiryButton);
		// ����numberQuerry��ѯ������Ŀ
		JButton numberButton = new JButton("Query the total number of pieces on the board");
		numberButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textAreaOut.setText(String.format(
						"%s��������Ŀ" + game.getPlayer1().getNumberOfPieces() + "\n%s��������Ŀ"
								+ game.getPlayer2().getNumberOfPieces(),
						game.getPlayer1().getName(), game.getPlayer2().getName()));
			}
		});
		panel9.add(numberButton);
		// ����һ���ύ��ť�������ť��ȡ�����ı�
		JButton pushButton = new JButton("�ύ");
		pushButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				history.setHistoryString(textAreaInput.getText());
				textAreaInput.setText("");
				if (history.getHistoryString().equals("end")) {
					jFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					jFrame1.setVisible(false);
					String resultString = game.getPlayer1().getName() + "����ʷ\n";
					for (String key : game.getPlayer1().getHistoryList()) {
						resultString = resultString + key + "\n";
					}
					resultString = resultString + game.getPlayer2().getName() + "����ʷ\n";
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
							textAreaOut.setText("������������������");
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
									textAreaOut.setText("������������������");
									break;
								}
							} else {
								textAreaOut.setText("������������������");
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
									textAreaOut.setText("������������������");
									break;
								}
							}else {
								textAreaOut.setText("������������������");
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
								textAreaOut.setText("������������������");
								break;
							}
						} else {
							textAreaOut.setText("������������������");
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
								textAreaOut.setText("������������������");
								break;
							}
						} else {
							textAreaOut.setText("������������������");
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
								textAreaOut.setText("������������������");
								break;
							}
						} else {
							textAreaOut.setText("������������������");
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
								textAreaOut.setText("������������������");
								break;
							}
						} else {
							textAreaOut.setText("������������������");
							break;
						}
					default:
						break;
					}
				}
			}

		});
		panel1.add(pushButton);
		// ����һ����ֱ��������, ������ 2�� JPanel ��������Ϊ���������ӵ�����
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
		// ���ô��ڵĴ�С���˸����ӣ�ÿ�����ӿ���Ϊ20�����أ�
		jFrame1.setSize(810, 850);
		// ���ڵ�λ��
		jFrame1.setLocation(500, 50);
		// ���ӵ�����������
		int m = 8;
		// ���ӵ�����
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
				// ��ÿ��λ����ӱ�ǩ
				JLabel jLabel = new JLabel();
				// ��ǩ�Ĵ�СΪ���ӵĴ�С
				jLabel.setSize(n, n);
				// ��ǩ��λ��Ϊ�������Ը��ӵĸ߶�/���
				jLabel.setLocation(i * n, (7 - j) * n);
				// ���ú�ɫ����
				if (game.getBoard().getPlaced(i, 7 - j) == false) {
					if ((i + 7 - j) % 2 == 0) {
						jLabel.setBackground(Color.BLACK);
						// ���ò�͸����Ϊ��͸��
						jLabel.setOpaque(true);
					} else {
						// ���ð�ɫ����
						jLabel.setBackground(Color.WHITE);
						jLabel.setOpaque(true);
					}
					// ���ñ߽���ɫ
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

		frame.setSize(700, 700); // ���ô��ڴ�С
		frame.setLocation(200, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���û�ͼ������Ĳ������˳���ͼ����
		frame.setVisible(true); // ��ʾ����, ��Ȼɶ������ʾ

		JPanel panel = new JPanel() { // ��ʼ��һ���»���
			private static final long serialVersionUID = 1L; // ���ùܣ��ɼӿɲ���

			@Override
			public void paint(Graphics g) { // ��д pait ����
				super.paint(g); // ���Ҫ���ϣ�������Ҳ��������ʾ
				for (int colum = 60; colum <= 600; colum = colum + 30) {// ��
					g.drawLine(60, colum, 600, colum);
				}
				for (int rand = 60; rand <= 600; rand = rand + 30) {// ��
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
