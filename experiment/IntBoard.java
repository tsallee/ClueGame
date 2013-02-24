package experiment;

import java.util.*;

public class IntBoard {
	private Map<Integer, LinkedList<Integer>> adjMtx;
	private boolean[] visited;

	public IntBoard() {
		// TODO Auto-generated constructor stub
	}

	public void calcAdjacencies(int index) {
		
	}

	public void startTargets(int location, int steps) {
		Arrays.fill(visited, false);
		visited[location] = true;
		adjMtx = new HashMap();
	}
	
	public HashSet getTargets() {
		return new HashSet();
	}
	
	public LinkedList<Integer> getAdjList(int index) {
		return new LinkedList<Integer>();
	}
	
	public int calcIndex(int row, int col) {
		return (row * 4 + col);
	}
}
