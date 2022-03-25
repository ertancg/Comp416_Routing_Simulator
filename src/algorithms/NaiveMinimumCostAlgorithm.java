package algorithms;

import simulator.NeighborInfo;

import java.util.*;
import java.util.stream.Collectors;

public class NaiveMinimumCostAlgorithm extends Algorithm {

    @Override
    public List<NeighborInfo> selectNeighbors(String origin, String destination, String previousHop,
                                              List<NeighborInfo> neighbors) {
    	// Your code goes here.
    	if(previousHop != null){
    		neighbors.removeIf(n -> n.address.equalsIgnoreCase(previousHop));
    	}
    	List<NeighborInfo> chosen = new ArrayList<NeighborInfo>();
    	chosen.add(neighbors.stream().min((n1,n2) -> Integer.compare(n1.cost, n2.cost) ).get());
    	
    	
        return chosen;
    }

    @Override
    public Algorithm copy() {
        return new NaiveMinimumCostAlgorithm();
    }

    @Override
    public String getName() {
        return "NaiveMinimumCost";
    }
}
