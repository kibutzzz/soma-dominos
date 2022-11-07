package input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DominoReader {

  private InputStreamReader inputStreamReader;

  public DominoReader(String fileName) {
    inputStreamReader = new InputStreamReader(DominoReader.class.getResourceAsStream(fileName));
  }

  public List<Problem> readProblems() {

    final var cases = new ArrayList<Problem>();

    try (BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
      final var dominoesCount = Integer.parseInt(bufferedReader.readLine());

      var nextLine = "";
      do {
        final var problem = new Problem();

        for (int i = 0; i < (nextLine.isEmpty() ? dominoesCount : Integer.parseInt(nextLine)); i++) {
          final var line = bufferedReader.readLine().split(" ");

          final var a = Integer.parseInt(line[0]);
          final var b = Integer.parseInt(line[1]);

          problem.addDomino(new Domino(a, b));
        }
        cases.add(problem);
        nextLine = bufferedReader.readLine();
      } while (!"0".equals(nextLine));
    } catch (IOException e) {
      throw new RuntimeException("error reading file", e);
    }

    return cases;
  }
}
