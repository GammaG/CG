/**
 * 
 */
package de.bht.fb6.cg1.raytracer.math.impl;

import de.bht.fb6.cg1.raytracer.math.Normal;
import de.bht.fb6.cg1.raytracer.math.Point3D;
import de.bht.fb6.cg1.raytracer.math.Vector3D;

/**
 * @author Martin Schultz
 * 
 */
public class Vector3DImpl implements Vector3D {
  private final double xValue;
  private final double yValue;
  private final double zValue;

  /**
   * Generate a new Vector3D.
   * 
   * @param x
   *          The x component of the Vector.
   * @param y
   *          The y component of the Vector.
   * @param z
   *          The z component of the Vector.
   */
  public Vector3DImpl(final double x, final double y, final double z) {
    this.xValue = x;
    this.yValue = y;
    this.zValue = z;
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fb6.cg1.raytracer.math.Vector3D#getMagnitude()
   */
  @Override
  public double getMagnitude() {
    return Math.sqrt(xValue * xValue + yValue * yValue + zValue * zValue);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * de.bht.fb6.cg1.raytracer.math.Vector3D#add(de.bht.fb6.cg1.raytracer.math
   * .Vector3D)
   */
  @Override
  public Vector3D add(Vector3D rhs) {

    if (rhs == null) {
      throw new IllegalArgumentException("Argument shouldn't be null");
    }

    return new Vector3DImpl(xValue + rhs.getX(), yValue + rhs.getY(), zValue + rhs.getZ());
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * de.bht.fb6.cg1.raytracer.math.Vector3D#add(de.bht.fb6.cg1.raytracer.math
   * .Normal)
   */
  @Override
  public Vector3D add(final Normal rhs) {

    if (rhs == null) {
      throw new IllegalArgumentException("Argument shouldn't be null");
    }

    return new Vector3DImpl(xValue + rhs.getX(), yValue + rhs.getY(), zValue + rhs.getZ());
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * de.bht.fb6.cg1.raytracer.math.Vector3D#sub(de.bht.fb6.cg1.raytracer.math
   * .Vector3D)
   */
  @Override
  public Vector3D sub(final Vector3D rhs) {

    if (rhs == null) {
      throw new IllegalArgumentException("Argument shouldn't be null");
    }

    return new Vector3DImpl(xValue - rhs.getX(), yValue - rhs.getY(), zValue - rhs.getZ());
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fb6.cg1.raytracer.math.Vector3D#mul(double)
   */
  @Override
  public Vector3D mul(final double rhs) {
    return new Vector3DImpl(xValue * rhs, yValue * rhs, zValue * rhs);
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fb6.cg1.raytracer.math.Vector3D#div(double)
   */
  @Override
  public Vector3D div(final double rhs) {
    return new Vector3DImpl(xValue / rhs, yValue / rhs, zValue / rhs);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * de.bht.fb6.cg1.raytracer.math.Vector3D#dot(de.bht.fb6.cg1.raytracer.math
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
   * @see
   * de.bht.fb6.cg1.raytracer.math.Vector3D#dot(de.bht.fb6.cg1.raytracer.math
   * .Vector3D)
   */
  @Override
  public double dot(final Vector3D rhs) {

    if (rhs == null) {
      throw new IllegalArgumentException("Argument shouldn't be null");
    }

    return this.xValue * rhs.getX() + this.yValue * rhs.getY() + this.zValue * rhs.getZ();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * de.bht.fb6.cg1.raytracer.math.Vector3D#cross(de.bht.fb6.cg1.raytracer.math
   * .Vector3D)
   */
  @Override
  public Vector3D cross(final Vector3D rhs) {

    if (rhs == null) {
      throw new IllegalArgumentException("Argument shouldn't be null");
    }

    return new Vector3DImpl(yValue * rhs.getZ() - zValue * rhs.getY(), zValue * rhs.getX() - xValue * rhs.getZ(),
        xValue * rhs.getY() - yValue * rhs.getX());
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fb6.cg1.raytracer.math.Vector3D#asNormal()
   */
  @Override
  public Normal asNormal() {
    return new NormalImpl(xValue, yValue, zValue);
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fb6.cg1.raytracer.math.Vector3D#asPoint()
   */
  @Override
  public Point3D asPoint() {
    return new Point3DImpl(xValue, yValue, zValue);
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fb6.cg1.raytracer.math.Vector3D#normalized()
   */
  @Override
  public Vector3D normalized() {
    final double factor = 1 / Math.sqrt(xValue * xValue + yValue * yValue + zValue * zValue);
    return new Vector3DImpl(factor * xValue, factor * yValue, factor * zValue);
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fb6.cg1.raytracer.math.Vector3D#getX()
   */
  @Override
  public double getX() {
    return xValue;
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fb6.cg1.raytracer.math.Vector3D#getY()
   */
  @Override
  public double getY() {
    return yValue;
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fb6.cg1.raytracer.math.Vector3D#getZ()
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
    if (!(obj instanceof Vector3DImpl)) {
      return false;
    }

    Vector3DImpl other = (Vector3DImpl) obj;

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
