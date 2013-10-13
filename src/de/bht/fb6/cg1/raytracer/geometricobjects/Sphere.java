package de.bht.fb6.cg1.raytracer.geometricobjects;

import java.util.ArrayList;
import java.util.Collections;

import de.bht.fb6.cg1.raytracer.math.Point3D;
import de.bht.fb6.cg1.raytracer.math.Ray;
import de.bht.fb6.cg1.raytracer.math.Vector3D;
import de.bht.fb6.cg1.raytracer.scene.GeometricObject;
import de.bht.fb6.cg1.raytracer.scene.Material;
import de.bht.fb6.cg1.raytracer.scene.ShadeRecord;
import de.bht.fb6.cg1.raytracer.util.Maybe;

/**
 * A implementation of a {@link Sphere}.
 * 
 * @author admin
 * 
 */
public class Sphere implements GeometricObject {
  private final Material material;
  private final Point3D center;
  private final double radius;

  /**
   * Create a new {@link Sphere}.
   * 
   * @param material
   *          The {@link Material}.
   * @param center
   *          The center {@link Point3D}.
   * @param radius
   *          The radius of the {@link Sphere}.
   */
  public Sphere(final Material material, final Point3D center, final double radius) {
    if (material == null || center == null) {
      throw new IllegalArgumentException("Cannot be null");
    }
    this.material = material;
    this.center = center;
    this.radius = radius;
  }

  @Override
  public Material getMaterial() {
    return material;
  }

  @Override
  public Maybe<ShadeRecord> hit(final Ray ray) {

    ArrayList<ShadeRecord> shadeRecords = new ArrayList<ShadeRecord>();

    final Point3D o = ray.getOrigin();
    final Vector3D d = ray.getDirection();

    double a = d.dot(d);
    double b = d.dot(o.sub(center).mul(2.0));
    double c = o.sub(center).dot(o.sub(center)) - 1.0 * radius * radius;

    double t1 = (-b + Math.sqrt(b * b - 4 * a * c)) / (2 * a);
    double t2 = (-b - Math.sqrt(b * b - 4 * a * c)) / (2 * a);

    if (!Double.isNaN(t1)) {

      Point3D pointT1 = ray.at(t1);
      shadeRecords.add(new ShadeRecord(t1, pointT1, pointT1.sub(center).asNormal(), this, ray));
    }

    if (!Double.isNaN(t2)) {

      Point3D pointT2 = ray.at(t2);
      shadeRecords.add(new ShadeRecord(t2, pointT2, pointT2.sub(center).asNormal(), this, ray));
    }

    Collections.sort(shadeRecords);

    return Maybe.listToMaybe(shadeRecords);
  }

  @Override
  public String toString() {
    return "Sphere [material=" + material + ", center=" + center + ", radius=" + radius + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (center == null ? 0 : center.hashCode());
    result = prime * result + (material == null ? 0 : material.hashCode());
    long temp;
    temp = Double.doubleToLongBits(radius);
    result = prime * result + (int) (temp ^ temp >>> 32);
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
    Sphere other = (Sphere) obj;
    if (center == null) {
      if (other.center != null) {
        return false;
      }
    } else if (!center.equals(other.center)) {
      return false;
    }
    if (material == null) {
      if (other.material != null) {
        return false;
      }
    } else if (!material.equals(other.material)) {
      return false;
    }
    if (Double.doubleToLongBits(radius) != Double.doubleToLongBits(other.radius)) {
      return false;
    }
    return true;
  }
}