package dataStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdjazenzListGraph implements Graph {

  private InputVertex root;
  private List<InputVertex> allVertices;

  public AdjazenzListGraph (InputVertex root) {
    this.root = root;
    allVertices = new ArrayList<>();
  }

  public InputVertex getRoot () {
    return this.root;
  }

  public void setRoot (InputVertex root) {
    if (!root.equals(this.root)) {
      this.allVertices.remove(root);
      this.allVertices.add(this.root);
      this.root = root;
    }
  }

  @Override
  public List<InputVertex> getAllVertices () {
    List<InputVertex> tmp = new ArrayList<>(getAllVerticesWithoutRoot());
    tmp.add(root);
    return tmp;
  }

  public List<InputVertex> getAllVerticesWithoutRoot () {
    return new ArrayList<>(allVertices);
  }

  @Override
  public List<InputVertex> getAdj (List<InputVertex> f, InputVertex u) {
    List<InputVertex> tmp = new ArrayList<>(getAllVertices());
    tmp.remove(u);
    for (InputVertex v : f) {
      tmp.remove(v);
    }
    return tmp;
  }

  @Override
  public void add (InputVertex u) {
    this.allVertices.add(u);
  }

  @Override
  public double getSumProfit () {
    double sum = 0.0;
    for (InputVertex v : this.allVertices) {
      if (v.getCurrentProfit() > 0) {
        sum += v.getCurrentProfit();
      } else {
        sum += 0.0;
      }
      //    	sum += v.getCurrentProfit();
    }
    return sum;
  }

  public void printGraph () {
    visualGraph().forEach(x -> System.out.println(x));
  }

  public void printGraph (Map<InputVertex, String> labelNodes) {
    visualGraph(labelNodes).forEach(x -> System.out.println(x));
  }

  public ArrayList<String> visualGraph () {
    return visualGraph(null);
  }

  public ArrayList<String> visualGraph (Map<InputVertex, String> labelNodes) {
    ArrayList<String> ret = new ArrayList<>();
    ret.add("Digraph{");
    //print all nodes:
    if (labelNodes == null) {
      int nodei = 0;
      labelNodes = new HashMap<>();
      for (InputVertex v : this.getAllVertices()) {
        labelNodes.put(v, "node" + nodei);
        nodei++;
      }
    }
    for (InputVertex v : this.getAllVertices()) {
      StringBuffer line = new StringBuffer();
      line.append(labelNodes.get(v)).append(" [label=\"").append(v.printXY()).append(",").append(v.getCurrentProfit())
          .append("\"];");
      ret.add(line.toString());
    }
    //print edge:
    for (InputVertex v : this.getAllVertices()) {
      InputVertex pi = v.getPi();
      if (pi != null) {
        StringBuffer line = new StringBuffer();
        //        pi->v
        line.append(labelNodes.get(pi)).append(" -> ").append(labelNodes.get(v)).append(" [label=\"")
            .append("p:" + v.getCurrentProfit()).append(",").append("\"]");
        line.append("[style=bold]");
        line.append(";");
        ret.add(line.toString());
      }
    }
    ret.add("}");
    return ret;
  }
  //
  //  public static void main (String[] args) {
  //    InputVertex root = new InputVertex(new Vertex(1, 2), 3, 5);
  //    InputVertex A = new InputVertex(new Vertex(2, 3), 4, 5);
  //    InputVertex B = new InputVertex(new Vertex(4, 4), 5, 5);
  //    InputVertex C = new InputVertex(new Vertex(6, 5), 6, 5);
  //    InputVertex D = new InputVertex(new Vertex(7, 6), 7, 5);
  //    InputVertex E = new InputVertex(new Vertex(9, 7), 8, 5);
  //    AdjazenzListGraph g = new AdjazenzListGraph(root);
  //    root.setPi(null);
  //    g.add(A);
  //    A.setPi(root);
  //    g.add(B);
  //    B.setPi(root);
  //    g.add(C);
  //    C.setPi(A);
  //    g.add(D);
  //    D.setPi(A);
  //    g.add(E);
  //    E.setPi(D);
  //    g.printGraph();
  //    g.getAllVertices().forEach(x-> System.out.println(x));
  //    g.getAllVerticesWithoutRoot().forEach(x-> System.out.println(x));
  //    g.getAdj(C).forEach(x-> System.out.println(x));
  // }
}
