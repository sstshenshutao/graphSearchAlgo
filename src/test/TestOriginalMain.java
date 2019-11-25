package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TestOriginalMain extends TestMain {

  public static void main (String[] args) {
    TestMain testMain = new TestOriginalMain();
    testMain.readFiletoMap("TestFile1");
    //    testMain.testDoubleGreedy.printMe();
    System.out.println("____________________");
    testMain.testDoubleGreedy.findShortestDistance("A", "H");
  }

  public static TestOriginalMain createOne (String filename) {
    TestOriginalMain testMain = new TestOriginalMain();
    testMain.readFiletoMap(filename);
    return testMain;
  }

  public int findShortestDistance (String A, String B) {
    return this.testDoubleGreedy.findShortestDistance(A, B);
  }

  protected void readFiletoMap (String filename) {
    //this I/O Operation is referred from lab 1.
    FileReader fr;
    //		int a=0;
    try {
      fr = new FileReader(filename);
      BufferedReader in = new BufferedReader(fr);
      String line;
      List<TestMain.AudMapElement> buff = new ArrayList<>();
      while ((line = in.readLine()) != null) {
        //				System.out.println(a++);
        TestMain.AudMapElement l = parselineDigraph(line);
        buff.add(l);
      }
      //add root
      TestMain.AudMapElement root = null;
      for (TestMain.AudMapElement l : buff) {
        if (l.eleType == 1) {
          root = l;
          this.testDoubleGreedy = new TestDoubleOriginalGreedy(l.vFrom);
          break;
        }
      }
      //add profit(edges)
      for (TestMain.AudMapElement l : buff) {
        if (l.eleType == 2) {
          System.out.println("addEdge:" + l.vTo + "|" + l.vFrom + "|" + l.distance);
          this.testDoubleGreedy.addTestEdge(l.vTo, l.vFrom, l.distance);
        }
      }
      //add other nodes
      if (root != null) {
        buff.remove(root);
      } else {
        System.out.println("readFiletoMap without root");
        System.exit(1);
      }
      for (TestMain.AudMapElement l : buff) {
        if (l.eleType == 1) {
          System.out.println("addNode:" + l.vFrom);
          this.testDoubleGreedy.addTestNode(l.vFrom);
        }
      }
      in.close();
      fr.close();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
