package dataStructure;

public class VertexGreedyInformation {
  private InputVertex pi; // prior
  private double currentProfit;//

  public VertexGreedyInformation () {
    this.pi = null;
    this.currentProfit=0.0;
  }

  public InputVertex getPi () {
    return pi;
  }

  public void setPi (InputVertex pi) {
    this.pi = pi;
  }

  public double getCurrentProfit () {
    return currentProfit;
  }

  public void setCurrentProfit (double currentProfit) {
    this.currentProfit = currentProfit;
  }


}
