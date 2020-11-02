/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>
 * PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph<L> implements Graph<L> {

	private final List<Vertex<L>> vertices = new ArrayList<>();

	// Abstraction function:
	//vertices�洢���еĵ���һ����ļ��ϣ��Դ˹���ͼ
	// Representation invariant:
	// ����Ȩֵ���ڵ���0
	//vertices������Ӧ�ú�vertices()������ȡ�Ķ�������һ��
	// Safety from rep exposure:
	// ʹ����final

	// TODO constructor

	// TODO checkRep


	@Override
	public boolean add(L vertex) {
		// TODO Auto-generated method stub
		// ����Ƿ���ӹ�����
		for (Vertex<L> vertice : vertices) {
			if (vertice.getVertice() != null) {
				if (vertice.getVertice().equals(vertex)) {// ����ҵ���ԭ���Ѿ���ӹ��ĵ㣬��ֱ�ӷ���false
					return false;
				}
			}
		} // ���û����ӹ��˶��㣬�����һ���¶���
		Vertex<L> vertice = new Vertex<L>();
		vertice.setVertice(vertex);
		vertices.add(vertice);
		return true;
	}

	@Override
	public int set(L source, L target, int weight) {
		// TODO Auto-generated method stub
		boolean flag = false;
		// ���ԭ���Ƿ���ڸ�����
		for (Vertex<L> vertice : vertices) {
			if (vertice.getVertice() != null) {
				if (vertice.getVertice().equals(source)) {// ���ȼ��source�Ƿ���ͬ
					flag = true;
					if (vertice.checkSame(source, target, weight)) {// ԭ��source��target��һ��
						if (weight != 0) {// �������Ĳ���������0����ô�͸��¸����ߣ�Ҳ������map�и���weight
							int weight1 = vertice.getMap().get(target);// ��ȡԭ����weight
							vertice.MapAdd(target, weight);
							return weight1;
						} else if (weight == 0) {// ���weight����0����ô�͸�map��ֵ��ֵΪ0�������Ѿ�ɾ��������
							int weight1 = vertice.getMap().get(target);// ��ȡԭ����weight
							vertice.getMap().put(target, 0);
							return weight1;
						}
					}
				}
			}
		}
		// ԭ��source������ڣ�����target������
		if (flag == true) {
			for (Vertex<L> vertice : vertices) {
				if (vertice.getVertice() != null) {
					if (vertice.getVertice().equals(source)) {// �ҵ�ԭ�� ��source
						if (weight != 0) {// weight�����ڣ���Ӹ�target�ͱ�
							vertice.MapAdd(target, weight);
							break;
						} else {// ����0����������target�����ڣ����ԾͲ����޸ģ��˳�
							break;
						}
					}
				}
			}
		} else {// ����ԭ��source��target�������ڣ�������µĶ��㣬�µı�
			Vertex<L> vertice = new Vertex<L>();
			vertice.setVertice(source);
			vertice.MapAdd(target, weight);
			vertices.add(vertice);
		}
		return 0;
	}

	@Override
	public boolean remove(L vertex) {
		// TODO Auto-generated method stub
		boolean flag = false;
		int i = 0;
		int n = vertices.size();
		while (i < n) {// ��Ϊ���remove�Ļ���ԭ����vertices���Ͼͱ仯�ˣ�����Ҫ�������һ��ѭ��
			for (Vertex<L> vertice : vertices) {
				if (vertice.getVertice() != null) {// ����ԭ����source����
					if (vertice.getVertice().equals(vertex)) {// ����Ѱ��source����Ҫɾ����vertex
						vertice.setVertice(null);// ���õ��vertice���ó�null�������Ѿ�ɾ���˸ö��㡣��Ϊ���������Ķ���û��ɾ����
						// ���Բ���ֱ��remove vertice�����ñ���ԭ����map������Ҫ�������������weight����Ϊ0
						for (L key : vertice.getMap().keySet()) {// �������������weight����Ϊ0
							vertice.getMap().put(key, 0);
						}
						flag = true;
						break;
					} else if (vertice.getMap().containsKey(vertex)) {// ���target����Ҫɾ���ĵ㡣��ôֱ�Ӱ���������source�е�map��remove����
						vertice.getMap().remove(vertex);
						flag = true;
						break;
					}
				} else {// ����֮ǰsource��ɾ����
					if (vertice.getMap().containsKey(vertex)) {// ��鱻ɾ���ĵ��Ƿ���source��map�У������ֱ��remove����
						vertice.getMap().remove(vertex);
						flag = true;
						break;
					}
				}
			}
			i++;
		}
		return flag;
	}

	@Override
	public Set<L> vertices() {
		Set<L> set = new HashSet<L>();
		for (Vertex<L> vertice : vertices) {
			if (vertice.getVertice() != null) {// ����������е�source
				set.add(vertice.getVertice());
			}
			for (L key : vertice.getMap().keySet()) {// �����������source���������е㣬��Ϊ��set������ͬ��Ԫ�ز����ظ����
				set.add(key);
			}
		}
		return set;
	}

	@Override
	public Map<L, Integer> sources(L target) {
		// TODO Auto-generated method stub
		Map<L, Integer> map = new HashMap<L, Integer>();
		for (Vertex<L> vertice : vertices) {
			if (vertice.getMap().containsKey(target) && vertice.getMap().get(target) > 0) {// ���ĳ��source�д���target��
				//����weight����0����ô�Ͱ�source���
				map.put(vertice.getVertice(), vertice.getMap().get(target));
			}
		}
		return map;
	}

	@Override
	public Map<L, Integer> targets(L source) {
		// TODO Auto-generated method stub
		Map<L, Integer> map = new HashMap<L, Integer>();
		for (Vertex<L> vertice : vertices) {
			if (vertice.getVertice() != null) {
				if (vertice.getVertice().equals(source)) {//���ȴ�vertices���ҵ�source
					for (L key : vertice.getMap().keySet()) {//����source��map�����weight����0������ӽ�ȥ
						if (vertice.getMap().get(key) > 0) {
							map.put(key, vertice.getMap().get(key));
						}
					}
					return map;
				}
			}

		}
		return new HashMap<L, Integer>();
	}

	// TODO toString()
	public String toString() {
		String string = "";
		List<L> haveprinted = new ArrayList<L>();
		List<L> d = new ArrayList<L>();//����ͳ�����ж����ǲ����бߣ�û�бߵĻ����͵�����ӡһ����
		if (vertices.isEmpty()) {
			string = "This is an Empty Graph\n";
		} else {
			for (Vertex<L> vertex : vertices) {//���Ƚ�����source��ӵ�D�У������д˶���
				if (vertex.getVertice() != null) {
					d.add(vertex.getVertice());
				}
			}
			for (Vertex<L> vertex : vertices) {
				// ��ӡ����
				for (L key : vertex.getMap().keySet()) {
					if (!d.contains(key) && !haveprinted.contains(key)) {
						string = string.concat(key + "\n");
						haveprinted.add(key);
					}
				}
			}
			for (Vertex<L> vertex : vertices) {
				// ��ӡ��
				string = string.concat(vertex.toString());
			}
		}
		return string;
	}

}

