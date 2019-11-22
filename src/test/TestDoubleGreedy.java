package test;

import core.Greedy;
import dataStructure.InputVertex;
import lab.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * this is the testDouble class for the greedy.
 * the profit function is replaced by manipulated data(@see testDoubleProfit())
 */
public class TestDoubleGreedy extends Greedy {

  public TestDoubleGreedy (InputVertex root, double priceCable, double priceFlow) {
    super(root, priceCable, priceFlow);
  }

  private double x = 1;
  private double y = 1;
  private Map<String, InputVertex> nameList = new HashMap<>();
  private Map<InputVertex, String> namePrintList = new HashMap<>();

  public TestDoubleGreedy (String name) {
    this(new InputVertex(0.0, 0.0, 0, 0), 0.0, 0.0);
    nameList.put(name, this.getG().getRoot());
    namePrintList.put(this.getG().getRoot(), name);
  }
  //  public boolean dynamicAdd(InputVertex toBeAdded) {
  //  public boolean dynamicAddTestDouble(String){
  //
  //  }

  public List<TestEdge> tmpList = new ArrayList<>();

  /**
   * this profit will override the original class Greedy.profit
   * this is a's profit via node b
   *
   * @param a
   * @param b
   * @return
   */
  @Override
  protected Double profit (InputVertex a, InputVertex b) {
    for (TestEdge testEdge : tmpList) {
      //      System.out.println(a);
      if (this.namePrintList.containsKey(a) && this.namePrintList.containsKey(b) && this.namePrintList.get(a).equals(
        testEdge.a.getName()) && this.namePrintList.get(b).equals(testEdge.b.getName())) {
        return testEdge.profit;
      }
    }
    return 0.0;
  }

  /**
   * this add a test edge from B to A
   *
   * @param aName  the Node you care about
   * @param bName  via node
   * @param profit the A's profit via B( "profit(B->A)" )
   */
  public void addTestEdge (String aName, String bName, double profit) {
    this.tmpList.add(new TestEdge(new TestVertex(aName), new TestVertex(bName), profit));
  }

  public static final int SOURCE_NOT_FOUND = -1;
  public static final int DESTINATION_NOT_FOUND = -2;
  public static final int SOURCE_DESTINATION_NOT_FOUND = -3;
  public static final int NO_PATH = -4;

  /**
   * @param name
   */
  public void addTestNode (String name) {
    InputVertex newV = new InputVertex(this.x, this.y, 0.0, 0.0);
    this.x++;
    this.y++;
    this.dynamicAdd(newV);
    nameList.put(name, newV);
    this.namePrintList.put(newV, name);
  }

  public int findShortestDistance (String A, String B) {
    int sd = 0;
    if (!this.nameList.containsKey(A) && !this.nameList.containsKey(B)) {
      sd = SOURCE_DESTINATION_NOT_FOUND;
    } else if (!this.nameList.containsKey(A)) {
      sd = SOURCE_NOT_FOUND;
    } else if (!this.nameList.containsKey(B)) {
      sd = DESTINATION_NOT_FOUND;
    } else if (A.equals(B)) {
      sd = 0;
    } else {
      this.getG().setRoot(this.nameList.get(A));
      this.dijkstra(0);
      sd = 1;
      printMe();
    }
    return sd;
  }

  public void printMe () {
    this.getG().printGraph(this.namePrintList);
  }

  @Override
  protected void relax (InputVertex u, InputVertex v, BiFunction<InputVertex, InputVertex, Double> profitFunction) {
    double profit = profitFunction.apply(v, u);
    if (v.getCurrentProfit() < profit) {
      v.setPi(u);
      v.setCurrentProfit(profit);
    }
  }

}
