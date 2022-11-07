package bootstrap.alternative;

import input.DominoReader;
import input.Problem;

import java.util.ArrayList;
import java.util.function.Consumer;


public class AlternativeSolution {

  public static void main(String[] args) {
    final var reader = new DominoReader("../in3");

    final var cases = reader.readProblems();

    cases.forEach(treeSolution());

  }

  private static Consumer<Problem> treeSolution() {
    return problem -> {
      final var dominoes = problem.getDominoes();
      final var root = new DominoTree(dominoes.get(0));
      try {
        for (int i = 1; i < dominoes.size(); i++) {
          root.addNext(dominoes.get(i));
        }
      } catch (CloneNotSupportedException e) {
        throw new RuntimeException(e);
      }

      final var leaves = new ArrayList<DominoTree>();
      root.getLeaves(leaves);
      leaves.stream()
          .filter(DominoTree::isEvenlyDistributed)
          .findAny()
          .ifPresentOrElse(
              dominoTree -> System.out.printf("%d nenhum dominó descartado ", dominoTree.sumTotal()),
              () -> System.out.println("impossivel"));


    };
  }

}
