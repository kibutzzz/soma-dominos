package bootstrap;

import input.Problem;
import input.Domino;
import input.DominoReader;
import output.ResultPrinter;

import java.util.ArrayList;
import java.util.function.Consumer;


public class Bootstrap {

  public static void main(String[] args) {
    final var reader = new DominoReader("../in3");
    final var printer = new ResultPrinter();

    final var cases = reader.readProblems();
//    cases.forEach(Problem::sort);

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
          .ifPresentOrElse(dominoTree -> {
            System.out.printf("%d nenhum dominó descartado ", dominoTree.sumTotal());
          }, () -> {
            System.out.println("impossivel");
          });


    };
  }

  private static void option2Failed(Problem problem, ResultPrinter printer) {
    final var availableDominoes = problem.getDominoes();

    final var solution = new ArrayList<Domino>();
    var sumSideA = 0;
    var sumSideB = 0;
    for (int i = 0; i < availableDominoes.size(); i++) {
      final var domino = availableDominoes.get(i);

      final var dominoSideABigger = domino.diff() >= 0;
      if (dominoSideABigger) {
        if (sumSideA > sumSideB) {
          domino.flip();
        }
      } else {
        if (sumSideA <= sumSideB) {
          domino.flip();
        }
      }
      solution.add(domino);

      sumSideA += domino.getA();
      sumSideB += domino.getB();

    }
    final var difference = sumSideA - sumSideB;
    if (difference != 0) {
      solution.stream().filter(domino -> domino.diff() == difference / 2)
          .findAny().ifPresent(Domino::flip);
    }
    printer.success(solution, null);
  }

  private static void option1Failed(Problem problem) {
    var isDifferent = false;
    do {
      final var sumA = problem.sumAllA();
      final var sumB = problem.sumAllB();

      final var difference = sumA - sumB;
      if (difference != 0) {
        isDifferent = true;
        problem.flipNearest(difference);
      } else {
        isDifferent = false;
      }

      System.out.printf("%d - %d %n", sumA, sumB);
    } while (isDifferent);
  }
}
