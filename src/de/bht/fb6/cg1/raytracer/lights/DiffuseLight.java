/**
 * 
 */
package de.bht.fb6.cg1.raytracer.lights;

import de.bht.fb6.cg1.raytracer.Tracer;
import de.bht.fb6.cg1.raytracer.color.RGBColor;
import de.bht.fb6.cg1.raytracer.math.Point3D;
import de.bht.fb6.cg1.raytracer.math.Vector3D;
import de.bht.fb6.cg1.raytracer.scene.light.Light;

/**
 * @author admin
 * 
 */
public class DiffuseLight implements Light {

  private final Point3D position;
  private final RGBColor color;

  /**
   * @param position
   * @param color
   * 
   */
  public DiffuseLight(final Point3D position, final RGBColor color) {
    if (position == null || color == null) {
      throw new IllegalArgumentException("Cannot be null");
    }

    this.position = position;
    this.color = color;
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.bht.fb6.cg1.raytracer.scene.light.Light#getColor()
   */
  @Override
  public RGBColor getColor() {
    return this.color;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * de.bht.fb6.cg1.raytracer.scene.light.Light#getDirectionFrom(de.bht.fb6.
   * cg1.raytracer.math.Point3D)
   */
  @Override
  public Vector3D getDirectionFrom(final Point3D point) {
    if (point == null) {
      throw new IllegalArgumentException("Cannot be Null");
    }
    return position.sub(point).normalized();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * de.bht.fb6.cg1.raytracer.scene.light.Light#visibleAt(de.bht.fb6.cg1.raytracer
   * .math.Point3D, de.bht.fb6.cg1.raytracer.Tracer)
   */
  @Override
  public boolean visibleAt(final Point3D point, final Tracer tracer) {
    if (point == null || tracer == null) {
      throw new IllegalArgumentException("Cannot be null");
    }

    boolean out = true;

    return out;
  }

  @Override
  public String toString() {
    return "DiffuseLight [position=" + position + ", color=" + color + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (color == null ? 0 : color.hashCode());
    result = prime * result + (position == null ? 0 : position.hashCode());
    return result;
  }

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
    DiffuseLight other = (DiffuseLight) obj;
    if (color == null) {
      if (other.color != null) {
        return false;
      }
    } else if (!color.equals(other.color)) {
      return false;
    }
    if (position == null) {
      if (other.position != null) {
        return false;
      }
    } else if (!position.equals(other.position)) {
      return false;
    }
    return true;
  }
}
