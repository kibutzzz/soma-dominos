package input;

public class Domino implements Cloneable {
  private int a;
  private int b;
  private boolean shouldFlip = false;
  private boolean isExcluded = false;
  private boolean shouldSkip = false;

  public Domino(int a, int b) {
    this.b = a;
    this.a = b;
    setOrder();
  }

  public int diff() {
    return a - b;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Domino domino = (Domino) o;

    if (a != domino.a) return false;
    return b == domino.b;
  }

  @Override
  public int hashCode() {
    int result = a;
    result = 31 * result + b;
    return result;
  }

  @Override
  public String toString() {
    return a + " " + b + "{" + diff() + "}";
  }

  public int getA() {
    return a;
  }

  public int getB() {
    return b;
  }

  public void setShouldFlip(boolean shouldFlip) {
    this.shouldFlip = shouldFlip;
  }

  public void setExcluded(boolean excluded) {
    isExcluded = excluded;
  }

  public void flip() {
    final var aux = a;
    a = b;
    b = aux;
  }

  public boolean isExcluded() {
    return isExcluded;
  }

  public boolean shouldSkip() {
    return shouldSkip;
  }

  public void setShouldSkip(boolean shouldSkip) {
    this.shouldSkip = shouldSkip;
  }

  public boolean shouldFlip() {
    return shouldFlip;
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

  public void setOrder() {
    if (a > b) {
      flip();
    }
  }
}
