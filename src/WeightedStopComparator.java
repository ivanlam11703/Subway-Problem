import java.util.*;

public class WeightedStopComparator implements Comparator<WeightedStop>
{
    public int compare(WeightedStop o1, WeightedStop o2)
    {
        if (o1.getWeight() > o2.getWeight())
        {
            return 1;
        }
        if (o1.getWeight() < o2.getWeight())
        {
            return -1;
        }
        if (o1.getStop().compareTo(o2.getStop()) < 0)
        {
            return 1;
        }
        if (o1.getStop().compareTo(o2.getStop()) > 0)
        {
            return -1;
        }
        return 0;
    }
}