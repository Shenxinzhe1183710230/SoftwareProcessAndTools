/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import P1.graph.Graph;

/**
 * A graph-based poetry generator.
 * 
 * <p>
 * GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph. Vertices in the graph are words. Words are defined as
 * non-empty case-insensitive strings of non-space non-newline characters. They
 * are delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>
 * For example, given this corpus:
 * 
 * <pre>
 *     Hello, HELLO, hello, goodbye!
 * </pre>
 * <p>
 * the graph would contain two edges:
 * <ul>
 * <li>("hello,") -> ("hello,") with weight 2
 * <li>("hello,") -> ("goodbye!") with weight 1
 * </ul>
 * <p>
 * where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>
 * Given an input string, GraphPoet generates a poem by attempting to insert a
 * bridge word between every adjacent pair of words in the input. The bridge
 * word between input words "w1" and "w2" will be some "b" such that w1 -> b ->
 * w2 is a two-edge-long path with maximum-weight weight among all the
 * two-edge-long paths from w1 to w2 in the affinity graph. If there are no such
 * paths, no bridge word is inserted. In the output poem, input words retain
 * their original case, while bridge words are lower case. The whitespace
 * between every word in the poem is a single space.
 * 
 * <p>
 * For example, given this corpus:
 * 
 * <pre>
 *     This is a test of the Mugar Omni Theater sound system.
 * </pre>
 * <p>
 * on this input:
 * 
 * <pre>
 *     Test the system.
 * </pre>
 * <p>
 * the output poem would be:
 * 
 * <pre>
 *     Test of the system.
 * </pre>
 * 
 * <p>
 * PS2 instructions: this is a required ADT class, and you MUST NOT weaken the
 * required specifications. However, you MAY strengthen the specifications and
 * you MAY add additional methods. You MUST use Graph in your rep, but otherwise
 * the implementation of this class is up to you.
 */
public class GraphPoet {

	private final Graph<String> graph = Graph.empty();

	public Graph<String> getGraph() {
		return graph;
	}

	// Abstraction function:
    // 将输入的文本单词提取作为顶点
    // 构建有向图
    // 转化为poem
	// Representation invariant:
	// 输入的文本words不为空
    // 有向图不为null
	// Safety from rep exposure:
	 // 所有fields都是private final
    // 防御式编程

	/**
	 * Create a new poet with the graph from corpus (as described above).
	 * 
	 * @param corpus text file from which to derive the poet's affinity graph
	 * @throws IOException if the corpus file cannot be found or read
	 */
	public GraphPoet(File corpus) throws IOException {///根据文件内容建立一个图
		List<String> convert = new ArrayList<String>();
		try {
			if ((!corpus.exists()) || (corpus.length() == 0)) {
				System.out.println("文件为空");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("读取文件错误");
		}
		BufferedReader br = new BufferedReader(new FileReader(corpus));
		String str;
		while ((str = br.readLine()) != null) {//首先读取每一行的内容
			String[] strArr = str.split(" ");
			for (String s : strArr) {
				if (s.matches("[a-zA-Z.!?;,]+")) {//根据正则表达式匹配一下，看是不是单词，或者单词带标点符合
					convert.add(s.toLowerCase());//全部转换为小写
				}
			}
		}
		br.close();
		for (int i = 0; i < convert.size(); i++) {
			int count = 0;
			boolean flag = false;
			if (i < convert.size() - 1) {
				while (convert.get(i).equals( convert.get(i + 1))){//看后面有几个重复的单词，有几个重复权重就是几
					count++;
					i++;
					flag = true;
				}
			}
			if (flag) {//有重复
				graph.add(convert.get(i));//添加重复的点
				graph.set(convert.get(i), convert.get(i), count);
				if (i != convert.size() - 1) {
					graph.add(convert.get(i + 1));
					graph.set(convert.get(i), convert.get(i + 1), 1);//和后面的的点创建一条边，权值设置为1
				}
			} else {//没有重复的点
				graph.add(convert.get(i));
				if (i != convert.size() - 1) {//和后面的的点创建一条边，权值设置为1
					graph.add(convert.get(i + 1));
					graph.set(convert.get(i), convert.get(i + 1), 1);
				}
			}
			count = 0;
		}
	}

	// TODO checkRep

	/**
	 * Generate a poem.
	 * 
	 * @param input string from which to create the poem
	 * @return poem (as described above)
	 */
	public String poem(String input) {
		String[] strArr = input.split(" ");//将输入拆分
		String resultString="";
		String maxString=null;
		boolean flag=false;
		List<String> inputList = new ArrayList<String>();
		List<String> bridge = new ArrayList<String>();
		Map<String, Integer> map = new HashMap<String, Integer>();//用来存储某一个顶点的所有target
		for(String s:strArr) {//转换成小写
			inputList.add(s.toLowerCase());
		}
		for(int i=0;i<inputList.size()-1;i++) {
			resultString=resultString.concat(inputList.get(i)+" ");
			map=graph.targets(inputList.get(i));
			for(String key:map.keySet()) {
				if(graph.targets(key).containsKey(inputList.get(i+1))){//检查此顶点所有的target中是否有bridge
					bridge.add(key);
					flag=true;
				}
			}
			if(flag)maxString=bridge.get(0);
			for(String key:bridge) {//如果有多条桥，选出权值最大的一个桥
				if(map.get(key)>map.get(maxString)) {
					maxString=key;
				}
			}
			if(maxString!=null)resultString=resultString.concat(maxString+" ");
			bridge.clear();
			flag=false;
			maxString=null;
		}
		resultString=resultString.concat(inputList.get(inputList.size()-1));//打印最后一个单词
		//将第一个单词首字符变成大写
		resultString=resultString.substring(0, 1).toUpperCase()+resultString.substring(1);
		String copyString=new String(resultString);
		return copyString;
		
	}

	// TODO toString()

}
