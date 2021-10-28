==============
=: Overview :=
==============

Optimization problem to find the fastest path between two given stops through the Boston T. 
For the sake of convenience, the only branch of the green line that is included is the E branch (to HEATH). 
The rest of the lines are included as shown in the image, and some bus routes are included as the ‘purple’ line.
I left a pdf image of the routes in /files as a visual representation of what I am working with in case anyone is curious. 
I've also left the .txt file for all the possible lines/routes I ended up using in /files as well.


===================
=: Core Concepts :=
===================

1. Appropriately implementing a Comparator to use with PriorityQueue.
                I needed to use a PriorityQueue with my implementation of
                Dijkstra's algorithm to optimize the speed of my program.
                To effectively use PriorityQueue with each WeightedStop, I
                had to make my own Comparator to give priority to the 
                WeightedStops with the least travel time.
               
2. File I/O: using I/O to parse a novel file format.
                I used readers to read in all the possible edges/lines along
                with their associated weights and colors.

3. Representing a weighted graph.
		I needed to represent all the travel times, travel routes, and
		stops as part of a weighted graph. Travel times were weight, 
		the travel routes were WeightedEdges, and the stops were
		vertices (WeightedStops in my code). My implementation for 
		the weighted graph is under the assumption that the graph 
		is undirected, but the code can be quickly altered to work 
		if the graph happens to be directed.
                
4. Dijkstra's algorithm.
                I implemented Dijkstra's algorithm which is a breadth-first
                search(BFS) algorithm. My implenetation of the algorithm also
                considers different route options given that the user does
                not want to take certain colored lines to reach their destination.
                My implementation was also done iteratively - I will try to do it
                recursively next time. Maybe I will also trying implementing a
                depth-first search(DFS) next time too.
                
       
