package bootstrap;

import input.Domino;
import input.DominoReader;
import input.Problem;

import java.util.List;

public class Solution {

  public static void main(String[] args) {
    //Lê todos os arquivos e resolve caso a caso
    List.of("../in", "../in1", "../in2", "../in3")
        .stream()
        .map(DominoReader::new)
        .forEach(reader -> {
          final var problems = reader.readProblems();
          problems.forEach(Solution::solve);
        });

  }

  private static void solve(Problem problem) {
    // ordena a lista de dominos lidos de acordo com a diferenca
    // entre o valor de cima e de baixo de cada domino
    problem.sort();
    final var dominoes = problem.getDominoes();

    //calcula a diferenca total entre cada os dominos de cima e de baixo para os calculos
    var totalDifference = sumAllDiff(dominoes, null);
    var result = new Result();
    //tenta resolver sem remover nenhum domino
    result = tryToSolve(dominoes, totalDifference, null, result);
    var outputString = "";
    if (result.isSolved()) {
      outputString = String.format("%d nenhum dominó descartado", result.getTotalSum());
    } else {
      //tenta resolver removendo dominos
      for (final var excluded : dominoes) {
        resetAllDominoes(dominoes);

        //calcula a diferenca dos dominos que não estão excluidos
        totalDifference = sumAllDiff(dominoes, excluded);

        //tenta resolver. Se resolver
        var newResult = tryToSolve(dominoes, totalDifference, excluded, result);
        if (newResult.isSolved()) {
          //se o novo resultado for melhor, substitui
          result = newResult.getTotalSum() > result.getTotalSum() ? newResult : result;
          outputString = String.format("%d descartado o dominó %s", result.getTotalSum(), result.getRemoved().toString());
        }
      }


    }
    if (!result.isSolved()) {
      outputString = "impossivel";
    }

    System.out.println(outputString);
  }

  private static int sumAllDiff(List<Domino> dominoes, Domino excluded) {
    return dominoes.stream()
        .filter(domino -> !domino.equals(excluded))
        .map(domino -> -domino.diff()).reduce(0, Integer::sum);
  }

  private static int sumAllExcept(List<Domino> dominoes, Domino excluded) {
    return dominoes.stream().filter(domino -> !domino.equals(excluded)).mapToInt(Domino::getBottom).sum();
  }

  private static void flipMarked(List<Domino> dominoes, Domino excluded) {
    dominoes.stream()
        .filter(Domino::shouldFlip)
        .filter(domino -> !domino.equals(excluded))
        .forEach(Domino::flip);
  }

  private static Result tryToSolve(List<Domino> dominoes, int totalDiff, Domino excluded, Result result) {


    //para cada domino: Tenta resolver sem poder girar tal domino.
    //isso serve para casos em que girar um a um não resolve o problema.
    for (int i = -1; i < dominoes.size(); i++) {
      resetAllDominoes(dominoes);
      //resolve uma vez sem pular
      if (i >= 0) {
        dominoes.get(i).setShouldSkip(true);
      }

      //soma dos valores que foram girados
      var flippedDiff = 0;
      // tenta resolver
      for (final var domino : dominoes) {
        if (domino.equals(excluded) || domino.shouldSkip()) {
          continue;
        }
        // testa se girar o domino atual ultrapassa o valor necessario
        // para resolver o problema
        if (-domino.diff() * 2 + flippedDiff <= totalDiff) {
          domino.setShouldFlip(true);
          flippedDiff -= domino.diff() * 2;
          //se chegou a um resultado, gira os marcados e calcula a soma total
          if (flippedDiff == totalDiff) {
            flipMarked(dominoes, excluded);
            final var sum = sumAllExcept(dominoes, excluded);
            // se o resultado calculado atual é maior q o anterior, substitui
            if (sum > result.getTotalSum()) {
              result = new Result(excluded, sum);
            }
            break;
          }
        }
      }
      if (i >= 0) {
        dominoes.get(i).setShouldSkip(false);
      }
    }
    return result;
  }

  private static void resetAllDominoes(List<Domino> dominoes) {
    dominoes.forEach(domino -> {
      domino.setOrder();
      domino.setShouldFlip(false);
    });
  }


}
