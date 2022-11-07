package output;

import input.Domino;

import java.util.List;

import static java.util.Objects.isNull;

public class ResultPrinter {

  public void success(List<Domino> solution, Domino removed) {
    final var total = solution.stream().mapToInt(Domino::getA).sum();
    if(isNull(removed)) {
      System.out.printf("%d nenhum dominó descartado%n", total);
      return;
    }

    System.out.printf("%d decartado o dominó %s%n", total, removed);

  }
  public void fail() {
    System.out.println("impossível%n");
  }
}
