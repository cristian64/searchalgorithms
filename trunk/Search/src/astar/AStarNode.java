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
    
    public void setParentNode(AStarNode parentNode)
    {
        this.parentNode = parentNode;
        g = parentNode.g + ((parentNode.x == x || parentNode.y == y) ? cost : diagonalCost);
        f = g + h;
    }
    
    public int getPossibleG(AStarNode parentNode)
    {
        return parentNode.g + ((parentNode.x == x || parentNode.y == y) ? cost : diagonalCost);
    }
    
    public AStarNode getParentNode()
    {
        return parentNode;
    }
    
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
    
    public boolean isOpen()
    {
        return open;
    }
    
    public void setOpen(boolean value)
    {
        open = value;
    }
    
    public boolean isClosed()
    {
        return closed;
    }
    
    public void setClosed(boolean value)
    {
        closed = value;
    }
}
