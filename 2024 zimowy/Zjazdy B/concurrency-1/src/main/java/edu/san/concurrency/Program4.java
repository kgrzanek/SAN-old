// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.concurrency;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Program4 {

  static final Semaphore s1 = new Semaphore(1, true);

  static final Lock l1 = new ReentrantLock(true);

  public static void main(String[] args) {
    System.out.println(Thread.currentThread());

    // deadlock1();
    l1.lock();
    l1.lock();
    l1.lock();

    l1.unlock();

    System.out.println("Gotowe");

  }

}
