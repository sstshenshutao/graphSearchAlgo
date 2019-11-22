package dataStructure;

public class Vertex {
  private double x;
  private double y;

  public Vertex (double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX () {
    return x;
  }

  public void setX (double x) {
    this.x = x;
  }

  public double getY () {
    return y;
  }

  public void setY (double y) {
    this.y = y;
  }

  public double distance (Vertex another){
    return Math.sqrt(Math.pow((another.getX()-this.x),2)+Math.pow((another.getY()-this.y),2));
  }

  @Override
  public boolean equals (Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Vertex vertex = (Vertex) o;
    if (Double.compare(vertex.x, x) != 0) {
      return false;
    }
    return Double.compare(vertex.y, y) == 0;
  }

  @Override
  public int hashCode () {
    int result;
    long temp;
    temp = Double.doubleToLongBits(x);
    result = (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(y);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  @Override
  public String toString () {
    return "Vertex{" + "x=" + x + ", y=" + y + '}';
  }

  public static void main (String[] args) {
    Vertex a= new Vertex(1,1);
    Vertex b= new Vertex(1,1);
    System.out.println(a.equals(b));
  }
}
