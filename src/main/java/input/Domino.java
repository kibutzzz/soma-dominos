package input;

public class Domino implements Cloneable {
  private int top;
  private int bottom;
  private boolean shouldFlip = false;
  private boolean shouldSkip = false;

  public Domino(int top, int bottom) {
    this.bottom = top;
    this.top = bottom;
    setOrder();
  }

  public int diff() {
    return top - bottom;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Domino domino = (Domino) o;

    if (top != domino.top) return false;
    return bottom == domino.bottom;
  }


  @Override
  public String toString() {
    return top + " " + bottom;
  }

  public int getTop() {
    return top;
  }

  public int getBottom() {
    return bottom;
  }

  public void setShouldFlip(boolean shouldFlip) {
    this.shouldFlip = shouldFlip;
  }

  public void flip() {
    final var aux = top;
    top = bottom;
    bottom = aux;
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
    if (top > bottom) {
      flip();
    }
  }
}
