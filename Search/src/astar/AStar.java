package astar;

import java.util.ArrayList;
import java.util.Collections;
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
    public PriorityQueue<AStarNode> openList;
    
    public AStar(AStarNode[][] nodes, AStarNode startNode, AStarNode endNode)
    {
        this.nodes = nodes;
        this.startNode = startNode;
        this.endNode = endNode;
        
        for (int i = 0; i < nodes.length; i++)
            for (int j = 0; j < nodes[i].length; j++)
                nodes[i][j].setEndNode(endNode);
    }
    
    public ArrayList<AStarNode> findPath()
    {
        openList = new PriorityQueue<AStarNode>();
        AStarNode currentNode;
        openList.add(startNode);
        startNode.setOpen(true);
        boolean pathFound = false;
        
        while (!openList.isEmpty() && !pathFound)
        {
            currentNode = openList.poll();
            currentNode.setClosed(true);
            currentNode.setOpen(false);
            
            ArrayList<AStarNode> children = currentNode.generateChildren(nodes);
            for (AStarNode i : children)
            {
                if (!i.isClosed())
                {
                    if (!i.isOpen())
                    {
                        i.setParentNode(currentNode);
                        openList.add(i);
                        i.setOpen(true);
                        
                        if (i == endNode)
                        {
                            pathFound = true;
                            break;
                        }
                    }
                    else
                    {
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
            ArrayList<AStarNode> path = new ArrayList<AStarNode>();
            AStarNode aux = endNode;
            while (aux != null)
            {
                path.add(0, aux);
                aux = aux.getParentNode();
            }
            return path;
        }
        
        return null;                
    }
}
