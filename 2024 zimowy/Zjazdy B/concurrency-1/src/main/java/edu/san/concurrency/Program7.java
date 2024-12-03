// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.concurrency;

import edu.san.concurrency.utils.Threads;

public class Program7 {

  static final LongCounter counter = new LongCounter(0L);

  public static void main(String... args) {
    System.out.println("Before: " + counter.getValue());
    runExperiment();
    System.out.println("After: " + counter.getValue());
  }

  static void runExperiment() {
    final var t1 = new Thread(() -> {
      for (var i = 0; i < 10_000_000; i++) {
        incrementCounter();
      }
      System.out.println("after t1.for: " + counter.getValue());
    });

    final var t2 = new Thread(() -> {
      for (var i = 0; i < 10_000_000; i++) {
        incrementCounter();
      }
      System.out.println("after t2.for: " + counter.getValue());
    });

    t1.start();
    t2.start();

    Threads.exec(t1::join);
    Threads.exec(t2::join);

  }

  private static void incrementCounter() {
    counter.inc();
  }

}
