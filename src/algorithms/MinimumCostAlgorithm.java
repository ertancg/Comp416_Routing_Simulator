package algorithms;

import simulator.NeighborInfo;

import java.util.*;
import java.util.stream.Collectors;

public class MinimumCostAlgorithm extends Algorithm {

    // IMPORTANT: Use this random number generator.
    Random rand = new Random(6391238);

    // IMPORTANT: You can maintain a state, e.g., a set of neighbors.
    private List<String> excluded = new ArrayList<String>();

    @Override
    public List<NeighborInfo> selectNeighbors(String origin, String destination, String previousHop,
                                              List<NeighborInfo> neighbors) {
    	// Your code goes here.
    	List<NeighborInfo> chosen = new ArrayList<NeighborInfo>();
    	List<NeighborInfo> aux = neighbors.stream().collect(Collectors.toList());
    	
    	if(previousHop == null){
    		chosen.add(aux.stream().min((n1,n2) -> Integer.compare(n1.cost, n2.cost) ).get());
    	}else{
    		excluded.add(previousHop);
    		aux.removeIf(n -> excluded.contains(n.address));
    		if(aux.size() == 1) chosen.add(aux.get(0));
    		else if(aux.isEmpty()) chosen.add(neighbors.get((rand.nextInt()%neighbors.size())));
    		else chosen.add(aux.stream().min((n1,n2) -> Integer.compare(n1.cost, n2.cost) ).get());
    	}
    	
    	
    	chosen.forEach(n -> excluded.add(n.address));
        return chosen;
    }

    @Override
    public Algorithm copy() {
        return new MinimumCostAlgorithm();
    }

    @Override
    public String getName() {
        return "MinimumCost";
    }
}
