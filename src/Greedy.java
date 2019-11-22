import dataStructure.*;
import dataStructure.Graph;
import dataStructure.InputVertex;
import dataStructure.Vertex;
import test.TestEdge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Greedy {

	private Graph g;
	private double priceCable;
	private double priceFlow;
	private List<Vertex> toBeAdded;

	public Greedy(InputVertex root, double priceCable, double priceFlow) {
		this.priceCable = priceCable;
		this.priceFlow = priceFlow;
		g = new AdjazenzListGraph(root);
		toBeAdded = new ArrayList<>();
	}

	public boolean dynamicAdd(InputVertex toBeAdded) {
		g.add(toBeAdded);
		// backup the old inputvertex.information
		Map<InputVertex, InputVertex> oldPi = new HashMap<>();
		for (InputVertex v : g.getAllVerticesWithoutRoot()) {
			oldPi.put(v, v.getPi());
		}
		Map<InputVertex, Double> oldCurrentProfit = new HashMap<>();
		for (InputVertex v : g.getAllVerticesWithoutRoot()) {
			oldCurrentProfit.put(v, v.getCurrentProfit());
		}
		if (!dijkstra(getSumProfit())) {
			// set the old information back.
			for (InputVertex v : g.getAllVerticesWithoutRoot()) {
				v.setPi(oldPi.get(v));
			}
		

			for (InputVertex v : g.getAllVerticesWithoutRoot()) {
		
				v.setCurrentProfit(oldCurrentProfit.get(v));
			}
			return false;
		}
//    System.out.println("ccc");
//    g.getAllVerticesWithoutRoot().forEach(x->System.out.println(x));
		return true;
	}

	private double getSumProfit() {
		return g.getSumProfit();
	}
	// public Boolean dynamicAdd (InputVertex toBeAdded) {
	// //all of the vertex in the graph
	// Vertex maxV=null; //graph.existed.point->newPoint maxProfit
	// double maxProfit= -1;
	// for (Vertex v : g.getAllVertices) {
	// double tmp=profit(toBeAdded,v);
	// if(tmp>maxProfit){
	// maxProfit = tmp;
	// maxV = v;
	// }
	// }
	// if (maxProfit<0){
	// return false;
	// }else {
	// g.addVertex(toBeAdded.getBase());
	// g.addEdge(maxV,toBeAdded.getBase(),maxProfit);
	// return true;
	// }
	// }

	/**
	 * net profit
	 *
	 * @param a the new point
	 * @param b the existed specific point in the graph
	 * @return profit to the specific point
	 */
	private double profit(InputVertex a, Vertex b) {
		double distance = a.getBase().distance(b);
		double cost = (distance * priceCable) + (a.getFlow() * priceFlow); // cost in my side.

		return (a.getFlow() * a.getPrice()) - cost;

	}

	/**
	 * net profit b-> a's profit
	 *
	 * @param a the new point
	 * @param b the existed specific point in the graph
	 * @return a.profit to the specific point b
	 */
	private Double profit(InputVertex a, InputVertex b) {
		double distance = a.getBase().distance(b.getBase());
		double cost = (distance * priceCable) + (a.getFlow() * priceFlow); // cost in my side.
		return (a.getFlow() * a.getPrice()) - cost;
	}

	
//	!!!Testcode fuer: Eingabe Profit
//	public List<TestEdge> tmpList= new ArrayList<>();
//	private Double testDoubleProfit(InputVertex a, InputVertex b) {
//		for (TestEdge testEdge : tmpList) {
//			if (testEdge.A.equals(a) && testEdge.B.equals(b)) {
//				return testEdge.profit;
//			}
//		}
//		return 0.0;
//	}
	
	private BiFunction<InputVertex, InputVertex, Double> profitFunction() {
		    return (x, y) -> profit(x, y);
//!!!Testcode fuer: Eingabe Profit
//		return (x, y) -> testDoubleProfit(x, y);
	}

	private boolean dijkstra(double sumProfit) {
		// WorkingSet w
		// FinishedSet F
		List<InputVertex> f = new ArrayList<>();
		List<InputVertex> w = new ArrayList<>(g.getAllVerticesWithoutRoot());
		// for all in w:w.clearAllInformation()
		initProfit(w);		
		f.add(g.getRoot());
		while (w.size() != 0) {
			InputVertex u = extractMaxProfit(w);// add pi and profit and delete u in w
			f.add(u);
			if (g.getAdj(f, u) != null && g.getAdj(f, u).size() != 0) {
				for (InputVertex v : g.getAdj(f, u)) {
					relax(v, u, profitFunction());
				}
			}
		}
		double newSumProfit = getSumProfit();
		return newSumProfit > sumProfit;
	}

	private InputVertex extractMaxProfit(List<InputVertex> w) {
		InputVertex k = w.get(0);
		for (InputVertex v : w) {
			if (v.getCurrentProfit() > k.getCurrentProfit()) {
				k = v;
			}
		}
		w.remove(k);
		return k;
	}

	private void initProfit(List<InputVertex> w) {
		InputVertex root = g.getRoot();
		root.setCurrentProfit(0);
		root.setPi(null);
		for (InputVertex k : w) {
			k.setCurrentProfit(profitFunction().apply(k, root));
			k.setPi(root);
		}
	}

	private void relax(InputVertex u, InputVertex v, BiFunction<InputVertex, InputVertex, Double> profitFunction) {
		// adjpoints of u: v
		//profit fron u to v is bigger tahn v's current
		double profit = profitFunction.apply(v, u);

		if ((v.getCurrentProfit() < profit + u.getCurrentProfit()|| profit > v.getCurrentProfit() ) && profit > 0)
//		if (v.getCurrentProfit() < profit && profit >= 0)
		// setPrior
		{
			
			v.setPi(u);
			// setProfit
//      v.setCurrentProfit(profit + u.getCurrentProfit());
			if(u.getCurrentProfit() > 0) {
				v.setCurrentProfit(profit);
			}else {
				v.setCurrentProfit(profit + u.getCurrentProfit());
			}
			

		}

	}

	public Graph getG() {
		return g;
	}
	
	
//	!!!Testcode fuer: Eingabe Profit
//	public static void main(String[] args) {	
//		Scanner input = new Scanner(System.in);
//		boolean rootFlag=false;
//		Greedy gre = null;
//		double priceCable= 1;
//		double priceFlow= 5;
//		InputVertex root = new InputVertex(0,0,priceCable, priceFlow);
//		gre= new Greedy(root, priceCable, priceFlow);
//		//1points
//		InputVertex newNodeA = new InputVertex(1,1,5, 8);
//		InputVertex newNodeB = new InputVertex(1,2,5, 8);
//
//		InputVertex newNodeC = new InputVertex(4,5,5, 8);
//		InputVertex newNodeD = new InputVertex(2,3,5, 8);
//		InputVertex newNodeE = new InputVertex(2,4,5, 8);
//		InputVertex newNodeF = new InputVertex(2,5,5, 8);
//		
//		
////		2edge
//		gre.tmpList.add(new TestEdge(newNodeA,root,5.0));
//		gre.tmpList.add(new TestEdge(newNodeB,root,1.0));
//		gre.tmpList.add(new TestEdge(newNodeC,root,4.0));
//		gre.tmpList.add(new TestEdge(newNodeE,root,4.0));
//		gre.tmpList.add(new TestEdge(newNodeB,newNodeA,3.0));
//		gre.tmpList.add(new TestEdge(newNodeC,newNodeA,1.0));
//		gre.tmpList.add(new TestEdge(newNodeC,newNodeB,1.0));
//		gre.tmpList.add(new TestEdge(newNodeD,root,1.0));
//		gre.tmpList.add(new TestEdge(newNodeD,newNodeA,2.0));
//		gre.tmpList.add(new TestEdge(newNodeD,newNodeB,1.0));
//		gre.tmpList.add(new TestEdge(newNodeD,newNodeC,1.0));
//		gre.tmpList.add(new TestEdge(newNodeF,newNodeD,2.0));
//		
//	//	gre.tmpList.add(new TestEdge(newNodeC,newNodeA, 0.0));
//	//	gre.tmpList.add(new TestEdge(newNodeA,newNodeB,2.0));
//		
//		
//		//3add!
//		gre.dynamicAdd(newNodeA);
//		gre.dynamicAdd(newNodeB);
//		gre.dynamicAdd(newNodeC);
//		gre.dynamicAdd(newNodeD);
//		gre.dynamicAdd(newNodeE);
//		gre.dynamicAdd(newNodeF);
////		4.run
//		gre.getG().printGraph();
//		System.out.println("die gesamte Profit ist: "+ gre.getG().getSumProfit());
//	}

}
