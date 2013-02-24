package experiment;

import java.util.*;

public class IntBoard {
	private Map<Integer, LinkedList<Integer>> adjMtx;
	private boolean[] visited;
	static final int BOARDSIZE = 16;

	public IntBoard() {
		adjMtx = new HashMap();
	}

	public void calcAdjacencies() {
		LinkedList<Integer> adj;
		for(int i = 0; i < BOARDSIZE; i++) {
			adj = new LinkedList<Integer>();
			if((i < 15) && ((i+1) % 4 != 0)) adj.add(i + 1);
			if((i > 0) && (i % 4 != 0)) adj.add(i - 1);
			if(i > 3) adj.add(i - 4);
			if(i < 12) adj.add(i + 4);
			adjMtx.put(i, adj);
		}
	}

	public void startTargets(int location, int steps) {
		Arrays.fill(visited, false);
		visited[location] = true;
	}
	
	public HashSet getTargets() {
		return new HashSet();
	}
	
	public LinkedList<Integer> getAdjList(int index) {
		return adjMtx.get(index);
	}
	
	public int calcIndex(int row, int col) {
		return (row * 4 + col);
	}
}
