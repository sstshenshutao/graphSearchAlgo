package dataStructure;

public class InputVertex {
	private Vertex base;
	private double price;
	private double flow;
	// tmp information for greedy
	private InputVertex pi; // prior
	private double currentProfit;//

	public InputVertex(Vertex base, double price, double flow) {
		this.base = base;
		this.price = price;
		this.flow = flow;
	}

	public InputVertex(double x, double y, double expectPrice, double expectFlow) {
		this(new Vertex(x, y), expectPrice, expectFlow);
	}

	public InputVertex getPi() {
		return pi;
	}

	public void setPi(InputVertex pi) {
		this.pi = pi;
	}

	public double getCurrentProfit() {
		return currentProfit;
	}

	public void setCurrentProfit(double currentProfit) {
		this.currentProfit = currentProfit;
	}

	public Vertex getBase() {
		return base;
	}

	public void setBase(Vertex base) {
		this.base = base;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getFlow() {
		return flow;
	}

	public void setFlow(double flow) {
		this.flow = flow;
	}

	@Override
	public boolean equals(Object o) {
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
	public int hashCode() {
		return base.hashCode();
	}

	@Override
	public String toString() {
		return "InputVertex{" + "base=" + base + ", price=" + price + ", flow=" + flow + ", pi=" + pi
				+ ", currentProfit=" + currentProfit + '}';
	}
	public String printXY() {
		return "("+this.getBase().getX()+","+this.getBase().getY()+")";
	}
}
