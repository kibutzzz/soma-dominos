package input;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Problem {
  private List<Domino> dominoes = new ArrayList<>();

  public void addDomino(Domino domino) {
    dominoes.add(domino);
  }

  public void sort() {
    dominoes.sort(Comparator.comparing(Domino::diff));
  }

  public List<Domino> getDominoes() {
    return dominoes;
  }

}
