package bootstrap;

import input.Domino;

public class Result {
  private Domino removed;
  private int totalSum;
  private boolean solved;

  public Domino getRemoved() {
    return removed;
  }

  public int getTotalSum() {
    return totalSum;
  }

  public boolean isSolved() {
    return solved;
  }


  public Result() {
    this(null, -1, false);
  }

  public Result(int totalSum) {
    this(null, totalSum);
  }

  public Result(Domino removed, int totalSum) {
    this(removed, totalSum, true);
  }

  public Result(Domino removed, int totalSum, boolean solved) {
    this.removed = removed;
    this.totalSum = totalSum;
    this.solved = solved;
  }
}