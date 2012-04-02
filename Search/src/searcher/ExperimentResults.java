package searcher;

/**
 * Class containing all results of the experiments of three algorithms.
 * The algorithms are: A*, Dijkstra, and Greedy Search.
 * 
 * @author Dennis Breuker
 */
public class ExperimentResults {
    /**
     * The following three variables contain information on results of the 
     * searches by the three different algorithms
     */
    private AlgorithmResults aStar;
    private AlgorithmResults dijkstra;
    private AlgorithmResults greedySearch;

    /**
     * Initializes the three algorithm results.
     */
    public ExperimentResults() {
        aStar = new AlgorithmResults();
        dijkstra = new AlgorithmResults();
        greedySearch = new AlgorithmResults();
    }

    public AlgorithmResults getaStar() {
        return aStar;
    }

    public void setaStar(AlgorithmResults aStar) {
        this.aStar = aStar;
    }

    public AlgorithmResults getDijkstra() {
        return dijkstra;
    }

    public void setDijkstra(AlgorithmResults dijkstra) {
        this.dijkstra = dijkstra;
    }

    public AlgorithmResults getGreedySearch() {
        return greedySearch;
    }

    public void setGreedySearch(AlgorithmResults greedySearch) {
        this.greedySearch = greedySearch;
    }
}
