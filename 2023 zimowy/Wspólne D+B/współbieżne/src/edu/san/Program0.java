// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san;

import java.util.ArrayList;

public class Program0 {

  static long n;

  public static void main(String[] args) {
    n = 0;
    final var threads = new ArrayList<Thread>();

    final var start = System.currentTimeMillis();
    for (var i = 0; i < 100; i++) {
      final var thread = new Thread(() -> {
        for (var j = 0; j < 10_000; j++) {
          // n++;
          final var n1 = n;
          final var n2 = n1 + 1;
          n = n2;
        }
      });

      threads.add(thread);
      thread.start();
    }

    Threads.joinAll(threads);
    System.out.println("Finished with n = " + n + " in " +
        (System.currentTimeMillis() - start) + " msecs.");

    // * RACE CONDITION(S)
  }
}
