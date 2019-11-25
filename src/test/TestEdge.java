package test;

import java.util.Scanner;

import dataStructure.InputVertex;

public class TestEdge {
	public TestVertex a;
	public TestVertex b;
	public double profit;
	public TestEdge (TestVertex a, TestVertex b, double profit) {
		this.a = a;
		this.b = b;
		this.profit = profit;
	}

	@Override
	public boolean equals (Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		TestEdge testEdge = (TestEdge) o;
		if (Double.compare(testEdge.profit, profit) != 0) {
			return false;
		}
		if (a != null
				? !a.equals(testEdge.a)
				: testEdge.a != null) {
			return false;
		}
		return b != null
					 ? b.equals(testEdge.b)
					 : testEdge.b == null;
	}

	@Override
	public int hashCode () {
		int result;
		long temp;
		result = a != null
						 ? a.hashCode()
						 : 0;
		result = 31 * result + (b != null
														? b.hashCode()
														: 0);
		temp = Double.doubleToLongBits(profit);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public String toString () {
		return "TestEdge{" + "a=" + a + ", b=" + b + ", profit=" + profit + '}';
	}

}
