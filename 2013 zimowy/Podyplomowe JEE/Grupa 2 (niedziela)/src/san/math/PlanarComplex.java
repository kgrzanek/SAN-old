package san.math;

final class PlanarComplex extends Complex {

  private final double re;

  private final double im;

  PlanarComplex(double re, double im) {
    this.re = re;
    this.im = im;
  }

  @Override
  public double re() {
    return this.re;
  }

  @Override
  public double im() {
    return this.im;
  }

  @Override
  public double modulus() {
    return Math.sqrt(this.re() * this.re() + this.im() * this.im());
  }

  @Override
  public double argument() {
    return Math.atan2(this.im(), this.re());
  }

  @Override
  public Complex add(Complex other) {
    return new PlanarComplex(this.re() + other.re(), this.im() + other.im());
  }

  @Override
  public Complex asPlanar() {
    return this;
  }

  @Override
  public Complex asPolar() {
    return new PolarComplex(this.modulus(), this.argument());
  }

  @Override
  public String toString() {
    if (this.re() == 0) {
      return this.im() + "j";
    }
    StringBuilder repr = new StringBuilder("(");

    double imAbs = Math.abs(this.im());
    String sign = Math.signum(this.im()) < 0 ? "-" : "+";

    repr.append(this.re).append(sign).append(imAbs).append("j");
    return repr.append(")").toString();
  }

  // public final double re;
  //
  // public final double im;
  //
  // public PlanarComplex(double re, double im) {
  // this.re = re;
  // this.im = im;
  // }
  //
  // public PlanarComplex add(PlanarComplex other) {
  // return new PlanarComplex(this.re + other.re, this.im + other.im);
  // }

}
