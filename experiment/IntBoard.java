package experiment;

import java.util.*;

public class IntBoard {
	private Map<Integer, LinkedList<Integer>> adjMtx;
	private HashSet<Integer> targets;
	private boolean[] visited;
	static final int BOARDSIZE = 16;

	//Default Constructor.
	public IntBoard() {
		adjMtx = new HashMap();
	}
	
	//Finished. Calculates all adjacent spaces for every index on our board.
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
		visited = new boolean[BOARDSIZE];
		targets = new HashSet<Integer>();
		visited[location] = true;
		calcTargets(location, steps);
		if(targets.contains(location)) targets.remove(location);
	}
	
	public void calcTargets(int location, int steps) {
		LinkedList<Integer> adjList = getAdjList(location);
		for(int cell : adjList) {
			visited[cell] = true;
			if(steps == 1) targets.add(cell);
			else calcTargets(cell, steps - 1);
			visited[cell] = false;
		}
	}
	
	public HashSet getTargets() {
		return targets;
	}
	
	//Finished, returns a linked list with all the adjacent spaces to a matrix.
	public LinkedList<Integer> getAdjList(int index) {
		return adjMtx.get(index);
	}
	
	//Finished. Returns a single int for any row/col combination on board.
	public int calcIndex(int row, int col) {
		return (row * 4 + col);
	}
}
