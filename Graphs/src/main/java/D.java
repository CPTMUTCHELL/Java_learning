import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class D {
    Wgraph graph;
    List<Integer> sup;
    public D(Wgraph graph,  List<Integer> sup) {
        this.graph = graph;
        this.sup=sup;
    }
    PriorityQueue<Integer> q=new PriorityQueue<>();
    LinkedList<Integer> visited=new LinkedList<>();

    void d(Integer s, Integer target){
        if (!visited.contains(target)) {
            for (Iterator<Integer> it = graph.getNeighbors(s).keySet().iterator(); it.hasNext(); ) {
                Integer integer = it.next();
                if ((graph.getList().get(s).get(integer) + sup.get(s)) < (sup.get(integer))) {
                    sup.set(integer, graph.getList().get(s).get(integer) + sup.get(s));
                    q.add(graph.getList().get(s).get(integer) + sup.get(s));
                }
            }
            visited.add(s);
            s = sup.indexOf(q.poll());
            d(s, target);
        }
        else {
            System.out.println("Dist: "+sup.get(target)+" "+visited);
        }
    }
}
