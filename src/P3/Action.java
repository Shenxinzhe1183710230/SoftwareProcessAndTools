package P3;

public interface Action {
	/**
	 * �������ֵĸÿ����ӷ����������ϣ�������Ϸ�����ͣ���ͬ��������Ϸ�е�λ�ú��岻ͬ��
	 * 
	 * @param player         ����
	 * @param piece          һ������
	 * @param targetPosition ָ��λ��
	 * @return �����Ӳ������ڸ����֡�ָ����λ�ó������̵ķ�Χ��ָ��λ���������ӡ���ָ���������Ѿ��������Ϸ���false������Ϊtrue
	 */
	public boolean placePiece(Player player, Piece piece, Position targetPosition);

	/**
	 * �����ڳ�ʼλ�õ������ƶ���Ŀ��λ�á�
	 * 
	 * @param player         ����
	 * @param nowPosition    ��ǰλ������
	 * @param targetPosition Ŀ��λ������
	 * @return ָ����λ�ó������̵ķ�Χ��Ŀ�ĵ������������ӡ���ʼλ�����޿��ƶ������ӡ�����λ����ͬ����ʼλ�õ����Ӳ��Ǹ��������У�����false�����򷵻�true
	 */
	public boolean movePiece(Player player, Position nowPosition, Position targetPosition);

	/**
	 * ���ӣ����Χ�壩����λ���ϵĶ��������Ƴ�
	 * 
	 * @param player         ����
	 * @param targetPosition Ҫ�Ƴ�λ�õ�����
	 * @return ��λ�ó������̵ķ�Χ����λ�������ӿ��ᡢ�������Ӳ��ǶԷ������򷵻�false
	 */
	public boolean removePiece(Player player, Position targetPosition);

	/**
	 * ���ӣ���Թ������壩����һ��λ���ϵ������ƶ����ڶ���λ�ã��ڶ���λ����ԭ�еĶ������Ӵ��������Ƴ�
	 * 
	 * @param player         ���� * @param nowPosition ��ǰλ������
	 * @param targetPosition Ŀ��λ������
	 * @returnָ����λ�ó������̵ķ�Χ����һ��λ���������ӡ��ڶ���λ���������ӡ�����λ����ͬ����һ��λ���ϵ����Ӳ����Լ������ӡ��ڶ���λ���ϵ����Ӳ��ǶԷ����� ����false�����򷵻�true
	 */
	public boolean eatPiece(Player player, Position nowPosition, Position targetPosition);
}
