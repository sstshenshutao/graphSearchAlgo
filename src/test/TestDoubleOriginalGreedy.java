package test;

import dataStructure.InputVertex;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class TestDoubleOriginalGreedy extends TestDoubleGreedy {

  public TestDoubleOriginalGreedy (InputVertex root, double priceCable, double priceFlow) {
    super(root, priceCable, priceFlow);
  }

  public TestDoubleOriginalGreedy (String name) {
    super(name);
  }

  /**
   * change the max to min, so that the original dijkstra can run
   *
   * @param w
   * @return
   */
  @Override
  protected InputVertex extractMaxProfit (List<InputVertex> w) {
    InputVertex k = w.get(0);
    for (InputVertex v : w) {
      if (v.getCurrentProfit() < k.getCurrentProfit()) {
        k = v;
      }
    }
    w.remove(k);
    return k;
  }

  /**
   * change it to the original dijkstra
   * @param v
   * @param u
   * @param profitFunction
   */
  @Override
  protected void relax (InputVertex v, InputVertex u, BiFunction<InputVertex, InputVertex, Double> profitFunction) {
    double profit = profitFunction.apply(v, u);//first error
    if (v.getCurrentProfit() > profit+u.getCurrentProfit()) {
      v.setPi(u);
      v.setCurrentProfit(profit+u.getCurrentProfit());
    }
  }

  @Override
  protected boolean dijkstra (double sumProfit) {
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
      System.out.println("1111"+getAdjNode(f, u));
      if (getAdjNode(f, u) != null && getAdjNode(f, u).size() != 0) {
        for (InputVertex v : getAdjNode(f, u)) {
          relax(v, u, profitFunction());
        }
      }
    }
    double newSumProfit = getSumProfit();
    return newSumProfit > sumProfit;
  }

  private List<InputVertex> getAdjNode (List<InputVertex> f, InputVertex u) {
    List<InputVertex> uAllAdj = new ArrayList<>();
    for (TestEdge e : tmpList) {
      if (nameList.get(e.b.getName()).equals(u)) {
        uAllAdj.add(nameList.get(e.a.getName()));
      }
    }
    uAllAdj.removeAll(f);
    System.out.println(uAllAdj);
    return uAllAdj;
  }

  /**
   * don't call dynamic add, but call add node to graph
   *
   * @param name
   */
  public void addTestNode (String name) {
    InputVertex newV = new InputVertex(this.x, this.y, 0.0, 0.0);
    System.out.println("now add:" + name);
    this.x++;
    this.y++;
    this.namePrintList.put(newV, name);
    this.g.add(newV);
    this.tmpList.forEach(x -> System.out.println(x));
    this.printMe();
    nameList.put(name, newV);
  }
  @Override
  protected Double profit (InputVertex a, InputVertex b) {
    for (TestEdge testEdge : tmpList) {
      //      System.out.println(a);
      if (this.namePrintList.containsKey(a) && this.namePrintList.containsKey(b) && this.namePrintList.get(a).equals(
        testEdge.a.getName()) && this.namePrintList.get(b).equals(testEdge.b.getName())) {
        return testEdge.profit;
      }
    }
    return Double.MAX_VALUE;
  }
  public static void main (String[] args) {
  }

}
