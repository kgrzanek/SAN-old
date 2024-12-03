// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.concurrency;

public class LongCounter {

  private long value;

  LongCounter(long value) {
    this.value = value;
  }

  public synchronized long inc() {
    return ++value;
  }

  public synchronized long getValue() {
    return value;
  }
}
