/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

/**
 *
 * @author Cristian
 */
public class AStarNode implements Comparable<AStarNode>
{
    private int x;
    private int y;
    private int cost;
    private int diagonalCost;
    private AStarNode parentNode;
    private AStarNode endNode;
    private int f;
    private int g;
    private int h;
    
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
    }

    @Override
    public int compareTo(AStarNode o)
    {
        return f - o.f;
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
    
    public AStarNode getParentNode()
    {
        return parentNode;
    }
    
    public void setEndNode(AStarNode endNode)
    {
        this.endNode = endNode;
        h = Math.abs(endNode.x - x) + Math.abs(endNode.y - y); //TODO take into account the heuristic
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
}
