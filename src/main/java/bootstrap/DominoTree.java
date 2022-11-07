package bootstrap;

import input.Domino;

import java.util.List;

public class DominoTree {

  private static int level = 0;

  private DominoTree positive;
  private DominoTree negative;
  private DominoTree parent;
  private Domino current;

  private int currentDifference;

  public DominoTree(Domino current) {
    this.current = current;
    currentDifference = current.diff();
  }

  private DominoTree(Domino current, DominoTree parent) {
    this(current);
    this.setParent(parent);
    currentDifference = current.diff() + parent.currentDifference;
  }

  public void addNext(Domino next) throws CloneNotSupportedException {
    if (positive != null || negative != null) {
      negative.addNext(next);
      positive.addNext(next);
    } else {
      positive = new DominoTree(next, this);
      negative = new DominoTree(getFlippedClone(next), this);
    }



  }

  private Domino getFlippedClone(Domino next) throws CloneNotSupportedException {
    final var clone = (Domino) next.clone();
    clone.flip();
    return clone;
  }

  private void setParent(DominoTree parent) {
    this.parent = parent;
  }

  public void getLeaves(List<DominoTree> leaves) {
    if (positive != null || negative != null) {
      positive.getLeaves(leaves);
      negative.getLeaves(leaves);
    } else {
      leaves.add(this);
    }
  }

  public int sumTotal() {
    if(parent == null) {
      return current.getA();
    }
    return current.getA() + parent.sumTotal();
  }


  public boolean isEvenlyDistributed() {
    return currentDifference == 0;
  }
}
