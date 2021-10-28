public class WeightedEdge
{
    private final String fromStop;
    private final String toStop;
    private final double weight;
    private final String color;

    public WeightedEdge(String f, String t, double w, String c)
    {
        fromStop = f;
        toStop = t;
        weight = w;
        color = c;
    }

    public String getFromStop()
    {
        return fromStop;
    }

    public String getToStop()
    {
        return toStop;
    }

    public double getWeight()
    {
        return weight;
    }

    public String getColor()
    {
        return color;
    }

    public boolean equals(Object o)
    {
        if (o instanceof WeightedEdge)
        {
            if (((WeightedEdge)o).getColor().equals(color) && ((((WeightedEdge)o).getFromStop().equals(fromStop) || ((WeightedEdge)o).getFromStop().equals(toStop)) 
            || (((WeightedEdge)o).getToStop().equals(fromStop) || ((WeightedEdge)o).getToStop().equals(toStop))))
            {
                return true;
            }
        }
        return false;
    }

    public String toString()
    {
        return "(" + fromStop + "," + toStop + "," + weight + "," + color + ")";
    }
}