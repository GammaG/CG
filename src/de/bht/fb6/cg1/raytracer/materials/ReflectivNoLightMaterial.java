package de.bht.fb6.cg1.raytracer.materials;

import de.bht.fb6.cg1.raytracer.Tracer;
import de.bht.fb6.cg1.raytracer.color.RGBColor;
import de.bht.fb6.cg1.raytracer.math.impl.Point3DImpl;
import de.bht.fb6.cg1.raytracer.math.impl.RayImpl;
import de.bht.fb6.cg1.raytracer.math.impl.VHelper;
import de.bht.fb6.cg1.raytracer.scene.Material;
import de.bht.fb6.cg1.raytracer.scene.ShadeRecord;

/**
 * @author admin
 * 
 */
public class ReflectivNoLightMaterial implements Material {
  private final RGBColor color;
  private final float intense;

  /**
   * @param color
   *          The Color of the Material.
   * @param intense
   *          Intense of the reflection. Should be between 0 and 1.
   * 
   */
  public ReflectivNoLightMaterial(final RGBColor color, final float intense) {
    if (color == null) {
      throw new IllegalArgumentException("Cannot not be null");
    }
    this.color = color;
    // @formatter:off
      this.intense = Math.max( 0,
                     Math.min( 1, intense ) );
      // @formatter:on
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

    final double angle = VHelper.angleBetween(shadeRecord.normal.asVector(), shadeRecord.ray.getDirection().mul(-1));

    final float scalarAngle = (float) Math.max(0, Math.min((100 - Math.abs(angle)) / 100, 1));

    // Distance to Camera.
    // @formatter:off
      final float scalarDistance = ( float ) Math.max( 0,
                                       Math.min( 1,
                  ( 100 - Math.abs( new Point3DImpl( 0, 0, 0 ).sub( shadeRecord.localHitPoint ).getMagnitude() ) ) / 100) + 0.5 );
      final RGBColor ownRGB = color.mul( scalarAngle * scalarDistance );

      final RGBColor reflectionRGB = tracer.traceRay( new RayImpl( shadeRecord.localHitPoint,
                                                        shadeRecord.normal.reflect( shadeRecord.ray.getDirection() ) ) );
      // @formatter:on

    return ownRGB.mul(1 - intense).add(reflectionRGB.mul(intense));
  }

  @Override
  public String toString() {
    return "ReflectivNoLightMaterial [color=" + color + ", intense=" + intense + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (color == null ? 0 : color.hashCode());
    result = prime * result + Float.floatToIntBits(intense);
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
    ReflectivNoLightMaterial other = (ReflectivNoLightMaterial) obj;
    if (color == null) {
      if (other.color != null) {
        return false;
      }
    } else if (!color.equals(other.color)) {
      return false;
    }
    if (Float.floatToIntBits(intense) != Float.floatToIntBits(other.intense)) {
      return false;
    }
    return true;
  }

}