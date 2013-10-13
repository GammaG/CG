package de.bht.fb6.cg1.raytracer.math.impl;

import de.bht.fb6.cg1.raytracer.math.Mat3x3;
import de.bht.fb6.cg1.raytracer.math.Point3D;
import de.bht.fb6.cg1.raytracer.math.Vector3D;

/**
 * This class represents a Matrix 3x3.
 * 
 * @author Marco Seidler
 */
public class Mat3x3Impl implements Mat3x3 {

  private final Vector3D column0;
  private final Vector3D column1;
  private final Vector3D column2;

  /**
   * This will create a new Matrix 3x3.
   * 
   * @param column0
   *          First column of the Matrix.
   * @param column1
   *          Secound column of the Matrix.
   * @param column2
   *          Third column of the Matrix.
   */
  public Mat3x3Impl(final Vector3D column0, final Vector3D column1, final Vector3D column2) {

    if (column0 == null || column1 == null || column2 == null) {
      throw new IllegalArgumentException("Argument shouldn't be null");
    }

    this.column0 = column0;
    this.column1 = column1;
    this.column2 = column2;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * de.bht.fb6.cg1.raytracer.math.Mat3x3#replaceColumn0(de.bht.fb6.cg1.raytracer
   * .math.Vector3D)
   */
  @Override
  public Mat3x3 replaceColumn0(final Vector3D with) {

    if (with == null) {
      throw new IllegalArgumentException("Argument shouldn't be null");
    }

    return new Mat3x3Impl(with, column1, column2);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * de.bht.fb6.cg1.raytracer.math.Mat3x3#replaceColumn1(de.bht.fb6.cg1.raytracer
   * .math.Vector3D)
   */
  @Override
  public Mat3x3 replaceColumn1(final Vector3D with) {

    if (with == null) {
      throw new IllegalArgumentException("Argument shouldn't be null");
    }

    return new Mat3x3Impl(column0, with, column2);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * de.bht.fb6.cg1.raytracer.math.Mat3x3#replaceColumn2(de.bht.fb6.cg1.raytracer
   * .math.Vector3D)
   */
  @Override
  public Mat3x3 replaceColumn2(final Vector3D with) {

    if (with == null) {
      throw new IllegalArgumentException("Argument shouldn't be null");
    }

    return new Mat3x3Impl(column0, column1, with);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * de.bht.fb6.cg1.raytracer.math.Mat3x3#mul(de.bht.fb6.cg1.raytracer.math.
   * Mat3x3)
   */
  @Override
  public Mat3x3 mul(final Mat3x3 rhs) {

    if (rhs == null) {
      throw new IllegalArgumentException("Argument shouldn't be null");
    }

    return new Mat3x3Impl(rhs.mul(column0), rhs.mul(column1), rhs.mul(column2));
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * de.bht.fb6.cg1.raytracer.math.Mat3x3#mul(de.bht.fb6.cg1.raytracer.math.
   * Point3D)
   */
  @Override
  public Point3D mul(final Point3D rhs) {

    if (rhs == null) {
      throw new IllegalArgumentException("Argument shouldn't be null");
    }

    return new Point3DImpl(column0.getX() * rhs.getX() + column1.getX() * rhs.getY() + column2.getX() * rhs.getZ(),
        column0.getY() * rhs.getX() + column1.getY() * rhs.getY() + column2.getY() * rhs.getZ(), column0.getZ()
            * rhs.getX() + column1.getZ() * rhs.getY() + column2.getZ() * rhs.getZ());
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * de.bht.fb6.cg1.raytracer.math.Mat3x3#mul(de.bht.fb6.cg1.raytracer.math.
   * Vector3D)
   */
  @Override
  public Vector3D mul(final Vector3D rhs) {

    if (rhs == null) {
      throw new IllegalArgumentException("Argument shouldn't be null");
    }

    return new Vector3DImpl(column0.getX() * rhs.getX() + column1.getX() * rhs.getY() + column2.getX() * rhs.getZ(),
        column0.getY() * rhs.getX() + column1.getY() * rhs.getY() + column2.getY() * rhs.getZ(), column0.getZ()
            * rhs.getX() + column1.getZ() * rhs.getY() + column2.getZ() * rhs.getZ());
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fb6.cg1.raytracer.math.Mat3x3#getDeterminant()
   */
  @Override
  public double getDeterminant() {
    return column0.getX() * column1.getY() * column2.getZ() + column1.getX() * column2.getY() * column0.getZ()
        + column2.getX() * column0.getY() * column1.getZ() - column2.getX() * column1.getY() * column0.getZ()
        - column1.getX() * column0.getY() * column2.getZ() - column0.getX() * column2.getY() * column1.getZ();
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
    result = prime * result + (column0 == null ? 0 : column0.hashCode());
    result = prime * result + (column1 == null ? 0 : column1.hashCode());
    result = prime * result + (column2 == null ? 0 : column2.hashCode());
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
    if (!(obj instanceof Mat3x3Impl)) {
      return false;
    }
    Mat3x3Impl other = (Mat3x3Impl) obj;
    if (column0 == null) {
      if (other.column0 != null) {
        return false;
      }
    } else if (!column0.equals(other.column0)) {
      return false;
    }
    if (column1 == null) {
      if (other.column1 != null) {
        return false;
      }
    } else if (!column1.equals(other.column1)) {
      return false;
    }
    if (column2 == null) {
      if (other.column2 != null) {
        return false;
      }
    } else if (!column2.equals(other.column2)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "[  " + column0.getX() + "   " + column1.getX() + "   " + column2.getX() + "  ]\n" + "[  " + column0.getY()
        + "   " + column1.getY() + "   " + column2.getY() + "  ]\n" + "[  " + column0.getZ() + "   " + column1.getZ()
        + "   " + column2.getZ() + "  ]";
  }

}
