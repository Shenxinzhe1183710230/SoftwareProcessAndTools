/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

//1183710230-沈昕哲

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
	// vertice用来存储所有的顶点，edges用来存储所有的边，抽象化构成了一个图
	// Representation invariant:
	// 不变量在于隐含的数学关系
	// n个点，最多构成n*(n-1)条有向边
	// Safety from rep exposure:
	// 在可操作的情况下，所有的变量都定义为private , final
	// 顶点和边是mutable类型, 因此多处使用:
	// 防御式拷贝

	// TODO constructor

	// TODO checkRep
	public void checkRep() {
		assert edges.size() <= (vertices.size() * (vertices.size() - 1));
	}

	@Override
	public boolean add(L vertex) {
		// TODO Auto-generated method stub
		if (vertices.contains(vertex)) {// 检查原来是否有此顶点
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
			if (edge.checkSame(source, target, weight)) {// 原来有此边
				if (weight != 0) {// weight不等于0，更新此边
					int weight1 = edge.getWeight();// 记录先前的weight
					Edge<L> update_edge = new Edge<L>(source, target, weight);
					edges.set(edges.indexOf(edge), update_edge);
					checkRep();
					return weight1;
				} else if (weight == 0) {// weight等于0，删除此边，直接从edges中remove即可
					int weight1 = edge.getWeight();
					edges.remove(edge);
					checkRep();
					return weight1;
				}
			}
		}
		if (weight != 0) {// 原来没有此边
			if (!vertices.contains(source)) {// 原来没有source，添加一个顶点
				vertices.add(source);
			}
			if (!vertices.contains(target)) {// 原来没有target，添加一个顶点
				vertices.add(target);
			} // 添加一条新的边
			Edge<L> new_edge = new Edge<L>(source, target, weight);
			edges.add(new_edge);
			checkRep();
		}
		return 0;
	}

	@Override
	public boolean remove(L vertex) {
		// TODO Auto-generated method stub
		vertices.remove(vertex);// 首先移除此顶点
		boolean flag = false;
		int i = 0;
		int n = edges.size();
		while (i < n) {// 将和此顶点相连的所有边移除
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
		Set<L> vertice = new HashSet<>();// 防御式拷贝
		vertice.addAll(vertices);
		checkRep();
		return vertice;
	}

	@Override
	public Map<L, Integer> sources(L target) {
		// TODO Auto-generated method stub
		Map<L, Integer> map = new HashMap<L, Integer>();
		for (Edge<L> edge : edges) {
			if (edge.getTarget().equals(target) && edge.getWeight() > 0) {// 找到有target的边，将此边的source添加进去
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
			if (edge.getSource().equals(source) && edge.getWeight() > 0) {// 找到有source的边，将此边的target添加进去
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
			for (Edge<L> edge : edges) {// 统计有边相连的顶点
				D.add(edge.getSource());
			}
			for (L s : vertices) {// 打印顶点
				if (!D.contains(s))// 如果没有边和此顶点相连，那么就打印此顶点
					string = string.concat(s + "\n");
			}
			for (Edge<L> edge : edges) {// 打印每条边
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
	// 通过对边和顶点权值的抽象，构成了一条有向边，source。Target，weight分别代表一条有向边的源顶点了，目标顶点和权值
	// Representation invariant:
	// source和target不能是null
	// weight >= 0
	// Safety from rep exposure:
	// 尽量使用private和final来定义内部属性
	// 均使用了immutable数据类型

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

	public boolean checkSame(L source, L target, int weight) {// 检查原来是否有此条边
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
