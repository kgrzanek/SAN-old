// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Program2 {

  static ExecutorService threadPool = Executors.newFixedThreadPool(10);

  public static void main(String[] args)
      throws InterruptedException, ExecutionException {

    Runtime.getRuntime().addShutdownHook(new Thread(threadPool::close));

    System.out.println(
        "main() jest wykonywane w kontekście " + Thread.currentThread());

    threadPool.execute(() -> {
      System.out.println("Wewnątrz Runnable wykonywanego w kontekście "
          + Thread.currentThread());
    });

    final var future1 = threadPool.submit(() -> {
      System.out.println("Wewnątrz Callable wykonywanego w kontekście "
          + Thread.currentThread());
      return 4;
    });

    System.out.println("wartość future1 jest równa " + future1.get());

    threadPool.close();

  }

}
