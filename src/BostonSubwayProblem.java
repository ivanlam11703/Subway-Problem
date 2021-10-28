import java.util.*;
import java.io.*;

public class BostonSubwayProblem
{
    public static void main(String[] args) 
    {
        if (args.length < 3)
        {
            System.out.println("Format is \"filename fromStop toStop\" followed by any restricted colors");
            System.exit(0);
        }
        Graph g = createGraph(args[0]);
        String[] restrictedColors = new String[args.length - 3];
        for (int i = 3; i < args.length; i++)
        {
            restrictedColors[i-3] = args[i];
        }
        ArrayList<String> shortestPath = g.findShortestPath(args[1], args[2], restrictedColors);
        if (shortestPath.isEmpty() && restrictedColors.length != 0)
        {
            System.out.println("A path between the provided stops could not be found given the restricted line colors.");
        }
        else if (shortestPath.isEmpty())
        {
            System.out.println("A path between the provided stops could not be found.");
        }
        else if (shortestPath.get(0).equals("DNE"))
        {
            System.out.println("One of the provided stops does not exist.");
        }
        else if (args.length == 3)
        {
            System.out.println("The shortest path between " + args[1] + " and " + args[2] + " is:");
            for (int i = 0; i < shortestPath.size(); i++)
            {
                System.out.print(shortestPath.get(i));
                if (i < shortestPath.size() - 1)
                {
                    System.out.print(" => ");
                }
            }
            System.out.println("\nThe cost of taking this path is " + g.findPathWeight(args[1], args[2], restrictedColors) + ".");
        }
        else if (restrictedColors.length == 1)
        {
            System.out.println("The shortest path between " + args[1] + " and " + args[2] + " while avoiding the " + restrictedColors[0] + " line is:");
            for (int i = 0; i < shortestPath.size(); i++)
            {
                System.out.print(shortestPath.get(i));
                if (i < shortestPath.size() - 1)
                {
                    System.out.print(" => ");
                }
            }
            System.out.println("\nThe cost of taking this path is " + g.findPathWeight(args[1], args[2], restrictedColors) + ".");
        }
        else if (restrictedColors.length == 2)
        {
            System.out.println("The shortest path between " + args[1] + " and " + args[2] + " while avoiding the " 
            + restrictedColors[0] + " and " + restrictedColors[1] + " lines is:");
            for (int i = 0; i < shortestPath.size(); i++)
            {
                System.out.print(shortestPath.get(i));
                if (i < shortestPath.size() - 1)
                {
                    System.out.print(" => ");
                }
            }
            System.out.println("\nThe cost of taking this path is " + g.findPathWeight(args[1], args[2], restrictedColors) + ".");
        }
        else if (restrictedColors.length > 2)
        {
            System.out.print("The shortest path between " + args[1] + " and " + args[2] + " while avoiding the ");
            for (int i = 0; i < restrictedColors.length; i++)
            {
                System.out.print(restrictedColors[i]);
                if (i < restrictedColors.length - 2)
                {
                    System.out.print(", ");
                }
                if (i == restrictedColors.length - 2)
                {
                    System.out.print(", and ");
                }
            }
            System.out.print(" lines is:\n");
            for (int i = 0; i < shortestPath.size(); i++)
            {
                System.out.print(shortestPath.get(i));
                if (i < shortestPath.size() - 1)
                {
                    System.out.print(" => ");
                }
            }
            System.out.println("\nThe cost of taking this path is " + g.findPathWeight(args[1], args[2], restrictedColors) + ".");
        }
    }

    public static Graph createGraph(String inputFilename)
    {
        Graph g = new Graph();
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(inputFilename));
            String line = "";
            while ((line = br.readLine()) != null)
            {
                StringTokenizer st = new StringTokenizer(line);
                while (st.hasMoreTokens())
                {
                    String fromStop = st.nextToken();
                    String toStop = st.nextToken();
                    double weight = Double.parseDouble(st.nextToken());
                    String color = st.nextToken();
                    g.addStop(fromStop);
                    g.addStop(toStop);
                    g.addWeightedEdge(fromStop, toStop, weight, color);
                }
            }
            br.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return g;
    }
}