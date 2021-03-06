package test;

public class TestVertex {

  private String name;

  public String getName () {
    return name;
  }

  public TestVertex (String name) {
    this.name = name;
  }

  @Override
  public boolean equals (Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TestVertex that = (TestVertex) o;
    return name != null
           ? name.equals(that.name)
           : that.name == null;
  }

  @Override
  public int hashCode () {
    return name != null
           ? name.hashCode()
           : 0;
  }

  @Override
  public String toString () {
    return "TestVertex{" + name + '}';
  }

}
