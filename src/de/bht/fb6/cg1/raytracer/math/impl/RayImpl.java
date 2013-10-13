/**
 * 
 */
package de.bht.fb6.cg1.raytracer.math.impl;

import de.bht.fb6.cg1.raytracer.math.Point3D;
import de.bht.fb6.cg1.raytracer.math.Ray;
import de.bht.fb6.cg1.raytracer.math.Vector3D;

/**
 * @author Marco Seidler
 * 
 */
public class RayImpl implements Ray {

  private final Point3D origin;
  private final Vector3D direction;

  /**
   * Generate a new Ray.
   * 
   * @param origin
   *          The origin of the Ray as Point3D.
   * @param direction
   *          The direction of the Ray as Vector3D.
   */
  public RayImpl(Point3D origin, Vector3D direction) {

    if (origin == null || direction == null) {
      throw new IllegalArgumentException("Argument shouldn't be null");
    }

    this.origin = origin;
    this.direction = direction.normalized();
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fb6.cg1.raytracer.math.Ray#at(double)
   */
  @Override
  public Point3D at(final double t) {
    return origin.add(direction.mul(t));
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * de.bht.fb6.cg1.raytracer.math.Ray#tOf(de.bht.fb6.cg1.raytracer.math.Point3D
   * )
   */
  @Override
  public double tOf(final Point3D point) {

    if (point == null) {
      throw new IllegalArgumentException("Argument shouldn't be null");
    }

    // Calculate t for each coordinate
    final double xT = MathHelper.roundDouble((point.getX() - this.origin.getX()) / this.direction.getX());
    final double yT = MathHelper.roundDouble((point.getY() - this.origin.getY()) / this.direction.getY());
    final double zT = MathHelper.roundDouble((point.getZ() - this.origin.getZ()) / this.direction.getZ());

    System.out.println(xT + " " + yT + " " + zT);

    // Check if point lays on the ray
    if (xT == yT && yT == zT) {
      return xT;
    }

    return Double.NaN;
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fb6.cg1.raytracer.math.Ray#getOrigin()
   */
  @Override
  public Point3D getOrigin() {
    return origin;
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fb6.cg1.raytracer.math.Ray#getDirection()
   */
  @Override
  public Vector3D getDirection() {
    // TODO Auto-generated method stub
    return direction;
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
    result = prime * result + (direction == null ? 0 : direction.hashCode());
    result = prime * result + (origin == null ? 0 : origin.hashCode());
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
    if (getClass() != obj.getClass()) {
      return false;
    }
    RayImpl other = (RayImpl) obj;
    if (direction == null) {
      if (other.direction != null) {
        return false;
      }
    } else if (!direction.equals(other.direction)) {
      return false;
    }
    if (origin == null) {
      if (other.origin != null) {
        return false;
      }
    } else if (!origin.equals(other.origin)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "Origin:\n" + origin + "\nDirection:\n" + direction;
  }
}
