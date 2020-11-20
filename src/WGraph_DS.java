package ex1.src;

import java.io.Serializable;
import java.util.*;

public class WGraph_DS implements weighted_graph, Serializable {
	
	//first method
	private HashMap<Integer, node_info> graph;	//g = {n0.key = n0, n1.key = n1, ... , }
	private HashMap<node_info, HashMap<node_info, Double>> neighbors; //ni = {n0.key = {n1, n2}, n1.key = {}, n2.key = {n3} .. }

	
	private int mc;
	private int ec;
	
	private class Node implements node_info, Serializable {

		private double tag;
		private int key;
		private String info;
		
		public Node(int key) {
			this.tag = 0.0;
			this.key = key;
			this.info = "";
		}

		// private class of node
		/**
		 *at this method we return the node assigned key, and for each node there is unique key.
		 */
		@Override
		public int getKey() {
			return this.key;
		}

		/**
		 *at this method we are returning comment that is assigned to this node,
		 *each node has a String type variable that called "info"
		 */
		@Override
		public String getInfo() {
			// TODO Auto-generated method stub
			return this.info;
		}

		/**
		 *this method allows us to change the comment
		 *that is assigned to this node
		 */
		@Override
		public void setInfo(String s) {
			this.info = s;
		}

		/**
		 *this method returning the tag of the temporary data node
		 *that can be used as algorithms
		 */
		@Override
		public double getTag() {
			return this.tag;
		}

		/**
		 *this method allows us to set tag value for temporary
		 *marking of the node which can be used as algorithms.
		 */
		@Override
		public void setTag(double t) {
			this.tag = t;
		}

		/**
		 *To string method
		 */
		@Override
		public String toString() {
			return "" + this.key;
		}

