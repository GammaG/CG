package de.bht.fb6.cg1.raytracer.math.impl;

import de.bht.fb6.cg1.raytracer.math.Normal;
import de.bht.fb6.cg1.raytracer.math.Point3D;
import de.bht.fb6.cg1.raytracer.math.Vector3D;

/**
 * 
 * @author Marco Seidler
 */
public class Point3DImpl implements Point3D {

  private final double xValue;
  private final double yValue;
  private final double zValue;

  /**
   * Generate a new Point3D.
   * 
   * @param x
   *          The x component of the Point.
   * @param y
   *          The y component of the Point.
   * @param z
   *          The z component of the Point.
   */
  public Point3DImpl(final double x, final double y, final double z) {
    this.xValue = x;
    this.yValue = y;
    this.zValue = z;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * de.bht.fb6.cg1.raytracer.math.Point3D#add(de.bht.fb6.cg1.raytracer.math
   * .Vector3D)
   */
  @Override
  public Point3D add(final Vector3D rhs) {

    if (rhs == null) {
      throw new IllegalArgumentException("Argument shouldn't be null");
    }

    return new Point3DImpl(xValue + rhs.getX(), yValue + rhs.getY(), zValue + rhs.getZ());
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * de.bht.fb6.cg1.raytracer.math.Point3D#sub(de.bht.fb6.cg1.raytracer.math
   * .Point3D)
   */
  @Override
  public Vector3D sub(final Point3D rhs) {

    if (rhs == null) {
      throw new IllegalArgumentException("Argument shouldn't be null");
    }

    return new Vector3DImpl(xValue - rhs.getX(), yValue - rhs.getY(), zValue - rhs.getZ());
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * de.bht.fb6.cg1.raytracer.math.Point3D#sub(de.bht.fb6.cg1.raytracer.math
   * .Vector3D)
   */
  @Override
  public Point3D sub(final Vector3D rhs) {

    if (rhs == null) {
      throw new IllegalArgumentException("Argument shouldn't be null");
    }

    return new Point3DImpl(xValue - rhs.getX(), yValue - rhs.getY(), zValue - rhs.getZ());
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * de.bht.fb6.cg1.raytracer.math.Point3D#dot(de.bht.fb6.cg1.raytracer.math
   * .Normal)
   */
  @Override
  public double dot(final Normal rhs) {

    if (rhs == null) {
      throw new IllegalArgumentException("Argument shouldn't be null");
    }

    return this.xValue * rhs.getX() + this.yValue * rhs.getY() + this.zValue * rhs.getZ();
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fb6.cg1.raytracer.math.Point3D#asVector()
   */
  @Override
  public Vector3D asVector() {
    return new Vector3DImpl(xValue, yValue, zValue);
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fb6.cg1.raytracer.math.Point3D#asNormal()
   */
  @Override
  public Normal asNormal() {
    return new NormalImpl(xValue, yValue, zValue);
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fb6.cg1.raytracer.math.Point3D#getX()
   */
  @Override
  public double getX() {
    return xValue;
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fb6.cg1.raytracer.math.Point3D#getY()
   */
  @Override
  public double getY() {
    return yValue;
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fb6.cg1.raytracer.math.Point3D#getZ()
   */
  @Override
  public double getZ() {
    return zValue;
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
    temp = Double.doubleToLongBits(zValue);
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
    if (!(obj instanceof Point3DImpl)) {
      return false;
    }
    Point3DImpl other = (Point3DImpl) obj;
    if (Double.doubleToLongBits(MathHelper.roundDouble(xValue)) != Double.doubleToLongBits(MathHelper
        .roundDouble(other.xValue))) {
      return false;
    }
    if (Double.doubleToLongBits(MathHelper.roundDouble(yValue)) != Double.doubleToLongBits(MathHelper
        .roundDouble(other.yValue))) {
      return false;
    }
    if (Double.doubleToLongBits(MathHelper.roundDouble(zValue)) != Double.doubleToLongBits(MathHelper
        .roundDouble(other.zValue))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "[ " + xValue + " " + yValue + " " + zValue + " ]";
  }
}
