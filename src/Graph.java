import java.util.*;

public class Graph
{
    private ArrayList<String> stops;
    private ArrayList<WeightedEdge> edges;

    public Graph(ArrayList<String> s, ArrayList<WeightedEdge> e)
    {
        stops = s;
        edges = e;
    }

    public Graph()
    {
        this(new ArrayList<String>(), new ArrayList<WeightedEdge>());
    }

    public boolean addStop(String s)
    {
        if (stops.contains(s))
        {
            return false;
        }
        stops.add(s);
        return true;
    }

    public boolean addWeightedEdge(WeightedEdge we)
    {
        edges.add(we);
        return true;
    }

    public boolean addWeightedEdge(String from, String to, double weight, String color)
    {
        edges.add(new WeightedEdge(from, to, weight, color));
        return true;
    }

    private int getIndex(String stop)
    {
        for (int i = 0; i < stops.size(); i++)
        {
            if(stops.get(i).equals(stop))
            {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<String> findShortestPath(String fromStop, String toStop, String[] restrictedColors)
    {
        if (getIndex(fromStop) == -1 || getIndex(toStop) == -1)
        {
            return new ArrayList<String>(Arrays.asList("DNE"));
        }

        ArrayList<WeightedEdge> applicableEdges = edges;
        for (String color : restrictedColors)
        {
            applicableEdges.removeIf(e -> (e.getColor().equals(color)));
        }

        PriorityQueue<WeightedStop> minPriorityQueueByWeight = new PriorityQueue<>(stops.size(), new WeightedStopComparator());
        WeightedStop[] stopCost = new WeightedStop[stops.size()];
        String[] parentStop = new String[stops.size()];

        for (int i = 0; i < stops.size(); i++)
        {
            stopCost[i] = new WeightedStop(stops.get(i), Double.POSITIVE_INFINITY);
            parentStop[i] = "";
        }

        parentStop[getIndex(fromStop)] = fromStop;
        stopCost[getIndex(fromStop)] = new WeightedStop(fromStop, 0.0);

        for (int i = 0; i < stops.size(); i++)
        {
            minPriorityQueueByWeight.add(stopCost[i]);
        }

        while (minPriorityQueueByWeight.size() > 0)
        {
            WeightedStop v = minPriorityQueueByWeight.poll();
            if (v.getStop().equals(toStop)) break;
            if (parentStop[getIndex(v.getStop())].isEmpty()) break;

            for (WeightedEdge e : applicableEdges)
            {
                if (e.getFromStop().equals(v.getStop()))
                {
                    WeightedStop u = stopCost[getIndex(e.getToStop())];
                    if ((e.getWeight() + v.getWeight()) < u .getWeight())
                    {
                        minPriorityQueueByWeight.remove(u);
                        u.setWeight(e.getWeight() + v.getWeight());
                        minPriorityQueueByWeight.add(u);
                        stopCost[getIndex(e.getToStop())] = u;
                        parentStop[getIndex(e.getToStop())] = e.getFromStop();
                    }
                }
                else if (e.getToStop().equals(v.getStop()))								
				{																		
					WeightedStop u = stopCost[getIndex(e.getFromStop())];
					if ((v.getWeight() + e.getWeight()) < u.getWeight())
					{
						minPriorityQueueByWeight.remove(u);
						u.setWeight(e.getWeight() + v.getWeight());
						minPriorityQueueByWeight.add(u);
						stopCost[getIndex(e.getFromStop())] = u;
						parentStop[getIndex(e.getFromStop())] = e.getToStop();
					}
				}
            }
        }

        if (parentStop[getIndex(toStop)].isEmpty())
        {
            return new ArrayList<String>();
        }
        ArrayList<String> reversePath = new ArrayList<String>();
        ArrayList<String> forwardPath = new ArrayList<String>();
        String currentStop = toStop;
        reversePath.add(currentStop);

        while (!currentStop.equals(fromStop))
        {
            currentStop = parentStop[getIndex(currentStop)];
            reversePath.add(currentStop);
        }

        for (int i = reversePath.size() - 1; i >= 0; i--)
		{
			forwardPath.add(reversePath.get(i));
		}
        return forwardPath;
    }

    public double findPathWeight(String fromStop, String toStop, String[] restrictedColors)
    {
        ArrayList<String> path = findShortestPath(fromStop, toStop, restrictedColors);
        double weight = 0.0;
        for (int i = 0; i < path.size() - 1; i++)
		{
			for (WeightedEdge e : edges)
			{
				if (e.getFromStop().equals(path.get(i)) && e.getToStop().equals(path.get(i+1)) 
                || e.getToStop().equals(path.get(i)) && e.getFromStop().equals(path.get(i+1)))	
				{
					weight += e.getWeight();
				}
			}
		}
		return weight;
    }

    public String toString()
    {
        StringBuilder s = new StringBuilder("Stops: ");
		for( int i = 0; i < stops.size(); i++ )
		{
			s.append(stops.get(i));
			if( i < (stops.size()-1) )
			{
				s.append(", ");
			}	
		}
		s.append("\nEdges: ");
		for( int i = 0; i < edges.size(); i++ )
		{
			WeightedEdge currentEdge = edges.get(i);
			s.append("("+ currentEdge.getFromStop() + " => " + currentEdge.getToStop() + ", " + currentEdge.getWeight() + ", " + currentEdge.getColor() + ")");
			if( i < (edges.size()-1) )
			{
				s.append(", ");
			}
		}
		return s.toString();
    }
}