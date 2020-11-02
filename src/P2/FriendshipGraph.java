package P2;

import java.util.Queue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import P1.graph.ConcreteEdgesGraph;

public class FriendshipGraph extends ConcreteEdgesGraph<Person> {

	public void addVertex(Person friend) {// ���ö���������ͼ
		if (this.vertices().contains(friend)) {// �ж��Ƿ��Ѿ��ظ���ӹ�����
			System.out.print("error:���ظ��Ķ������\n");// ����Ѿ��ظ���ӹ�����ô���ӡ������Ϣ
		} else {
			this.add(friend);
		}
	}

	public void addEdge(Person friend1, Person friend2) {// ����������֮�����һ����
		if ((this.vertices().contains(friend2) != false) && (this.vertices().contains(friend1) != false)) {// �ж������������Ƿ��в����ڵĶ���
			if (friend1 != friend2) {// �ж��Ƿ�����ͬһ�������м����
				if (targets(friend1).containsKey(friend2) == false) {// �ж��Ƿ��Ѿ���ӹ��˱�
					set(friend1, friend2, 1);
				} else {
					System.out.print("error:�Ѿ����ڱߣ������ظ����\n");
				}
			} else {
				System.out.print("error:�����Լ�ָ���Լ����γɻ�·\n");
			}
		} else {
			System.out.print("error:���������еĶ���û�б����\n");
		}
	}

	public int getDistance(Person friend1, Person friend2) {//���ù�������㷨������̾��루ֻ������Ȩֵ��ͬ�������
		if (this.vertices().contains(friend2) != false && this.vertices().contains(friend1) != false) {//���ȼ��Ҫ������̾����������Ա�Ƿ񶼴���
			Map<Person, Integer> map = new HashMap<Person, Integer>();//�����洢����
			Queue<Person> queue = new LinkedList<Person>();//����һ������
			List<Person> visited = new ArrayList<Person>();//�洢�Ƿ���ʹ�
			for (Person p : vertices()) {//��ʼ��ÿһ������Ĳ�����ֵΪ0
				map.put(p, 0);
			}
			queue.offer(friend1);//����һ��������������
			visited.add(friend1);//���Ϊ���ʹ�
			while (!queue.isEmpty()) {
				Person xString = queue.element();//��ö��еĵ�һ��Ԫ��
				if (xString.getnameString().equals(friend2.getnameString())) {//�������Ԫ�ؾ���target��ֱ�ӷ���
					return map.get(xString);
				} else {//�����ɾ����һ��Ԫ��
					queue.poll();
				}
				for (Person key : targets(xString).keySet()) {//���չ���������������������Ԫ�����������е�
					if (key.getnameString().equals(friend2.getnameString())) {//����ҵ���target�����target���ڵĲ��������ҷ���
						map.put(key, (map.get(xString) + 1));
						return map.get(key);
					} else {//������ڣ����ν��Ͷ������������е��������У����Ҹ����ǵĲ�����ֵ
						if (!visited.contains(key)) {
							queue.offer(key);
							map.put(key, (map.get(xString) + 1));
							visited.add(key);
						}
					}
				}
			}
			return -1;
		} else {
			System.out.print("error:�г�Ա������\n");
			return -1;
		}
	}
}
