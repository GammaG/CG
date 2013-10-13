package de.bht.fb6.cg1.raytracer.materials;

import de.bht.fb6.cg1.raytracer.Tracer;
import de.bht.fb6.cg1.raytracer.color.RGBColor;
import de.bht.fb6.cg1.raytracer.math.impl.VHelper;
import de.bht.fb6.cg1.raytracer.scene.Material;
import de.bht.fb6.cg1.raytracer.scene.ShadeRecord;

/**
 * @author admin
 * 
 */
public class NoLightMaterial implements Material {
  private final RGBColor color;

  /**
   * @param color
   *          The color of the Material.
   * 
   */
  public NoLightMaterial(final RGBColor color) {
    if (color == null) {
      throw new IllegalArgumentException("Cannot be null");
    }
    this.color = color;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * de.bht.fb6.cg1.raytracer.scene.Material#getColorAt(de.bht.fb6.cg1.raytracer
   * .scene.ShadeRecord, de.bht.fb6.cg1.raytracer.Tracer)
   */
  @Override
  public RGBColor getColorAt(final ShadeRecord shadeRecord, final Tracer tracer) {
    if (shadeRecord == null || tracer == null) {
      throw new IllegalArgumentException("Cannot be null");
    }

    double angle = VHelper.angleBetween(shadeRecord.normal.asVector(), shadeRecord.ray.getDirection().mul(-1));

    float scalarAngle = (float) Math.max(0, Math.min((100 - Math.abs(angle)) / 100, 1));
    float scalarDistance = (float) Math.max(0, Math.min((100 - shadeRecord.t) / 100, 1));
    RGBColor ownRGB = color.mul(scalarAngle * scalarDistance);

    return ownRGB;
  }

  @Override
  public String toString() {
    return "NoLightMaterial [color=" + color + "]";
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
    NoLightMaterial other = (NoLightMaterial) obj;
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
