package input;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class Problem {
  private List<Domino> dominoes = new ArrayList<>();

  public void addDomino(Domino domino) {
    dominoes.add(domino);
  }

  public void sort() {
    dominoes.sort(Comparator.comparing(Domino::diff));
  }

  public Integer sumAllA() {
    return dominoes.stream().mapToInt(Domino::getA).sum();
  }

  public Integer sumAllB() {
    return dominoes.stream().mapToInt(Domino::getB).sum();
  }

  public List<Domino> getDominoes() {
    return dominoes;
  }

  public void flipNearest(int difference) {
    final var selectedDomino = dominoes.stream().reduce((domino, domino2) -> {
      if (domino.diff() - difference < domino2.diff() - difference) {
        return domino;
      }
      return domino2;
    }).orElseThrow();

    selectedDomino.flip();
  }
}
