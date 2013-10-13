package de.bht.fb6.cg1.raytracer.materials;

import de.bht.fb6.cg1.raytracer.Raytracer;
import de.bht.fb6.cg1.raytracer.Tracer;
import de.bht.fb6.cg1.raytracer.color.RGBColor;
import de.bht.fb6.cg1.raytracer.math.impl.RayImpl;
import de.bht.fb6.cg1.raytracer.scene.Material;
import de.bht.fb6.cg1.raytracer.scene.ShadeRecord;
import de.bht.fb6.cg1.raytracer.scene.light.AmbientLight;
import de.bht.fb6.cg1.raytracer.scene.light.Light;

/**
 * @author admin
 * 
 */
public class LightMaterial implements Material {
  private final RGBColor color;
  private final double cDiffuse;
  private final double cSpecular;
  private final double cShiness;
  private final float intense;

  /**
   * @param color
   *          The color of the Material.
   * @param intense
   * @param cDiffuse
   * @param cSpecular
   * @param cShiness
   * 
   */
  public LightMaterial(final RGBColor color, final float intense, final double cDiffuse, final double cSpecular,
      final double cShiness) {
    if (color == null || intense < 0 || cDiffuse < 0 || cSpecular < 0) {
      throw new IllegalArgumentException("The Arguments shouldn't be 'null'");
    }

    this.color = color;

    // @formatter:off
    this.cDiffuse = Math.max( 0, 
        Math.min( 1, cDiffuse ) );
    this.cSpecular = Math.max( 0, 
         Math.min( 1, cSpecular ) );
    this.cShiness = Math.max( 0, 
        Math.min( 20, cShiness ) );
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
      throw new IllegalArgumentException("The Arguments shouldn't be 'null'");
    }

    RGBColor diffuseLightColor = new RGBColor(0f, 0f, 0f);
    RGBColor specularLightColor = new RGBColor(0f, 0f, 0f);

    for (Light light : tracer.getWorld().getLights()) {

      if (light.visibleAt(shadeRecord.localHitPoint, tracer)) {

        final double cDistance = Math.max(0,
            Math.min(1, (400.0 - light.getDirectionFrom(shadeRecord.localHitPoint).getMagnitude()) / 400.0));

        // Diffuse
        diffuseLightColor = diffuseLightColor.add(light.getColor().mul(
            (float) Math.max(0, cDistance * cDiffuse
                * light.getDirectionFrom(shadeRecord.localHitPoint).normalized().dot(shadeRecord.normal))));

        // Specular
        specularLightColor = specularLightColor.add(light.getColor().mul(
            (float) (cDistance * 1.2 * Math.max(
                0,
                cSpecular
                    * Math.pow(
                        shadeRecord.normal.reflect(light.getDirectionFrom(shadeRecord.localHitPoint).normalized()).dot(
                            shadeRecord.ray.getDirection().normalized()), cShiness)))));

      }
    }

    for (AmbientLight light : tracer.getWorld().getAmbientLights()) {
      diffuseLightColor = diffuseLightColor.add(light.getColor().mul(Raytracer.CA));
    }

    final RGBColor ownRGB = color.mul(diffuseLightColor).add(specularLightColor);

    final RGBColor reflectionRGB = tracer.traceRay(new RayImpl(shadeRecord.localHitPoint, shadeRecord.normal
        .reflect(shadeRecord.ray.getDirection())));

    return ownRGB.mul(1 - intense).add(reflectionRGB.mul(intense));
  }

  @Override
  public String toString() {
    return "LightMaterial [color=" + color + ", cDiffuse=" + cDiffuse + ", cSpecular=" + cSpecular + ", cShiness="
        + cShiness + ", intense=" + intense + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    long temp;
    temp = Double.doubleToLongBits(cDiffuse);
    result = prime * result + (int) (temp ^ temp >>> 32);
    temp = Double.doubleToLongBits(cShiness);
    result = prime * result + (int) (temp ^ temp >>> 32);
    temp = Double.doubleToLongBits(cSpecular);
    result = prime * result + (int) (temp ^ temp >>> 32);
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
    LightMaterial other = (LightMaterial) obj;
    if (Double.doubleToLongBits(cDiffuse) != Double.doubleToLongBits(other.cDiffuse)) {
      return false;
    }
    if (Double.doubleToLongBits(cShiness) != Double.doubleToLongBits(other.cShiness)) {
      return false;
    }
    if (Double.doubleToLongBits(cSpecular) != Double.doubleToLongBits(other.cSpecular)) {
      return false;
    }
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