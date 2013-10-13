package de.bht.fb6.cg1.raytracer.math.impl;

import de.bht.fb6.cg1.raytracer.math.Point2D;

/**
 * 
 * @author Marco Seidler
 */
public class Point2DImpl implements Point2D {

  private final double xValue;
  private final double yValue;

  /**
   * This constructor create a new instance of a Point2D.
   * 
   * @param x
   *          The x component of the Point.
   * @param y
   *          The y component of the Point.
   */
  public Point2DImpl(final double x, final double y) {
    this.xValue = x;
    this.yValue = y;
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fb6.cg1.raytracer.math.Point2D#getX()
   */
  @Override
  public double getX() {
    return xValue;
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fb6.cg1.raytracer.math.Point2D#getY()
   */
  @Override
  public double getY() {
    return yValue;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    long temp;
    temp = Double.doubleToLongBits(xValue);
    result = prime * result + (int) (temp ^ temp >>> 32);
    temp = Double.doubleToLongBits(yValue);
    result = prime * result + (int) (temp ^ temp >>> 32);
    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof Point2DImpl)) {
      return false;
    }
    Point2DImpl other = (Point2DImpl) obj;
    if (Double.doubleToLongBits(MathHelper.roundDouble(xValue)) != Double.doubleToLongBits(MathHelper
        .roundDouble(other.xValue))) {
      return false;
    }
    if (Double.doubleToLongBits(MathHelper.roundDouble(yValue)) != Double.doubleToLongBits(MathHelper
        .roundDouble(other.yValue))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "[ " + xValue + " " + yValue + " ]";
  }
}
