package dataStructure;

public class InputVertex {

  private Vertex base;
  private double price;
  private double flow;
  // tmp information for greedy
  private VertexGreedyInformation vertexGreedyInformation;

  public InputVertex (Vertex base, double price, double flow) {
    this.base = base;
    this.price = price;
    this.flow = flow;
    this.init();
  }

  private void init () {
    vertexGreedyInformation = new VertexGreedyInformation();
  }

  public InputVertex (double x, double y, double expectPrice, double expectFlow) {
    this(new Vertex(x, y), expectPrice, expectFlow);
  }

  public InputVertex getPi () {
    return this.vertexGreedyInformation.getPi();
  }

  public void setPi (InputVertex pi) {
    this.vertexGreedyInformation.setPi(pi);
  }

  public double getCurrentProfit () {
    return this.vertexGreedyInformation.getCurrentProfit();
  }

  public void setCurrentProfit (double currentProfit) {
    this.vertexGreedyInformation.setCurrentProfit(currentProfit);
  }

  public Vertex getBase () {
    return base;
  }

  public void setBase (Vertex base) {
    this.base = base;
  }

  public double getPrice () {
    return price;
  }

  public void setPrice (double price) {
    this.price = price;
  }

  public double getFlow () {
    return flow;
  }

  public void setFlow (double flow) {
    this.flow = flow;
  }

  @Override
  public boolean equals (Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InputVertex that = (InputVertex) o;
    return base.equals(that.base);
  }

  @Override
  public int hashCode () {
    return base.hashCode();
  }

  @Override
  public String toString () {
    InputVertex pi =this.vertexGreedyInformation.getPi();
    return "{" + this.printXY() + "[price=" + price + ", flow=" + flow + "], pi=" + (pi==null?"null":printXY())
    + ", currentProfit=" + this.vertexGreedyInformation.getCurrentProfit() + '}';
  }

  public String printXY () {
    return "(" + this.getBase().getX() + "," + this.getBase().getY() + ")";
  }

}
