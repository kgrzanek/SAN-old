// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.concurrency.utils;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

public final class Threads {

  @FunctionalInterface
  public interface ThrowingRunnable {

    void run() throws InterruptedException;

  }

  public static void exec(ThrowingRunnable body) {
    try {
      body.run();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    }
  }

  public static void execAcquiring(Semaphore s, Runnable body) {
    boolean isAcquired = false;
    try {
      s.acquire();
      isAcquired = true;
      body.run();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    } finally {
      if (isAcquired) {
        s.release();
      }
    }
  }

  public static void execLocking(Lock lock, Runnable body) {
    boolean isLocked = false;
    try {
      lock.lock();
      isLocked = true;
      body.run();
    }
    finally {
      if (isLocked) {
        lock.unlock();
      }
    }

  }

  private Threads() {}

}
