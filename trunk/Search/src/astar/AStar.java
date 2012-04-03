package astar;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 *
 * @author Cristian
 */
public class AStar
{
    private AStarNode[][] nodes;
    private AStarNode startNode;
    private AStarNode endNode;
    private int nodesExpanded;
    
    /**
     * Create a new instance of the algorithm.
     * It does not calculate the path until findPath() is executed.
     * @param nodes Matrix of nodes with x, y, cost and diagonal cost set.
     * @param startNode Reference to the start node. This node must be already in the nodes matrix.
     * @param endNode Reference to the end node. This node must be already in the nodes matrix.
     */
    public AStar(AStarNode[][] nodes, AStarNode startNode, AStarNode endNode)
    {
        this.nodes = nodes;
        this.startNode = startNode;
        this.endNode = endNode;
        
        // Set the end node to everynode, in order to calculate H.
        for (int i = 0; i < nodes.length; i++)
            for (int j = 0; j < nodes[i].length; j++)
                nodes[i][j].setEndNode(endNode);
    }
    
    /**
     * @return Number of nodes that have been expanded after executing findPath().
     */
    public int getNodesExpanded()
    {
        return nodesExpanded;
    }
    
    /**
     * It finds a path between the start node and the end node that were set in the constructor of the class.
     * @return Returns a list of integers that represent the x and y component alternatively. If there is no path, it returns an empty list.
     */
    public ArrayList<Integer> findPath()
    {
        PriorityQueue<AStarNode> openList = new PriorityQueue<AStarNode>();
        AStarNode currentNode;
        openList.add(startNode);
        startNode.setOpen(true);
        boolean pathFound = false;
        
        nodesExpanded = 0;
        
        // Keep this loop while there are promising nodes that haven't been examined yet or the path is found.
        while (!openList.isEmpty() && !pathFound)
        {
            // Get the most promising node from the open list and close the node.
            currentNode = openList.poll();
            currentNode.setClosed(true);
            currentNode.setOpen(false);
            nodesExpanded++;
            
            // Generate adjacent nodes from the one that is being examined.
            ArrayList<AStarNode> children = currentNode.generateChildren(nodes);
            for (AStarNode i : children)
            {
                // Discard the node if it's already closed or non walkable node.
                if (!i.isClosed() && i.getCost() != AStarNode.NONWALKABLE_COST)
                {
                    if (!i.isOpen())
                    {
                        // If the node is not open, it is added to the list.
                        i.setParentNode(currentNode);
                        openList.add(i);
                        i.setOpen(true);
                        
                        // Check if this is the goal.
                        if (i == endNode)
                        {
                            pathFound = true;
                            break;
                        }
                    }
                    else
                    {
                        // If the node was already open, then it's check if this new G is better than the previous one.
                        if (i.getG() > i.getPossibleG(currentNode))
                        {
                            openList.remove(i);
                            i.setParentNode(currentNode);
                            openList.add(i);
                        }
                    }                        
                }
            }           
        }
        
        if (pathFound)
        {
            // Create the path in a reverse way from the goal to the start node.
            ArrayList<Integer> path = new ArrayList<Integer>();
            AStarNode aux = endNode;
            while (aux != null)
            {
                path.add(0, aux.getY());
                path.add(0, aux.getX());
                aux = aux.getParentNode();
            }
            return path;
        }
        
        return new ArrayList<Integer>();
    }
}
