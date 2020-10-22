package A2;
import java.util.*;

public class Kruskal{

    public static WGraph kruskal(WGraph g){
    		/*Takes as input a graph and returns the MST of the input graph*/
    		int gNbNodes = g.getNbNodes();
    		int gNbEdges = g.listOfEdgesSorted().size();
    		DisjointSets set = new DisjointSets(gNbNodes);
    		WGraph mst = new WGraph();
        for (int i=0;i<gNbEdges;i++) {
        		Edge tempEdge = g.listOfEdgesSorted().get(i);
        		if(IsSafe(set,tempEdge)==true) {
        			set.union(tempEdge.nodes[0], tempEdge.nodes[1]);
        			mst.addEdge(tempEdge);
        		}
        }
        return mst;
    }

    public static Boolean IsSafe(DisjointSets p, Edge e){
    		if (p.find(e.nodes[0])==p.find(e.nodes[1])) {
    			return false;
    		} else {
    			return true;
    		}
    }

    public static void main(String[] args){

        String file = args[0];
        WGraph g = new WGraph(file);
        WGraph t = kruskal(g);
        System.out.println(t);

   } 
}