		/**
		 *Equals method
		 */
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof Node)) return false;

			Node node = (Node) o;

			if (node.tag != this.tag) return false;
			if (key != node.key) return false;
			return info != null ? info == node.info : node.info == null;
		}

		/**
		 *Hashcode is function that can get specific value and creating one-to-one value
		 *that can be saved in the memory where the object that the function got.
		 *For example inside the class of hashmap to store each object in a specific location
		 *we use hashcode by key of the object, while you implemented the hashcode to your class
		 *its say to java that there is option to give hashcode to the object when you save the object,
		 *that’s why we used it in this assignment.
		 */
		@Override
		public int hashCode() {
			return key;
		}
	}

	/**
	 *Ps class there is 12 methods that implements weighted graph interface,
	 *weighted graph interface is representing one-way weighted graph.
	 *The class that implements our interface is WGraph-Ps.
	 *The main data Structure I choose to use for this project is HashMap because the runtime is 0(1).
	 *Additionally, when returning values from HashMap,
	 * we are getting collection, which is one of staff we need for certain methods.
	 */
	public WGraph_DS() {
		// TODO Auto-generated constructor stub
		this.mc = 0;
		this.ec = 0;

		this.graph = new HashMap<>();
		this.neighbors  = new HashMap<>();


	}

	/**
	 * return the node_data by the node_id,
	 * @param key - the node_id
	 * @return the node_data by the node_id, null if none.
	 */

	//this method returns node-data by the node-id with argument that called 'key'.
	// All the nodes packed inside hash-map witch called nodes,
	// the key to hash map for each node info is node id
	@Override
	public node_info getNode(int key) {
		return this.graph.get(key);
	}

	/**
	 * return true iff (if and only if) there is an edge between node1 and node2
	 * Note: this method should run in O(1) time.
	 * @param node1
	 * @param node2
	 * @return
	 */

	//this method returns true only if there is edge between node 1 and node 2,
	//basically, for each graph there is hashmap that inside it there is another hash map.
	//The main hashmap is for each node (the key is node id),
	//the internal hashmap is for storing neighborhood of each node at the graph
	@Override
	public boolean hasEdge(int node1, int node2) {

		if(this.nodeSize() <= 1 || this.edgeSize() == 0 || this.getNode(node1) == null ||
				this.getNode(node2) == null || node1 == node2) return false;

			if(this.neighbors.get(getNode(node1)) != null
					&& this.neighbors.get(getNode(node1)).get(getNode(node2)) != null)
				return true;
			return false;
	}

	/**
	 * return the weight if the edge (node1, node2) exist. In case
	 * there is no such edge - should return -1
	 * Note: this method should run in O(1) time.
	 * @param node1
	 * @param node2
	 * @return
	 */
	//this method returns the edge weight between the arguments,
	//node 1 and node 2, if there is no such weight the method will return -1.
	@Override
	public double getEdge(int node1, int node2) {
		if (!hasEdge(node1, node2)) return -1;
		return this.neighbors.get(getNode(node1)).get(getNode(node2));
	}

	/**
	 * add a new node to the graph with the given key.
	 * Note: this method should run in O(1) time.
	 * Note2: if there is already a node with such a key -> no action should be performed.
	 * @param key
	 */

	//this method receives fixed value as argument which called 'key' inside NodeInfo and should
	//be added to the graph, if there is already node with key, no action is taken.
	@Override
	public void addNode(int key) {
		if (!this.graph.containsKey(key)) { // first solution -> node.key is new
			this.graph.put(key, new Node(key));
			this.neighbors.put(getNode(key), new HashMap<>());
			//this.weights.put(key, new HashMap<Integer,Double>());

			this.mc++;
		}
	}

	/**
	 * Connect an edge between node1 and node2, with an edge with weight >=0.
	 * Note: this method should run in O(1) time.
	 * Note2: if the edge node1-node2 already exists - the method simply updates the weight of the edge.
	 */

	//This method receives 3 arguments: node 1, node 2 and weight.
	//This method checks whatever there is connection between node 1 and node 2
	//by the hashmap of the neighborhood, if there is such connection
	//the method checks if their weight is the same,
	//if not the method will not do anything.
	//In case an update is created, we create connection between node 1 and node 2
	//and put them as neighborhood and storing weight.
	@Override
	public void connect(int node1, int node2, double w) {

		node_info a = this.graph.get(node1);
		node_info b = this.graph.get(node2);

		if ((this.graph.containsKey(node1)) && (this.graph.containsKey(node2))) {
			if(node1 != node2) {
				// in case and node1 and node2 are connected
				if (hasEdge(node1, node2)) {

					if(this.neighbors.get(a).get(b) != w) {
						this.neighbors.get(a).put(b, w);
						this.neighbors.get(b).put(a, w);
						mc++;
					}

				}
				if (!hasEdge(node1, node2) && !hasEdge(node2, node1)) {
					this.neighbors.get(getNode(node1)).put(getNode(node2), w);
					this.neighbors.get(getNode(node2)).put(getNode(node1), w);

					this.ec++; //increase edge count
					this.mc++;
				}
			}
		}
	}

	/**
	 * This method return a pointer (shallow copy) for a
	 * Collection representing all the nodes in the graph.
	 * Note: this method should run in O(1) tim
	 * @return Collection<node_data>
	 */

	//this method returns collection of all the nodes in the chart.
	@Override
	public Collection<node_info> getV() {
		return this.graph.values();
	}

	/**
	*
	* This method returns a Collection containing all the
	* nodes connected to node_id
	* Note: this method can run in O(k) time, k - being the degree of node_id.
	* @return Collection<node_info>
	*/

	//this method returns the collection that is representing all
	//the neighborhood nodes of the specific node
	@Override
	public Collection<node_info> getV(int node_id) {
		if(this.neighbors.containsKey(getNode(node_id)))
		return this.neighbors.get(getNode(node_id)).keySet();
			return null;//o(1)
	}

	/**
	 * Delete the node (with the given ID) from the graph -
	 * and removes all edges which starts or ends at this node.
	 * This method should run in O(n), |V|=n, as all the edges should be removed.
	 * @return the data of the removed node (null if none).
	 * @param key
	 */

	//this method remove node from the graph and all its neighborhood connection
	//in case we do not find the node at the graph no action is performed,
	//and we return null.
	@Override
	public node_info removeNode(int key) {
		if(this.nodeSize() == 0) return null;
		if(this.graph.containsKey(key)) {
			node_info a = this.getNode(key);
			if (this.neighbors.containsKey(getNode(key))) {
				Iterator<node_info>iterator = this.getV(key).iterator();
				while(iterator.hasNext()) {
					node_info n = iterator.next();
					removeEdge(key, n.getKey());
					iterator = getV(key).iterator();
				}
				this.neighbors.get(getNode(key)).remove(key);
			}
			this.graph.remove(key);
			this.mc++;
			return a;
		}
		return null;
	}

	/**
	 * Delete the edge from the graph,
	 * Note: this method should run in O(1) time.
	 * @param node1
	 * @param node2
	 */

	//this method removes the connection between the arguments,
	//and if one of them is not to be found or is not connected to each another,
	//no action is performed.
	@Override
	public void removeEdge(int node1, int node2) {
			if (this.neighbors.get(getNode(node1)).get(getNode(node2)) != null && hasEdge(node1, node2)) {
				node_info a = this.graph.get(getNode(node1).getKey());
				node_info b = this.graph.get(getNode(node2).getKey());
				this.neighbors.get(getNode(node1)).remove(b);
				this.neighbors.get(getNode(node2)).remove(a);
				this.mc++;
				this.ec--;
			}
		}

	/** return the number of vertices (nodes) in the graph.
	 * Note: this method should run in O(1) time.
	 * @return
	 */

	//This method returns the numbers of the nodes at t the graph
	@Override
	public int nodeSize() {
		return this.graph.size();
	}

	/**
	 * return the number of edges (undirectional graph).
	 * Note: this method should run in O(1) time.
	 * @return
	 */

	//this method returns the numbers of edges that there is between the nodes in the chart.
	//Its goal to count the numbers of edges at the graph,
	//the counter is the returned value.
	@Override
	public int edgeSize() {
		// TODO Auto-generated method stub
		return this.ec;
	}

	/**
	 * return the Mode Count - for testing changes in the graph.
	 * Any change in the inner state of the graph should cause an increment in the ModeCount
	 * @return
	 */
	//this method returns the counting mode,
	//its goal is to count the numbers of changes at the internal
	//mode of each graph.
	//The mc is the returned value.
	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return this.mc;
	}
	//To string method
	public String toString() {
		return "" + this.getV();
	}

	//Equals method
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof WGraph_DS)) return false;

		WGraph_DS wGraph_ds = (WGraph_DS) o;

		if (ec != wGraph_ds.ec) return false;
		if (graph != null ? !graph.equals(wGraph_ds.graph) : wGraph_ds.graph != null) return false;
		return neighbors != null ? neighbors.equals(wGraph_ds.neighbors) : wGraph_ds.neighbors == null;
	}
}
