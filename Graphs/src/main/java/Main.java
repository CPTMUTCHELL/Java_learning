import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        //System.out.println(graph);
        Wgraph wgraph=new Wgraph();
        wgraph.addEdge(0,1,1);
        wgraph.addEdge(0,3,8);
        wgraph.addEdge(1,2,4);
        wgraph.addEdge(1,4,3);
        wgraph.addEdge(2,3,6);
        wgraph.addEdge(3,4,7);
        List<Integer> sup=new ArrayList<>();
        for (int i = 0; i <=wgraph.getList().size()-1; i++) {
            if (i==0) sup.add(0,0);
            else sup.add(i,10000);
        }
        D d=new D(wgraph,sup);
        d.d(0,2);
        
    }
}
