package ex1.tests;

import ex1.src.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WGraph_AlgoTest {

    @Test
    void init() {
        weighted_graph g0 = new WGraph_DS();
        weighted_graph g1 = new WGraph_DS();
        g0.addNode(5);
        g1.addNode(10);

        weighted_graph_algorithms ga = new WGraph_Algo(g0);

        assertEquals(g0, ga.getGraph());

        ga.init(g1);

        assertEquals(g1,ga.getGraph());
    }

    @Test
    void getGraph() {
        weighted_graph g0 = new WGraph_DS();
        g0.addNode(10);

        weighted_graph_algorithms ga = new WGraph_Algo(g0);

        assertEquals(g0, ga.getGraph());
    }

    @Test
    void copy() {
        weighted_graph g0 = new WGraph_DS();

        for(int i = 0; i < 10000; i++) g0.addNode(i);
        weighted_graph_algorithms ga = new WGraph_Algo(g0);

        weighted_graph g1 =ga.copy();
        assertEquals(g0,g1);

        g0.removeNode(0);

        assertNotEquals(g0,g1);
    }

    @Test
    void isConnected() {

        weighted_graph g = new WGraph_DS();
        int n = 100000;

        for(int i = 0; i < n; i++) g.addNode(i);
        for(int i = 1; i < n; i++) g.connect(0, i,1);

        weighted_graph_algorithms ga = new WGraph_Algo(g);
        assertEquals(true, ga.isConnected());

        g.removeEdge(0, n / 2);
        assertEquals(false, ga.isConnected());




    }

    @Test
    void shortestPathDist() {
        weighted_graph g = new WGraph_DS();
        int n = 10;

        for(int i = 0; i < n; i++) g.addNode(i);

        // [0,1,2,3,4,5] : weights: [2,1,3,1,5] = 12
        g.connect(0,1,2);
        g.connect(1,2,1);
        g.connect(2,3,3);
        g.connect(3,4,1);
        g.connect(4,5,5);

        // [0,6,7,8,9,5] : weights: [4,1,2,1,2] = 11
        g.connect(0,6,4);
        g.connect(6,7,1);
        g.connect(7,8,3);
        g.connect(8,9,1);
        g.connect(9,5,2);

        weighted_graph_algorithms ga = new WGraph_Algo(g);

        double da =  ga.shortestPathDist(0,5);
        assertEquals(11,da);

        g.connect(9,5,4);

        double db =  ga.shortestPathDist(0,5);
        assertEquals(12,db);

    }

    @Test
    void shortestPath() {
        weighted_graph g = new WGraph_DS();
        int n = 10;

        for(int i = 0; i < n; i++) g.addNode(i);

        // [0,1,2,3,4,5] : weights: [2,1,3,1,5] = 12
        g.connect(0,1,2);
        g.connect(1,2,1);
        g.connect(2,3,3);
        g.connect(3,4,1);
        g.connect(4,5,5);

        // [0,6,7,8,9,5] : weights: [4,1,2,1,2] = 11
        g.connect(0,6,4);
        g.connect(6,7,1);
        g.connect(7,8,3);
        g.connect(8,9,1);
        g.connect(9,5,2);

        weighted_graph_algorithms ga = new WGraph_Algo(g);
        int[] pathA = {0,1,2,3,4,5};
        int[] pathB = {0,6,7,8,9,5};

        List<node_info> lb =  ga.shortestPath(0,5);
        for(int i = 0; i < lb.size(); i++) assertEquals(pathB[i],lb.get(i).getKey());

        g.connect(9,5,4);

        List<node_info> la =  ga.shortestPath(0,5);
        for(int i = 0; i < la.size(); i++) assertEquals(pathA[i],la.get(i).getKey());
    }

    @Test
    void save_load() {
        weighted_graph g = new WGraph_DS();
        for(int i = 0 ; i < 1000; i++) g.addNode(i);
        for(int i = 1 ; i < 1000; i++) g.connect(1,i,2);
        weighted_graph_algorithms ga = new WGraph_Algo(g);
        ga.save("test.obj");

        weighted_graph_algorithms gb = new WGraph_Algo();
        gb.load("test.obj");

        assertEquals(ga.getGraph().getV().toString(), gb.getGraph().getV().toString());

    }
}