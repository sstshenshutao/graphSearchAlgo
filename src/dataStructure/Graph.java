package dataStructure;

import java.util.List;
import java.util.Map;

public interface Graph {

  public InputVertex getRoot ();

  public List<InputVertex> getAllVertices ();

  public List<InputVertex> getAllVerticesWithoutRoot ();

  public List<InputVertex> getAdj (List<InputVertex> f, InputVertex u);

  public void add (InputVertex u);

  public double getSumProfit ();

  public void printGraph ();

  public void printGraph (Map<InputVertex, String> labelNodes);

  public void setRoot (InputVertex root);

}
