package P2;

import java.util.Queue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import P1.graph.ConcreteEdgesGraph;

public class FriendshipGraph extends ConcreteEdgesGraph<Person> {

	public void addVertex(Person friend) {// 将该顶点加入这个图
		if (this.vertices().contains(friend)) {// 判断是否已经重复添加过顶点
			System.out.print("error:有重复的顶点添加\n");// 如果已经重复添加过，那么便打印错误信息
		} else {
			this.add(friend);
		}
	}

	public void addEdge(Person friend1, Person friend2) {// 在两个顶点之间加上一条边
		if ((this.vertices().contains(friend2) != false) && (this.vertices().contains(friend1) != false)) {// 判断两个顶点中是否有不存在的顶点
			if (friend1 != friend2) {// 判断是否是在同一个顶点中加入边
				if (targets(friend1).containsKey(friend2) == false) {// 判断是否已经添加过此边
					set(friend1, friend2, 1);
				} else {
					System.out.print("error:已经存在边，不可重复添加\n");
				}
			} else {
				System.out.print("error:不能自己指向自己，形成环路\n");
			}
		} else {
			System.out.print("error:两个点中有的顶点没有被添加\n");
		}
	}

	public int getDistance(Person friend1, Person friend2) {//采用广度优先算法，算最短距离（只适用于权值相同的情况）
		if (this.vertices().contains(friend2) != false && this.vertices().contains(friend1) != false) {//首先检查要求算最短距离的两个成员是否都存在
			Map<Person, Integer> map = new HashMap<Person, Integer>();//用来存储层数
			Queue<Person> queue = new LinkedList<Person>();//生成一个队列
			List<Person> visited = new ArrayList<Person>();//存储是否访问过
			for (Person p : vertices()) {//初始将每一个顶点的层数赋值为0
				map.put(p, 0);
			}
			queue.offer(friend1);//将第一个顶点加入队列中
			visited.add(friend1);//标记为访问过
			while (!queue.isEmpty()) {
				Person xString = queue.element();//获得队列的第一个元素
				if (xString.getnameString().equals(friend2.getnameString())) {//如果队首元素就是target，直接返回
					return map.get(xString);
				} else {//否则就删除第一个元素
					queue.poll();
				}
				for (Person key : targets(xString).keySet()) {//按照广度优先搜索，遍历与队首元素相连的所有点
					if (key.getnameString().equals(friend2.getnameString())) {//如果找到了target，算出target所在的层数，并且返回
						map.put(key, (map.get(xString) + 1));
						return map.get(key);
					} else {//如果不在，依次将和队首相连的所有点放入队列中，并且给他们的层数赋值
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
			System.out.print("error:有成员不存在\n");
			return -1;
		}
	}
}
