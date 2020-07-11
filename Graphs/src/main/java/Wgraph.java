import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Wgraph {
    private HashMap<Integer, HashMap<Integer,Integer>> list=new HashMap<>();
    public void addVertex(Integer v){
        list.put(v,new HashMap<>());

    }
    public void addEdge(Integer s,Integer dest, Integer w){
        if (!list.containsKey(s)) addVertex(s);
        if (!list.containsKey(dest)) {
            addVertex(dest);
        }
            list.get(dest).put(s,w);
            list.get(s).put(dest,w);
    }
    public HashMap<Integer,Integer> getNeighbors(Integer v){
        return new HashMap<>(list.get(v));
    }

    @Override
    public String toString() {
        return "Wgraph{" +
                "list=" + list +
                '}';
    }

    public HashMap<Integer, HashMap<Integer, Integer>> getList() {
        return list;
    }


}

