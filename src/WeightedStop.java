public class WeightedStop
{
    private final String stop;
    private double weight;

    public WeightedStop(String s, double w)
    {
        stop = s;
        weight = w;
    }

    public String getStop()
    {
        return stop;
    }

    public double getWeight()
    {
        return weight;
    }

    public void setWeight(double newWeight)
    {
        weight = newWeight;
    }

    public boolean equals(Object o)
    {
        if (o instanceof WeightedStop)
        {
            if(((WeightedStop)o).getStop().equals(stop))
            {
                return true;
            }
        }
        return false;
    }

    public String toString()
    {
        return "(" + stop + "," + weight + ")";
    }
}