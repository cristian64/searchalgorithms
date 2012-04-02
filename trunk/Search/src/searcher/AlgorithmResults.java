package searcher;

import java.util.ArrayList;

/**
 * Class containing all results of the experiments of one algorithm (e.g., A*).
 * 
 * @author Dennis Breuker
 */
public class AlgorithmResults {
    private int bestPathCost;
    private int nodesExpanded;
    /**
     * Integer list containing the indices of the tiles in the best path.
     */
    private ArrayList<Integer> solutionPath = new ArrayList<Integer>();

    public AlgorithmResults() {
        this(0, 0);
    }

    /**
     * This constructor immediately initializes the class attributes.
     * 
     * @param bestPathCost The cost of the best path
     * @param nodesExpanded Number of expanded nodes
     */
    public AlgorithmResults(int bestPathCost, int nodesExpanded) {
        this.bestPathCost = bestPathCost;
        this.nodesExpanded = nodesExpanded;
    }

    public int getBestPathCost() {
        return bestPathCost;
    }

    public void setBestPathCost(int cost) {
        this.bestPathCost = cost;
    }

    public int getNodesExpanded() {
        return nodesExpanded;
    }

    public void setNodesExpanded(int nodesExpanded) {
        this.nodesExpanded = nodesExpanded;
    }

    public ArrayList<Integer> getSolutionPath() {
        return solutionPath;
    }

    public void setSolutionPath(ArrayList<Integer> solutionPath) {
        this.solutionPath = solutionPath;
    }
}
