// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.patterns.factories;

import org.junit.jupiter.api.Test;

class Point2DTest {

  @Test
  void testOf() {
    var p1 = Point2D.of(3, 4);
    System.out.println(p1);
  }

  @Test
  void testOfPolar() {
    var p2 = Point2D.ofPolar(16, Math.PI / 4);
    System.out.println(p2);
  }

}
