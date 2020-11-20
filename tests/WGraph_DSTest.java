package ex1.tests;

import ex1.src.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WGraph_DSTest {

    @Test
    void getNode() {

        int n = 1000000;
        weighted_graph g = new WGraph_DS();
        for(int i = 0; i < n; i++) g.addNode(i);
        for(int i = 0; i < n; i++) assertEquals(i, g.getNode(i).getKey());
        assertEquals(null, g.getNode(n));

    }

    @Test
    void hasEdge() {
        weighted_graph g = new WGraph_DS();

        //int n = 10000;

        for(int i = 0; i < 10; i++) g.addNode(i);

        for(int i = 0; i < 9; i++) g.connect(i, 9,1.1);
        for(int i = 0; i < 9; i++) assertEquals(true, g.hasEdge(i,9));
        for(int i = 0; i < 9; i++) g.connect(i, 9,2.2);
        for(int i = 0; i < 9; i++) assertEquals(true, g.hasEdge(i,9));
        for(int i = 0; i < 9; i++) g.removeEdge(i, 9);
        for(int i = 0; i < 9; i++) assertEquals(false, g.hasEdge(i,9));

    }

    @Test
    void getEdge() {
        weighted_graph g = new WGraph_DS();

        int n = 10000;
        for(int i = 0; i < n; i++)  g.addNode(i);
        for(int i = 1; i < n; i++) g.connect(0, i, 9.65);
        for(int i = 1; i < n; i++) assertEquals(9.65, g.getEdge(0, i));
        for(int i = 1; i < n; i++) g.connect(0, i, 9.75);
        for(int i = 1; i < n; i++) assertEquals(9.75, g.getEdge(0, i));
        for(int i = 1; i < n; i++) g.removeEdge(0, i);
        for(int i = 1; i < n; i++) assertEquals(-1, g.getEdge(0, i));

    }

    @Test
    void addNode() {
        weighted_graph g = new WGraph_DS();

        int n = 100000;
        for(int i = 0; i < n; i++)  g.addNode(i);

        int mc = g.getMC();

        for(int i = 0; i < n; i++)  g.addNode(i); // should do nothing

        assertEquals(mc, g.getMC());
    }

    @Test
    void connect() {
        weighted_graph g = new WGraph_DS();

        int n = 100000;
        for(int i = 0; i < n; i++)  g.addNode(i);

        for(int i = 1; i < n; i++) g.connect(0, i, 9.65);
        int mc = g.getMC();
        for(int i = 1; i < n; i++) g.connect(0, i, 9.65);
        assertEquals(mc, g.getMC());
        for(int i = 1; i < n; i++) assertEquals(9.65, g.getEdge(0, i));


        for(int i = 1; i < n; i++) g.connect(0, i, 9.75);
        mc = g.getMC();
        for(int i = 1; i < n; i++) g.connect(0, i, 9.75);
        assertEquals(mc, g.getMC());
        for(int i = 1; i < n; i++) assertEquals(9.75, g.getEdge(0, i));

    }

    @Test
    void getV() {

        weighted_graph g = new WGraph_DS();

        int n = 60000;

        int[] keys = new int[n];
        for(int i = 0; i < n; i++) {
            g.addNode(i);
            keys[i] = i;
        }

        int k = 0;
        for(node_info node : g.getV()) {
            assertEquals(keys[k], node.getKey());
            k++;
        }

    }

    @Test
    void testGetV() {

        weighted_graph g = new WGraph_DS();
        int n = 1000;
        int[] keys = new int[n];
        for(int i = 0; i < n; i++) g.addNode(i);
        for(int i = 1; i < n; i++) {
            g.connect(0,i,6.53);
            keys[i-1] = i;
        }

        int k = 0;
        for(node_info node : g.getV(0)) {
            assertEquals(keys[k], node.getKey());
            k++;
        }

    }

    @Test
    void removeNode() {
        weighted_graph g = new WGraph_DS();
        int n = 10000;
        for(int i = 0; i < n; i++) g.addNode(i);
        assertEquals(n, g.nodeSize());

        for(int i = 0; i < n; i++) g.removeNode(i);
        assertEquals(0, g.nodeSize());
    }

    @Test
    void removeEdge() {
        weighted_graph g = new WGraph_DS();
        int n = 10000;
        for(int i = 0; i < n; i++) g.addNode(i);
        for(int i = 1; i < n; i++) g.connect(0, i,10);
        for(int i = 1; i < n; i++) assertEquals(true, g.hasEdge(0,i));
        for(int i = 1; i < n; i++) g.removeEdge(0, i);
        for(int i = 1; i < n; i++) assertEquals(false, g.hasEdge(0,i));

    }

    @Test
    void nodeSize() {
        weighted_graph g = new WGraph_DS();
        int n = 10000;
        for(int i = 0; i < n; i++) g.addNode(i);
        assertEquals(n, g.nodeSize());

        for(int i = 0; i < n; i++) g.removeNode(i);
        assertEquals(0, g.nodeSize());
    }

    @Test
    void edgeSize() {
        weighted_graph g = new WGraph_DS();
        int n = 10000;
        for(int i = 0; i < n; i++) g.addNode(i);
        for(int i = 1; i < n; i++) g.connect(0, i,10);
        assertEquals(n-1, g.edgeSize());
        for(int i = 1; i < n; i++) g.connect(0, i,20.2);
        assertEquals(n-1, g.edgeSize());

        for(int i = 0; i < n / 2; i++) g.removeEdge(0,i);
        assertEquals(n-(n/2), g.edgeSize());



    }
}