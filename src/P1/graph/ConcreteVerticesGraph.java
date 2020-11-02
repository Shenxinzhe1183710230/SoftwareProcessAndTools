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
	//vertices存储所有的点这一对象的集合，以此构成图
	// Representation invariant:
	// 所有权值大于等于0
	//vertices的数量应该和vertices()方法获取的顶点数量一致
	// Safety from rep exposure:
	// 使用了final

	// TODO constructor

	// TODO checkRep


	@Override
	public boolean add(L vertex) {
		// TODO Auto-generated method stub
		// 检查是否添加过顶点
		for (Vertex<L> vertice : vertices) {
			if (vertice.getVertice() != null) {
				if (vertice.getVertice().equals(vertex)) {// 如果找到了原来已经添加过的点，就直接返回false
					return false;
				}
			}
		} // 如果没有添加过此顶点，就添加一个新顶点
		Vertex<L> vertice = new Vertex<L>();
		vertice.setVertice(vertex);
		vertices.add(vertice);
		return true;
	}

	@Override
	public int set(L source, L target, int weight) {
		// TODO Auto-generated method stub
		boolean flag = false;
		// 检查原来是否存在该条边
		for (Vertex<L> vertice : vertices) {
			if (vertice.getVertice() != null) {
				if (vertice.getVertice().equals(source)) {// 首先检查source是否相同
					flag = true;
					if (vertice.checkSame(source, target, weight)) {// 原来source和target都一样
						if (weight != 0) {// 如果输入的参数不等于0，那么就更新该条边，也就是在map中更改weight
							int weight1 = vertice.getMap().get(target);// 获取原来的weight
							vertice.MapAdd(target, weight);
							return weight1;
						} else if (weight == 0) {// 如果weight等于0，那么就该map中值赋值为0，代表已经删除这条边
							int weight1 = vertice.getMap().get(target);// 获取原来的weight
							vertice.getMap().put(target, 0);
							return weight1;
						}
					}
				}
			}
		}
		// 原来source顶点存在，但是target不存在
		if (flag == true) {
			for (Vertex<L> vertice : vertices) {
				if (vertice.getVertice() != null) {
					if (vertice.getVertice().equals(source)) {// 找到原来 的source
						if (weight != 0) {// weight不等于，添加该target和边
							vertice.MapAdd(target, weight);
							break;
						} else {// 等于0，但是现在target不存在，所以就不做修改，退出
							break;
						}
					}
				}
			}
		} else {// 代表原来source和target都不存在，就添加新的顶点，新的边
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
		while (i < n) {// 因为如果remove的话，原来的vertices集合就变化了，所以要在外面加一层循环
			for (Vertex<L> vertice : vertices) {
				if (vertice.getVertice() != null) {// 代表原来的source存在
					if (vertice.getVertice().equals(vertex)) {// 首先寻找source等于要删除的vertex
						vertice.setVertice(null);// 将该点的vertice设置成null，代表已经删除了该顶点。因为和它相连的顶点没有删除。
						// 所以不能直接remove vertice，还得保留原来的map，但是要将和它相连点的weight设置为0
						for (L key : vertice.getMap().keySet()) {// 将和它相连点的weight设置为0
							vertice.getMap().put(key, 0);
						}
						flag = true;
						break;
					} else if (vertice.getMap().containsKey(vertex)) {// 如果target等于要删除的点。那么直接把它从它的source中的map中remove即可
						vertice.getMap().remove(vertex);
						flag = true;
						break;
					}
				} else {// 代表之前source被删除了
					if (vertice.getMap().containsKey(vertex)) {// 检查被删除的点是否在source的map中，如果在直接remove即可
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
			if (vertice.getVertice() != null) {// 首先添加所有的source
				set.add(vertice.getVertice());
			}
			for (L key : vertice.getMap().keySet()) {// 接着添加所有source相连的所有点，因为是set所以相同的元素不会重复添加
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
			if (vertice.getMap().containsKey(target) && vertice.getMap().get(target) > 0) {// 如果某个source中存在target，
				//并且weight大于0，那么就把source添加
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
				if (vertice.getVertice().equals(source)) {//首先从vertices中找到source
					for (L key : vertice.getMap().keySet()) {//遍历source的map，如果weight大于0，就添加进去
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
		List<L> d = new ArrayList<L>();//用来统计所有顶点是不是有边，没有边的话，就单独打印一个点
		if (vertices.isEmpty()) {
			string = "This is an Empty Graph\n";
		} else {
			for (Vertex<L> vertex : vertices) {//首先将所有source添加到D中，代表有此顶点
				if (vertex.getVertice() != null) {
					d.add(vertex.getVertice());
				}
			}
			for (Vertex<L> vertex : vertices) {
				// 打印顶点
				for (L key : vertex.getMap().keySet()) {
					if (!d.contains(key) && !haveprinted.contains(key)) {
						string = string.concat(key + "\n");
						haveprinted.add(key);
					}
				}
			}
			for (Vertex<L> vertex : vertices) {
				// 打印边
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
	private L vertice;//存储顶点
	private Map<L, Integer> map = new HashMap<L, Integer>();//存储和该顶点相连的所有顶点

	// Abstraction function:
	//vertice存储图中的一个顶点
    // 使用HashMap存取映射关系，map存储图中和该顶点想连的所有顶点
    // key为该点的target , value为该点的weight	
	// Representation invariant:
	//所有和该顶点相连的边的权值均大于等于0
	// Safety from rep exposure:
	// 所有fields是private
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

	public boolean checkSame(L source, L target, int weight) {//检查是否原来有此边
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
