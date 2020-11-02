/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

//1183710230-�����

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
 * 
 * @param <L>
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {

	private final Set<L> vertices = new HashSet<>();
	private final List<Edge<L>> edges = new ArrayList<>();

	// Abstraction function:
	// vertice�����洢���еĶ��㣬edges�����洢���еıߣ����󻯹�����һ��ͼ
	// Representation invariant:
	// ������������������ѧ��ϵ
	// n���㣬��๹��n*(n-1)�������
	// Safety from rep exposure:
	// �ڿɲ���������£����еı���������Ϊprivate , final
	// ����ͱ���mutable����, ��˶ദʹ��:
	// ����ʽ����

	// TODO constructor

	// TODO checkRep
	public void checkRep() {
		assert edges.size() <= (vertices.size() * (vertices.size() - 1));
	}

	@Override
	public boolean add(L vertex) {
		// TODO Auto-generated method stub
		if (vertices.contains(vertex)) {// ���ԭ���Ƿ��д˶���
			return false;
		} else {
			vertices.add(vertex);
			return true;
		}
	}

	@Override
	public int set(L source, L target, int weight) {
		// TODO Auto-generated method stub
		for (Edge<L> edge : edges) {
			if (edge.checkSame(source, target, weight)) {// ԭ���д˱�
				if (weight != 0) {// weight������0�����´˱�
					int weight1 = edge.getWeight();// ��¼��ǰ��weight
					Edge<L> update_edge = new Edge<L>(source, target, weight);
					edges.set(edges.indexOf(edge), update_edge);
					checkRep();
					return weight1;
				} else if (weight == 0) {// weight����0��ɾ���˱ߣ�ֱ�Ӵ�edges��remove����
					int weight1 = edge.getWeight();
					edges.remove(edge);
					checkRep();
					return weight1;
				}
			}
		}
		if (weight != 0) {// ԭ��û�д˱�
			if (!vertices.contains(source)) {// ԭ��û��source�����һ������
				vertices.add(source);
			}
			if (!vertices.contains(target)) {// ԭ��û��target�����һ������
				vertices.add(target);
			} // ���һ���µı�
			Edge<L> new_edge = new Edge<L>(source, target, weight);
			edges.add(new_edge);
			checkRep();
		}
		return 0;
	}

	@Override
	public boolean remove(L vertex) {
		// TODO Auto-generated method stub
		vertices.remove(vertex);// �����Ƴ��˶���
		boolean flag = false;
		int i = 0;
		int n = edges.size();
		while (i < n) {// ���ʹ˶������������б��Ƴ�
			for (Edge<L> edge : edges) {
				if (edge.getSource().equals(vertex) || edge.getTarget().equals(vertex)) {
					edges.remove(edge);
					flag = true;
					checkRep();
					break;
				}
			}
			i++;
		}
		return flag;
	}

	@Override
	public Set<L> vertices() {
		Set<L> vertice = new HashSet<>();// ����ʽ����
		vertice.addAll(vertices);
		checkRep();
		return vertice;
	}

	@Override
	public Map<L, Integer> sources(L target) {
		// TODO Auto-generated method stub
		Map<L, Integer> map = new HashMap<L, Integer>();
		for (Edge<L> edge : edges) {
			if (edge.getTarget().equals(target) && edge.getWeight() > 0) {// �ҵ���target�ıߣ����˱ߵ�source��ӽ�ȥ
				map.put(edge.getSource(), edge.getWeight());
			}
		}
		checkRep();
		return map;
	}

	@Override
	public Map<L, Integer> targets(L source) {
		// TODO Auto-generated method stub
		Map<L, Integer> map = new HashMap<L, Integer>();
		for (Edge<L> edge : edges) {
			if (edge.getSource().equals(source) && edge.getWeight() > 0) {// �ҵ���source�ıߣ����˱ߵ�target��ӽ�ȥ
				map.put(edge.getTarget(), edge.getWeight());
			}
		}
		checkRep();
		return map;
	}

	// TODO toString()
	@Override
	public String toString() {
		String string = "";
		List<L> D = new ArrayList<L>();
		if (edges.isEmpty() && vertices.isEmpty()) {
			string = "This is an Empty Graph";
		} else {
			for (Edge<L> edge : edges) {// ͳ���б������Ķ���
				D.add(edge.getSource());
			}
			for (L s : vertices) {// ��ӡ����
				if (!D.contains(s))// ���û�бߺʹ˶�����������ô�ʹ�ӡ�˶���
					string = string.concat(s + "\n");
			}
			for (Edge<L> edge : edges) {// ��ӡÿ����
				string = string.concat(edge.toString());
			}
		}
		checkRep();
		return string;
	}

}

/**
 * TODO specification Immutable. This class is internal to the rep of
 * ConcreteEdgesGraph.
 * 
 * <p>
 * PS2 instructions: the specification and implementation of this class is up to
 * you.
 */
class Edge<L> {

	// TODO fields
	private final L source;
	private final L target;
	private final int weight;
	// Abstraction function:
	// ͨ���ԱߺͶ���Ȩֵ�ĳ��󣬹�����һ������ߣ�source��Target��weight�ֱ����һ������ߵ�Դ�����ˣ�Ŀ�궥���Ȩֵ
	// Representation invariant:
	// source��target������null
	// weight >= 0
	// Safety from rep exposure:
	// ����ʹ��private��final�������ڲ�����
	// ��ʹ����immutable��������

	// TODO constructor
	public Edge(L source, L target, int weight) {
		this.source = source;
		this.target = target;
		this.weight = weight;
		checkRep();
	}

	// TODO checkRep
	public void checkRep() {
		assert source != null;
		assert target != null;
		assert weight >= 0;
	}

	// TODO methods
	public L getSource() {
		checkRep();
		return source;
	}

	public L getTarget() {
		checkRep();
		return target;
	}

	public int getWeight() {
		checkRep();
		return weight;
	}

	public boolean checkSame(L source, L target, int weight) {// ���ԭ���Ƿ��д�����
		if (getSource().equals(source) && getTarget().equals(target)) {
			checkRep();
			return true;
		} else {
			checkRep();
			return false;
		}
	}

	// TODO toString()
	public String toString() {
		String s = this.getSource() + "->" + this.getTarget() + ":" + this.getWeight() + "\n";
		checkRep();
		return s;
	}

}
