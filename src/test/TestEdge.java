package test;

import java.util.Scanner;

import dataStructure.InputVertex;

public class TestEdge {
	public InputVertex A;
	public InputVertex B;
	public double profit;
	public TestEdge(InputVertex a, InputVertex b, double profit) {
		super();
		A = a;
		B = b;
		this.profit = profit;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((A == null) ? 0 : A.hashCode());
		result = prime * result + ((B == null) ? 0 : B.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestEdge other = (TestEdge) obj;
		if (A == null) {
			if (other.A != null)
				return false;
		} else if (!A.equals(other.A))
			return false;
		if (B == null) {
			if (other.B != null)
				return false;
		} else if (!B.equals(other.B))
			return false;
		return true;
	}

}
