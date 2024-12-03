package san.concurrent;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Stoppable implements Runnable {

  private final AtomicBoolean stopped = new AtomicBoolean(false);

  public void stop() {
    this.stopped.set(true);
  }

  public boolean isStopped() {
    return this.stopped.get();
  }

}
