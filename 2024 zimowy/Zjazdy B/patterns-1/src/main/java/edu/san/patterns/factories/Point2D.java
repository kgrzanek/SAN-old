// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.patterns.factories;

import java.util.Objects;

public final class Point2D {

  // FACTORY METHOD (CONSTRUCTOR METHOD)

  public static Point2D of(double x, double y) {
//    x = Math.abs(x);
//    y = Math.abs(y);
    return new Point2D(x, y);
  }

  public static Point2D ofPolar(double r, double alpha) {
    return of(r * Math.cos(alpha), r * Math.sin(alpha));
  }

  private final double x;

  private final double y;

  private Point2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!(obj instanceof Point2D))
      return false;
    Point2D other = (Point2D) obj;
    return Double.doubleToLongBits(x) == Double.doubleToLongBits(other.x)
        && Double.doubleToLongBits(y) == Double.doubleToLongBits(other.y);
  }

  @Override
  public String toString() {
    return "Point2D [x=" + x + ", y=" + y + "]";
  }

}
