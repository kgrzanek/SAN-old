// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.concurrency;

import java.util.concurrent.atomic.AtomicLong;

import edu.san.concurrency.utils.Threads;

class Program3 {

  static final AtomicLong counter = new AtomicLong(0L);

  public static void main(String... args) {
    System.out.println("Before: " + counter.get());
    runExperiment();
    System.out.println("After: " + counter.get());
  }

  static void runExperiment() {
    final var t1 = new Thread(() -> {
      for (var i = 0; i < 10_000_000; i++) {
        incrementCounter();
      }
      System.out.println("after t1.for: " + counter.get());
    });

    final var t2 = new Thread(() -> {
      for (var i = 0; i < 10_000_000; i++) {
        incrementCounter();
      }
      System.out.println("after t2.for: " + counter.get());
    });

    t1.start();
    t2.start();

    Threads.exec(t1::join);
    Threads.exec(t2::join);

  }

  private static void incrementCounter() {
    counter.incrementAndGet();
  }
}