/**
 * TODO specification Mutable. This class is internal to the rep of
 * ConcreteVerticesGraph.
 * 
 * <p>
 * PS2 instructions: the specification and implementation of this class is up to
 * you.
 */
class Vertex<L> {

	// TODO fields
	private L vertice;//�洢����
	private Map<L, Integer> map = new HashMap<L, Integer>();//�洢�͸ö������������ж���

	// Abstraction function:
	//vertice�洢ͼ�е�һ������
    // ʹ��HashMap��ȡӳ���ϵ��map�洢ͼ�к͸ö������������ж���
    // keyΪ�õ��target , valueΪ�õ��weight	
	// Representation invariant:
	//���к͸ö��������ıߵ�Ȩֵ�����ڵ���0
	// Safety from rep exposure:
	// ����fields��private
	// TODO constructor

	// TODO methods
	public L getVertice() {
		checkRep();
		return vertice;
	}

	public void setVertice(L vertice) {
		this.vertice = vertice;
	}

	public Map<L, Integer> getMap() {
		checkRep();
		return map;
	}

	public void MapAdd(L target, int weight) {
		this.map.put(target, weight);
	}

	public boolean checkSame(L source, L target, int weight) {//����Ƿ�ԭ���д˱�
		if (map.containsKey(target)) {
			checkRep();
			return true;
		}
		else {
			checkRep();
			return false;
		}
	}

	// TODO toString()
	public String toString() {
		String s = "";
		if (this.getMap().isEmpty()) {
			s = this.getVertice() + "\n";
		} else if (this.vertice != null) {
			for (L key : this.getMap().keySet()) {
				if (this.getMap().get(key) != 0) {
					s = s.concat(this.getVertice() + "->" + key + ":" + this.getMap().get(key) + "\n");
				}
			}
		}
		checkRep();
		return s;
	}

	// TODO checkRep
	public void checkRep() {
		if (!map.isEmpty()) {
			for (int i : map.values()) {
				assert i >= 0;
			}
		}
	}



}
