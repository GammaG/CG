/**
 * 
 */
package de.bht.fb6.cg1.raytracer.math.impl;

import de.bht.fb6.cg1.raytracer.math.Normal;
import de.bht.fb6.cg1.raytracer.math.Point3D;
import de.bht.fb6.cg1.raytracer.math.Vector3D;

/**
 * @author Marco Seidler
 * 
 */
public class NormalImpl implements Normal {
  private final double xValue;
  private final double yValue;
  private final double zValue;

  /**
   * This constructor create a new instance of a Normal.
   * 
   * @param x
   *          The x component of the Normal.
   * @param y
   *          The y component of the Normal.
   * @param z
   *          The z component of the Normal.
   */
  public NormalImpl(final double x, final double y, final double z) {
    final double factor = 1.0 / Math.sqrt(x * x + y * y + z * z);

    this.xValue = factor * x;
    this.yValue = factor * y;
    this.zValue = factor * z;
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fb6.cg1.raytracer.math.Normal#mul(double)
   */
  @Override
  public Normal mul(final double rhs) {
    return new NormalImpl(xValue * rhs, yValue * rhs, zValue * rhs);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * de.bht.fb6.cg1.raytracer.math.Normal#add(de.bht.fb6.cg1.raytracer.math.
   * Normal)
   */
  @Override
  public Normal add(final Normal rhs) {

    if (rhs == null) {
      throw new IllegalArgumentException("Argument shouldn't be null");
    }

    return new NormalImpl(xValue + rhs.getX(), yValue + rhs.getY(), zValue + rhs.getZ());
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fb6.cg1.raytracer.math.Normal#normalized()
   */
  @Override
  public Normal normalized() {
    final double factor = 1.0 / Math.sqrt(xValue * xValue + yValue * yValue + zValue * zValue);
    return new NormalImpl(factor * xValue, factor * yValue, factor * zValue);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * de.bht.fb6.cg1.raytracer.math.Normal#reflect(de.bht.fb6.cg1.raytracer.math
   * .Vector3D)
   */
  @Override
  public Vector3D reflect(final Vector3D v) {

    if (v == null) {
      throw new IllegalArgumentException("Argument shouldn't be null");
    }

    return this.normalized().asVector().mul(-2 * v.dot(this.normalized())).add(v);
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fb6.cg1.raytracer.math.Normal#asVector()
   */
  @Override
  public Vector3D asVector() {
    return new Vector3DImpl(xValue, yValue, zValue);
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fb6.cg1.raytracer.math.Normal#asPoint()
   */
  @Override
  public Point3D asPoint() {
    return new Point3DImpl(xValue, yValue, zValue);
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fb6.cg1.raytracer.math.Normal#getX()
   */
  @Override
  public double getX() {
    return xValue;
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fb6.cg1.raytracer.math.Normal#getY()
   */
  @Override
  public double getY() {
    return yValue;
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fb6.cg1.raytracer.math.Normal#getZ()
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
    if (!(obj instanceof NormalImpl)) {
      return false;
    }
    NormalImpl other = (NormalImpl) obj;
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
