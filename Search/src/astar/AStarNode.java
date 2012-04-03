package astar;

import java.util.ArrayList;

/**
 *
 * @author Cristian
 */
public class AStarNode implements Comparable<AStarNode>
{
    public static int MIN_COST = 10;
    public static int MIN_DIAGONAL_COST = 14;
    public static int NONWALKABLE_COST = 999999999;
    public enum AlgorithmType { ASTAR, DIJKSTRA, GREEDY }
    public static AlgorithmType algorithmType = AlgorithmType.ASTAR;
    
    private int x;
    private int y;
    private int cost;
    private int diagonalCost;
    private AStarNode parentNode;
    private AStarNode endNode;
    private int f;
    private int g;
    private int h;
    private boolean open;
    private boolean closed;
    
    /**
     * Node that is used for the algorithm AStar.
     * @param x Component x of the position of the node. It represents the columns.
     * @param y Component y of the position of the node. It represents the rows.
     * @param cost Cost to step on this node.
     * @param diagonalCost Cost to step on this node with a diagonal move.
     */
    public AStarNode(int x, int y, int cost, int diagonalCost)
    {
        this.x = x;
        this.y = y;
        this.cost = cost;
        this.diagonalCost = diagonalCost;
        parentNode = null;
        endNode = null;
        f = 0;
        g = 0;
        h = 0;
        open = false;
        closed = false;
    }

    /**
     * It is needed to be able to ordered the list of elements according to how promising they are.
     * Determines which node is better according to the F value.
     * @param o Another node that is going to be compared with.
     * @return Returns 0 if they are the same. -1 if the first one is better. 1 in other situation.
     */
    @Override
    public int compareTo(AStarNode o)
    {
        if (algorithmType == AlgorithmType.ASTAR)
            return f - o.f;
        else if (algorithmType == AlgorithmType.DIJKSTRA)
            return g - o.g;
        else
            return h - o.h;        
    }
    
    /**
     * Generates the adjacent nodes for this particular node.
     * @param nodes Matrix of nodes to get to know how big the matrix is.
     * @return Returns a list with the nodes surrounding this node.
     */
    public ArrayList<AStarNode> generateChildren(AStarNode[][] nodes)
    {
        ArrayList<AStarNode> children = new ArrayList<AStarNode>();
        
        if (x + 1 < nodes[0].length)
            children.add(nodes[y][x + 1]);
        if (y + 1 < nodes.length)
            children.add(nodes[y + 1][x]);
        if (x - 1 >= 0)
            children.add(nodes[y][x - 1]);
        if (y - 1 >= 0)
            children.add(nodes[y - 1][x]);
        
        if (x + 1 < nodes[0].length && y + 1 < nodes.length)
            children.add(nodes[y + 1][x + 1]);
        if (x + 1 < nodes[0].length && y - 1 >= 0)
            children.add(nodes[y - 1][x + 1]);
        if (x - 1 >= 0 && y + 1 < nodes.length)
            children.add(nodes[y + 1][x - 1]);
        if (x - 1 >= 0 &&  y - 1 >= 0)
            children.add(nodes[y - 1][x - 1]);
        
        return children;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public int getCost()
    {
        return cost;
    }
    
    public int getDiagonalCost()
    {
        return diagonalCost;
    }
    
    /**
     * Changes the parent node for the node.
     * Afterwards, the G value is recalculated (and the F value as well).
     * @param parentNode New parent node for the node.
     */
    public void setParentNode(AStarNode parentNode)
    {
        this.parentNode = parentNode;
        g = parentNode.g + ((parentNode.x == x || parentNode.y == y) ? cost : diagonalCost);
        f = g + h;
    }
    
    /**
     * Checks, without changing the parent node, the possible G after changing it.
     * @param parentNode Possible parent node for this node.
     * @return The G value of the node if the parent was the one sent in the parameters.
     */
    public int getPossibleG(AStarNode parentNode)
    {
        return parentNode.g + ((parentNode.x == x || parentNode.y == y) ? cost : diagonalCost);
    }
    
    /**
     * @return Indicates the node from where it came to this node.
     */
    public AStarNode getParentNode()
    {
        return parentNode;
    }
    
    /**
     * Change the end node for this node.
     * Afterwards, H is calculated.
     * @param endNode End node (the goal).
     */
    public void setEndNode(AStarNode endNode)
    {
        this.endNode = endNode;
        
        if (Math.abs(endNode.x - x) > Math.abs(endNode.y - y))
        {
            h = Math.abs(endNode.y - y) * MIN_DIAGONAL_COST + (Math.abs(endNode.x - x) - Math.abs(endNode.y - y)) * MIN_COST;
        }
        else
        {
            h = Math.abs(endNode.x - x) * MIN_DIAGONAL_COST + (Math.abs(endNode.y - y) - Math.abs(endNode.x - x)) * MIN_COST;
        }
        
        f = g + h;
    }
    
    /**
     * @return Indicates the goal node.
     */
    public AStarNode getEndNode()
    {
        return endNode;
    }
    
    public int getF()
    {
        return f;
    }

    public int getG()
    {
        return g;
    }
    
    public int getH()
    {
        return h;
    }
    
    /**
     * Checks if the node has been already add into the list of promising nodes.
     * @return Return true if the node was already expanded.
     */
    public boolean isOpen()
    {
        return open;
    }
    
    /**
     * Establish if the node was added to the list of promising nodes.
     * @param value New value for the state of this variable.
     */
    public void setOpen(boolean value)
    {
        open = value;
    }
    
    /**
     * Checks if the node has been already expanded.
     * @return Return true if the node was already expanded.
     */
    public boolean isClosed()
    {
        return closed;
    }
    
    /**
     * Establish if the node was expanded or node.
     * @param value New value for the state of this variable.
     */
    public void setClosed(boolean value)
    {
        closed = value;
    }
}
