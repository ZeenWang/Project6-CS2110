package a5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import a4.Heap;
import common.NotImplementedError;
import graph.Edge;
import graph.LabeledEdge;
import graph.Node;

/** We've provided depth-first search as an example; you need to implement Dijkstra's algorithm.
 */
public class GraphAlgorithms  {
	/** Return the Nodes reachable from start in depth-first-search order */
	public static <N extends Node<N,E>, E extends Edge<N,E>>
	List<N> dfs(N start) {
		
		Stack<N> worklist = new Stack<N>();
		worklist.add(start);
		
		Set<N>   visited  = new HashSet<N>();
		List<N>  result   = new ArrayList<N>();
		while (!worklist.isEmpty()) {
			// invariants:
			//    - everything in visited has a path from start to it
			//    - everything in worklist has a path from start to it
			//      that only traverses visited nodes
			//    - nothing in the worklist is visited
			N next = worklist.pop();
			visited.add(next);
			result.add(next);
			for (N neighbor : next.outgoing().keySet())
				if (!visited.contains(neighbor))
					worklist.add(neighbor);
		}
		return result;
	}
	
	/**
	 * Return a minimal path from start to end.  This method should return as
	 * soon as the shortest path to end is known; it should not continue to search
	 * the graph after that. 
	 * 
	 * @param <N> The type of nodes in the graph
	 * @param <E> The type of edges in the graph; the weights are given by e.label()
	 * @param start The node to search from
	 * @param end   The node to find
	 */
	public static <N extends Node<N,E>, E extends LabeledEdge<N,E,Integer>>
	List<N> shortestPath(N start, N end) {
		Heap<N, Integer> frontier=new Heap<N, Integer>(new NodeComparator());
		Heap<N, Integer> settled=new Heap<N, Integer>(new NodeComparator());
		HashMap<N, N> finalPath= new HashMap<N, N>(); // the second N is the last Node before get to the first Node
		List<N> finalResult=new LinkedList<N>();
		
		if(!dfs(start).contains(end))
			return finalResult;
		frontier.add(start, 0);
		finalPath.put(start, start);
		N f=start;
		int d=0;
		while(!settled.contain(end)) {
			d=frontier.getPriority(frontier.peek());
			f=frontier.poll();
			settled.add(f, d);
			//finalResult.add(f);
			for (N wNode : f.outgoing().keySet()) {
				if(settled.contain(wNode) ) {
					if(d+f.outgoing().get(wNode).label() < settled.getPriority(wNode)) {
						settled.changePriority(wNode,d+f.outgoing().get(wNode).label());
						finalPath.replace(wNode, f);
					}
				}else if(frontier.contain(wNode)) {
					if(d+f.outgoing().get(wNode).label() < frontier.getPriority(wNode)) {
						frontier.changePriority(wNode, d+f.outgoing().get(wNode).label());
						finalPath.replace(wNode, f);
					}
				}else{
					frontier.add(wNode, d+f.outgoing().get(wNode).label());
					finalPath.put(wNode, f);
				}
			}
		}
		
		//finalPath.put(end, f);
		N temp=end;
		while(!temp.equals(start)) {
			finalResult.add(0,temp);
			temp=finalPath.get(temp);
		}
		finalResult.add(0,start);
		System.out.println(finalResult);
		return finalResult;
		
	}
	
	
	public static class NodeComparator implements Comparator<Integer>{
		
		public NodeComparator() {
			super();
		}
		
		@Override
		public int compare(Integer o1, Integer o2) {
			if(o1==o2)
				return 0;
			return o1<o2?1:-1;
		}
	}
	
	
}
