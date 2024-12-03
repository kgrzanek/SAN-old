// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;

import org.junit.jupiter.api.Test;

class EqualsHashCodeTest {

  @Test
  void testPointEquals() {
    final var p1 = new Point(1, 2);
    final var p2 = new Point(3, 4);
    final var p3 = new Point(1, 2);

    assertThat(p1.equals(p3)).isTrue();
    assertThat(p1.equals(p2)).isFalse();
  }

  @Test
  void testColoredPointEquals() {
    final var p1 = new ColoredPoint(1, 2, Color.BLUE);
    final var p2 = new ColoredPoint(3, 4, Color.RED);
    final var p3 = new ColoredPoint(1, 2, Color.BLUE);

    assertThat(p1.equals(p3)).isTrue();
    assertThat(p1.equals(p2)).isFalse();
  }

  @Test
  void testMixedPointEquals() {
    final var p1 = new Point(1, 2);
    final var p2 = new ColoredPoint(1, 2, Color.GREEN);

    assertThat(p2.equals(p1)).isTrue();
    assertThat(p1.equals(p2)).isTrue();
  }
}

class Point {

  double x, y;

  Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public final int hashCode() {
    return Objects.hash(x, y);
  }

  @Override
  public final boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!(obj instanceof final Point other))
      return false;
    return Double.doubleToLongBits(x) == Double.doubleToLongBits(other.x)
        && Double.doubleToLongBits(y) == Double.doubleToLongBits(other.y);
  }

}

enum Color {
  RED, GREEN, BLUE
}

@FunctionalInterface
interface Validator<T> {
  boolean isValid(T obj);
}

abstract class AbstractValidator<T> implements Validator<T> {
  final boolean validate(T obj) {
    if (!isValid(obj))
      throw new IllegalArgumentException();
    return true;
  }
}

class EmailValidator extends AbstractValidator<String> {
  @Override
  public boolean isValid(String obj) {
    return obj.endsWith("@gmail.com");
  }
}

class ColoredPoint extends Point {

  Color color;

  ColoredPoint(double x, double y, Color color) {
    super(x, y);
    this.color = color;
  }

//  @Override
//  public int hashCode() {
//    final var prime = 31;
//    var result = super.hashCode();
//    return prime * result + Objects.hash(color);
//  }
//
//  @Override
//  public boolean equals(Object obj) {
//    if (this == obj)
//      return true;
//    if (!super.equals(obj) || !(obj instanceof final ColoredPoint other))
//      return false;
//    return color == other.color;
//  }
}