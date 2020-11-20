# Assignment 1
## by Nadav Keysar
Assignment 1 is basically an improvement of assignment 0, this assignment is representing 
non-directional weighted graph. At the project we used three interfaces for implementation:
*  node info – this interface is representing the data of the node with unweighted graph. the class that implementing our interface is Node info, which is an internal class within WGraph class, this class is representing node at the graph (vertex). At this class we have many methods such as getting information about a node or define the node and get the node key, also, we can get the node tag.
At NodeInfo class there is five methods that implements node-info interface: 
1.	getkey- at this method we return the node assigned key, and for each node there is unique key.
2.	getInfo- at this method we are returning comment that is assigned to this node, each node has a String type variable that called "info"*.	 setInfo- this method allows us to change the comment that is assigned to this node
3.	getTag- this method returning the tag of the temporary data node that can be used as algorithms
4.	setTag- this method allows us to set tag value for temporary marking of the node which can be used as algorithms.
** Additionally, at NodeInfo class we have implementation of toString, equals and hashcode methods.

* At WGraph-Ps class there is 12 methods that implements weighted graph interface, weighted graph interface is representing one-way weighted graph.
The class that implements our interface is WGraph-Ps.
The main data Structure I choose to use for this project is HashMap because the runtime is 0(1).  Additionally, when returning values from HashMap, we are getting collection, which is one of staff we need for certain methods.
At this class, the functions of the operations in the graph are applied: 
1. get Node- this method returns node-data by the node-id with argument that called 'key'. All the nodes packed inside hash-map witch called nodes, the key to hash map for each node info is node id
2. hasEdge- this method returns true only if there is edge between node 1 and node 2, basically, for each graph there is hashmap that inside it there is another hash map. The main hashmap is for each node (the key is node id), the internal hashmap is for storing neighborhood of each node at the graph
3. getEdge- this method returns the edge weight between the arguments, node 1 and node 2, if there is no such weight the method will return -1.
4. addNode- this method receives fixed value as argument which called 'key' inside NodeInfo and should be added to the graph, if there is already node with key, no action is taken.
5. connect- this method receives 3 arguments: node 1, node 2 and weight. This method checks whatever there is connection between node 1 and node 2 by the hashmap of the neighborhood, if there is such connection the method checks if their weight is the same, if not the method will not do anything. In case an update is created, we create connection between node 1 and node 2 and put them as neighborhood and storing weight.
6. getV- this method returns collection of all the nodes in the chart.
7. collection get v- this method returns the collection that is representing all the neighborhood nodes of the specific node
8. remove Node- this method remove node from the graph and all its neighborhood connection in case we do not find the node at the graph no action is performed, and we return null.
9. remove edge- this method removes the connection between the arguments, and if one of them is not to be found or is not connected to each another, no action is performed.
10. nodeSize- this method returns the numbers of the nodes at t the graph
11. edgeSize -this method returns the numbers of edges that there is between the nodes in the chart. Its goal to count the numbers of edges at the graph, the counter is the returned value.
12. getmc – this method returns the counting mode; its goal is to count the numbers of changes at the internal mode of each graph. the mc is the returned value.
* at this class, those functions are implemented, and their role is to do activities at the graph, such as, adding rib, deleting rib, adding vertex, and deleting vertex. At class WGraph-Ps there is also toString, equals and hashcode methods.

*	WGraph_Algo is the class that is implements the interface weight_graph_algorithm, at WGraph_Algo there is 8 methods that implement weight-graph-algorithm interface:
1.	Init -this method initialize graph 
2.	getGraph- this method returns the graph that we are working on this class.
3.	copy- this method returns deep copy of weighted graph.
4.	isConnected- this method returns 'True' if there is valid path in each node -one with other.
5.	shortest pathDist- this method returns the length of the shortest path
6.	shortest path- this method returns the shortest path
7.	save- this method saves the graph in a file at the computer, if successes- return True, if failed returns False. 
8.	load- this method loads the graph from a computer file, if the loading was a success returning True, if not- returning False.

* we also include at WGraph-Algo class the method toString, equals and hashcode