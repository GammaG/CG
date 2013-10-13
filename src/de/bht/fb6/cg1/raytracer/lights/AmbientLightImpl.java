/**
 * 
 */
package de.bht.fb6.cg1.raytracer.lights;

import de.bht.fb6.cg1.raytracer.Tracer;
import de.bht.fb6.cg1.raytracer.color.RGBColor;
import de.bht.fb6.cg1.raytracer.math.Point3D;
import de.bht.fb6.cg1.raytracer.math.Vector3D;
import de.bht.fb6.cg1.raytracer.math.impl.Vector3DImpl;
import de.bht.fb6.cg1.raytracer.scene.light.AmbientLight;

/**
 * A implementation of a {@link AmbientLight}.
 * 
 * @author admin
 * 
 */
public class AmbientLightImpl implements AmbientLight {

  private final RGBColor color;

  /**
   * Create a new {@link AmbientLight}.
   * 
   * @param color
   *          The {@link RGBColor} of the {@link AmbientLight}.
   */
  public AmbientLightImpl(final RGBColor color) {
    if (color == null) {
      throw new IllegalArgumentException("Cannot be null");
    }
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
    return new Vector3DImpl(0, 0, 0);
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
    return true;
  }

  @Override
  public String toString() {
    return "AmbientLightImpl [color=" + color + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (color == null ? 0 : color.hashCode());
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
    AmbientLightImpl other = (AmbientLightImpl) obj;
    if (color == null) {
      if (other.color != null) {
        return false;
      }
    } else if (!color.equals(other.color)) {
      return false;
    }
    return true;
  }

}