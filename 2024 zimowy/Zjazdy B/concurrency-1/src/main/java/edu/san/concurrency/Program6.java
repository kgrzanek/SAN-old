// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.concurrency;

import edu.san.concurrency.utils.Threads;

public class Program6 {

  static long counter = 0;

  public static void main(String... args) {
    System.out.println("Before: " + counter);
    runExperiment();
    System.out.println("After: " + counter);
  }

  static void runExperiment() {
    final var start = System.currentTimeMillis();

    final var t1 = new Thread(() -> {
      for (var i = 0; i < 10_000_000; i++) {
        synchronized (Program6.class) {
          counter++;
        }
      }
      System.out.println("after t1.for: " + counter);
    });

    final var t2 = new Thread(() -> {
      for (var i = 0; i < 10_000_000; i++) {
        synchronized (Program6.class) {
          counter++;
        }
      }
      System.out.println("after t2.for: " + counter);
    });

    t1.start();
    t2.start();

    Threads.exec(t1::join);
    Threads.exec(t2::join);

    final var end = System.currentTimeMillis();
    System.out.println("It took " + (end - start) + " msecs");
  }

}
