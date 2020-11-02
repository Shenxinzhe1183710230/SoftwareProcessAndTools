package P3;

import java.util.Arrays;

public class Board {
	// Abstraction function:
	// Board �����������������̣�type�������̵����ͣ����廹��Χ�壬size���������̵ı߳���
	//placed��ʾ�����ϵ�λ��ռ�����
	// Representation invariant:
	// Board����ӳ��Ϊ��
	// Safety from rep exposure:
	// ����fields���� private and final
	// ʹ��immutable��������
	final private String type;// ��������
	final private int size;// ���̵ĳ���
	private boolean[][] placed;// �Ƿ񱻷���

	//constructor
	public Board(String typeString, int sizeInt) {
		// TODO Auto-generated constructor stub
		this.type = typeString;
		this.size = sizeInt;
		sizeInt++;
		this.placed = new boolean[sizeInt][sizeInt];
		for (int i = 0; i < sizeInt; i++) {
			Arrays.fill(placed[i], Boolean.FALSE);
		}
	}

	public int getSize() {
		int sizecopy = size;
		return sizecopy;
	}

	public boolean getPlaced(int x, int y) {// ��ѯĳ��λ�õ�ռ�����
		boolean copy = placed[x][y];
		return copy;
	}

	public void setPlacedTrue(int x, int y) {// �ı�ĳλ�õ�״̬�������ӣ�
		this.placed[x][y] = true;
	}

	public void setPlacedFalse(int x, int y) {// �ı�ĳλ�õ�״̬�������ӣ�
		this.placed[x][y] = false;
	}

	public String getType() {
		String copyString = new String(type);
		return copyString;
	}

}
