package test;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TestMain {

  protected TestDoubleGreedy testDoubleGreedy;

  public static void main (String[] args) {
    TestMain testMain= new TestMain();
    testMain.readFiletoMap("TestFile1");
    testMain.testDoubleGreedy.printMe();
    System.out.println("____________________");
    testMain.testDoubleGreedy.findShortestDistance("A","H");
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
      TestMain.AudMapElement root=null;
      for (TestMain.AudMapElement l : buff) {
        if (l.eleType == 1) {
          root=l;
          this.testDoubleGreedy = new TestDoubleGreedy(l.vFrom);
          break;
        }
      }
      //add profit(edges)
      for (TestMain.AudMapElement l : buff) {
        if (l.eleType == 2) {
          System.out.println("addEdge:"+l.vTo+"|"+l.vFrom+"|"+l.distance);
          this.testDoubleGreedy.addTestEdge(l.vTo,l.vFrom,l.distance);
        }
      }
      //add other nodes
      if (root!=null){
        buff.remove(root);
      }else {
        System.out.println("readFiletoMap without root");
        System.exit(1);
      }

      for (TestMain.AudMapElement l : buff) {
        if (l.eleType == 1) {
          System.out.println("addNode:"+l.vFrom);
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

  protected class AudMapElement {

    int eleType; //0:nothing, 1:v , 2:e
    Double distance; //see the distance as profit
    Double waitTime;
    Double speed;
    String vFrom;
    String vTo;

    public AudMapElement (int eleType) {
      this.eleType = eleType;
    }

  }

  protected TestMain.AudMapElement parselineDigraph (String line) {
    int eleType = (line.contains("->"))
                  ? 2
                  : (line.contains(";"))
                    ? 1
                    : 0;
    TestMain.AudMapElement ret = null;
    if (eleType == 2) {
      int index = line.indexOf('-');
      //			System.out.println("->index:"+index);
      String firstV = line.substring(0, index).trim();
      //			System.out.println("firstV:"+firstV);
      int index2 = line.indexOf('[');
      String secondV = line.substring(index + 2, index2).trim();
      //			System.out.println("decondV:"+secondV);
      index = line.indexOf('"');
      index2 = line.indexOf('"', index + 1);
      String[] ds = line.substring(index + 1, index2).trim().split(",");
      Double distance = Double.parseDouble(ds[0].trim());
      Double speed = Double.parseDouble(ds[1].trim());
      ret = new TestMain.AudMapElement(eleType);
      ret.distance = distance;
      ret.speed = speed;
      ret.vFrom = firstV;
      ret.vTo = secondV;
    } else if (eleType == 1) {
      int index = line.indexOf('[');
      String firstV = line.substring(0, index).trim();
      index = line.indexOf('"');
      int index2 = line.indexOf('"', index + 1);
      String[] vw = line.substring(index + 1, index2).trim().split(",");
      Double waitTime = Double.parseDouble(vw[1].trim());
      ret = new TestMain.AudMapElement(eleType);
      ret.vFrom = firstV;
      ret.waitTime = waitTime;
    } else {
      ret = new TestMain.AudMapElement(eleType);
    }
    return ret;
  }

}
