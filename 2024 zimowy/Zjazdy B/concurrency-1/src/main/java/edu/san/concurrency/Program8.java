// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.concurrency;

public class Program8 {

  public static void main(String[] args) {
    synchronized (Program8.class) {
      System.out.println("First take");
      synchronized (Program8.class) {
        System.out.println("Second take");
      }
    }

    System.out.println("Finished");
  }

}
